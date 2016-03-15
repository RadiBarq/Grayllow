package com.hfad.nablusmunicipality1;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.EventLogTags;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.io.InputStream;
import android.support.v7.app.AppCompatActivity;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class Reports1 extends AppCompatActivity{

    String myJSON;

    int CHEKER = 0;
    public static int Counter = 0;

    private ProgressDialog pDialog;
    public static String Description;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_LIKES = "likes";
    private static final String TAG_DESCRIPTION = "description";
    private ActionBarDrawerToggle drawerToggle;
    JSONArray peoples = null;
    public static final String TAG_ID = "id";
    public static String idNum;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<HashMap<String, String>> personList;
    ListView list;
    public static String image_id;
    String hey = "http://androdimysqlapp.azurewebsites.net//images/";
    String url = hey + image_id;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    TextView textView;


    //This is related to the drawer
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar myChildToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports1);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);

       // drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


       // mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CHEKER = 1;
                personList = new ArrayList<HashMap<String, String>>();
                getData();
            }
        });

     toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // textView.setText(LoginActicity.USER_NAME);



        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerToggle = setupDrawerToggle();

        mDrawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

             //myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            //setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar

        //android.support.v7.app.ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);



        // Enable the Up button


        //drawerLayout.setDrawerListener(drawerToggle);



//        mDrawerToggle = new ActionBarDrawerToggle(this,
  //              drawerLayout, myChildToolbar, R.string.open_drawer, R.string.close_drawer) {
//
            /** Called when a drawer has settled in a completely closed state. */
  //          public void onDrawerClosed(View view) {
     //           super.onDrawerClosed(view);

  //              invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
    //      }

        //    /** Called when a drawer has settled in a completely open state. */
      //      public void onDrawerOpened(View drawerView) {
            //    super.onDrawerOpened(drawerView);
          //      invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        //    }
        //};

//        mDrawerLayout.setDrawerListener(mDrawerToggle);



        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view

        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                       @Override
                                                       public boolean onNavigationItemSelected(MenuItem item) {

                                                           selectDrawerItem(item);
                                                           return true;
                                                       }

                                                   });



        // When the header button clicked




        personList = new ArrayList<HashMap<String, String>>();
        getData();
        list = (ListView)findViewById(R.id.listView);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               idNum = ((TextView) view.findViewById(R.id.id)).getText().toString();

                HashMap posData = new HashMap();
                posData.put("id", idNum);
                PostResponseAsyncTask task = new PostResponseAsyncTask(Reports1.this, posData, false, new AsyncResponse() {

                    @Override
                    public void processFinish(String s) {

                        if (s.matches(""))
                            Toast.makeText(Reports1.this, "تامد من اتصال الشبكه", Toast.LENGTH_LONG);
                        else {
                            Description = s;
                            Intent in = new Intent(Reports1.this, Test.class);
                            startActivity(in);
                        }
                    }
                });
                task.execute("http://androdimysqlapp.azurewebsites.net/getOrder-1.php");

            }
        });


    }

    // TODO The Activity result




    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }



    public void selectDrawerItem(MenuItem menuItem)
    {
        android.support.v4.app.Fragment fragment = null;
        Class fragmentClass;

        switch(menuItem.getItemId())
        {
            case R.id.nav_first_fragment:
                fragmentClass = Org_List.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = Add_Org.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = LogoutFragment.class;
                        break;

            default:
                fragmentClass = Org_List.class;
        }

        try {
            fragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();

    }



    @Override
    public void onBackPressed()
    {
        if (mDrawer.isDrawerOpen(GravityCompat.START))
        {
            mDrawer.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }





    private ActionBarDrawerToggle setupDrawerToggle()
    {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open_drawer,  R.string.close_drawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }


        };
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    protected void showList() {


        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

                for (int i = 0; i < peoples.length(); i++) {
                    JSONObject c = peoples.getJSONObject(i);
                    String id = c.getString(TAG_ID);
                    String description = c.getString(TAG_DESCRIPTION);
                    String likes = c.getString(TAG_LIKES);
                    String report_date = c.getString("report_date");
                    String area = c.getString("area");

                    HashMap<String, String> persons = new HashMap<String, String>();

                    persons.put(TAG_ID, id);
                    persons.put(TAG_DESCRIPTION, description);
                    persons.put(TAG_LIKES, likes);
                    persons.put("report_date", report_date);
                    persons.put("area", area);

                    personList.add(persons);


                }

                ListAdapter adapter = new SimpleAdapter(
                        Reports1.this, personList, R.layout.list_item,
                        new String[]{TAG_ID, TAG_DESCRIPTION, TAG_LIKES, "report_date", "area"},
                        new int[]{R.id.id, R.id.description, R.id.likes, R.id.date, R.id.area}
                );

                list.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }





    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }




    private void setActionBarTitle() {
        String title;
        title = ("Organizations");
        getActionBar().setTitle(title);
    }



    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute()
            {
                if (CHEKER == 0) {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(Reports1.this);
                    pDialog.setMessage("Loading...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
            }




            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://androdimysqlapp.azurewebsites.net/android_connect/get-data.php");

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
                mSwipeRefreshLayout.setRefreshing(false);

                if (myJSON == null)
                    Toast.makeText(Reports1.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


                if (myJSON != null)
                showList();

            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    public void onClickFloat(View view)
    {
        Intent intent = new Intent(Reports1.this, Order.class);
        startActivity(intent);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }





    public void onClickAdd(View view)
    {
        Toast.makeText(Reports1.this, "added", Toast.LENGTH_LONG).show();
    }

}