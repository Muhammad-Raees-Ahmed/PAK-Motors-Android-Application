package com.example.shopping.ApplicationProgrammingInterface;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_Class_Car {
    @SerializedName("Message")
    @Expose
    String message;

    @SerializedName("Results")
    @Expose
    public List<Datum_car> Results=null;

    public class Datum_car{
        @SerializedName("MakeName")
        @Expose
        public String Company;
        @SerializedName("VehicleTypeName")
        @Expose
        public String type;
    }

}
