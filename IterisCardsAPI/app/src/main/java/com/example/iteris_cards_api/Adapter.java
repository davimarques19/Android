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

public class Adapter extends RecyclerView.Adapter<Adapter.PropertyHolder> {

    Context context;
    LayoutInflater layoutInflater;
    List<Property> properties;

        public Adapter(Context context, List<Property> properties) {
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
        Glide.with(context).load(property.getImage()).into(propertyHolder.image);
        propertyHolder.owner.setText(property.getTv_owner());
        propertyHolder.address.setText(property.getTv_address());
        propertyHolder.businessType.setText(property.getTv_type());
        propertyHolder.price.setText(property.getTv_price());
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public class PropertyHolder extends RecyclerView.ViewHolder {
        TextView address;
        TextView price;
        TextView owner;
        TextView businessType;
        ImageView image;

        public PropertyHolder(@NonNull View itemView) {
            super(itemView);
            address.findViewById(R.id.tv_address);
            price.findViewById(R.id.tv_price);
            owner.findViewById(R.id.tv_owner);
            businessType.findViewById(R.id.tv_type);
            image.findViewById(R.id.iv_image);
        }
    }
}
