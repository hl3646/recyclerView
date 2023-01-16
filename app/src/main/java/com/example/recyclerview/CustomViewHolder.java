package com.example.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder
{
    TextView tv;

    public CustomViewHolder(@NonNull View itemView)
    {
        super(itemView);
        tv = itemView.findViewById(R.id.textView);
    }
}
