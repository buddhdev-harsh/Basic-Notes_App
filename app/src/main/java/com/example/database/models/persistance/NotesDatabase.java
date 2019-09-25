package com.example.database.models.persistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.database.models.Notes;

@Database(entities = {Notes.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {
   public static final String DATABASE_NAME="notes_db";
   private static NotesDatabase instance;
   static NotesDatabase getInstance(final Context context){
       if(instance==null){
           instance= Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,DATABASE_NAME).build();
       }
    return instance;
   }
   public abstract NoteDoa getNoteDoa();
}
