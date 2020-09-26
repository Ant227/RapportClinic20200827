package com.example.rapportclinic20200827;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTv,ageGenderTv, sulphurTv;
    private Intent getIntent;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        getIntent = getIntent();


        nameTv = findViewById(R.id.profile_name);
        ageGenderTv =findViewById(R.id.profile_age);



        if(getIntent.hasExtra("patient")) {
            patient = (Patient) getIntent.getSerializableExtra("patient");

            nameTv.setText(patient.getName());
            ageGenderTv.setText(patient.getAge() + "Y0, "+patient.getSex());
        }




    }

    public void profileAddNewVisitor(View view){
        //Intent intent = new Intent(ProfileActivity.this,Form2.class);
        Intent intent = new Intent(ProfileActivity.this,VisitActivity.class);
        startActivity(intent);
    }
}
