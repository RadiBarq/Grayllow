package com.hfad.nablusmunicipality1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.CheckBox;
import android.content.ContentValues;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import android.app.Application;

public class Report extends AppCompatActivity implements AsyncResponse {
    private ProgressDialog pDialog;
    public String id;
    public static String TAG_ID = "id";
    private static final String url_order_details = "http://androdimysqlapp.azurewebsites.net/getOrder.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_GET = "get";
    private TextView textView_area;
    private TextView textView_description;
    private TextView textView_report_status;
    private TextView textView_likes;

    private TextView textView_report_date;
    private FloatingActionButton floatButton;
    int like1;
    public static String area;
    public  static int Checker = 0;
      String sArea, sDescription;   int sLikes;  // Declare a variable to handle your data.
    Bitmap image = null;
    ImageView imageView;
    public static String image_id;
    String hey = "http://androdimysqlapp.azurewebsites.net/images/";
    String url = hey + image_id;

    private View mProgressView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        mProgressView = findViewById(R.id.login_progress3);

        if (Checker == 1) {
            Toast.makeText(this, "تاكد من اتصال الشبكة", Toast.LENGTH_LONG);
            Intent intent = new Intent(this, Report.class);
            startActivity(intent);
            finish();
        }



        // Universal IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(false).cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .displayer(new FadeInBitmapDisplayer(500)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();



        textView_area = (TextView) findViewById(R.id.textView10);
        textView_description = (TextView) findViewById(R.id.textView11);
        textView_likes = (TextView) findViewById(R.id.textView12);
        imageView = (ImageView) findViewById(R.id.imageView5);
        textView_report_status = (TextView) findViewById(R.id.textView7);
        textView_report_date = (TextView) findViewById(R.id.textView8);

        floatButton = (FloatingActionButton) findViewById(R.id.hab);
        if (Test.likesChecker == 1)
            floatButton.hide();
        else
            floatButton.show();
        area = R4.area;
        textView_area.setText(area);
        textView_description.setText(Reports1.Description);
        textView_likes.setText(R2.Likes);
        textView_report_status.setText(R8.report_status);
        textView_report_date.setText(R9.report_date);
        Intent i = getIntent();
        id = i.getStringExtra("id");


        ImageLoader.getInstance().init(config);
        if (Test.image_checker == 0) {

            Toast.makeText(Report.this, "جاري تحميل الصوره", Toast.LENGTH_SHORT).show();
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(url, imageView, defaultOptions);

        }
         else if (Test.image_checker == 1) {
            imageView.setImageResource(R.drawable.iconnote);
        }


        showProgress(false);

    }



            public void onClickLikesButton(View view) {
                like1 = Integer.valueOf(R2.Likes);
                like1++;
                HashMap postData = new HashMap();
                postData.put("id", Reports1.idNum);
                postData.put("likes", String.valueOf(like1));
                //postData.put("counterNumber", LoginActicity.COUNTER_NUMBER);
                postData.put("reportId", R5.COUNTERNUMBER);
                PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
                task.execute("http://androdimysqlapp.azurewebsites.net/android_connect/updateLikes.php");

            }

            // Do this when the image clicked
            public void onClickImage(View view) {
                if (Test.image_checker == 0) {
                    Intent intent = new Intent(this, DisplayImage.class);
                    startActivity(intent);
                }

            }

            // This is related to connect the photo to the server son
            @Override
            public void processFinish(String s) {
                if (s.matches("")) {
                    textView_likes.setText(String.valueOf(like1));
                    floatButton.hide();

                }
            }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show)
    {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimeTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimeTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
        else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        }

    }



        }
