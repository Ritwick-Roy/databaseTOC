package com.example.databasetoc;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    EditText name,password;
    Button view,signout;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        view = findViewById(R.id.view);
        signout = findViewById(R.id.signout);

        DB = new DBHelper(this);

        view.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();
            String passwordTXT = password.getText().toString();
            if (nameTXT.equals("")||passwordTXT.equals(""))
                Toast.makeText(ViewActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            else if(DB.checkusernamepassword(nameTXT,passwordTXT)){
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(ViewActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()) {
                    if(nameTXT.equals(res.getString(0)))
                    {
                        buffer.append("Name :").append(res.getString(0)).append("\n");
                        buffer.append("Email :").append(res.getString(2)).append("\n");
                        buffer.append("Roll number :").append(res.getString(3)).append("\n");
                        buffer.append("Batch :").append(res.getString(4)).append("\n\n");
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
            else Toast.makeText(ViewActivity.this, "Invalid id/password", Toast.LENGTH_SHORT).show();
        });
        signout.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}