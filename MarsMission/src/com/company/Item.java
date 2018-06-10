package com.company;

public class  Item {

    public String name;
    public int mass;

    public Item(String name, int mass) {
        this.name = name;
        this.mass = mass;
    }

    @Override
    public String toString() {
        return name + " " + mass;
    }

/*    public String getName(String line) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMass(String line) {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }*/
}
