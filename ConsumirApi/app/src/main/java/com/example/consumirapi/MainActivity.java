package com.example.consumirapi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

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
    PropertyAdapter propertyAdapter;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvProperty = findViewById(R.id.rv_property);
        properties = new ArrayList<>();
        propertyAdapter = new PropertyAdapter(this, properties);
        rvProperty.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvProperty.setAdapter(propertyAdapter);
        list();
    }


    private void list() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iteris.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PropertyService service = retrofit.create(PropertyService.class);
        service.list().enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                properties.addAll(response.body());
                propertyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        //MenuItem menuItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) menuItem.getActionView();
        //searchView.setOnQueryTextListener(this);

        return true;
    }

    /**
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<Property> newList = new ArrayList<>();

        for(Property name : properties) {
            if(name.toString().toLowerCase().contains(userInput)){
                newList.add(name);
            }
        }

        propertyAdapter.updateList(newList);
        return true;
    }
    **/
}
