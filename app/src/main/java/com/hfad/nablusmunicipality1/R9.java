package com.hfad.nablusmunicipality1;

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

public class R9 extends AppCompatActivity implements AsyncResponse {

    Intent intent;
    public static String report_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, Report.class);
        HashMap postData = new HashMap();
        postData.put("id", Reports1.idNum);
        PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
        task.execute("http://androdimysqlapp.azurewebsites.net/getOrder-6.php");

    }

    @Override
    public void processFinish(String s) {

        startActivity(intent);
        report_date = s;
        finish();
    }



}
