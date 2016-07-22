package com.hfad.grayllow;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.widget.CursorAdapter;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asyntask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;


public class Add_Nasa extends AppCompatActivity implements AsyncResponse{
    public static final String EXTRA_REPORT = "reporteNo";
    public String area;
    public String description;
    public String phoneNumber;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    public int likes = 0;
    private String Report_status = "1";
    String sLocation = "";
    public Button library_button;

    // Those related to the spinner
    Spinner spinner;
    String selected_item = "";

    // Specify the layout to use when the list of choices appears



    // This integers related to integers
    private int PICK_IMAGE_REQUEST = 1;
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";
    private Bitmap bitmap;
    private Uri filePath;
    String filname = "images";
    int random;
    Location lastLocation;
    String reportDate;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    //This is Related to the location
    GoogleApiClient client;


    // This is related to keep the screen on while uploading the photo
    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyWakelockTag");


        // This is related to the spinner
        // Create an ArrayAdapter using the string array and a default spinner layout



        editText2 = (EditText) findViewById(R.id.editText2);

        library_button = (Button) findViewById(R.id.button);

        Random rand = new Random();



        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        reportDate = sdf.format(date);

    }


    public void onClickLibraryPhoto(View view) {
        showFileChooser();
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            filePath = data.getData().normalizeScheme();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void onFloatButtonClicked(View view) {
        description = editText2.getText().toString();

        if (description.matches("")) {
            Context context = getApplicationContext();
            Toast.makeText(this, "You forget to add report description", Toast.LENGTH_SHORT).show();
            return;
        }

        else {

            ContentValues values = new ContentValues();
            /**
             values.put("AREA", area);
             values.put("DESCRIPTION", description);
             values.put("IMAGE_RESOURCE_ID", phoneNumber);
             values.put("LIKES", likes);
             database.insert("REPORTSE", null, values);
             Intent loginIntent = new Intent (this, Reports.class);
             startActivity(loginIntent);
             Context context = getApplicationContext();
             */



            wakeLock.acquire();

            String uploadImage = "NoPhoto";
            if (bitmap != null) {
                uploadImage = getStringImage(bitmap);
            }
            // This realted to async
            HashMap postData = new HashMap();

            postData.put("image", uploadImage);
            postData.put("x",2323);
            postData.put("y", 1232);
            postData.put("description", )

            // This check if the photo there is a photo or not
            if (bitmap != null)
                postData.put("filename", String.valueOf(random) + ".jpeg");
            else
                postData.put("filename","");

            PostResponseAsyncTask task = new PostResponseAsyncTask((AsyncResponse) this, postData);
            task.execute("http://androdimysqlapp.azurewebsites.net/addNasa.php");

        }
    }

    @Override
    public void processFinish(String result) {
        if (result.equals("success")) {
            Toast.makeText(this, "Your report is added successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Reports1.class);
            wakeLock.release();
            finish();
        } else {
            Toast.makeText(this, "Something wrong!, please try again", Toast.LENGTH_LONG).show();
            wakeLock.release();
        }

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodeImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodeImage;

    }

    public void onClickTakePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }
    public void onClickLocation(View view)
    {
        if (client == null) {
            client = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        client.connect();
    }

}