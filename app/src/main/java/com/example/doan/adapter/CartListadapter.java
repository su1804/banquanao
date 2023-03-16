package com.example.doan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan.DB.cart;
import com.example.doan.R;
import com.example.doan.domain.menu_domain;
import com.example.doan.domain.inteface.changeNumber;

import java.util.ArrayList;

public class CartListadapter extends RecyclerView.Adapter<CartListadapter.ViewHolder> {
ArrayList<menu_domain> ListitemSelect;
    private cart  cart;
    changeNumber changeNumber;

    public CartListadapter(ArrayList<menu_domain> ListitemSelect, Context context, changeNumber changeNumber) {
        this.ListitemSelect = ListitemSelect;
        cart = new cart(context);
        this.changeNumber = changeNumber;
    }

    @NonNull
    @Override
    public CartListadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent,false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CartListadapter.ViewHolder holder, int position) {
        holder.title.setText(ListitemSelect.get(position).getTitle());
        holder.tienEachitem.setText("$"+ListitemSelect.get(position).getTien());
        holder.totalEachItem.setText("$" +Math.round(ListitemSelect.get(position).getNumberIntCart()* ListitemSelect.get(position).getTien()));
        holder.numberItem.setText(String.valueOf(ListitemSelect.get(position).getNumberIntCart()));

        int draw = holder.itemView.getContext().getResources()
                .getIdentifier(ListitemSelect.get(position).getPic(), "drawable",
                 holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                                .load(draw)
                                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.plusNumberitem(ListitemSelect, position, () -> {
                    notifyDataSetChanged();
                    changeNumber.changed();
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.plusNumberitem(ListitemSelect, position, () -> {
                    notifyDataSetChanged();
                    changeNumber.changed();
                });
            }
        });{

        }




    }

    @Override
    public int getItemCount() {
        return ListitemSelect.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, tienEachitem, thanhtoan;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, numberItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletxt);
            pic = itemView.findViewById(R.id.picitem2);
            tienEachitem = itemView.findViewById(R.id.tienEachitem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.Plusbtn);
            minusItem = itemView.findViewById(R.id.minusBtn);
            numberItem =itemView.findViewById(R.id.numberItem);

        }


    }
}
