package com.example.envision.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.envision.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonDetailRecyclerView extends RecyclerView.Adapter<PersonDetailRecyclerView.PersonDetailsViewHolder> {

    ArrayList<String> list;
    public PersonDetailRecyclerView(ArrayList<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public PersonDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person_detail_cardview,parent,false);
        final PersonDetailsViewHolder cardViewHolder = new PersonDetailsViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonDetailsViewHolder holder, int position) {
        holder.accountID.setText(list.get(position));
        holder.accountType.setText(list.get(position));
        holder.accountBalance.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class PersonDetailsViewHolder extends RecyclerView.ViewHolder{

        TextView accountID,accountBalance,accountType;
        public PersonDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            accountBalance = itemView.findViewById(R.id.activity_person_detail_account_balance);
            accountType = itemView.findViewById(R.id.activity_person_detail_account_type);
            accountID = itemView.findViewById(R.id.activity_person_detail_account_id);

        }

    }
}
