package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    public void sign_up(View view) {
        String name = ((EditText) findViewById(R.id.editTextText)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextText2)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        String birthday = ((EditText) findViewById(R.id.editTextDate)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();

        if (name.equals("")||email.equals("")||phone.equals("")||birthday.equals("")||password.equals("")||password2.equals("")) {
            Toast.makeText(this, "Please fill in all the blank!", Toast.LENGTH_LONG).show();
        }
        else if (password.equals(password2)) {
            dbhelper_user.DB_VERSION = main.dbversion++;
            dbmanager_user = new dbmanager_user(this);
            dbmanager_user.open();
            dbmanager_user.insert(name, email, phone, birthday, password, "null");
            Cursor cursor = dbmanager_user.fetchByName(name);
            cursor.moveToLast();
            int user_id=Integer.parseInt(cursor.getString(0));
            Toast.makeText(this, user_id+"", Toast.LENGTH_SHORT).show();
            cursor.close();
            dbmanager_user.close();

            dbhelper_login_history.DB_VERSION = main.dbversion++;
            dbmanager_login_history = new dbmanager_login_history(this);
            dbmanager_login_history.open();
            dbmanager_login_history.insert(user_id, "logged in", "null");
            dbmanager_login_history.close();

            Intent intent = new Intent (this, profile.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "The password is not matched", Toast.LENGTH_SHORT).show();
        }
    }

    public void term(View view) {
        Intent intent = new Intent (this, terms_conditions.class);
        startActivity(intent);
    }
}