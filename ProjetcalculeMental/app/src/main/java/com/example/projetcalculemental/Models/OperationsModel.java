package com.example.projetcalculemental.Models;

public class OperationsModel {
    private String _firstValue;
    private String _secondValue;
    private String _operator;

    public OperationsModel(String _firstValue, String _secondValue, String _operator) {
        this._firstValue = _firstValue;
        this._secondValue = _secondValue;
        this._operator = _operator;
    }

    public String getFirstValue() {
        return _firstValue;
    }

    public String getSecondValue() {
        return _secondValue;
    }

    public String getOperator() {
        return _operator;
    }
}
