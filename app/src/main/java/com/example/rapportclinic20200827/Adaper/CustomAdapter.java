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

import com.example.rapportclinic20200827.MyDataBaseHelper;
import com.example.rapportclinic20200827.ProfileActivity;
import com.example.rapportclinic20200827.R;

import java.util.ArrayList;

import com.example.rapportclinic20200827.Patient;
import com.example.rapportclinic20200827.Visit;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;


    private ArrayList<Patient> patients;

    private MyDataBaseHelper myDb;


    public CustomAdapter(Context context,ArrayList<Patient> patients){
        this.context = context;
        this.patients = patients;
        myDb = MyDataBaseHelper.getInstance(context);

    }

    public void setPatients(ArrayList<Patient> patients){
        this.patients = patients;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.all_patient_layout,parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        Patient patient = patients.get(position);




        holder.name.setText(patient.getName());
        holder.date.setText(patient.getDate());

        try{

            ArrayList<Visit> visits = myDb.readVisits(patient);
            Visit lastVisit = visits.get(0);
            String visitInfo = lastVisit.getHistory().split("\n")[0];
            if(visitInfo.length()>20)
                 visitInfo = visitInfo.substring(0,17) + "...";
            int visitCount = visits.size();
            Log.d("Patient : ",patient.toString());
            Log.d("Number of Visits : ","" + visitCount);

            holder.visit_info.setText(visitInfo);
            holder.total_visit_number.setText(visitCount + " Visits ");
            holder.date.setText("Last Visit : " + lastVisit.getDate());

        }
        catch(Exception e) {

            Log.d("Exception info", e.toString());

            holder.visit_info.setText(" No Visit");

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);


                intent.putExtra("patient", patient);




                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {


            return patients.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , date, visit_info, total_visit_number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.all_patient_name);
            date = itemView.findViewById(R.id.all_patient_date);
            visit_info = itemView.findViewById(R.id.visit_info);
            total_visit_number = itemView.findViewById(R.id.total_visit_number);
        }
    }


}
