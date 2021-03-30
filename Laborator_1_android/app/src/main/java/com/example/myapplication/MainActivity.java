package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickTest(View view){
        EditText editText =  findViewById(R.id.editText);//caut dupa id edit textul si textul meu
        TextView textView= findViewById(R.id.textView);
        textView.setText(editText.getText());//setez textu din edit test
        //ca sa rulam functia asta trebuie sa o adaugam in activity xml la button -onClick
    }
}