package com.example.zadanie1;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IDaoAccess {

    @Query("SELECT * FROM users order by uid desc")
    LiveData<List<User>> getAll();

    @Insert
    void insertData(User... user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM users")
    Cursor findAllRecords();
}
