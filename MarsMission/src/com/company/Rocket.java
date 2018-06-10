package com.company;

import java.util.ArrayList;

public class Rocket implements SpaceShip {

    int rocketCost ;
    double load;                                       // The mass of the current payload
    double maxLoad;
    double launchRisk ;
    double crashRisk ;
    ArrayList loadInventory = new ArrayList();

    @Override
    public boolean launch(double load) {
        return true;
    }

    @Override
    public boolean land(double load) {
        return true;
    }

    @Override
    public boolean canCarry(Item thisItem, int maxLoad) {

        System.out.println("Load : " + load + " kg   Item mass : " + thisItem.mass + " kg   maxLoad : " + maxLoad + " kg");
        System.out.print("Can Carry?");
        if (load + thisItem.mass <= maxLoad) {
            System.out.println("   YES");
            return true;
        } else{
            System.out.println("    NO can't carry\n");
            return false;
        }
    }

    @Override
    public int carry(Item thisItem) {
        // System.out.println("carry");
        load += thisItem.mass;
        return 0;
    }
}
