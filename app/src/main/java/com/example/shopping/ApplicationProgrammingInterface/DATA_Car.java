package com.example.shopping.ApplicationProgrammingInterface;

public class DATA_Car {
    String company;
    String car_type;

    public DATA_Car(String company, String car_type) {
        this.company = company;
        this.car_type = car_type;
    }

    public String getCompany() {
        return company;
    }

    public String getCar_type() {
        return car_type;
    }
}
