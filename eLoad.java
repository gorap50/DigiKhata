package com.example.digikhata;

public class eLoad {
    private int id;
    private String number,operator,amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public eLoad(int id, String operator, String number, String amount) {
        this.id = id;
        this.number = number;
        this.operator = operator;
        this.amount = amount;
    }
}
