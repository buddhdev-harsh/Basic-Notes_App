package com.example.database.insetAsync;

import android.os.AsyncTask;

import com.example.database.models.Notes;
import com.example.database.models.persistance.NoteDoa;

public class InsertAsync extends AsyncTask<Notes , Void , Void > {

    private NoteDoa mnoteDoa;
    public InsertAsync(NoteDoa noteDoa){
        mnoteDoa=noteDoa;
    }
    @Override
    protected Void doInBackground(Notes... notes)    {
        mnoteDoa.insetNotes(notes);
        return null;
    }
}
