package com.fkucuk.model;

/**
 * Created by fat on 13.12.2016.
 */
public class Measure {
    private String label;
    private float eqv;
    private float qty;

    public Measure() {
    }

    public Measure(String label, float eqv, float qty) {
        this.label = label;
        this.eqv = eqv;
        this.qty = qty;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getEqv() {
        return eqv;
    }

    public void setEqv(float eqv) {
        this.eqv = eqv;
    }
}
