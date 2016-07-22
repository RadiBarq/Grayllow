package com.hfad.grayllow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.annotation.AnnotationFormatError;
import java.util.HashMap;
import android.support.v7.widget.Toolbar;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class organizationsList extends AppCompatActivity {


    ListView list;
    ArrayList<HashMap<String, String>> personList;
    public static String idNumOrganization;
    String myJSON;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    public static final String TAG_ID = "id";
    private static final String TAG_LIKES = "likes";
    private static final String TAG_DESCRIPTION = "description";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
                // TODO getData();


        });


        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Reports1.Organization_ID = ((TextView) view.findViewById(R.id.description)).getText().toString();
                Intent intent = new Intent(organizationsList.this, Reports1.class);
                startActivity(intent);
            }
        });


        getData();
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {

                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String description = c.getString(TAG_DESCRIPTION);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_ID, id);
                persons.put(TAG_DESCRIPTION, description);

                personList.add(persons);

            }


            ListAdapter adapter = new SimpleAdapter(

                    organizationsList.this, personList, R.layout.list_item2,
                    new String[]{TAG_ID, TAG_DESCRIPTION},
                    new int[]{R.id.id, R.id.description}

            );

            list.setAdapter(adapter);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }



    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute()
            {

                    super.onPreExecute();
                    pDialog = new ProgressDialog(organizationsList.this);
                    pDialog.setMessage("Loading...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();

            }


            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://androdimysqlapp.azurewebsites.net/android_connect/get-data1.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {

                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                    } catch (Exception squish) {
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                pDialog.dismiss();

                if (myJSON == null)
                    Toast.makeText(organizationsList.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


                if (myJSON != null)
                    showList();

            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

}



