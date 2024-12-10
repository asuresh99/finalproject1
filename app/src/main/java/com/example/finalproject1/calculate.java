package com.example.finalproject1;

public class calculate {

    private static String errorMessage;
    public String getErrorMessage() {
        return errorMessage;
    }


    public static double calcBMI(double feet, double inch, double weight) {
        double bMI = 0;


        if(feet <= 0 && weight <= 0){
            errorMessage = "Height and Weight can't be zero or less";
        }
        else {
            double height = (feet * 12) + inch;
            bMI = weight * 703 / height;
            bMI = bMI / height;
        }
        return bMI;
    }

    public static String determineCat(double bMI){
        String bMICat = "";

        if(bMI < 16) {
            bMICat = "Severely Underweight";
        }
        else if(bMI >=  16 && bMI < 18.4) {
            bMICat = "Underweight";
        }
        else if(bMI >=  18.5 && bMI < 25) {
            bMICat = "Normal";
        }
        else if(bMI >=  25 && bMI < 30) {
            bMICat = "Overweight";
        }
        else if(bMI >=  30 && bMI < 35) {
            bMICat = "Moderately Obese";
        }
        else if(bMI >=  35 && bMI < 40) {
            bMICat = "Severely Obese";
        }
        else if(bMI >=  40) {
            bMICat = "Morbidly Obese";
        }

        return bMICat;

    }

}
