package com.hfad.nablusmunicipality1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickLogin(View view)
    {
        Intent loginIntent = new Intent (this, LoginActicity.class);
        startActivity(loginIntent);
    }

    public void onClickWaterCycle(View view)
    {
        Intent waterCycleIntent = new Intent(this, Report.class);
        startActivity(waterCycleIntent);
    }

    public void onClickContact(View view)
    {
        Intent contactIntent = new Intent(this, Contact.class);
        startActivity(contactIntent);
    }

    public void onClickAbout(View view)
    {
        Intent aboutIntent = new Intent(this, About.class);
        startActivity(aboutIntent);

    }

}


