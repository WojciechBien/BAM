package com.example.zadanie1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    User(){};
    User(String username, int lastnumber)
    {
        userName = username;
        lastNumber = lastnumber;
    }
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String userName;

    public int lastNumber;
}
