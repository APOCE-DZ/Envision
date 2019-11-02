package com.example.envision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

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
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
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
                Toast.makeText(invokingActivity, "" + v.getId(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
