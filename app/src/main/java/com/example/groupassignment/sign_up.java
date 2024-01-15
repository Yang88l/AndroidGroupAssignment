package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbhelpers.dbhelper_login_history;
import com.example.groupassignment.dbhelpers.dbhelper_user;
import com.example.groupassignment.dbmanagers.dbmanager_favourite;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_history;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class sign_up extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    public static boolean isChecked=false;
    private com.example.groupassignment.dbmanagers.dbmanager_favourite dbmanager_favourite;
    private com.example.groupassignment.dbmanagers.dbmanager_plan_history dbmanager_plan_history;
 private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //Top Navigation
        BaseActivity.setupToolbar(this);
////background.video(this);
//        bg = findViewById(R.id.background);
//
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.background;
//        Uri videoUri = Uri.parse(videoPath);
//        bg.setVideoURI(videoUri);
//
//        bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//                bg.start();
//            }
//        });

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

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor_all = dbmanager_user.fetchALL();
        boolean same_name = false;
        if (cursor_all != null && cursor_all.moveToFirst()) {
            do {
                if (name.equals(cursor_all.getString(1))) {
                    same_name = true;
                }
            } while (cursor_all.moveToNext());
        }
        cursor_all.close();
        dbmanager_user.close();
        main.updateVersion();

        if (name.equals("")||email.equals("")||phone.equals("")||birthday.equals("")||password.equals("")||password2.equals("")) {
            Toast.makeText(this, "Please fill in all the blank!", Toast.LENGTH_LONG).show();
        }
        else if (same_name) {
            Toast.makeText(this, "The name is used. Try another name.", Toast.LENGTH_SHORT).show();
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
                main.updateVersion();

                dbmanager_plan_history = new dbmanager_plan_history(this);
                dbmanager_plan_history.open();
                dbmanager_plan_history.insert(getUserID(), getLoginID(), "", "");
                dbmanager_plan_history.close();
                main.updateVersion();

                Toast.makeText(this, dbhelper_login_history.DB_VERSION + "" + dbhelper_user.DB_VERSION, Toast.LENGTH_SHORT).show();

                dbmanager_favourite = new dbmanager_favourite(this);
                dbmanager_favourite.open();
                dbmanager_favourite.insert(user_id, "hotel", 1, 0);
                dbmanager_favourite.insert(user_id, "hotel", 2, 0);
                dbmanager_favourite.insert(user_id, "hotel", 3, 0);
                dbmanager_favourite.insert(user_id, "play", 1, 0);
                dbmanager_favourite.insert(user_id, "play", 2, 0);
                dbmanager_favourite.insert(user_id, "play", 3, 0);
                dbmanager_favourite.insert(user_id, "food", 1, 0);
                dbmanager_favourite.insert(user_id, "food", 2, 0);
                dbmanager_favourite.insert(user_id, "food", 3, 0);
                dbmanager_favourite.close();
                main.updateVersion();

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
    public void notification(View view) { startActivity(new Intent(this, notification.class));}
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }

    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public void profile(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        String status=cursor.getString(3);
        cursor.close();
        dbmanager_login_history.close();
        main.updateVersion();
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        if (status.equals("logged out")) {
            Intent intent = new Intent(this, log_in.class);
            startActivity(intent);
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        }
    }

    public int getUserID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int user_id=Integer.parseInt(cursor_login.getString(1));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return user_id;
    }

    public int getLoginID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int login_id=Integer.parseInt(cursor_login.getString(0));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return login_id;
    }
//    @Override
//    protected void onResume() {
//        // Resume the video playback from the saved position
//        bg.seekTo(currentPosition);
//        bg.start();
//        super.onResume();
//    }
//    @Override
//    protected void onRestart() {
//        bg.start();
//        super.onRestart();
//    }
//    @Override
//    protected void onPause() {
//        // Save the current playback position
//        currentPosition = bg.getCurrentPosition();
//        // Pause the video playback
//        bg.pause();
//        super.onPause();
//    }
//    @Override
//    protected void onDestroy() {
//        bg.stopPlayback();
//        super.onDestroy();
//    }
//
//    @Override
//    public void onBackPressed() {
//        currentPosition = bg.getCurrentPosition();
//        bg.pause();
//        super.onBackPressed();
//    }
}