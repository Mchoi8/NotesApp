package com.example.matthew.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RemoveNotes extends AppCompatActivity {

    // Google Firebase Database Reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<Note> NotesList;

    private ArrayList<Note> searchResults;

    private NoteAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_notes);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");

        // Initializes the local data structures
        NotesList = new ArrayList<Note>();
        searchResults = new ArrayList<Note>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener() {
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                NotesList.add(dataSnapshot.getValue(Note.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        myRef.addChildEventListener(childEventListener);

        // Sets up the list view/list adapter to read from the search results array
        listAdapter = new NoteAdapter(this, searchResults);

        //Associates the ListView to the adapter
        ListView results = findViewById(R.id.listViewResults);
    }

    // Upon button click of "Remove"
    public void remove(View view) {
        int count = 0;
        listAdapter.clear();    // clears any content
        EditText remove_text = (EditText) findViewById(R.id.editTextRemovalTitle);
        String string_text = remove_text.getText().toString();  // Extracts name from EditText


        for (Note note_obj : NotesList)
        {
            if (string_text.equals(note_obj.getTitle()))
            {
                NotesList.remove(note_obj.getTitle());
                myRef.child(note_obj.getIdNum()).removeValue();
                Toast.makeText(this, string_text + " successfully removed", Toast.LENGTH_LONG).show();
            } else
            {
                count++;
            }
        }
        if (count == NotesList.size())
        {
            Toast.makeText(this, "Sorry, " + string_text + " not found: Please Try Again", Toast.LENGTH_LONG).show();
        }

        remove_text.setText("");
    }


    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
