package com.example.databasetoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    EditText name,password;
    Button btnDelete, signout;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        signout = findViewById(R.id.signout);
        btnDelete = findViewById(R.id.btnDelete);
        DB = new DBHelper(this);

        btnDelete.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();
            String passwordTXT = password.getText().toString();

            if (nameTXT.equals("")||passwordTXT.equals(""))
                Toast.makeText(Delete.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            else {
                if(DB.checkusernamepassword(nameTXT,passwordTXT))
                {
                    Boolean checkdeletedata = DB.deletedata(nameTXT);
                    if (checkdeletedata)
                    {
                        Toast.makeText(Delete.this, "Account deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(Delete.this, "Account doesn't exist", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(Delete.this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
            }
        });
        signout.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}