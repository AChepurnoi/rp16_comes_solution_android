package com.bionic.td_android.Data.Provider.user;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.bionic.td_android.Data.Provider.base.AbstractSelection;

/**
 * Selection for the {@code user} table.
 */
public class UserSelection extends AbstractSelection<UserSelection> {
    @Override
    protected Uri baseUri() {
        return UserColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UserCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public UserCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UserCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public UserCursor query(Context context) {
        return query(context, null);
    }


    public UserSelection id(long... value) {
        addEquals("user." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection idNot(long... value) {
        addNotEquals("user." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection orderById(boolean desc) {
        orderBy("user." + UserColumns._ID, desc);
        return this;
    }

    public UserSelection orderById() {
        return orderById(false);
    }

    public UserSelection serverid(Long... value) {
        addEquals(UserColumns.SERVERID, value);
        return this;
    }

    public UserSelection serveridNot(Long... value) {
        addNotEquals(UserColumns.SERVERID, value);
        return this;
    }

    public UserSelection serveridGt(long value) {
        addGreaterThan(UserColumns.SERVERID, value);
        return this;
    }

    public UserSelection serveridGtEq(long value) {
        addGreaterThanOrEquals(UserColumns.SERVERID, value);
        return this;
    }

    public UserSelection serveridLt(long value) {
        addLessThan(UserColumns.SERVERID, value);
        return this;
    }

    public UserSelection serveridLtEq(long value) {
        addLessThanOrEquals(UserColumns.SERVERID, value);
        return this;
    }

    public UserSelection orderByServerid(boolean desc) {
        orderBy(UserColumns.SERVERID, desc);
        return this;
    }

    public UserSelection orderByServerid() {
        orderBy(UserColumns.SERVERID, false);
        return this;
    }

    public UserSelection email(String... value) {
        addEquals(UserColumns.EMAIL, value);
        return this;
    }

    public UserSelection emailNot(String... value) {
        addNotEquals(UserColumns.EMAIL, value);
        return this;
    }

    public UserSelection emailLike(String... value) {
        addLike(UserColumns.EMAIL, value);
        return this;
    }

    public UserSelection emailContains(String... value) {
        addContains(UserColumns.EMAIL, value);
        return this;
    }

    public UserSelection emailStartsWith(String... value) {
        addStartsWith(UserColumns.EMAIL, value);
        return this;
    }

    public UserSelection emailEndsWith(String... value) {
        addEndsWith(UserColumns.EMAIL, value);
        return this;
    }

    public UserSelection orderByEmail(boolean desc) {
        orderBy(UserColumns.EMAIL, desc);
        return this;
    }

    public UserSelection orderByEmail() {
        orderBy(UserColumns.EMAIL, false);
        return this;
    }

    public UserSelection password(String... value) {
        addEquals(UserColumns.PASSWORD, value);
        return this;
    }

    public UserSelection passwordNot(String... value) {
        addNotEquals(UserColumns.PASSWORD, value);
        return this;
    }

    public UserSelection passwordLike(String... value) {
        addLike(UserColumns.PASSWORD, value);
        return this;
    }

    public UserSelection passwordContains(String... value) {
        addContains(UserColumns.PASSWORD, value);
        return this;
    }

    public UserSelection passwordStartsWith(String... value) {
        addStartsWith(UserColumns.PASSWORD, value);
        return this;
    }

    public UserSelection passwordEndsWith(String... value) {
        addEndsWith(UserColumns.PASSWORD, value);
        return this;
    }

    public UserSelection orderByPassword(boolean desc) {
        orderBy(UserColumns.PASSWORD, desc);
        return this;
    }

    public UserSelection orderByPassword() {
        orderBy(UserColumns.PASSWORD, false);
        return this;
    }

    public UserSelection firstname(String... value) {
        addEquals(UserColumns.FIRSTNAME, value);
        return this;
    }

    public UserSelection firstnameNot(String... value) {
        addNotEquals(UserColumns.FIRSTNAME, value);
        return this;
    }

    public UserSelection firstnameLike(String... value) {
        addLike(UserColumns.FIRSTNAME, value);
        return this;
    }

    public UserSelection firstnameContains(String... value) {
        addContains(UserColumns.FIRSTNAME, value);
        return this;
    }

    public UserSelection firstnameStartsWith(String... value) {
        addStartsWith(UserColumns.FIRSTNAME, value);
        return this;
    }

    public UserSelection firstnameEndsWith(String... value) {
        addEndsWith(UserColumns.FIRSTNAME, value);
        return this;
    }

    public UserSelection orderByFirstname(boolean desc) {
        orderBy(UserColumns.FIRSTNAME, desc);
        return this;
    }

    public UserSelection orderByFirstname() {
        orderBy(UserColumns.FIRSTNAME, false);
        return this;
    }

    public UserSelection lastname(String... value) {
        addEquals(UserColumns.LASTNAME, value);
        return this;
    }

    public UserSelection lastnameNot(String... value) {
        addNotEquals(UserColumns.LASTNAME, value);
        return this;
    }

    public UserSelection lastnameLike(String... value) {
        addLike(UserColumns.LASTNAME, value);
        return this;
    }

    public UserSelection lastnameContains(String... value) {
        addContains(UserColumns.LASTNAME, value);
        return this;
    }

    public UserSelection lastnameStartsWith(String... value) {
        addStartsWith(UserColumns.LASTNAME, value);
        return this;
    }

    public UserSelection lastnameEndsWith(String... value) {
        addEndsWith(UserColumns.LASTNAME, value);
        return this;
    }

    public UserSelection orderByLastname(boolean desc) {
        orderBy(UserColumns.LASTNAME, desc);
        return this;
    }

    public UserSelection orderByLastname() {
        orderBy(UserColumns.LASTNAME, false);
        return this;
    }

    public UserSelection insertion(String... value) {
        addEquals(UserColumns.INSERTION, value);
        return this;
    }

    public UserSelection insertionNot(String... value) {
        addNotEquals(UserColumns.INSERTION, value);
        return this;
    }

    public UserSelection insertionLike(String... value) {
        addLike(UserColumns.INSERTION, value);
        return this;
    }

    public UserSelection insertionContains(String... value) {
        addContains(UserColumns.INSERTION, value);
        return this;
    }

    public UserSelection insertionStartsWith(String... value) {
        addStartsWith(UserColumns.INSERTION, value);
        return this;
    }

    public UserSelection insertionEndsWith(String... value) {
        addEndsWith(UserColumns.INSERTION, value);
        return this;
    }

    public UserSelection orderByInsertion(boolean desc) {
        orderBy(UserColumns.INSERTION, desc);
        return this;
    }

    public UserSelection orderByInsertion() {
        orderBy(UserColumns.INSERTION, false);
        return this;
    }

    public UserSelection sex(String... value) {
        addEquals(UserColumns.SEX, value);
        return this;
    }

    public UserSelection sexNot(String... value) {
        addNotEquals(UserColumns.SEX, value);
        return this;
    }

    public UserSelection sexLike(String... value) {
        addLike(UserColumns.SEX, value);
        return this;
    }

    public UserSelection sexContains(String... value) {
        addContains(UserColumns.SEX, value);
        return this;
    }

    public UserSelection sexStartsWith(String... value) {
        addStartsWith(UserColumns.SEX, value);
        return this;
    }

    public UserSelection sexEndsWith(String... value) {
        addEndsWith(UserColumns.SEX, value);
        return this;
    }

    public UserSelection orderBySex(boolean desc) {
        orderBy(UserColumns.SEX, desc);
        return this;
    }

    public UserSelection orderBySex() {
        orderBy(UserColumns.SEX, false);
        return this;
    }

    public UserSelection fourweekpayoff(Integer... value) {
        addEquals(UserColumns.FOURWEEKPAYOFF, value);
        return this;
    }

    public UserSelection fourweekpayoffNot(Integer... value) {
        addNotEquals(UserColumns.FOURWEEKPAYOFF, value);
        return this;
    }

    public UserSelection fourweekpayoffGt(int value) {
        addGreaterThan(UserColumns.FOURWEEKPAYOFF, value);
        return this;
    }

    public UserSelection fourweekpayoffGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.FOURWEEKPAYOFF, value);
        return this;
    }

    public UserSelection fourweekpayoffLt(int value) {
        addLessThan(UserColumns.FOURWEEKPAYOFF, value);
        return this;
    }

    public UserSelection fourweekpayoffLtEq(int value) {
        addLessThanOrEquals(UserColumns.FOURWEEKPAYOFF, value);
        return this;
    }

    public UserSelection orderByFourweekpayoff(boolean desc) {
        orderBy(UserColumns.FOURWEEKPAYOFF, desc);
        return this;
    }

    public UserSelection orderByFourweekpayoff() {
        orderBy(UserColumns.FOURWEEKPAYOFF, false);
        return this;
    }

    public UserSelection zerohours(Integer... value) {
        addEquals(UserColumns.ZEROHOURS, value);
        return this;
    }

    public UserSelection zerohoursNot(Integer... value) {
        addNotEquals(UserColumns.ZEROHOURS, value);
        return this;
    }

    public UserSelection zerohoursGt(int value) {
        addGreaterThan(UserColumns.ZEROHOURS, value);
        return this;
    }

    public UserSelection zerohoursGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.ZEROHOURS, value);
        return this;
    }

    public UserSelection zerohoursLt(int value) {
        addLessThan(UserColumns.ZEROHOURS, value);
        return this;
    }

    public UserSelection zerohoursLtEq(int value) {
        addLessThanOrEquals(UserColumns.ZEROHOURS, value);
        return this;
    }

    public UserSelection orderByZerohours(boolean desc) {
        orderBy(UserColumns.ZEROHOURS, desc);
        return this;
    }

    public UserSelection orderByZerohours() {
        orderBy(UserColumns.ZEROHOURS, false);
        return this;
    }

    public UserSelection contracthours(Integer... value) {
        addEquals(UserColumns.CONTRACTHOURS, value);
        return this;
    }

    public UserSelection contracthoursNot(Integer... value) {
        addNotEquals(UserColumns.CONTRACTHOURS, value);
        return this;
    }

    public UserSelection contracthoursGt(int value) {
        addGreaterThan(UserColumns.CONTRACTHOURS, value);
        return this;
    }

    public UserSelection contracthoursGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.CONTRACTHOURS, value);
        return this;
    }

    public UserSelection contracthoursLt(int value) {
        addLessThan(UserColumns.CONTRACTHOURS, value);
        return this;
    }

    public UserSelection contracthoursLtEq(int value) {
        addLessThanOrEquals(UserColumns.CONTRACTHOURS, value);
        return this;
    }

    public UserSelection orderByContracthours(boolean desc) {
        orderBy(UserColumns.CONTRACTHOURS, desc);
        return this;
    }

    public UserSelection orderByContracthours() {
        orderBy(UserColumns.CONTRACTHOURS, false);
        return this;
    }

    public UserSelection enabled(Integer... value) {
        addEquals(UserColumns.ENABLED, value);
        return this;
    }

    public UserSelection enabledNot(Integer... value) {
        addNotEquals(UserColumns.ENABLED, value);
        return this;
    }

    public UserSelection enabledGt(int value) {
        addGreaterThan(UserColumns.ENABLED, value);
        return this;
    }

    public UserSelection enabledGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.ENABLED, value);
        return this;
    }

    public UserSelection enabledLt(int value) {
        addLessThan(UserColumns.ENABLED, value);
        return this;
    }

    public UserSelection enabledLtEq(int value) {
        addLessThanOrEquals(UserColumns.ENABLED, value);
        return this;
    }

    public UserSelection orderByEnabled(boolean desc) {
        orderBy(UserColumns.ENABLED, desc);
        return this;
    }

    public UserSelection orderByEnabled() {
        orderBy(UserColumns.ENABLED, false);
        return this;
    }

    public UserSelection verified(Integer... value) {
        addEquals(UserColumns.VERIFIED, value);
        return this;
    }

    public UserSelection verifiedNot(Integer... value) {
        addNotEquals(UserColumns.VERIFIED, value);
        return this;
    }

    public UserSelection verifiedGt(int value) {
        addGreaterThan(UserColumns.VERIFIED, value);
        return this;
    }

    public UserSelection verifiedGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.VERIFIED, value);
        return this;
    }

    public UserSelection verifiedLt(int value) {
        addLessThan(UserColumns.VERIFIED, value);
        return this;
    }

    public UserSelection verifiedLtEq(int value) {
        addLessThanOrEquals(UserColumns.VERIFIED, value);
        return this;
    }

    public UserSelection orderByVerified(boolean desc) {
        orderBy(UserColumns.VERIFIED, desc);
        return this;
    }

    public UserSelection orderByVerified() {
        orderBy(UserColumns.VERIFIED, false);
        return this;
    }

    public UserSelection postalcode(String... value) {
        addEquals(UserColumns.POSTALCODE, value);
        return this;
    }

    public UserSelection postalcodeNot(String... value) {
        addNotEquals(UserColumns.POSTALCODE, value);
        return this;
    }

    public UserSelection postalcodeLike(String... value) {
        addLike(UserColumns.POSTALCODE, value);
        return this;
    }

    public UserSelection postalcodeContains(String... value) {
        addContains(UserColumns.POSTALCODE, value);
        return this;
    }

    public UserSelection postalcodeStartsWith(String... value) {
        addStartsWith(UserColumns.POSTALCODE, value);
        return this;
    }

    public UserSelection postalcodeEndsWith(String... value) {
        addEndsWith(UserColumns.POSTALCODE, value);
        return this;
    }

    public UserSelection orderByPostalcode(boolean desc) {
        orderBy(UserColumns.POSTALCODE, desc);
        return this;
    }

    public UserSelection orderByPostalcode() {
        orderBy(UserColumns.POSTALCODE, false);
        return this;
    }

    public UserSelection passwordexpire(Integer... value) {
        addEquals(UserColumns.PASSWORDEXPIRE, value);
        return this;
    }

    public UserSelection passwordexpireNot(Integer... value) {
        addNotEquals(UserColumns.PASSWORDEXPIRE, value);
        return this;
    }

    public UserSelection passwordexpireGt(int value) {
        addGreaterThan(UserColumns.PASSWORDEXPIRE, value);
        return this;
    }

    public UserSelection passwordexpireGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.PASSWORDEXPIRE, value);
        return this;
    }

    public UserSelection passwordexpireLt(int value) {
        addLessThan(UserColumns.PASSWORDEXPIRE, value);
        return this;
    }

    public UserSelection passwordexpireLtEq(int value) {
        addLessThanOrEquals(UserColumns.PASSWORDEXPIRE, value);
        return this;
    }

    public UserSelection orderByPasswordexpire(boolean desc) {
        orderBy(UserColumns.PASSWORDEXPIRE, desc);
        return this;
    }

    public UserSelection orderByPasswordexpire() {
        orderBy(UserColumns.PASSWORDEXPIRE, false);
        return this;
    }

    public UserSelection workscheduleId(Long... value) {
        addEquals(UserColumns.WORKSCHEDULE_ID, value);
        return this;
    }

    public UserSelection workscheduleIdNot(Integer... value) {
        addNotEquals(UserColumns.WORKSCHEDULE_ID, value);
        return this;
    }

    public UserSelection workscheduleIdGt(int value) {
        addGreaterThan(UserColumns.WORKSCHEDULE_ID, value);
        return this;
    }

    public UserSelection workscheduleIdGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.WORKSCHEDULE_ID, value);
        return this;
    }

    public UserSelection workscheduleIdLt(int value) {
        addLessThan(UserColumns.WORKSCHEDULE_ID, value);
        return this;
    }

    public UserSelection workscheduleIdLtEq(int value) {
        addLessThanOrEquals(UserColumns.WORKSCHEDULE_ID, value);
        return this;
    }

    public UserSelection orderByWorkscheduleId(boolean desc) {
        orderBy(UserColumns.WORKSCHEDULE_ID, desc);
        return this;
    }

    public UserSelection orderByWorkscheduleId() {
        orderBy(UserColumns.WORKSCHEDULE_ID, false);
        return this;
    }

    public UserSelection employerId(String... value) {
        addEquals(UserColumns.EMPLOYER_ID, value);
        return this;
    }

    public UserSelection employerIdNot(String... value) {
        addNotEquals(UserColumns.EMPLOYER_ID, value);
        return this;
    }

    public UserSelection employerIdLike(String... value) {
        addLike(UserColumns.EMPLOYER_ID, value);
        return this;
    }

    public UserSelection employerIdContains(String... value) {
        addContains(UserColumns.EMPLOYER_ID, value);
        return this;
    }

    public UserSelection employerIdStartsWith(String... value) {
        addStartsWith(UserColumns.EMPLOYER_ID, value);
        return this;
    }

    public UserSelection employerIdEndsWith(String... value) {
        addEndsWith(UserColumns.EMPLOYER_ID, value);
        return this;
    }

    public UserSelection orderByEmployerId(boolean desc) {
        orderBy(UserColumns.EMPLOYER_ID, desc);
        return this;
    }

    public UserSelection orderByEmployerId() {
        orderBy(UserColumns.EMPLOYER_ID, false);
        return this;
    }

    public UserSelection role(Integer... value) {
        addEquals(UserColumns.ROLE, value);
        return this;
    }

    public UserSelection roleNot(Integer... value) {
        addNotEquals(UserColumns.ROLE, value);
        return this;
    }

    public UserSelection roleGt(int value) {
        addGreaterThan(UserColumns.ROLE, value);
        return this;
    }

    public UserSelection roleGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.ROLE, value);
        return this;
    }

    public UserSelection roleLt(int value) {
        addLessThan(UserColumns.ROLE, value);
        return this;
    }

    public UserSelection roleLtEq(int value) {
        addLessThanOrEquals(UserColumns.ROLE, value);
        return this;
    }

    public UserSelection orderByRole(boolean desc) {
        orderBy(UserColumns.ROLE, desc);
        return this;
    }

    public UserSelection orderByRole() {
        orderBy(UserColumns.ROLE, false);
        return this;
    }

    public UserSelection synchronize(Integer... value) {
        addEquals(UserColumns.SYNCHRONIZE, value);
        return this;
    }

    public UserSelection synchronizeNot(Integer... value) {
        addNotEquals(UserColumns.SYNCHRONIZE, value);
        return this;
    }

    public UserSelection synchronizeGt(int value) {
        addGreaterThan(UserColumns.SYNCHRONIZE, value);
        return this;
    }

    public UserSelection synchronizeGtEq(int value) {
        addGreaterThanOrEquals(UserColumns.SYNCHRONIZE, value);
        return this;
    }

    public UserSelection synchronizeLt(int value) {
        addLessThan(UserColumns.SYNCHRONIZE, value);
        return this;
    }

    public UserSelection synchronizeLtEq(int value) {
        addLessThanOrEquals(UserColumns.SYNCHRONIZE, value);
        return this;
    }

    public UserSelection orderBySynchronize(boolean desc) {
        orderBy(UserColumns.SYNCHRONIZE, desc);
        return this;
    }

    public UserSelection orderBySynchronize() {
        orderBy(UserColumns.SYNCHRONIZE, false);
        return this;
    }
}
