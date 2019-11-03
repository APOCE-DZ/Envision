package com.example.envision.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.envision.View.PatientDetailActivity;
import com.example.envision.R;
import com.example.envision.brestcancer.model.Patient;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PatientListRecyclerView extends RecyclerView.Adapter<PatientListRecyclerView.ListPageViewHolder>{

    private Context context;
    private ArrayList<Patient> patients;

    public PatientListRecyclerView(Context context, ArrayList<Patient> patients){
        this.context = context;
        this.patients = patients;
    }

    @NonNull
    @Override
    public ListPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_page_cardview,parent,false);
        final ListPageViewHolder listPageViewHolder = new ListPageViewHolder(view);
        return listPageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListPageViewHolder holder, int position) {
        holder.textView.setText(patients.get(position).getName());
        holder.imageView.setImageResource(R.drawable.male);

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }


    public class ListPageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        TextView textView;
        ImageView imageView;

        public ListPageViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.listpage_textview);
            imageView = itemView.findViewById(R.id.listpage_imageview);
            cardView = itemView.findViewById(R.id.listpage_cardview);

            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(v == cardView){
                Intent intent = new Intent(context.getApplicationContext(), PatientDetailActivity.class);
                intent.putExtra("PATIENT",patients.get(getAdapterPosition()));
                context.startActivity(intent);
            }
        }
    }
}
