package com.example.envision.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.envision.R;
import com.example.envision.View.PersonDetailsActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListPageRecyclerView extends RecyclerView.Adapter<ListPageRecyclerView.ListPageViewHolder>{

    Context context;
    ArrayList<String> list;

    public ListPageRecyclerView(Context context, ArrayList<String> list){
        this.context = context;
        this.list = list;
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
        holder.textView.setText(list.get(position));
        if(position % 2 == 0)
            holder.imageView.setImageResource(R.drawable.male);
        else
            holder.imageView.setImageResource(R.drawable.female);
    }

    @Override
    public int getItemCount() {
        return list.size();
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
                Toast.makeText(itemView.getContext(), ""+v.getId(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context.getApplicationContext(), PersonDetailsActivity.class));
            }
        }
    }
}
