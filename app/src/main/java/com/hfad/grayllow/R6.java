package com.hfad.grayllow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;


public class R6 extends AppCompatActivity implements AsyncResponse {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, r7.class);
        // This is related to async
        HashMap postData1 = new HashMap();
        //postData1.put("counterNumber", LoginActicity.COUNTER_NUMBER);
        postData1.put("reportId", R5.COUNTERNUMBER);
        PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData1);
        task.execute("http://androdimysqlapp.azurewebsites.net/checkLikes.php");

    }

    @Override
    public void processFinish(String s) {

        if(s.matches("success"))
        {

        }




        startActivity(intent);
        finish();

    }
}
