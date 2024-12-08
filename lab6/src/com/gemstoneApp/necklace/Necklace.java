package com.gemstoneApp.necklace;

import com.gemstoneApp.gemstone.Gemstone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Necklace implements Serializable {
    private List<Gemstone> gemstones;

    public Necklace() {
        this.gemstones = new ArrayList<>();
    }

    public Necklace(List<Gemstone> gemstones) {
        this.gemstones = new ArrayList<>(gemstones);
    }

    public void addGemstone(Gemstone gemstone) {
        gemstones.add(gemstone);
    }

    // Other existing methods
    public Float calculateWeight() {
        float totalWeight = 0;
        for (Gemstone gemstone : gemstones) {
            totalWeight += gemstone.getWeight();
        }
        return totalWeight;
    }

    public Float calculatePrice() {
        float totalPrice = 0;
        for (Gemstone gemstone : gemstones) {
            totalPrice += gemstone.calculatePrice();
        }
        return totalPrice;
    }

    public void sortByGemPrice() {
        // Implement sorting logic
    }

    public List<Gemstone> getGemstones() {
        return gemstones;
    }
}
