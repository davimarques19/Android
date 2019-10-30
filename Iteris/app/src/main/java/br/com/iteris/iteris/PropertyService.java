package br.com.iteris.iteris;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PropertyService {
    @GET("properties.json")
    Call<List<Property>> list();
}
