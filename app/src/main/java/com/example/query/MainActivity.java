package com.example.query;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mReferance;
    Button query1,query2,query3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        query1 = findViewById(R.id.Query1);
        query1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Query1Activity.class);
                startActivity(intent);

            }
        });
        query2 = findViewById(R.id.Query2);
        query2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Query2Activity.class);
                startActivity(intent);
            }
        });

        query3 = findViewById(R.id.Query3);
        query3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Query3Activity.class);
                startActivity(intent);
            }
        });


    }




}


