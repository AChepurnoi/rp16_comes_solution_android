package com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypesViews;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;

import java.util.Date;

/**
 * Created by Granium on 02.06.16.
 */
public interface IDayType {

    public Date getStartDate();
    public Date getEndDate();
    public void setStartDate(Date date);
    public void setEndDate(Date date);
    public DayType getDayType();
    public View getViewPresentation(AppCompatActivity activity);


}
