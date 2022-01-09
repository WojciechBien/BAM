package com.example.zadanie3

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    private val urlAdress = URL("https://jsonplaceholder.typicode.com/posts")
    var READ_CONTACTS_PERMISSION_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver()
    }

    fun getRequest(view: android.view.View) {
        Thread{getRequestInternal()}.start();
    }

    private fun getRequestInternal()
    {
        val connection = urlAdress.openConnection() as HttpsURLConnection

        connection.requestMethod = "GET"

       // val retreiveResponseCode = connection.responseCode

        val inputStream = BufferedReader(InputStreamReader(connection.inputStream))

        val responseContent = StringBuffer()

        var input: String?

        while (inputStream.readLine().also { input = it } != null)
        {
            responseContent.append(input)
        }

        inputStream.close()

        println(responseContent)
        getNetworkInfo()
    }

    private fun getNetworkInfo()
    {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        Log.d(TAG,"is connected: ${networkInfo?.isConnected}")
        Log.d(TAG,"Type: ${networkInfo?.type} ${ConnectivityManager.TYPE_WIFI}")
    }

    private fun registerReceiver()
    {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver,intentFilter)
    }
    val receiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.action)
            {
                getNetworkInfo()
            }
        }
    }

    @SuppressLint("Range")
    fun getContacts(view: android.view.View)
    {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),READ_CONTACTS_PERMISSION_REQUEST_CODE)
    }
    @SuppressLint("Range")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_CONTACTS_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {
                val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null,
                    null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
                while (cursor!!.moveToNext())
                {
                    val contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    Log.d(TAG,"Contact $contactId $displayName")
                }
            } else {
            val showDialog = Toast.makeText(this,"aplikacja nie ma odpowiednich uprawnień",Toast.LENGTH_LONG)
                showDialog.show()
            }
        }
    }
}