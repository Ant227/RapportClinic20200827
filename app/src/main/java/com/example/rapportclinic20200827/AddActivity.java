package com.example.rapportclinic20200827;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    private EditText name,age,gender;

    private MyDataBaseHelper myDb;
    private Patient patient ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.add_name);
        age = findViewById(R.id.add_age);
        gender = findViewById(R.id.add_gender);

        myDb = MyDataBaseHelper.getInstance(this);
    }

    public void addData(View view){
        String nameS, ageS, genderS;

        nameS = name.getText().toString();
        ageS = age.getText().toString();
        genderS = gender.getText().toString();


        if (TextUtils.isEmpty(nameS) ||
                TextUtils.isEmpty(ageS) ||
                TextUtils.isEmpty(genderS)) {
            Toast.makeText(this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDateString = dateformat.format(c);




            patient = new Patient(null,nameS,new Integer(ageS),genderS,formattedDateString);

            myDb.insert(patient);
            patient = myDb.getLastPatient();




            name.setText("");
            age.setText("");
            gender.setText("");


            Intent intent = new Intent(AddActivity.this,VisitActivity.class);



            intent.putExtra("patient",patient);

            startActivity(intent);
            finish();

        }
    }

    public void addNewVisitor(View view){
        //Intent intent = new Intent(AddActivity.this,Form2.class);
        //startActivity(intent);
    }

}
