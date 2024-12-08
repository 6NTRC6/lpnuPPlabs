package com.droids.droid;

public class BattleDroid extends Droid {
    public BattleDroid(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public String toString() {
        return "BattleDroid{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", damage=" + getDamage() +
                '}';
    }
}
