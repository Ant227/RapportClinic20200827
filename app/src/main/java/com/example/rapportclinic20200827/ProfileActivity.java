package com.example.rapportclinic20200827;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
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

            patient = (Patient) intent.getSerializableExtra("patient");

            if(patient != null) {

                visits = myDb.readVisits(patient);
            }
            if (visits != null) {

                visitcustomadapter = new VisitCustomAdapter(this, patient, visits);
                vist_recycler_view.setAdapter(visitcustomadapter);
            }
            nameTv.setText(patient.getName());
            ageGenderTv.setText(patient.getAge() + "Y0, "+patient.getSex());
        };








    }



    public void onResume(){
        super.onResume();

        if(patient != null) {

            visits = myDb.readVisits(patient);
        }
        if (visits != null) {

            visitcustomadapter = new VisitCustomAdapter(this, patient, visits);
            vist_recycler_view.setAdapter(visitcustomadapter);
        }
        nameTv.setText(patient.getName());
        ageGenderTv.setText(patient.getAge() + "Y0, "+patient.getSex());
    }



    public void profileAddNewVisitor(View view){

        Intent intent = new Intent(ProfileActivity.this,VisitActivity.class);
        intent.putExtra("patient",patient);

        startActivity(intent);
    }
}
