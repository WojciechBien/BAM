package com.example.zadanie1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendName(View view) {
        Intent intent  = new Intent(this, UserActivity.class);
        EditText eText =  (EditText)findViewById(R.id.editTextTextPersonName);
        String textBoxText = eText.getText().toString();
        intent.putExtra("name",textBoxText);
        startActivity(intent);
    }
}