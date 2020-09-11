package com.example.dream_cookery.ViewHolder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dream_cookery.Interface.ItemClickListener;
import com.example.dream_cookery.R;

public class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtClassName, txtClassDescription;
    public ImageView imageView;
    public ItemClickListener listener;

    public ClassViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.class_image);
        txtClassName = (TextView) itemView.findViewById(R.id.class_name);
        txtClassDescription = (TextView) itemView.findViewById(R.id.class_description);
    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);

    }
}
