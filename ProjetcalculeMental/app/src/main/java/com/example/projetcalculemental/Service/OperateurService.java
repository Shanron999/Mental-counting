package com.example.projetcalculemental.Service;

import com.example.projetcalculemental.Models.OperationsModel;

public class OperateurService {

    public static double calculResultat(OperationsModel Operations) {
        double resultat;
        double firstValue;
        double secondValue;
        String firstValueAsString = Operations.getFirstValue();
        String secondValueAsString = Operations.getSecondValue();
        String operator = Operations.getOperator();

        firstValue = Double.parseDouble(firstValueAsString);
        secondValue = Double.parseDouble(secondValueAsString);


        switch (operator) {
            case "+":
                resultat = firstValue + secondValue;
                break;
            case "-":
                resultat = firstValue - secondValue;
                break;
            case "*":
                resultat = firstValue * secondValue;
                break;
            case "/":
                resultat = firstValue / secondValue;
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
        return resultat;
    }
}
