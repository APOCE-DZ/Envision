package com.example.envision.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.envision.View.BankruptPredictorActivity;
import com.example.envision.View.PatientListActivity;
import com.example.envision.R;
import com.example.envision.View.CustomerListPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainRecyclerView extends RecyclerView.Adapter<MainRecyclerView.CardViewHolder> {
    
    Context invokingActivity;
    ArrayList<Integer> cardArrayList;


    public MainRecyclerView(Context context, ArrayList<Integer> cardArrayList){
        this.invokingActivity = context;
        this.cardArrayList = cardArrayList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_screen_cardview,parent,false);
        final CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if(position == 0)
            holder.imageView.setImageResource(R.drawable.money);
        if(position == 1)
            holder.imageView.setImageResource(R.drawable.cancer);
        if (position == 2)
            holder.imageView.setImageResource(R.drawable.loan);

    }

    @Override
    public int getItemCount() {
        return cardArrayList.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private ImageView imageView;
        private View layoutView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutView = itemView;
            cardView = itemView.findViewById(R.id.main_screen_cardview);
            imageView = itemView.findViewById(R.id.main_screen_imageview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(v == cardView){
                if(getAdapterPosition() == 0)
                    invokingActivity.startActivity(new Intent(invokingActivity.getApplicationContext(), CustomerListPage.class));

                if(getAdapterPosition() == 1)
                    invokingActivity.startActivity(new Intent(invokingActivity.getApplicationContext(), PatientListActivity.class));

                if(getAdapterPosition() == 2)
                    invokingActivity.startActivity(new Intent(invokingActivity.getApplicationContext(), BankruptPredictorActivity.class));

            }
        }
    }
}
