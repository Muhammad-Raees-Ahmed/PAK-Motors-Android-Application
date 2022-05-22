package com.example.shopping;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.shopping.ApplicationProgrammingInterface.Adapter;
import com.example.shopping.ApplicationProgrammingInterface.Adapter_car;
import com.example.shopping.ApplicationProgrammingInterface.DATA;
import com.example.shopping.ApplicationProgrammingInterface.DATA_Car;
import com.example.shopping.ApplicationProgrammingInterface.JPH_API;
import com.example.shopping.ApplicationProgrammingInterface.Model_Class;
import com.example.shopping.ApplicationProgrammingInterface.Model_Class_Car;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setProgressStyle(ProgressDialog.BUTTON_POSITIVE);
        mProgressDialog.show();
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.activity_car, container, false);
        ArrayList<DATA_Car> my_arrayList_car=new ArrayList<>();
        Adapter_car adapter_car=new Adapter_car(getActivity(),my_arrayList_car);
        ListView listView = (ListView)  rootview.findViewById(R.id.list_view_car);
        listView.setAdapter(adapter_car);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vpic.nhtsa.dot.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JPH_API jsonPlaceHolderAPI = retrofit.create(JPH_API.class);

        Call<Model_Class_Car> call = jsonPlaceHolderAPI.get_car_data("https://vpic.nhtsa.dot.gov/api/vehicles/GetMakesForVehicleType/car?format=json");

        call.enqueue(new Callback<Model_Class_Car>() {
            @Override
            public void onResponse(Call<Model_Class_Car> call, Response<Model_Class_Car> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Model_Class_Car my_Car_data=response.body();
                List<Model_Class_Car.Datum_car> datumList=my_Car_data.Results;


                for (Model_Class_Car.Datum_car datum:datumList){
                    String CompanyName=datum.Company;
                    String CarType=datum.type;

                    DATA_Car data_car = new DATA_Car(CompanyName,CarType);
                    my_arrayList_car.add(data_car);
                    // jab bhi api fetch hogi or phr listview me add hogi to aesy hogi
                    listView.setAdapter(adapter_car);
            }
        }

            @Override
            public void onFailure(Call<Model_Class_Car> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

            }

            });

        return  rootview;
    }
}