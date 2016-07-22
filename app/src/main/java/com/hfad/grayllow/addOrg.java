package com.hfad.grayllow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class addOrg extends AppCompatActivity {


    EditText Organization_id;
    HashMap postData2 = new HashMap();
    String org_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_org);
        Organization_id = (EditText) findViewById(R.id.editTextOrg);
    }


    public void onClickAdd(View view)
    {

        // When the add button clicked

        String org_id_string = Organization_id.getText().toString();

        HashMap postData = new HashMap();
        postData2.put("user_name", LoginActicity.USER_NAME);
        postData2.put("org_id", org_id_string);

        postData.put("org_id", org_id_string);


        com.kosalgeek.genasync12.PostResponseAsyncTask task1 = new com.kosalgeek.genasync12.PostResponseAsyncTask(this, postData, false, new com.kosalgeek.genasync12.AsyncResponse() {
            @Override
            public void processFinish(String s) {

                org_name = s;

                 postData2.put("org_name", org_name);

            }
        });


        task1.execute("http://androdimysqlapp.azurewebsites.net/GetOrgName.php");


        com.kosalgeek.genasync12.PostResponseAsyncTask task2 = new com.kosalgeek.genasync12.PostResponseAsyncTask(this, postData2, true, new com.kosalgeek.genasync12.AsyncResponse() {
            @Override
            public void processFinish(String s) {

                if (s.matches("success"))
                {
                    Toast.makeText(addOrg.this, "The Organization has Added", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(addOrg.this, "Please Check The Organization Id!", Toast.LENGTH_LONG).show();
                }

            }

        });

        task2.execute("http://androdimysqlapp.azurewebsites.net/addOrg.php");
    }
}
