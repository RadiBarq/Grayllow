package com.hfad.nablusmunicipality1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sbitanyhome on 12/31/2015.
 */
public class Reports_ids extends SQLiteOpenHelper {

    private static final String DB_NAME = "reports_ids";        // The name of our database
    private static final int DB_VERSION = 1;                    // the version of the databse

    Reports_ids(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE REPORTS_IDS(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "IDS INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}
