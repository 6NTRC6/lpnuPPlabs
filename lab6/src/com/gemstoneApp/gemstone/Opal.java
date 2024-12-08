package com.gemstoneApp.gemstone;

public class Opal extends Gemstone {
    private Float waterContent;

    public Opal(String name, Float weight, Float basePrice, Float transparency, Float waterContent) {
        super(name, weight, basePrice, transparency);
        this.waterContent = waterContent;
    }

    @Override
    public String toString() {
        return "Opal{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", basePrice=" + basePrice +
                ", transparency=" + transparency +
                ", cutRatio=" + cutRatio +
                ", waterContent=" + waterContent +
                ", price=$" + calculatePrice() +
                '}';
    }
// Getters and setters

    public Float getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(Float waterContent) {
        this.waterContent = waterContent;
    }

    @Override
    public Float calculatePrice() {
        // Implement price calculation logic for opals
        return super.calculatePrice()*(1+waterContent*0.01f);
    }
}