package com.example.databasetoc;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText name, password, email, rno, batch;
    Button update, signout;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        rno = findViewById(R.id.rno);
        batch = findViewById(R.id.batch);
        update = findViewById(R.id.update);
        signout = findViewById(R.id.signout);
        DB = new DBHelper(this);
        update.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();
            String passwordTXT = password.getText().toString();
            String emailTXT = email.getText().toString();
            String rnoTXT = rno.getText().toString();
            String batchTXT = batch.getText().toString();
            if (nameTXT.equals("")||passwordTXT.equals("")||emailTXT.equals("")||rnoTXT.equals("")||batchTXT.equals(""))
                Toast.makeText(UpdateActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkupdatedata = DB.updateuserdata(nameTXT, passwordTXT, emailTXT, rnoTXT, batchTXT);
                if (checkupdatedata) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(UpdateActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(UpdateActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        signout.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}