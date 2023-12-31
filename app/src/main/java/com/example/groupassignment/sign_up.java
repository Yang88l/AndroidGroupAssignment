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
    public static boolean isChecked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        EditText editTextName = findViewById(R.id.editTextText);
        EditText editTextEmail = findViewById(R.id.editTextText2);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextBirthday = findViewById(R.id.editTextDate);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        EditText editTextPassword2 = findViewById(R.id.editTextTextPassword2);

        Intent intent = getIntent();
        if(intent.getStringExtra("name")!=null)
            editTextName.setText(intent.getStringExtra("name"));
        if(intent.getStringExtra("email")!=null)
            editTextEmail.setText(intent.getStringExtra("email"));
        if(intent.getStringExtra("phone")!=null)
            editTextPhone.setText(intent.getStringExtra("phone"));
        if(intent.getStringExtra("birthday")!=null)
            editTextBirthday.setText(intent.getStringExtra("birthday"));
        if(intent.getStringExtra("password")!=null)
            editTextPassword.setText(intent.getStringExtra("password"));
        if(intent.getStringExtra("password2")!=null)
            editTextPassword2.setText(intent.getStringExtra("password2"));
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
            if (isChecked) {
                dbmanager_user = new dbmanager_user(this);
                dbmanager_user.open();
                dbmanager_user.insert(name, email, phone, birthday, password, "null");
                Cursor cursor = dbmanager_user.fetchByName(name);
                int user_id = Integer.parseInt(cursor.getString(0));
                cursor.close();
                dbmanager_user.close();
                main.updateVersion();
                Toast.makeText(this, dbhelper_login_history.DB_VERSION + "" + dbhelper_user.DB_VERSION, Toast.LENGTH_SHORT).show();
                dbmanager_login_history = new dbmanager_login_history(this);
                dbmanager_login_history.open();
                dbmanager_login_history.insert(user_id, "logged in", "null");
                dbmanager_login_history.close();
                Toast.makeText(this, dbhelper_login_history.DB_VERSION + "" + dbhelper_user.DB_VERSION, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, set_picture.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "You need to read Terms and Conditions.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "The password is not matched", Toast.LENGTH_SHORT).show();
        }
    }

    public void term(View view) {
        Intent intent = new Intent (this, terms_conditions.class);

        EditText editTextName = findViewById(R.id.editTextText);
        EditText editTextEmail = findViewById(R.id.editTextText2);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextBirthday = findViewById(R.id.editTextDate);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        EditText editTextPassword2 = findViewById(R.id.editTextTextPassword2);

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String birthday = editTextBirthday.getText().toString();
        String password = editTextPassword.getText().toString();
        String password2 = editTextPassword2.getText().toString();

        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("phone", phone);
        intent.putExtra("birthday", birthday);
        intent.putExtra("password", password);
        intent.putExtra("password2", password2);

        startActivity(intent);
    }

}