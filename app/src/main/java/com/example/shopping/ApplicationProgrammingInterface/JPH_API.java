package com.example.shopping.ApplicationProgrammingInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JPH_API {

    @GET()
    Call<Model_Class> get_bike_data(@Url String url);

    @GET()
    Call<Model_Class_Car> get_car_data(@Url String url);





}
