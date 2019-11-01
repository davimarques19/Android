package com.example.consumirapi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
        properties = new ArrayList<>();
        list();
        setUpRecyclerView();
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

    private void setUpRecyclerView() {
        rvProperty = findViewById(R.id.rv_property);
        rvProperty.setHasFixedSize(true);
        rvProperty.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        propertyAdapter = new PropertyAdapter(this, properties);
        rvProperty.setAdapter(propertyAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                propertyAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
