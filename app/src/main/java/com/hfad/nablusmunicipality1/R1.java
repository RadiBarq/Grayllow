package com.hfad.nablusmunicipality1;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

/**
 * Created by sbitanyhome on 1/11/2016.
 */
public class R1 implements AsyncResponse {

    String id;
    String description;

    R1(String id)
    {
        HashMap postData = new HashMap();
        postData.put("id", id);
        PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
        task.execute("http://androdimysqlapp.azurewebsites.net/R1.php");
    }


    @Override
    public void processFinish(String s) {

        description = s;

    }

    public String returnDescription()
    {
        return description;
    }
}
