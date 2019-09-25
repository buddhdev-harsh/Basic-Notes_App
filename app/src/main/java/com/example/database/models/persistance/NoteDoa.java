package com.example.database.models.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.database.models.Notes;

import java.util.List;

@Dao
public interface NoteDoa {

    @Insert
    long[] insetNotes(Notes... notes);
    @Query("SELECT * FROM notes")
    LiveData<List<Notes>> getNotes();
    @Delete
    int delete(Notes... notes);
    @Update
    int update(Notes... notes);
}
