package com.example.providercaller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    ContentResolver contentResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callProvider();
    }

    @SuppressLint("Range")
    private void callProvider()
    {
        contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
            Uri.parse("content://com.example.zadanie1/users/1"),null,null,null,null);

        if (cursor.moveToFirst())
        {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("\n");
        while (!cursor.isAfterLast()) {
            strBuild.append("\n id: " + cursor.getString(cursor.getColumnIndex("uid")) + " username: " + cursor.getString(cursor.getColumnIndex("userName"))
       + " count: " +cursor.getString(cursor.getColumnIndex("lastNumber")));
        cursor.moveToNext();
    }
        Log.i("provider", strBuild.toString());
    }
        else
            {
        Log.w("provider", "No records found");
    }
    }
}