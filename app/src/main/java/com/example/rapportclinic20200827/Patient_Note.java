package com.example.rapportclinic20200827;

import java.util.ArrayList;

public class Patient_Note {

    private Patient patient = null;
    private ArrayList<Visit> visits = null;

    public Patient_Note(Patient patient, ArrayList<Visit> visits){
        this.patient = patient;
        this.visits = visits;
    }


}
