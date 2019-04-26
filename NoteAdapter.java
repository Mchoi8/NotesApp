package com.example.matthew.notesapp;


import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {
    private Context mContext;
    private List<Note> NotesList = new ArrayList<Note>();

    public NoteAdapter( Context context, ArrayList<Note> list)
    {
        super( context, 0, list);
        mContext = context;
        NotesList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        // Associates the list with the XML Layout file "contact_view"
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.content_scrolling2,parent,false);

        // Individually handles each Contact in the contactList
        Note currentNote = NotesList.get(position);

        // Extracts the name of the Contact
        TextView name = (TextView) listItem.findViewById(R.id.textViewTitle);
        name.setText(currentNote.getTitle());

        // Extracts the phone number of the Contact --------- the content of the notes
        TextView note_text = (TextView) listItem.findViewById(R.id.textViewNotes);
        note_text.setText(currentNote.getIdNum());

        return listItem;
    }
}
