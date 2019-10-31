package com.example.consumirapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyHolder> {

    Context context;
    LayoutInflater layoutInflater;
    List<Property> properties;

    public PropertyAdapter(Context context, List<Property> properties) {
        this.context = context;
        this.properties = properties;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public PropertyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.houses, viewGroup, false);
        return new PropertyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyHolder propertyHolder, int i) {
        Property property = properties.get(i);
        Glide.with(context).load(property.getImage()).into(propertyHolder.ivProperty);
        propertyHolder.tvAddress.setText(property.getAddress());
        propertyHolder.tvPrice.setText(property.getPrice());
        propertyHolder.tvOwner.setText(property.getOwner());
        propertyHolder.tvType.setText(property.getBusinessType());
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public class PropertyHolder extends RecyclerView.ViewHolder {

        TextView tvAddress;
        TextView tvPrice;
        TextView tvOwner;
        TextView tvType;
        ImageView ivProperty;

        public PropertyHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.address);
            tvPrice = itemView.findViewById(R.id.price);
            tvOwner = itemView.findViewById(R.id.owner);
            tvType = itemView.findViewById(R.id.type);
            ivProperty = itemView.findViewById(R.id.img);
        }
    }
}
