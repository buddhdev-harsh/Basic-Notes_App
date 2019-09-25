package com.example.database.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;
import com.example.database.models.Notes;
import com.example.database.utils.Utility;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {
    private static final String TAG = "NotesRecyclerAdapter";
    private ArrayList<Notes> mnotes = new ArrayList<>();
    private OnNotesClicked monNotesClicked;
    public NotesRecyclerAdapter(ArrayList<Notes> notes,OnNotesClicked onNotesClicked) {
        this.mnotes = notes;
        this.monNotesClicked=onNotesClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view,monNotesClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       try{
        String month=mnotes.get(position).getTimestamp().substring(0,2);
        month= Utility.getMonthFromNumber(month);
        String Year=mnotes.get(position).getTimestamp().substring(3);
        String timestamp=month+" "+Year;
           holder.timestamp.setText(timestamp);
           holder.title.setText(mnotes.get(position).getTitle());
       }catch(Exception e){

       }


    }

    @Override
    public int getItemCount() {
        return mnotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, timestamp;
        OnNotesClicked monNotesClicked;
        public ViewHolder(@NonNull View itemView,OnNotesClicked onNotesClicked) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            this.monNotesClicked=onNotesClicked;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            monNotesClicked.OnNoteClick(getAdapterPosition());
        }
    }
    public interface OnNotesClicked{
        void OnNoteClick(int i);
    }
}
