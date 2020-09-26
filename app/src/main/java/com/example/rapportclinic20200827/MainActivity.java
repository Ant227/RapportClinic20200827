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

        myDb = new MyDataBaseHelper(this);


        patients = myDb.readPatients();

        customAdapter = new CustomAdapter(this,patients);




        mainRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecyclerView.setLayoutManager(linearLayoutManager);
        mainRecyclerView.setAdapter(customAdapter);
        mainRecyclerView.setNestedScrollingEnabled(false);


       materialSearchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if(hasFocus){
                   patients.clear();
                   //patients = myDb.readPatients();
                   mainRecyclerView.setAdapter(customAdapter);
                  customAdapter.notifyDataSetChanged();
               }
               else{
                   patients.clear();

                   patients = myDb.readPatients();
                   mainRecyclerView.setAdapter(customAdapter);
               }
           }
       });

       materialSearchBar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               goToSearchActivity();
           }
       });

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(s.toString())){
                    patients.clear();


                    patients = myDb.readPatients();

                    mainRecyclerView.setAdapter(customAdapter);


                }
                else{
                    patients.clear();

                    patients = myDb.getPatientsByName(s.toString());

                    mainRecyclerView.setAdapter(customAdapter);
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
        customAdapter = new CustomAdapter(this,patients);
        mainRecyclerView.setAdapter(customAdapter);
    }



    public void goToAddActivity(View view){
        Intent intent = new Intent(MainActivity.this,AddActivity.class);

        startActivity(intent);
    }


    private void goToSearchActivity(){


        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        //intent.putExtra("patients",patients);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                materialSearchBar,"searchBar");

        startActivity(intent, options.toBundle());


    }

    public void mainProfilePopup(View view){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.profile_popup);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}
