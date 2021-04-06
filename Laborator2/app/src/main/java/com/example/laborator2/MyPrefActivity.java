package com.example.laborator2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyPrefActivity extends PreferenceActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pref);
        getPreferenceManager().setSharedPreferencesName("my_pref");
        addPreferencesFromResource(R.xml.my_pref);
//        SharedPreferences pref = getSharedPreferences("my_pref", 0);
//        SharedPreferences.Editor edit = pref.edit();
//        TextView textView=findViewById(R.id.editTextTextEmailAddress);
//        textView.setText(pref.getInt("my_value_2",123));
    }
}
