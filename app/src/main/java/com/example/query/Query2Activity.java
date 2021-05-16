package com.example.query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.query.Model.Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Query2Activity extends AppCompatActivity {
    private ArrayList<Model> modellist= new ArrayList<>();
    private ArrayList<Model> filteredModellist= new ArrayList<>();
    DatePickerDialog.OnDateSetListener setListenerup;
    DatePickerDialog.OnDateSetListener setListeneroff;
    Button btnup,btnoff,buttonshow,buttonnext,buttonback;
    String dateoff,dateup;
    TextView gun1,gun2,gun3,gun4,gun5,mesafe1,mesafe2,mesafe3,mesafe4,mesafe5;
    TableLayout tableLayout;
    Boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query2);
        aBoolean=false;

        btnup = findViewById(R.id.btnup);
        btnoff = findViewById(R.id.btnoff);
        buttonshow = findViewById(R.id.showButton);

        tableLayout = findViewById(R.id.tableLayoutQuery2);
        gun1 = findViewById(R.id.gun1Query2);
        gun2 = findViewById(R.id.gun2Query2);
        gun3 = findViewById(R.id.gun3Query2);
        gun4 = findViewById(R.id.gun4Query2);
        gun5 = findViewById(R.id.gun5Query2);
        mesafe1 = findViewById(R.id.mesafe1Query2);
        mesafe2 = findViewById(R.id.mesafe2Query2);
        mesafe3 = findViewById(R.id.mesafe3Query2);
        mesafe4 = findViewById(R.id.mesafe4Query2);
        mesafe5 = findViewById(R.id.mesafe5Query2);
        buttonnext = findViewById(R.id.nextButtonQuery2);
        buttonback = findViewById(R.id.backButtonQuery2);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialogon = new DatePickerDialog(
                        Query2Activity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        dateup = year+"/"+month+"/"+dayOfMonth;
                        if (month<10)
                        {
                            dateup = year+"/"+"0"+month+"/"+dayOfMonth;
                        }


                    }
                },year,month,day);
                    datePickerDialogon.getDatePicker().setMaxDate(System.currentTimeMillis());
                    datePickerDialogon.show();
            }
        });

        btnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialogoff = new DatePickerDialog(
                        Query2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        dateoff = year+"/"+month+"/"+dayOfMonth;
                        if(month<10)
                        {
                            dateoff = year+"/"+"0"+month+"/"+dayOfMonth;
                        }


                    }
                },year,month,day);
                datePickerDialogoff.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialogoff.show();



            }

        });
        buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                come();
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
                Intent intent = new Intent(Query2Activity.this,Query3Activity.class);
                startActivity(intent);
            }
        });


        }

        void come()
        {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            reference.orderByChild("trip_distance").addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren())
                    {
                        Model model = snapshot1.getValue(Model.class);
                        modellist.add(model);
                       int b = StringToDate(model.getTpep_pickup_datetime(),model.getTpep_dropoff_datetime(),dateup,dateoff);
                       if (b==1)
                       {
                         filteredModellist.add(model);
                       }
                    }
                    gun1.setText(filteredModellist.get(0).getTpep_pickup_datetime());
                    gun2.setText(filteredModellist.get(1).getTpep_pickup_datetime());
                    gun3.setText(filteredModellist.get(2).getTpep_pickup_datetime());
                    gun4.setText(filteredModellist.get(3).getTpep_pickup_datetime());
                    gun5.setText(filteredModellist.get(4).getTpep_pickup_datetime());

                    mesafe1.setText(filteredModellist.get(0).getTrip_distance()+"");
                    mesafe2.setText(filteredModellist.get(1).getTrip_distance()+"");
                    mesafe3.setText(filteredModellist.get(2).getTrip_distance()+"");
                    mesafe4.setText(filteredModellist.get(3).getTrip_distance()+"");
                    mesafe5.setText(filteredModellist.get(4).getTrip_distance()+"");
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });

        }

    public static int StringToDate(String datestringup,String datestringoff,String dateup,String dateoff) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        SimpleDateFormat targetFormat  = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date datemodelup = originalFormat.parse(datestringup);
            String modelDateTimeup =  targetFormat.format(datemodelup);

            Date datemodeloff = originalFormat.parse(datestringoff);
            String modelDateTimeoff =  targetFormat.format(datemodeloff);



            //System.out.println(dateup);
            if (modelDateTimeup.compareTo(dateup) >= 0 &&  modelDateTimeoff.compareTo(dateoff) <= 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }


        } catch (ParseException ex) {
            System.out.println("hata");
            return 100;
        }

    }

    }
