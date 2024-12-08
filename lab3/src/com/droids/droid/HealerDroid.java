package com.droids.droid;

import com.droids.battle.Battle;

public class HealerDroid extends Droid {
    private final int healingPower;
    private final int maxEnergy;
    private int energy;

    public HealerDroid(String name, int health, int damage, int healingPower, int energy) {
        super(name, health, damage);
        this.healingPower = healingPower;
        this.maxEnergy = energy;
        this.energy = energy;
    }

    public void heal(Droid droid) {
        if (energy <= 0) {
            System.out.println("Not enough energy to heal");
            return;
        }

        if (droid.getHealth() <= 0) {
            Battle.addToBattleLog(droid.getName() + " is already down and cannot be healed!\n");
            return;
        }
        int healthAfterHealing = Math.min(getHealth() + healingPower, droid.getMaxHealth());
        this.setHealth(healthAfterHealing);

        Battle.addToBattleLog(String.format("%s healed %s for %d health! Current health: %d\n",
                getName(), droid.getName(), healingPower, droid.getHealth()));
    }


    public int getEnergy() {
        return energy;
    }

    public void increaseEnergy(int amount) {
        if (amount <= 0) {
            return;
        }
        energy = Math.min(energy + amount, maxEnergy);
    }

    @Override
    public void restoreStats() {
        super.restoreStats();
        this.energy = maxEnergy;
    }

    @Override
    public void takeDamage(int damage) {
        if (isAlive()) {
            health -= damage;
            if (health < 0) {
                health = 0;
            }
            if (energy > 0 && isAlive()) {
                heal(this);
            }
        } else {
            health = 0;
        }
    }

    @Override
    public String toString() {
        return "HealerDroid{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", damage=" + getDamage() +
                ", energy=" + getEnergy() +
                ", healingPower=" + healingPower +
                '}';
    }
}
