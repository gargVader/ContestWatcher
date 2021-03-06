package com.example.mvvmpractice.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmpractice.Dao.ContestDao;
import com.example.mvvmpractice.Model.Contest;

@Database(entities = {Contest.class}, version = 1)
public abstract class ContestDatabase extends RoomDatabase {

    private static ContestDatabase instance;
    public abstract ContestDao getContestDao();

    public static synchronized ContestDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context, ContestDatabase.class, "contest_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
