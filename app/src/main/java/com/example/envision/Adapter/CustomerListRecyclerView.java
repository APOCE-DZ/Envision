package com.example.envision.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.envision.R;
import com.example.envision.View.CustomerDetailsActivity;
import com.example.envision.loandefaulter.model.Customer;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerListRecyclerView extends RecyclerView.Adapter<CustomerListRecyclerView.CustomerListViewHolder>{

    private Context context;
    private ArrayList<Customer> customers;

    public CustomerListRecyclerView(Context context, ArrayList<Customer> customers){
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_page_cardview,parent,false);
        final CustomerListViewHolder customerListViewHolder = new CustomerListViewHolder(view);
        return customerListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListViewHolder holder, int position) {
        holder.textView.setText(customers.get(position).getFirstName()+" "+customers.get(position).getLastName());
        if(position % 2 == 0)
            holder.imageView.setImageResource(R.drawable.male);
        else
            holder.imageView.setImageResource(R.drawable.female);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }


    public class CustomerListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        TextView textView;
        ImageView imageView;

        public CustomerListViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.listpage_textview);
            imageView = itemView.findViewById(R.id.listpage_imageview);
            cardView = itemView.findViewById(R.id.listpage_cardview);

            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(v == cardView){
                //Toast.makeText(itemView.getContext(), ""+v.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), CustomerDetailsActivity.class);
                intent.putExtra("CUSTOMER", customers.get(getAdapterPosition()));
                context.startActivity(intent);
            }
        }
    }
}
