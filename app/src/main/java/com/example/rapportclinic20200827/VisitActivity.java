package com.example.rapportclinic20200827;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private int defaultColor,hightlightColor;

    private String examSuggest [] = {"Blood Pressure - ",
                                        "90/60 mmHg",
                                        "100/70 mmHg",
                                        "110/70 mmHg",
                                        "120/80 mmHg",
                                        "130/90 mmHg",
                                        "140/90 mmHg",
            "SpO2 - ",
            "99 % on air ",
            "97 % on air ",
            "95 % on air",
            "93 % on air "
    };

    private CheckBox  []symptomCheckBoxs;


    private String [] sympotms = {"Fever", "Cough", "LOA", "LOW","Murmur"};

    private Button examButtons[];
    private symptomClickListener listener;
    private SymptomTouchListener symptomTouchListener;
    private GestureDetectorCompat mDetector;

    class SymptomTouchListener extends GestureDetector.SimpleOnGestureListener{

        View view;

        public SymptomTouchListener(View view){
            this.view=view;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("e1 :",e1.toString());
            Log.d("e2 : ", e2.toString());
            Log.d("velocity X : ",velocityX+"");
            Log.d("velocity Y : ", velocityY + "");
            Log.d("View : ",view.toString());

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }


    private void createButtons(ViewGroup viewgroup){
        examButtons = new Button[examSuggest.length];
        for(int i = 0; i< examSuggest.length; i++){
            examButtons[i] = new Button(this);
            examButtons[i].setText(examSuggest[i]);
            viewgroup.addView(examButtons[i]);
        }
    }

    private void createSymptomCheckBoxs(ViewGroup viewgroup){
        listener = new symptomClickListener();

        symptomCheckBoxs = new CheckBox[sympotms.length];

        for(int i = 0; i<sympotms.length; i++){
            symptomCheckBoxs[i]=new CheckBox(this);
            symptomCheckBoxs[i].setText(sympotms[i]);
            viewgroup.addView(symptomCheckBoxs[i]);
            symptomCheckBoxs[i].setOnClickListener(listener);


        }
    }

    private class symptomClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            onCheckBoxClicked(view);
        }

        public void onCheckBoxClicked(View view){

            CheckBox symptomCheckBox = (CheckBox) view;
            String symptom = symptomCheckBox.getText().toString();
            boolean checked = symptomCheckBox.isChecked();
            history = historyText.getText().toString();
            if(checked) {

                history = history.replace("\n" + symptom + " (+) \n", "");
                history = history.replace("\n" + symptom + " (-) \n", "");
                history = history + "\n" + symptom + " (+) \n";
                history.trim();
                historyText.setText(history);
            }
            else {
                history = history.replace(symptom + " (+)",symptom + " (-)");
                history.trim();
                historyText.setText(history);
            }
        }
    }



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

        defaultColor = historyButton.getCurrentTextColor();
        hightlightColor = Color.RED;










        historyButton.setTextColor(hightlightColor);

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


        //button_container = findViewById(R.id.button_container);


        //button_container.addView(myBt[0]);
        //button_container.addView(myBt[0]);
        //button_container.addView(myBt[1]);

        LinearLayout test = findViewById(R.id.test_relative_layout);

        createSymptomCheckBoxs(test);

        //createButtons(test);

        //RelativeLayout layout = new RelativeLayout(this);

        //layout.setLayoutMode(RelativeLayout.ALIGN_LEFT);


        historyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //historyText.setText(history);
                historyText.setVisibility(View.VISIBLE);
                examinationText.setVisibility(View.GONE);
                treatmentText.setVisibility(View.GONE);

                historyButton.setTextColor(hightlightColor);
                examinationButton.setTextColor(defaultColor);
                treatmentButton.setTextColor(defaultColor);
            }
        });

        examinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examinationText.setVisibility(View.VISIBLE);
                historyText.setVisibility(View.GONE);
                treatmentText.setVisibility(View.GONE);

                historyButton.setTextColor(defaultColor);
                examinationButton.setTextColor(hightlightColor);
                treatmentButton.setTextColor(defaultColor);
            }
        });

        treatmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                treatmentText.setVisibility(View.VISIBLE);
                historyText.setVisibility(View.GONE);
                examinationText.setVisibility(View.GONE);

                historyButton.setTextColor(defaultColor);
                examinationButton.setTextColor(defaultColor);
                treatmentButton.setTextColor(hightlightColor);
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

                myDb.insert(visit);
                finish();

            }
        });



    }



}