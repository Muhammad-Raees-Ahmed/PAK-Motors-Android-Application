package com.example.shopping;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.shopping.ApplicationProgrammingInterface.Adapter;
import com.example.shopping.ApplicationProgrammingInterface.DATA;
import com.example.shopping.ApplicationProgrammingInterface.JPH_API;
import com.example.shopping.ApplicationProgrammingInterface.Model_Class;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BikeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setProgressStyle(ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        mProgressDialog.show();
        View rootview =inflater.inflate(R.layout.activity_bike, container, false);

        ArrayList<DATA> my_arrayList=new ArrayList<>();
        Adapter adapter=new Adapter(getActivity(),my_arrayList);
        ListView listView = (ListView)  rootview.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vpic.nhtsa.dot.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JPH_API jsonPlaceHolderAPI = retrofit.create(JPH_API.class);


        Call<Model_Class> call = jsonPlaceHolderAPI.get_bike_data("https://vpic.nhtsa.dot.gov/api/vehicles/GetWMIsForManufacturer/hon?format=json");

        call.enqueue(new Callback<Model_Class>() {
            @Override
            public void onResponse(Call<Model_Class> call, Response<Model_Class> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Model_Class my_data=response.body();
                List<Model_Class.Datum> datumList=my_data.Results;
              

                for (Model_Class.Datum datum:datumList){
                    String BikeName=datum.a_bikename;
                    String Country=datum.a_country;
                    String CreatedOn=datum.a_date;

                    DATA data = new DATA(BikeName, Country, CreatedOn);
                    my_arrayList.add(data);
                    // jab bhi api fetch hogi or phr listview me add hogi to aesy hogi
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Model_Class> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

            }
        });
        return  rootview;
    }
}