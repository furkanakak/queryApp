package com.example.query;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.widget.EditText;
import android.widget.FrameLayout;


public class Query3Activity extends AppCompatActivity {
    EditText gun,ay,yil;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query3);

        gun = findViewById(R.id.Query3gun);
        ay = findViewById(R.id.Query3ay);
        yil = findViewById(R.id.Query3yil);

        MapsFragment fragment = new MapsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction = transaction.add(R.id.framelayout,fragment,"gdsfs");
        transaction.commit();






    }
}