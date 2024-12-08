package com.droids.droid;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int damage;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        this.health = Math.max(this.health - damage, 0);
    }

    public void restoreStats() {
        this.health = maxHealth;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return String.format("%s (Health: %d, Damage: %d)", name, health, damage);
    }
}
