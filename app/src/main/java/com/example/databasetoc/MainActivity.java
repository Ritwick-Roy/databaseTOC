package com.example.databasetoc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    Button insert,btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        insert = findViewById(R.id.btnInsert);
        btnlogin = findViewById(R.id.btnlogin);
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(view -> {
            String user=name.getText().toString();
            String pass=password.getText().toString();

            if(user.equals("")||pass.equals(""))
                Toast.makeText(MainActivity.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
            else
            {
                Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                if(checkuserpass)
                {

                    Toast.makeText(MainActivity.this,"Sign in successful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"Invalid username/password",Toast.LENGTH_SHORT).show();
            }
        });
        insert.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
            startActivity(intent);
        });
    }
}