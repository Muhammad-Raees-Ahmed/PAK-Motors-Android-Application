package com.example.shopping.ApplicationProgrammingInterface;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_Class {
    @SerializedName("Message")
    @Expose
    String message;

    @SerializedName("Results")
    @Expose
   public List<Datum> Results=null;

    public class Datum {
        @SerializedName("Country")
        @Expose
        public String a_country;
        @SerializedName("Name")
        @Expose
       public String a_bikename;
        @SerializedName("CreatedOn")
        @Expose
       public String a_date;
    }
}
