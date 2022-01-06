package com.example.zadanie1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NumberReceiver extends BroadcastReceiver {

    private UsersDatabase database;
    private String name;
    private int number;
    @Override
    public void onReceive(Context context, Intent intent) {

        database = UsersDatabase.getInstance(context);
        name = intent.getStringExtra("name");
        number = intent.getIntExtra("number",0);

        Log.i(null, "\n Username: " + name);
        Log.i(null, "Last number: " + number);

        User user = new User(name,number);

        AsyncTask.execute(() -> database.daoAccess().insertData(user));

    }

    public void countRecords(Context ctx)
    {
        if(database == null)
        {
            database = UsersDatabase.getInstance(ctx);
        }

        Cursor result = database.daoAccess().findAllRecords();

        if (result.moveToFirst()){
        do {
            Log.i(null, "User no.: " + result.getString(0) + "; username: " + result.getString(1) +"; last number: " +
                    + result.getInt(2));
        }
        while (result.moveToNext());
    }
    }
}
