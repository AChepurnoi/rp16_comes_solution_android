package com.bionic.td_android.Data.Provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.bionic.td_android.Data.Provider.job.JobColumns;
import com.bionic.td_android.Data.Provider.ride.RideColumns;
import com.bionic.td_android.Data.Provider.shift.ShiftColumns;
import com.bionic.td_android.Data.Provider.user.UserColumns;
import com.bionic.td_android.Data.Provider.workschedule.WorkscheduleColumns;

public class TDASQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = TDASQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "td_app.db";
    private static final int DATABASE_VERSION = 2;
    private static TDASQLiteOpenHelper sInstance;
    private final Context mContext;
    private final TDASQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_JOB = "CREATE TABLE IF NOT EXISTS "
            + JobColumns.TABLE_NAME + " ( "
            + JobColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + JobColumns.USERID + " INTEGER, "
            + JobColumns.JOBID + " INTEGER DEFAULT 0 "
            + " );";

    public static final String SQL_CREATE_TABLE_RIDE = "CREATE TABLE IF NOT EXISTS "
            + RideColumns.TABLE_NAME + " ( "
            + RideColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RideColumns.SERVERID + " INTEGER, "
            + RideColumns.STARTTIME + " INTEGER DEFAULT 0, "
            + RideColumns.ENDTIME + " INTEGER DEFAULT 0, "
            + RideColumns.SHIFTID + " INTEGER DEFAULT 0 "
            + " );";

    public static final String SQL_CREATE_TABLE_SHIFT = "CREATE TABLE IF NOT EXISTS "
            + ShiftColumns.TABLE_NAME + " ( "
            + ShiftColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShiftColumns.SERVERID + " INTEGER, "
            + ShiftColumns.STARTTIME + " INTEGER DEFAULT 0, "
            + ShiftColumns.ENDTIME + " INTEGER DEFAULT 0, "
            + ShiftColumns.PAUSE + " INTEGER DEFAULT 0, "
            + ShiftColumns.USERID + " INTEGER DEFAULT 0, "
            + ShiftColumns.SYNCHRONIZE + " INTEGER DEFAULT 0 "
            + " );";

    public static final String SQL_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + UserColumns.TABLE_NAME + " ( "
            + UserColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserColumns.SERVERID + " INTEGER, "
            + UserColumns.EMAIL + " TEXT, "
            + UserColumns.PASSWORD + " TEXT, "
            + UserColumns.FIRSTNAME + " TEXT, "
            + UserColumns.LASTNAME + " TEXT, "
            + UserColumns.INSERTION + " TEXT, "
            + UserColumns.SEX + " TEXT, "
            + UserColumns.FOURWEEKPAYOFF + " INTEGER, "
            + UserColumns.ZEROHOURS + " INTEGER, "
            + UserColumns.CONTRACTHOURS + " INTEGER, "
            + UserColumns.ENABLED + " INTEGER, "
            + UserColumns.VERIFIED + " INTEGER, "
            + UserColumns.POSTALCODE + " TEXT, "
            + UserColumns.PASSWORDEXPIRE + " INTEGER, "
            + UserColumns.WORKSCHEDULE_ID + " INTEGER, "
            + UserColumns.EMPLOYER_ID + " TEXT, "
            + UserColumns.ROLE + " INTEGER DEFAULT 1, "
            + UserColumns.SYNCHRONIZE + " INTEGER DEFAULT 0 "
            + " );";

    public static final String SQL_CREATE_TABLE_WORKSCHEDULE = "CREATE TABLE IF NOT EXISTS "
            + WorkscheduleColumns.TABLE_NAME + " ( "
            + WorkscheduleColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WorkscheduleColumns.SERVERID + " INTEGER DEFAULT 0, "
            + WorkscheduleColumns.CREATIONTIME + " INTEGER DEFAULT 0, "
            + WorkscheduleColumns.SUNDAY + " TEXT DEFAULT '0', "
            + WorkscheduleColumns.MONDAY + " TEXT DEFAULT '0', "
            + WorkscheduleColumns.TUESDAY + " TEXT DEFAULT '0', "
            + WorkscheduleColumns.WEDNESDAY + " TEXT DEFAULT '0', "
            + WorkscheduleColumns.THURSDAY + " TEXT DEFAULT '0', "
            + WorkscheduleColumns.FRIDAY + " TEXT DEFAULT '0', "
            + WorkscheduleColumns.SATURDAY + " TEXT DEFAULT '0', "
            + WorkscheduleColumns.SYNCHRONIZE + " INTEGER DEFAULT 0 "
            + " );";

    // @formatter:on

    public static TDASQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static TDASQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static TDASQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new TDASQLiteOpenHelper(context);
    }

    private TDASQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new TDASQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static TDASQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new TDASQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private TDASQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new TDASQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_JOB);
        db.execSQL(SQL_CREATE_TABLE_RIDE);
        db.execSQL(SQL_CREATE_TABLE_SHIFT);
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_WORKSCHEDULE);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
