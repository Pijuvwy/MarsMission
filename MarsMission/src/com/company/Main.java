package com.company;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        int repetitions = 10;                                           // how many times to repeat simulation
                                                                        // to get average
        int u1RunningExpendTotal = 0;
        for (int u1sims = 0; u1sims < repetitions; u1sims++) {          // run multiple simulations with U1 rockets
            int TotalExpenditureU1 = 0;
            ArrayList<Rocket> p1Used;
            ArrayList<Rocket> p2Used;
            ArrayList<Rocket> p1And2Used = new ArrayList<>();
            //int p1RocketsUsed = 0;
            //int p2RocketsUsed = 0;
            //int TotalRocketsUsed =0;

            Simulation thisU1Simulation = new Simulation();
            System.out.println("\nStart simulation");
            ArrayList<Item> P1LoadList = thisU1Simulation.loadItems(1);
            ArrayList<Item> P2LoadList = thisU1Simulation.loadItems(2);
            p1Used = thisU1Simulation.loadU1(P1LoadList);
            p2Used = thisU1Simulation.loadU1(P2LoadList);

    /*    System.out.println("Phase 1 rockets built : " + p1Used.size() + "\n");
        System.out.println("Phase 2 rockets built : " + p2Used.size() + "\n");
        p1And2Used.addAll(p1Used);
        p1And2Used.addAll(p2Used);
        System.out.println("Total rockets built : " + p1And2Used.size() + "\n");*/

            System.out.println("\n");
            int expenditure = thisU1Simulation.U1simulation();
            TotalExpenditureU1 = TotalExpenditureU1 + expenditure;
            System.out.println("Budget expended : $ " + TotalExpenditureU1 + " million");
            u1RunningExpendTotal = u1RunningExpendTotal + TotalExpenditureU1;   // tally the expenditure for all runs
        }

        int u2RunningExpendTotal = 0;
        for (int u1sims = 0; u1sims < repetitions; u1sims++) {                    // run simulations with U2 rockets
            int TotalExpenditureU2 = 0;
            ArrayList<Rocket> p1Used;
            ArrayList<Rocket> p2Used;
            ArrayList<Rocket> p1And2Used = new ArrayList<>();
            //int p1RocketsUsed = 0;
            //int p2RocketsUsed = 0;
            //int TotalRocketsUsed =0;

            Simulation thisU2Simulation = new Simulation();
            System.out.println("\nStart simulation");
            ArrayList<Item> P1LoadList = thisU2Simulation.loadItems(1);
            ArrayList<Item> P2LoadList = thisU2Simulation.loadItems(2);
            p1Used = thisU2Simulation.loadU2(P1LoadList);
            p2Used = thisU2Simulation.loadU2(P2LoadList);
            System.out.println("\n");
            int expenditure = thisU2Simulation.U2simulation();
            TotalExpenditureU2 = TotalExpenditureU2 + expenditure;
            System.out.println("Budget expended : $ " + TotalExpenditureU2 + " million");
            u2RunningExpendTotal = u2RunningExpendTotal + TotalExpenditureU2;   // tally the expenditure for all runs
        }
        System.out.println("\nU1: Average Budget expended : $ " + u1RunningExpendTotal / repetitions + " million");
        System.out.println("\nU2: Average Budget expended : $ " + u2RunningExpendTotal / repetitions + " million");
    }
}
