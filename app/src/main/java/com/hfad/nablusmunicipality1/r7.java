package com.hfad.nablusmunicipality1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class r7 extends AppCompatActivity implements AsyncResponse{

    public static int image_checker = 0;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, R8.class);

        // This is related to async
        HashMap postData = new HashMap();
        postData.put("id", Reports1.idNum);
        PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
        task.execute("http://androdimysqlapp.azurewebsites.net/getOrder-4.php");
    }

    @Override
    public void processFinish(String s) {

        if (s.matches("")) {
            image_checker = 1;
            Intent intent = new Intent(this, R8.class);
            startActivity(intent);
            finish();
        } else {
            image_checker = 0;
            Report.image_id = s;

            startActivity(intent);
            finish();

        }
    }
}
