package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.groupassignment.dbmanagers.dbmanager_book_history;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_history;
import com.example.groupassignment.dbmanagers.dbmanager_play_info;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRcode extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_book_history dbmanager_book_history;

    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    private ImageView qr;
private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //QR Code
        qr = findViewById(R.id.qr_code);

        dbmanager_book_history = new dbmanager_book_history(this);
        dbmanager_book_history.open();
        Cursor cursor = dbmanager_book_history.fetch(getUserID());
        cursor.moveToLast();
        cursor.moveToPrevious();
        name = cursor.getString(2);
        cursor.close();
        dbmanager_book_history.close();
        main.updateVersion();

        Intent intent = getIntent();
        double price = intent.getDoubleExtra("price", 0);
        String text = "\tReceipt\n" + "Price: " + String.format("RM%.2f", price) + "\nBooking: " + name;
        MultiFormatWriter writer = new MultiFormatWriter();

        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE,600,600);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    public void finish(View view) {
        startActivity(new Intent(this,main.class));
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

}