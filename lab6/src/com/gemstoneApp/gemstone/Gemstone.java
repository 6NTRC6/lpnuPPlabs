package com.gemstoneApp.gemstone;

import java.io.Serializable;

public class Gemstone implements Serializable {
    protected String name;
    protected Float weight;
    protected Float basePrice;
    protected Float transparency;
    protected Float cutRatio = 1.0f;

    public Gemstone(String name, Float weight, Float basePrice, Float transparency) {
        this.name = name;
        this.weight = weight;
        this.basePrice = basePrice;
        this.transparency = transparency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Float getTransparency() {
        return transparency;
    }

    public void setTransparency(Float transparency) {
        this.transparency = transparency;
    }

    public Float getCutRatio() {
        return cutRatio;
    }

    public void setCutRatio(Float cutRatio) {
        this.cutRatio = cutRatio;
    }

    @Override
    public String toString() {
        return "Gemstone{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", basePrice=" + basePrice +
                ", transparency=" + transparency +
                ", cutRatio=" + cutRatio +
                ", price=$" + calculatePrice() +
                '}';
    }
// Getters and setters

    public Float calculatePrice() {
        return basePrice * weight * (1+transparency*0.3f) * cutRatio;
    }
}
