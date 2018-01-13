package com.volkangurbuz.feedmewith;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Button getInfo;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInfo = findViewById(R.id.getButton);
        spinner = findViewById(R.id.topicListSpinner);

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.listOfTopic));
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setAdapter(spinnerArrayAdapter);


    }


    public void showInfo(View view) {

        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        String topic = spinner.getSelectedItem().toString();
        i.putExtra("key",topic);
        startActivity(i);

    }
}
