package com.example.databasetoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText name, password, email, rno, batch;
    Button insert,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        rno = findViewById(R.id.rno);
        batch = findViewById(R.id.batch);
        insert = findViewById(R.id.btnInsert);
        signin= findViewById(R.id.signin);
        DB = new DBHelper(this);
        insert.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();
            String passwordTXT = password.getText().toString();
            String emailTXT = email.getText().toString();
            String rnoTXT = rno.getText().toString();
            String batchTXT = batch.getText().toString();
            if (nameTXT.equals("") || passwordTXT.equals("") || emailTXT.equals("") || rnoTXT.equals("") || batchTXT.equals(""))
                Toast.makeText(SignUpActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            else if(! DB.checkusername(nameTXT)){
                Boolean checkinsertdata = DB.insertuserdata(nameTXT, passwordTXT, emailTXT, rnoTXT, batchTXT);
                if (checkinsertdata) {
                    Toast.makeText(SignUpActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(SignUpActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(SignUpActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
        });
        signin.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}