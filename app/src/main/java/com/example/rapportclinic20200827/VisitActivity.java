package com.example.rapportclinic20200827;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class VisitActivity extends AppCompatActivity {

    private Button myBt;
    private ScrollView button_scroll_view;
    private LinearLayout visit_layout;
    private Guideline visit_button_guideline;
    private ConstraintLayout button_conainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);


        myBt = new Button(this);
        myBt.setText("Cough x 3 days");


        button_scroll_view = findViewById(R.id.button_scroll_view);
        visit_layout = findViewById(R.id.visit_layout);
        //visit_button_guideline = findViewById(R.id.visit_button_guideline);
        button_conainer = findViewById(R.id.button_container);

        button_conainer.addView(myBt);

    }
}