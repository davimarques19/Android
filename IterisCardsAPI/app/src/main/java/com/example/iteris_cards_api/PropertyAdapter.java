package com.example.iteris_cards_api;

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
        propertyHolder.tvOwner.setText(property.getOwner());
        propertyHolder.tvAddress.setText(property.getAddress());
        propertyHolder.tvBusinessType.setText(property.getBusinessType());
        propertyHolder.tvPrice.setText(property.getPrice());
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public static class PropertyHolder extends RecyclerView.ViewHolder {

        TextView tvOwner;
        TextView tvAddress;
        TextView tvBusinessType;
        TextView tvPrice;
        ImageView ivProperty;

        public PropertyHolder(@NonNull View itemView){
            super(itemView);
            tvOwner = itemView.findViewById(R.id.tv_owner);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvBusinessType = itemView.findViewById(R.id.tv_type);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivProperty = itemView.findViewById(R.id.iv_image);
        }
    }
}
