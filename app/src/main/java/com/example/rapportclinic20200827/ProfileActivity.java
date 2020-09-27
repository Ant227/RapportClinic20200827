package com.example.rapportclinic20200827;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rapportclinic20200827.Adaper.VisitCustomAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTv,ageGenderTv, sulphurTv;
    private Intent intent;
    private Patient patient = null;
    private MyDataBaseHelper myDb;
    private RecyclerView vist_recycler_view;
    private VisitCustomAdapter visitcustomadapter;
    private ArrayList<Visit> visits = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDb = MyDataBaseHelper.getInstance(this);


        intent = getIntent();


        nameTv = findViewById(R.id.profile_name);
        ageGenderTv =findViewById(R.id.profile_age);
        vist_recycler_view = findViewById(R.id.visit_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        vist_recycler_view.setLayoutManager(linearLayoutManager);
        vist_recycler_view.setHasFixedSize(true);
        vist_recycler_view.setNestedScrollingEnabled(false);




        if(intent.hasExtra("patient")) {
            Log.d("before cast","before cast");
            patient = (Patient) intent.getSerializableExtra("patient");
            Log.d("Patient Name ", "Patient Name");
            Log.d("Patient Name",patient.getName());
            if(patient != null) {
                System.out.println("Patient is not null");
                System.out.println(patient.toString());
                visits = myDb.readVisits(patient);
            }
            if (visits != null) {
                System.out.println("Visit is not null");
                System.out.println(visits.toString());
                System.out.println("Before visitcustomadaper");
                visitcustomadapter = new VisitCustomAdapter(this, patient, visits);


                System.out.println("Before visit recycler");
                vist_recycler_view.setAdapter(visitcustomadapter);
            }
            nameTv.setText(patient.getName());
            ageGenderTv.setText(patient.getAge() + "Y0, "+patient.getSex());
        }
        ;



    }

    public void profileAddNewVisitor(View view){
        //Intent intent = new Intent(ProfileActivity.this,Form2.class);
        Intent intent = new Intent(ProfileActivity.this,VisitActivity.class);
        intent.putExtra("patient",patient);

        startActivity(intent);
    }
}
