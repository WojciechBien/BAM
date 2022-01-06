package com.example.zadanie1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {
    private String name;
    private Intent intent;
    private NumberReceiver _numberReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTextView();
        _numberReceiver = new NumberReceiver();
        registerReceiver(_numberReceiver, new IntentFilter("receiver"));
    }

    private void setTextView()
    {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        TextView textView = findViewById(R.id.textView);
        textView.setText(name);

    }

    public void stopServices(View view) {
        stopService(intent);
    }

    public void startService(View view) {
        intent = new Intent(getApplicationContext(),CountingService.class).putExtra("name",name);
        startService(intent);
    }

    @Override
    public void onDestroy()
    {
        unregisterReceiver(_numberReceiver);
        stopServices(null);
        super.onDestroy();
    }

    public void countRecords(View view) {
        _numberReceiver.countRecords(getApplicationContext());
    }
}