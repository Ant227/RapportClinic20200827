package com.example.rapportclinic20200827;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    private MaterialSearchBar materialSearchBar;
    private ArrayList<Patient> patients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        patients = new ArrayList<>();
        materialSearchBar = findViewById(R.id.search_searchBar);




      materialSearchBar.openSearch();



    }


}
