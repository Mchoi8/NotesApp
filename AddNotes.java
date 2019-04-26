package com.example.matthew.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;
public class AddNotes extends AppCompatActivity {
    // Google Firebase Database References
    private DatabaseReference myRef;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");
    }

    public void NoteAdd(View view) {
        EditText editName = findViewById(R.id.editTextTitle);
        String title = editName.getText().toString();
        EditText text = findViewById(R.id.editTextBox);
        String text_box = text.getText().toString();

        if (title.length() == 0)
        {
            Toast.makeText(this, "Try again: Area Left Blank ", Toast.LENGTH_LONG).show();

        } else
        {
            Random random = new Random();
            int NoteID = random.nextInt(90000) + 10000;
            String str_id = Integer.toString(NoteID);
            Note note = new Note(title, str_id, text_box);
            myRef.child(str_id).setValue(note);
            Toast.makeText(this, note.getTitle() + " successfully added and saved", Toast.LENGTH_LONG).show();

        }

        // Resets fields
        editName.setText("");
        text.setText("");
    }

    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

