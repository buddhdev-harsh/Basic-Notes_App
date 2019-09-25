package com.example.database.insetAsync;

import android.os.AsyncTask;

import com.example.database.models.Notes;
import com.example.database.models.persistance.NoteDoa;

public class UpdateAsync extends AsyncTask<Notes , Void , Void > {

    private NoteDoa mnoteDoa;
    public UpdateAsync(NoteDoa noteDoa){
        mnoteDoa=noteDoa;
    }
    @Override
    protected Void doInBackground(Notes... notes)    {
        mnoteDoa.update(notes);
        return null;
    }
}
