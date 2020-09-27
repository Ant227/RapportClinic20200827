package com.example.rapportclinic20200827;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewVisitActivity extends AppCompatActivity {

    private TextView    patient_info_TextView,
                        visit_date_TextView,
                        historyTextView,
                        examinationTextView,
                        treatmentTextView;

    private Patient patient;
    private Visit visit;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visit);

        patient_info_TextView = findViewById(R.id.patient_info);
        visit_date_TextView = findViewById(R.id.visit_date_TextView);
        historyTextView = findViewById(R.id.historyTextView);
        examinationTextView = findViewById(R.id.examinationTextView);
        treatmentTextView = findViewById(R.id.treatmentTextView);


        intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");
        visit = (Visit) intent.getSerializableExtra("visit");

        patient_info_TextView.setText(patient.getName() + "\t\t"+patient.getAge() + " years \t\t\t" + patient.getSex());
        visit_date_TextView.setText("Date : " + visit.getDate());
        historyTextView.setText(visit.getHistory());
        examinationTextView.setText(visit.getExamination());
        treatmentTextView.setText(visit.getTreatment());

    }
}