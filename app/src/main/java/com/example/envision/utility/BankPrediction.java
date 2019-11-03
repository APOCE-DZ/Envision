package com.example.envision.utility;

public class BankPrediction {
    public static int getGenderValue(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return 1;
        } else {
            return 2;
        }
    }
    public static int getWorkValue(String edu) {
        if(edu.equalsIgnoreCase("fulltime")){
            return 1;
        } else if(edu.equalsIgnoreCase("parttime")){
            return 2;
        } else {
            return 4;
        }
    }
    public static int getMaritalStatusValue(String marital) {
        if(marital.equalsIgnoreCase("married")){
            return 1;
        } else if(marital.equalsIgnoreCase("single")){
            return 2;
        } else {
            return 3;
        }
    }


}