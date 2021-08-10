package com.example.databasetoc;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    Button update, delete, view, signout;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        signout = findViewById(R.id.signout);

        DB = new DBHelper(this);

        update.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),UpdateActivity.class);
            startActivity(intent);
        });

        delete.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),Delete.class);
            startActivity(intent);
        });
        view.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),ViewActivity.class);
            startActivity(intent);
        });
        signout.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}