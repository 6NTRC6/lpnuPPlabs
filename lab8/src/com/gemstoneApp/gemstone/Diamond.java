package com.gemstoneApp.gemstone;

public class Diamond extends Gemstone {
    private Float hardness;

    public Diamond(String name, Float weight, Float basePrice, Float transparency, Float hardness) {
        super(name, weight, basePrice, transparency);
        this.hardness = hardness;
    }

    @Override
    public String toString() {
        return "Diamond{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", basePrice=" + basePrice +
                ", transparency=" + transparency +
                ", cutRatio=" + cutRatio +
                ", hardness=" + hardness +
                ", price=$" + calculatePrice() +
                '}';
    }
// Getters and setters

    public Float getHardness() {
        return hardness;
    }

    public void setHardness(Float hardness) {
        this.hardness = hardness;
    }

    @Override
    public Float calculatePrice() {
        // Implement price calculation logic for diamonds
        return super.calculatePrice()*(1+hardness*0.01f);
    }
}
