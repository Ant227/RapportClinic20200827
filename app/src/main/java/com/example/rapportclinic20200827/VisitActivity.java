package com.example.rapportclinic20200827;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class VisitActivity extends AppCompatActivity {

    private Button myBt[];

    private LinearLayout button_container;

    private  MyDataBaseHelper myDb;
    private Visit visit;
    private String history, examination, treatment;

    private Patient patient;
    private Intent intent;

    private Button historyButton, examinationButton, treatmentButton, saveButton;
    private EditText historyText, examinationText, treatmentText;
    private TextView patient_info;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        historyButton = findViewById(R.id.historyButton);
        examinationButton = findViewById(R.id.examinationButton);
        treatmentButton = findViewById(R.id.treatmentButton);
        saveButton = findViewById(R.id.saveButton);
        historyText = findViewById(R.id.historyText);
        examinationText = findViewById(R.id.examinationText);
        treatmentText = findViewById(R.id.treatmentText);



        patient_info = findViewById(R.id.patient_info);

        intent = getIntent();
        myDb =  MyDataBaseHelper.getInstance(this);

        patient = (Patient) intent.getSerializableExtra("patient");

        patient_info.setText(patient.getName() +"\t\t"+patient.getAge()+ " years");

        myBt = new Button[2];
        myBt [0]= new Button(this);
        myBt[0].setText("Cough x 3 days");
        myBt[1] = new Button(this);
        myBt[1].setText("Fever x 1 day");
        history = "hello world!";


        button_container = findViewById(R.id.button_container);


        //button_container.addView(myBt[0]);
        button_container.addView(myBt[0]);
        button_container.addView(myBt[1]);


        historyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //historyText.setText(history);
                historyText.setVisibility(View.VISIBLE);
                examinationText.setVisibility(View.GONE);
                treatmentText.setVisibility(View.GONE);
            }
        });

        examinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examinationText.setVisibility(View.VISIBLE);
                historyText.setVisibility(View.GONE);
                treatmentText.setVisibility(View.GONE);
            }
        });

        treatmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                treatmentText.setVisibility(View.VISIBLE);
                historyText.setVisibility(View.GONE);
                examinationText.setVisibility(View.GONE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDateString = dateformat.format(c);

                visit = new Visit(
                        null,
                        patient.getID(),
                        formattedDateString,
                        historyText.getText().toString(),
                        examinationText.getText().toString(),
                        treatmentText.getText().toString()
                );

                myDb.addVisit(visit);




                //Intent myIntent = new Intent(VisitActivity.this,ProfileActivity.class);


                //myIntent.putExtra("patient",patient);

                //startActivity(myIntent);
                finish();

            }
        });



    }



}