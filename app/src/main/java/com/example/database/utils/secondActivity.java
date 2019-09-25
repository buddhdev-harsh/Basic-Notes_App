package com.example.database.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.database.R;
import com.example.database.adapters.NotesRecyclerAdapter;
import com.example.database.models.Notes;
import com.example.database.models.persistance.NoteRepos;
import com.example.database.models.persistance.NotesDatabase;

public class secondActivity extends AppCompatActivity implements View.OnTouchListener ,
        GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener, TextWatcher,android.view.View.OnClickListener {

    private static final String TAG="NOTEACTIVITY";
    private Lined medittext;
    private EditText meditText1;
    private TextView mViewTitle;
    private RelativeLayout mcheckcontainer,mbackarrowcontainer;
    private ImageButton mcheck,marrow;
    private NoteRepos mnoteRepos;
    private Notes mfinalNote;

    private static final int Edit=1;
    private static final int View=0;
    private int mMode;


    private Boolean misNewNote;
    private Notes initialNote;
    private GestureDetector mgestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mcheck=findViewById(R.id.checkarrow);
        marrow=findViewById(R.id.backarrow);
        mcheckcontainer=findViewById(R.id.checkcont);
        mbackarrowcontainer=findViewById(R.id.backcont);
        medittext=findViewById(R.id.notetext);
        mViewTitle=findViewById(R.id.texttitle);
        meditText1=findViewById(R.id.note_edit_title);
        mnoteRepos=new NoteRepos(this);
        helpListeners();
        if(getIncomingIntent()){
            setNewNoteProp();
            editmode();
        }else{
            setNoteProperties();
            disablecontentinteraction();
        }

    }
    @Override
    public void onClick(android.view.View view) {
        switch (view.getId()){

            case R.id.checkarrow: {
                viewmode();
                hideSoftKeyBoard();
                break;
            }case R.id.texttitle: {
                editmode();
                meditText1.requestFocus();
                meditText1.setSelection(meditText1.length());
                break;

            }case R.id.backarrow:{
                finish();
                break;
            }
        }

    }


    public boolean getIncomingIntent(){
        if(getIntent().hasExtra("NNN")){
            mMode=View;
            misNewNote=false;
            initialNote=getIntent().getParcelableExtra("NNN");
            mfinalNote=new Notes();
            mfinalNote.setTitle(initialNote.getTitle());
            mfinalNote.setTimestamp(initialNote.getTimestamp());
            mfinalNote.setContent(initialNote.getContent());
            mfinalNote.setId(initialNote.getId());
            return false;
        }
        mMode=Edit;
        misNewNote=true;
        return true;
    }
    private void editmode(){
        mbackarrowcontainer.setVisibility(android.view.View.GONE);
        mcheckcontainer.setVisibility(android.view.View.VISIBLE);
        mViewTitle.setVisibility(android.view.View.GONE);
        meditText1.setVisibility(android.view.View.VISIBLE);

        mMode=Edit;
        enablecontentinteraction();
    }
    private void disablecontentinteraction(){
        medittext.setKeyListener(null);
        medittext.setFocusable(false);
        medittext.setFocusableInTouchMode(false);
        medittext.setCursorVisible(false);
        medittext.clearFocus();

    }
    private void enablecontentinteraction(){
        medittext.setKeyListener(new EditText(this).getKeyListener());
        medittext.setFocusable(true);
        medittext.setFocusableInTouchMode(true);
        medittext.setCursorVisible(true);
        medittext.requestFocus();

    }

    @Override
    public void onBackPressed() {
        if(mMode==Edit){
            onClick(mcheck);
        }else {
            super.onBackPressed();
        }
    }

    private void viewmode(){
        mbackarrowcontainer.setVisibility(android.view.View.VISIBLE);
        mcheckcontainer.setVisibility(android.view.View.GONE);
        mViewTitle.setVisibility(android.view.View.VISIBLE);
        meditText1.setVisibility(android.view.View.GONE);
        disablecontentinteraction();
        mMode=View;
        String temp=medittext.getText().toString();
        temp=temp.replace("\n","");
        temp=temp.replace("","");
        if(temp.length()>0){
            mfinalNote.setTitle(meditText1.getText().toString());
            mfinalNote.setContent(medittext.getText().toString());
            String timestamp=Utility.getcurrenttime();
            mfinalNote.setTimestamp(timestamp);
            if(!mfinalNote.getContent().equals(initialNote.getContent())||!mfinalNote.getTitle().equals(initialNote.getTitle())){
                SaveChange();
            }

        }




    }
    private void hideSoftKeyBoard(){
        InputMethodManager imm=(InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view=this.getCurrentFocus();
        if(view==null){
            view=new View(this);

        }
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    private void SaveChange(){
            if(misNewNote){
                SaveNewNote();
            }else{
            Updatenotes();
            }
    }

    private void Updatenotes(){
        mnoteRepos.UpdateNOtes(mfinalNote);
    }
    private void SaveNewNote(){
        mnoteRepos.InsetNoteTask(mfinalNote);
    }
    private void helpListeners(){
        medittext.setOnTouchListener(this);
        mgestureDetector=new GestureDetector(this,this);
        mViewTitle.setOnClickListener(this);
        mcheck.setOnClickListener(this);
        marrow.setOnClickListener(this);
        meditText1.addTextChangedListener(this);
    }
    private void setNewNoteProp(){
        mViewTitle.setText("Note Title");
        meditText1.setText("Note Title");
        mfinalNote=new Notes();
        initialNote=new Notes();
        initialNote.setTitle("Note Title");
        mfinalNote.setTitle("Note Title");

    }
    private void setNoteProperties(){
        mViewTitle.setText(initialNote.getTitle());
        meditText1.setText(initialNote.getTitle());
        medittext.setText(initialNote.getContent());

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return mgestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG,"double tapppeeddd");
        editmode();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mViewTitle.setText(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
