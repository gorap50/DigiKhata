package com.example.digikhata;

public class User {
    private String amount;
    private String name;


    public User(String name, String amount) {
        this.name = name;
        this.amount = amount;

    }
    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

}
