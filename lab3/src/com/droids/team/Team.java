package com.droids.team;

import com.droids.droid.Droid;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Droid> droids;

    public Team(String name) {
        this.name = name;
        this.droids = new ArrayList<>();
    }

    public void addDroid(Droid droid) {
        if (droid != null) {
            droids.add(droid);
        }
    }

    public int getSize() {
        return droids.size();
    }

    public List<Droid> getDroids() {
        return new ArrayList<>(droids);
    }

    public Droid getDroid(int index) {
        if (index <= 0 || index > droids.size()) {
            return null;
        } else {
            return droids.get(index);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return droids.stream().anyMatch(Droid::isAlive);
    }

    @Override
    public String toString() {
        return String.format("Team: %s, Droids: %s", name, droids);
    }
}
