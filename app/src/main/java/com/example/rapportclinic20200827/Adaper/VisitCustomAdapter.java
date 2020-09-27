package com.example.rapportclinic20200827.Adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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


    public VisitCustomAdapter(Context context,
                              Patient patient,
                              ArrayList<Visit> visits){
        this.context = context;
        this.patient = patient;
        this.visits = visits;
    }

    public void setVisits(ArrayList<Visit> visits){
        this.visits = visits;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewVisitActivity.class);

                intent.putExtra("visit",visits.get(position));
                intent.putExtra("patient",patient);



                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {


        return visits.size();
    }

    public class VisitViewHolder extends RecyclerView.ViewHolder {

        TextView name , date;

        public VisitViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.all_patient_name);
            date = itemView.findViewById(R.id.all_patient_date);
        }
    }


}
