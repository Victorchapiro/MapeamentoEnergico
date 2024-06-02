package com.example.mapeamentoenergico;

public class ApplianceAssessment {
    private double valueBill;
    private String name;

    public ApplianceAssessment(double valueBill, String name) {
        this.valueBill = valueBill;
        this.name = name;
    }

    public double getValueBill() {
        return valueBill;
    }

    public void setValueBill(double valueBill) {
        this.valueBill = valueBill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
