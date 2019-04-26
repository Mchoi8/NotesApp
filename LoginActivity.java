package com.example.matthew.notesapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity{

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes");

    }

    public void Login(View view) {
        EditText loginName = findViewById(R.id.Username);
        EditText passwordEntered = findViewById(R.id.password);
        String loginString = loginName.getText().toString();
        String passwordString = passwordEntered.getText().toString();

        // THE ONLY VALID USERNAME IS Admin AND THE ONLY VALID PASSWORD IS 1234  SINCE IT IS JUST AN ADMIN LOGIN
        if (loginString.equals("Admin") && passwordString.equals("1234"))
        {
            Toast.makeText(this, loginString + " successfully logged into the database! ", Toast.LENGTH_LONG).show();
            Random random = new Random();
            int NoteID = random.nextInt(90000) + 10000;
            String str_id = Integer.toString(NoteID);
            String LoginPwCombo = loginString + ": " + passwordString;
            myRef.child(str_id).setValue(LoginPwCombo);

        } else
        {
            Toast.makeText(this, "Sorry Invalid Username or Password! Try again", Toast.LENGTH_LONG).show();
        }

        loginName.setText("");
        passwordEntered.setText("");
    }

    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

