package com.trendyol.basket.model;

public class BasketInfoModel {
    private float subtotal;
    private float cargo = 0;
    private float theOverallTotal;

    public BasketInfoModel(float subtotal) {
        this.subtotal = subtotal;
        if (subtotal < 100)
            this.cargo = 4.99F;
        this.theOverallTotal = subtotal + cargo;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getCargo() {
        return cargo;
    }

    public void setCargo(float cargo) {
        this.cargo = cargo;
    }

    public float getTheOverallTotal() {
        return theOverallTotal;
    }

    public void setTheOverallTotal(float theOverallTotal) {
        this.theOverallTotal = theOverallTotal;
    }
}
