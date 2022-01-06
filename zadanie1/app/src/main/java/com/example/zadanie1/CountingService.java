package com.example.zadanie1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CountingService extends Service {
    String name;
    ArrayList<Thread> threads = new ArrayList<>();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        name = intent.getStringExtra("name");
        Countdown();
        return super.onStartCommand(intent,flags,startId);
    }

    private void Countdown()
    {
        threads.add(new Thread(){
            @Override public void run(){
           int number = 0;
           while(true)
           {
               try {
                   number++;
                   Log.i("Timer","Czas: " + number);
                   Thread.sleep(1000);
               }
               catch (Exception e)
               {
                    Intent i = new Intent("receiver");
                    i.putExtra("name",name);
                    i.putExtra("number",number);
                    sendBroadcast(i);
                    return;
               }
           }
       }});
        threads.get(threads.size() - 1).start();
    }

    @Override
    public void onDestroy()
    {
        for (Thread thread : threads)
        {
            thread.interrupt();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}