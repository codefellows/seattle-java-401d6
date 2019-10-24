package com.ferreirae.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onSaveButtonPressed(View view) {
        System.out.println("button pressed");

        // save whatever the user typed into SharedPreferences

        // grab what the user typed in
        EditText nameEditText = findViewById(R.id.nameEditText);
        String name = nameEditText.getText().toString();
        // grab the SharedPreference in which to save the data
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // save the data
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.apply();
    }
}
