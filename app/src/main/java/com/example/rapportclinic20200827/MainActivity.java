package com.example.rapportclinic20200827;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

import com.example.rapportclinic20200827.Adaper.CustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    private MyDataBaseHelper myDb;
    private ArrayList<Patient> patients;
    private CustomAdapter customAdapter;
    private MaterialSearchBar materialSearchBar;
    private FloatingActionButton fab;
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainRecyclerView = findViewById(R.id.main_recycler_view);
        materialSearchBar = findViewById(R.id.main_searchBar);
        fab = findViewById(R.id.floatingActionButton);
        nestedScrollView = findViewById(R.id.main_scroll);


        myDb = MyDataBaseHelper.getInstance(this);


        patients = myDb.readPatients();

        customAdapter = new CustomAdapter(this,patients);




        mainRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecyclerView.setLayoutManager(linearLayoutManager);
        mainRecyclerView.setAdapter(customAdapter);
        mainRecyclerView.setNestedScrollingEnabled(false);






        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.d("materialSearchBar :"," beforeTextChanged method called");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("materialSearchBar :"," OnTextChanged method called");

            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d("materialSearchBar :"," afterTextChanged method called");
                Log.d("afterTextChanged :", s.toString());

                if(TextUtils.isEmpty(s.toString())){

                    patients.clear();
                    patients = myDb.readPatients();
                    customAdapter.setPatients(patients);
                    customAdapter.notifyDataSetChanged();

                }
                else{
                    patients.clear();
                    patients = myDb.getPatientsByName(s.toString());
                    customAdapter.setPatients(patients);
                    customAdapter.notifyDataSetChanged();
                }
            }
        });

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });




    }


    protected void onResume(){
        super.onResume();
        patients = myDb.readPatients();
        customAdapter.setPatients(patients);
        mainRecyclerView.setAdapter(customAdapter);
    }




    public void goToAddActivity(View view){
        Intent intent = new Intent(MainActivity.this,AddActivity.class);


        startActivity(intent);
    }




    public void mainProfilePopup(View view){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.profile_popup);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}
