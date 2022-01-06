package com.example.zadanie1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UsersDatabase extends RoomDatabase{
    public abstract IDaoAccess daoAccess();

    private static UsersDatabase instance;

    public static UsersDatabase getInstance(Context ctx) {
        if(instance == null)
            instance = Room.databaseBuilder(ctx.getApplicationContext(), UsersDatabase.class,
                "myDatabase")
                    .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        return instance;

    }



}
