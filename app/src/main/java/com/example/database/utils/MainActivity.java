package com.example.database.utils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.database.R;
import com.example.database.adapters.NotesRecyclerAdapter;
import com.example.database.models.Notes;
import com.example.database.models.persistance.NoteRepos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNotesClicked,
View.OnClickListener{
    private static final String TAG="MAinActivity";

    private RecyclerView mRecyclerview;
    private ArrayList<Notes> mnotes=new ArrayList<>();
    private NotesRecyclerAdapter mnotesRecyclerAdapter;
    private NoteRepos mnoteRepos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.Recycleview);
        mnoteRepos=new NoteRepos(this);
        initRecyclerView();
        insertFakeNotes();
        RetriveNotes();
        findViewById(R.id.fab).setOnClickListener(this);
        setSupportActionBar((Toolbar)findViewById(R.id.Toolbar));
        setTitle("Notes");
    }
    private void RetriveNotes(){
        mnoteRepos.retriveNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                if(mnotes.size()>0){
                    mnotes.clear();
                }if(notes!=null){
                    mnotes.addAll(notes);
                }
                mnotesRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }
    private void insertFakeNotes(){
        for(int i = 0; i < 1000; i++){
            Notes note = new Notes();
            note.setTitle("title #" + i);
            note.setContent("content #: " + i);
            note.setTimestamp("Jan 2019");
            mnotes.add(note);
        }
        mnotesRecyclerAdapter.notifyDataSetChanged();
    }



    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        itemspacing itemDecorator = new itemspacing(10);
        new ItemTouchHelper(itemtouch).attachToRecyclerView(mRecyclerview);
        mRecyclerview.addItemDecoration(itemDecorator);
        mnotesRecyclerAdapter = new NotesRecyclerAdapter(mnotes, this);
        mRecyclerview.setAdapter(mnotesRecyclerAdapter);
    }

    @Override
    public void OnNoteClick(int i) {
        Intent intent=new Intent(this,secondActivity.class);
        intent.putExtra("NNN",mnotes.get(i));
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent2=new Intent(this,secondActivity.class);
        startActivity(intent2);
    }
    private void deletenote(Notes notes){
        mnotes.remove(notes);
        mnotesRecyclerAdapter.notifyDataSetChanged();
        mnoteRepos.DeleteNote(notes);

    }
    private ItemTouchHelper.SimpleCallback itemtouch=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deletenote(mnotes.get(viewHolder.getAdapterPosition()));
        }
    };
}
