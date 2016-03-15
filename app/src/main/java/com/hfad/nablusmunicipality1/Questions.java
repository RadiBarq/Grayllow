package com.hfad.nablusmunicipality1;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class Questions extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ListView listQuestion = getListView();
        ArrayAdapter<QuestionsData> listAdapter = new ArrayAdapter<QuestionsData>(
                this,
                android.R.layout.simple_list_item_1,
                QuestionsData.questions);
                listQuestion.setAdapter(listAdapter);
    }

}
