package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText emailField;
    SharedPreferences sp;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = (EditText)findViewById(R.id.Lab3editText2);
        sp = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String savedString = sp.getString("ReserveName", "Default value");

        emailField.setHint(savedString);

        loginBtn = (Button)findViewById(R.id.Lab3LoginBtn);
        loginBtn.setOnClickListener( c -> {

            Intent profilePage = new Intent(MainActivity.this, ProfileActivity.class);
            //Give directions to go from this page, to SecondActivity
            EditText et = (EditText)findViewById(R.id.Lab3editText2);

            profilePage.putExtra("emailTyped", et.getText().toString());

            //Now make the transition:
            startActivityForResult( profilePage, 345);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //get an editor object
        SharedPreferences.Editor editor = sp.edit();

        //save what was typed under the name "ReserveName"
        String whatWasTyped = emailField.getText().toString();
        editor.putString("ReserveName", whatWasTyped);

        //write it to disk:
        editor.commit();
    }
}