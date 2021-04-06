package com.example.laborator2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        Intent intent=getIntent();
        String message=intent.getStringExtra("Test");

        Log.d("mesaj",message);
        Log.d("mesaj","aparent e null sad face");
        Toast toast=Toast.makeText(this,message,Toast.LENGTH_LONG);
        toast.show();

    }
}
