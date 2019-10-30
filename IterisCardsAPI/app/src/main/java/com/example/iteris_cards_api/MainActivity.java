package com.example.iteris_cards_api;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvProperty;
    List<Property> properties;
    Adapter adapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvProperty = findViewById(R.id.rv_property);
        properties = new ArrayList<>();
        adapter = new Adapter(this, properties);
        rvProperty.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvProperty.setAdapter(adapter);
        list();
    }

    public void list() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://iteris.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PropertyService service = retrofit.create(PropertyService.class);
        service.list().enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                properties.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {

            }
        });
    }
}
