package com.example.diariodehumor;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {HumorEntry.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract HumorDao humorDao();
}

