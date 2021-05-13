package com.example.query;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.query.Model.Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class Query1Activity extends AppCompatActivity {
    ArrayList<Model> modellist= new ArrayList<>();
    ArrayList<Model> list= new ArrayList<>();
    TextView gun1,gun2,gun3,gun4,gun5,mesafe1,mesafe2,mesafe3,mesafe4,mesafe5;
    Button buttonnext,buttonback,buttonshow;
    TableLayout tableLayout;
    Boolean aBoolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aBoolean=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query1);
        tableLayout = findViewById(R.id.tableLayout);
        gun1 = findViewById(R.id.gun1);
        gun2 = findViewById(R.id.gun2);
        gun3 = findViewById(R.id.gun3);
        gun4 = findViewById(R.id.gun4);
        gun5 = findViewById(R.id.gun5);
        mesafe1 = findViewById(R.id.mesafe1);
        mesafe2 = findViewById(R.id.mesafe2);
        mesafe3 = findViewById(R.id.mesafe3);
        mesafe4 = findViewById(R.id.mesafe4);
        mesafe5 = findViewById(R.id.mesafe5);
        buttonback = findViewById(R.id.backButton);
        buttonnext = findViewById(R.id.nextButton);
        buttonshow = findViewById(R.id.showButton);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Model model = snapshot1.getValue(Model.class);
                    modellist.add(model);
                }

                Collections.sort(modellist,new Mesafe_Siralama());

                gun1.setText(modellist.get(0).getTpep_pickup_datetime());
                gun2.setText(modellist.get(1).getTpep_pickup_datetime());
                gun3.setText(modellist.get(2).getTpep_pickup_datetime());
                gun4.setText(modellist.get(3).getTpep_pickup_datetime());
                gun5.setText(modellist.get(4).getTpep_pickup_datetime());

                mesafe1.setText(modellist.get(0).getTrip_distance()+"");
                mesafe2.setText(modellist.get(1).getTrip_distance()+"");
                mesafe3.setText(modellist.get(2).getTrip_distance()+"");
                mesafe4.setText(modellist.get(3).getTrip_distance()+"");
                mesafe5.setText(modellist.get(4).getTrip_distance()+"");




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aBoolean=!aBoolean;
                if (aBoolean==true)
                {
                    tableLayout.setVisibility(View.VISIBLE);
                    buttonshow.setText("SONUCU GIZLE");
                }
                else
                {
                    tableLayout.setVisibility(View.INVISIBLE);
                    buttonshow.setText("SONUCU LISTELE");
                }
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Query1Activity.this,Query2Activity.class);
                startActivity(intent);
            }
        });






    }



    class Mesafe_Siralama implements Comparator<Model> {

        // Function to compare
        public int compare(Model v1, Model v2)
        {
            if (v1.getTrip_distance() == v2.getTrip_distance())
                return 0;
            else if (v1.getTrip_distance() > v2.getTrip_distance())
                return -1;
            else
                return 1;
        }
    }

}
