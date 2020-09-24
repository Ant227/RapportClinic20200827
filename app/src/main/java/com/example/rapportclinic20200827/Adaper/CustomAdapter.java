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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;


    private ArrayList<Patient> patients;


    public CustomAdapter(Context context,ArrayList<Patient> patients){
        this.context = context;
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



        holder.name.setText(patients.get(position).getName());
        holder.date.setText(patients.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);


                intent.putExtra("name",patients.get(position).getName());
                intent.putExtra("age",patients.get(position).getAge());
                intent.putExtra("gender",patients.get(position).getSex());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {


        return patients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.all_patient_name);
            date = itemView.findViewById(R.id.all_patient_date);
        }
    }


}
