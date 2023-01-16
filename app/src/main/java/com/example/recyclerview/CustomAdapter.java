package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>
{
    Context context;
    List<item> items;
    private ItemClickListener itemListener;

    public CustomAdapter(Context context, List<item> items, ItemClickListener itemClickListener)
    {
        this.context = context;
        this.items = items;
        this.itemListener = itemClickListener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
        holder.tv.setText(items.get(position).getNumber());
        holder.itemView.setOnClickListener(view -> {
            itemListener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
