package com.example.database.insetAsync;

import android.os.AsyncTask;

import com.example.database.models.Notes;
import com.example.database.models.persistance.NoteDoa;

public class DeleteAsync extends AsyncTask<Notes , Void , Void > {

    private NoteDoa mnoteDoa;
    public DeleteAsync(NoteDoa noteDoa){
        mnoteDoa=noteDoa;
    }
    @Override
    protected Void doInBackground(Notes... notes)    {
        mnoteDoa.delete(notes);
        return null;
    }
}
