package com.example.envision.loandefaulter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Customer implements Serializable {

    /*Personal Information*/
    private String customerId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String birthDate;
    private String address;
    private String workType;
    private String relationshipStatus;
    private double totalIncome;

    /*Accounts*/
    private HashMap<String, ArrayList<Account>> accounts = new HashMap<>();

    /*Bill Payments*/
    private ArrayList<Transaction> billPayments;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public HashMap<String, ArrayList<Account>> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, ArrayList<Account>> accounts) {
        this.accounts = accounts;
    }

}
