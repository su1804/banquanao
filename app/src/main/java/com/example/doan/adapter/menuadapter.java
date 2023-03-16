package com.example.doan.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan.R;
import com.example.doan.domain.menu_domain;
import com.example.doan.hiensanpham;

import java.util.ArrayList;

public class menuadapter extends RecyclerView.Adapter<menuadapter.ViewHolder> {
ArrayList<menu_domain> menu_domains;

    public menuadapter(ArrayList<menu_domain> menu_domains) {
        this.menu_domains = menu_domains;
    }

    @NonNull
    @Override
    public menuadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_menu, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull menuadapter.ViewHolder holder, int position) {
        holder.title.setText(menu_domains.get(position).getTitle());
        holder.tien.setText(String.valueOf(menu_domains.get(position).getTien()));

        int draw = holder.itemView.getContext().getResources()
                .getIdentifier(menu_domains.get(position).getPic(), "drawable",
                 holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                                .load(draw)
                                .into(holder.pic);
        holder.butn.setOnClickListener(view -> {
            Intent intent =new Intent(holder.itemView.getContext(), hiensanpham.class);
            intent.putExtra("object", menu_domains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return menu_domains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, tien;
        ImageView pic;
        Button butn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletxt);
            pic = itemView.findViewById(R.id.pic);
            tien = itemView.findViewById(R.id.tien);
            butn = itemView.findViewById(R.id.butn);
        }


    }
}
