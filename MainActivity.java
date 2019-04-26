package com.example.matthew.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

     public void UserLogin( View view)
    {
        Intent intent = new Intent( this, LoginActivity.class);
        startActivity(intent);
    }

    public void NoteAdd( View view)
    {
        Intent intent = new Intent( this, AddNotes.class);
        startActivity(intent);
    }

    public void ViewAllNotes(View view)
    {
        Intent intent = new Intent(this, ViewNote.class);
        startActivity( intent );
    }

    public void NoteSearching(View view)
    {
        Intent intent = new Intent(this, SearchNotes.class);
        startActivity( intent );
    }

    public void NoteRemoval(View view )
    {
        Intent intent = new Intent(this, RemoveNotes.class);
        startActivity( intent);
    }

}
