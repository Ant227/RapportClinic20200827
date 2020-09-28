package com.example.rapportclinic20200827;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;


import com.example.rapportclinic20200827.Adaper.CustomAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    private MaterialSearchBar materialSearchBar;
    private ArrayList<Patient> patients;
    private RecyclerView searchRecyclerView;
    private CustomAdapter searchCustomAdapter;
    private MyDataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        myDb = MyDataBaseHelper.getInstance(this);
        patients = myDb.readPatients();

        materialSearchBar = findViewById(R.id.search_searchBar);

        searchRecyclerView = findViewById(R.id.searchRecyclerView);

        searchCustomAdapter = new CustomAdapter(this, patients);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        searchRecyclerView.setLayoutManager(linearLayoutManager);
        searchRecyclerView.setAdapter(searchCustomAdapter);
        searchRecyclerView.setNestedScrollingEnabled(false);
        searchRecyclerView.setHasFixedSize(true);


        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.d("materialSearchBar :", " beforeTextChanged method called");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d("materialSearchBar :", " OnTextChanged method called");

            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d("materialSearchBar :", " afterTextChanged method called");
                Log.d("afterTextChanged :", s.toString());


                patients.clear();

                patients = myDb.getPatientsByName(s.toString());

                Log.d("patient : ", patients.toString());

                for(int index = 0; index < patients.size(); index++){
                    Log.d("Patient No" + index + " Name ",patients.get(index).getName());
                }
                searchCustomAdapter.setPatients(patients);


                searchCustomAdapter.notifyDataSetChanged();
                searchRecyclerView.setAdapter(searchCustomAdapter);


            }
        });



   // materialSearchBar.openSearch();

    }




}
