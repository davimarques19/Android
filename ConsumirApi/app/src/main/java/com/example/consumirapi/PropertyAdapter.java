package com.example.consumirapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyHolder> implements Filterable {

    Context context;
    LayoutInflater layoutInflater;
    List<Property> properties;
    List<Property> propertyListFull;

    public PropertyAdapter(Context context, List<Property> properties) {
        this.context = context;
        this.properties = properties;
        propertyListFull = new ArrayList<>(properties);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public PropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.houses, parent, false);
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Property> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(propertyListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Property item : propertyListFull) {
                    if (item.getAddress().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            properties.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
