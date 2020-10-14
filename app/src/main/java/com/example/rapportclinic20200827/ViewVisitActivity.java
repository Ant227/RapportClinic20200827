package com.example.rapportclinic20200827;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewVisitActivity extends AppCompatActivity {

    private TextView    patient_info_TextView,
                        visit_date_TextView,
                        historyTextView,
                        examinationTextView,
                        treatmentTextView;

    private Patient patient;
    private Visit visit;
    private Intent intent;
    private String visit_info;
    private AlertDialog.Builder builder;


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
        visit_info = intent.getStringExtra("visit_info");
        Log.d("Visit Info : ",visit_info);
        patient_info_TextView.setText(patient.getName() + "\t\t"+patient.getAge() + " years \t\t\t" + patient.getSex());
        visit_date_TextView.setText("Date : " + visit.getDate());
        historyTextView.setText(visit.getHistory());
        examinationTextView.setText(visit.getExamination());
        treatmentTextView.setText(visit.getTreatment());

    }

    public void deleteClicked(View view){


        builder = new AlertDialog.Builder(view.getContext());

        builder.setMessage("Do you want to delete "+visit_info+"?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int num = MyDataBaseHelper.getInstance().delete(visit);
                        Toast.makeText(view.getContext().getApplicationContext(),visit_info + "has been deleted",Toast.LENGTH_LONG).show();
                        finish();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });

        AlertDialog alert = builder.create();

        alert.setTitle("Warning");
        alert.show();





    }
}