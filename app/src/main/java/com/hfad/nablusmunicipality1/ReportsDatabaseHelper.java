package com.hfad.nablusmunicipality1;


import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import android.widget.Toast;

public class ReportsDatabaseHelper extends SQLiteOpenHelper  {

    private static final String DB_NAME = "reportse";    // The name of our database
    private static final int DB_VERSION = 1;    // the version of the database

    ReportsDatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE REPORTSE(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "AREA TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID TEXT, "
                + "LIKES);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}
