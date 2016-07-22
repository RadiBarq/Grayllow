package com.hfad.grayllow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

import com.kosalgeek.asynctask.AsyncResponse;

public class RigesterActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigester);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    public void onClickRegButton(View view) {

        EditText edittext4 = (EditText) findViewById(R.id.editText4);
        EditText editText5 = (EditText) findViewById(R.id.editText5);
        EditText editText6 = (EditText) findViewById(R.id.editText6);

        String counterNumber = edittext4.getText().toString();
        String userName = editText5.getText().toString();
        String password = editText6.getText().toString();

        if (counterNumber.matches(""))
            Toast.makeText(this, "نسيت رقم العداد", Toast.LENGTH_SHORT).show();
        else if (userName.matches(""))
            Toast.makeText(this, "نسيت خانة الاسم", Toast.LENGTH_SHORT).show();
        else if (password.matches(""))
            Toast.makeText(this, "نسيت كلمة السر", Toast.LENGTH_SHORT).show();
        else {

            HashMap postData = new HashMap();
            postData.put("name", userName);
            postData.put("counterNumber", counterNumber);
            postData.put("password", password);
            PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
            task.execute("http://androdimysqlapp.azurewebsites.net//register.php");
        }
    }


    @Override
    public void processFinish(String result) {
        if (result.equals("success")) {
            Toast.makeText(this, "تم تسجيلك بنجاح", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActicity.class);
            startActivity(intent);
            finish();
        }

        else
            Toast.makeText(this, "يوجد خطا في الشبكه اعد المحاوله", Toast.LENGTH_LONG).show();
    }


}
