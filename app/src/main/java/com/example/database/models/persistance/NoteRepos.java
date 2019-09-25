package com.example.database.models.persistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.database.insetAsync.DeleteAsync;
import com.example.database.insetAsync.InsertAsync;
import com.example.database.insetAsync.UpdateAsync;
import com.example.database.models.Notes;

import java.util.List;

public class NoteRepos {

    private NotesDatabase mnotesDatabase;
    public NoteRepos(Context context){
            mnotesDatabase=NotesDatabase.getInstance(context);
    }

    public void InsetNoteTask(Notes notes){
        new InsertAsync(mnotesDatabase.getNoteDoa()).execute(notes);
    }
    public void UpdateNOtes(Notes notes){
        new UpdateAsync(mnotesDatabase.getNoteDoa()).execute(notes);
    }
    public LiveData<List<Notes>> retriveNotes(){
        return mnotesDatabase.getNoteDoa().getNotes();
    }
    public void DeleteNote(Notes notes){
        new DeleteAsync(mnotesDatabase.getNoteDoa()).execute(notes);
    }


}
