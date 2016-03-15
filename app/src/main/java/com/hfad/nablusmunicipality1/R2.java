package com.hfad.nablusmunicipality1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class R2 extends AppCompatActivity implements AsyncResponse{
    Intent intent;
    public static String Likes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, R4.class);
        HashMap posData = new HashMap();
        posData.put("id", Reports1.idNum);

    }

    @Override
    public void processFinish(String s) {

        Likes = s;
        startActivity(intent);
        finish();
    }
}
