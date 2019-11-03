package com.example.envision.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.envision.R;
import com.example.envision.loandefaulter.model.Account;
import com.example.envision.loandefaulter.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerDetailRecyclerView extends RecyclerView.Adapter<CustomerDetailRecyclerView.CustomerDetailsViewHolder> {

    private Context context;
    private ArrayList<Account> accounts;

    public CustomerDetailRecyclerView(Context context, Customer customer){
        this.context = context;
        this.accounts = new ArrayList<>();
        this.accounts.addAll(customer.getAccounts().get("bankAccounts"));
        this.accounts.addAll(customer.getAccounts().get("creditCardAccounts"));
    }

    @NonNull
    @Override
    public CustomerDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person_detail_cardview,parent,false);
        final CustomerDetailsViewHolder cardViewHolder = new CustomerDetailsViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerDetailsViewHolder holder, int position) {
        holder.accountID.setText(accounts.get(position).getAccountId());
        if(accounts.get(position).getCardType().equalsIgnoreCase("DDA")){
            holder.accountType.setText("Chequing");
        } else if(accounts.get(position).getCardType().equalsIgnoreCase("SDA")){
            holder.accountType.setText("Saving");
        } else {
            holder.accountType.setText(accounts.get(position).getCardType());
        }
        holder.accountBalance.setText(String.valueOf(accounts.get(position).getBalance()));
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }


    public class CustomerDetailsViewHolder extends RecyclerView.ViewHolder{

        TextView accountID,accountBalance,accountType;
        public CustomerDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            accountBalance = itemView.findViewById(R.id.activity_person_detail_account_balance);
            accountType = itemView.findViewById(R.id.activity_person_detail_account_type);
            accountID = itemView.findViewById(R.id.activity_person_detail_account_id);
        }

    }
}
