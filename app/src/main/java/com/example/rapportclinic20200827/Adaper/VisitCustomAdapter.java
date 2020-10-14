package com.example.rapportclinic20200827.Adaper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rapportclinic20200827.Doctor;
import com.example.rapportclinic20200827.ProfileActivity;
import com.example.rapportclinic20200827.R;

import java.util.ArrayList;

import com.example.rapportclinic20200827.Patient;
import com.example.rapportclinic20200827.ViewVisitActivity;
import com.example.rapportclinic20200827.Visit;
import com.example.rapportclinic20200827.VisitActivity;

public class VisitCustomAdapter extends RecyclerView.Adapter<VisitCustomAdapter.VisitViewHolder> {

    private Context context;
    private Patient patient;

    private ArrayList<Visit> visits;
    private int visitCount;

    public VisitCustomAdapter(Context context,
                              Patient patient,
                              ArrayList<Visit> visits){
        this.context = context;
        this.patient = patient;
        this.visits = visits;
        visitCount = visits.size();
    }

    public void setVisits(ArrayList<Visit> visits){
        this.visits = visits;
        visitCount = visits.size();
    }
    @NonNull
    @Override
    public VisitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.all_patient_layout,parent, false);
        return new VisitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitViewHolder holder, final int position) {



        holder.name.setText(patient.getName());
        holder.date.setText(visits.get(position).getDate());
        String visitInfo = visits.get(position).getHistory().split("\n")[0];
        if(visitInfo.length()>20)
            visitInfo = visitInfo.substring(0,17) + "...";
        holder.visit_info.setText(visitInfo);
        int visitNumber = visitCount-position;
        holder.visit_number.setText("Visit #" + visitNumber);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewVisitActivity.class);

                intent.putExtra("visit",visits.get(position));
                intent.putExtra("patient",patient);
                intent.putExtra("visit_info",holder.visit_number.getText());
                Log.d("Visist info ",visits.get(position).toString());

                //Doctor doctor = new Doctor();
                //doctor = new Doctor(32,"Hello","MBBS");
                //visits.get(position).


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {


        return visits.size();
    }

    public class VisitViewHolder extends RecyclerView.ViewHolder {

        TextView name , date, visit_info, visit_number;

        public VisitViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.all_patient_name);
            date = itemView.findViewById(R.id.all_patient_date);
            visit_info = itemView.findViewById(R.id.visit_info);
            visit_number = itemView.findViewById(R.id.total_visit_number);
        }
    }


}
