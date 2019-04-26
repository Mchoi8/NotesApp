package com.example.matthew.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchNotes extends AppCompatActivity {


    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    // Local data structure that will store all the values from the database
    private ArrayList<Note> NotesList;
    private ArrayList<Note> searchResults;

    // ArrayAdapter allows the results to be displayed in a list on the app
    private NoteAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_notes);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");

        // Initializes the local data structure to store the database
        NotesList = new ArrayList<Note>();

        // Set up an array that will have the contents that you want to display
        searchResults = new ArrayList<Note>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener(){
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Adds the Contact to the local data structure
                NotesList.add(dataSnapshot.getValue(Note.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
        listAdapter = new NoteAdapter( this, searchResults);
        ListView results = findViewById(R.id.listViewResults);
        //results.setAdapter(listAdapter);
    }

    public void search(View view)
    {
        int count = 0;
        listAdapter.clear();    // clears any content
        EditText search_text = (EditText) findViewById(R.id.editTextSearchTitle);
        String search_string = search_text.getText().toString();  // Extracts name from EditText


        for (Note note_obj : NotesList)
        {
            if (search_string.equals(note_obj.getTitle()))
            {
                Toast.makeText(this, search_string + " is in the list of Notes!", Toast.LENGTH_LONG).show();
            } else
            {
                count++;
            }
        }
        if (count == NotesList.size())
        {
            Toast.makeText(this, "Sorry, " + search_string + " not found: Please Try Again", Toast.LENGTH_LONG).show();
        }

        search_text.setText("");

    }

    public void menu( View view )
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity( intent);
    }
}
