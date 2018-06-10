package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Simulation {

    private Item oneItem;
    private Rocket thisU1, thisU2;
    ArrayList<Rocket> arrayU1s = new ArrayList<>();
    ArrayList<Rocket> arrayU2s = new ArrayList<>();
    private int numOfRockets = 0;

    private File phase1Resources = new File("./resources/phase-1.txt");
    private File phase2Resources = new File("./resources/phase-2.txt");

    public ArrayList loadItems(int phase) {
        // System.out.println("loadItems");
        if (phase == 1) {
            // System.out.println("loadItems phase 1");
            ArrayList<Item> P1LoadList = new ArrayList();                   // phase 1 load list
            try (Scanner scanner1 = new Scanner(phase1Resources)) {
                int index = 0;
                String name;
                int mass;
                while (scanner1.hasNextLine()) {
                    String line = scanner1.nextLine();
                    String[] part = line.split("\\=");
                    // String[] part = line.split("(?<=\\D)(?=\\d)");
                    name = part[0].trim();
                    mass = Integer.parseInt(part[1]);
                    oneItem = new Item(name, mass);
                    P1LoadList.add(oneItem);
                    index++;
                }
            } catch (FileNotFoundException b) {
                System.out.println("There is no list");
            }

            System.out.println(P1LoadList);
            // System.out.println("return P1LoadList");
            return P1LoadList;
        } else {
            // System.out.println("loadItems phase 2");
            ArrayList<Item> P2LoadList = new ArrayList();                       // phase 2 load list
            try (Scanner scanner2 = new Scanner(phase2Resources)) {
                int index = 0;
                String name;
                int mass;
                while (scanner2.hasNextLine()) {
                    String line = scanner2.nextLine();
                    String[] part = line.split("\\=");
                    // String[] part = line.split("(?<=\\D)(?=\\d)");
                    name = part[0].trim();
                    mass = Integer.parseInt(part[1]);
                    oneItem = new Item(name, mass);
                    P2LoadList.add(oneItem);
                    index++;
                }
            } catch (FileNotFoundException b) {
                System.out.println("There is no list");
            }
            // System.out.println(P2LoadList);
            // System.out.println("return P2LoadList");
            return P2LoadList;
        }
    }

    public ArrayList loadU1(List<Item> toLoadList) {
        // System.out.println("loadU1");
        System.out.println(toLoadList);
        // System.out.println("toLoadList size is : " + toLoadList.size());
        // System.out.println("Size of arrayU1s is : " + arrayU1s.size());
        int listIndex = 0;
        thisU1 = new U1();                                          // build a new rocket
        int numberOfU1s = 1;
        System.out.println("\nHAVE BUILT ROCKET NUMBER 1\n");
        while (listIndex < toLoadList.size()) {                     // while there's still stuff to load
            System.out.println("Item number : " + listIndex);
            Item thisItem = toLoadList.get(listIndex);              // pick the first item from the list
            if (thisU1.canCarry(thisItem, 8000)) {                // if the rocket can add the item
                thisU1.carry(thisItem);                       // load it
                System.out.println("Have loaded " + thisItem.name + " onto rocket number " + numberOfU1s + ".");
                System.out.println("This rocket's load is now " + thisU1.load + " kg.\n");
                listIndex++;                   // increment index
            } else {                                        // otherwise TODO look for lighter item to fit
                arrayU1s.add(thisU1);                        // add the rocket to the arrayList of rockets
                // System.out.println("Size of arrayU1s is : " + arrayU1s.size());
                thisU1 = new U1();                          // and build a new rocket
                numberOfU1s++;                              // add to the count of rockets
                System.out.println("HAVE BUILT ROCKET NUMBER " + numberOfU1s + ".\n");
            }
        }
        arrayU1s.add(thisU1);                        // add the LAST rocket to the arrayList of rockets
        System.out.println("Have built and loaded " + numberOfU1s + " U1 rockets.\n");
        // System.out.println("return arrayU1s");
        // System.out.println("Size of arrayU1s is : " + arrayU1s.size());
        return arrayU1s;
    }

    public ArrayList loadU2(List<Item> toLoadList) {
        // System.out.println("loadU2");
        System.out.println(toLoadList);
        // System.out.println("toLoadList size is : " + toLoadList.size());
        // System.out.println("Size of arrayU2s is : " + arrayU2s.size());
        int listIndex = 0;
        thisU2 = new U2();                                          // build a new rocket
        int numberOfU2s = 1;
        System.out.println("\nHAVE BUILT ROCKET NUMBER 1\n");
        while (listIndex < toLoadList.size()) {                     // while there's still stuff to load
            System.out.println("Item number : " + listIndex);
            Item thisItem = toLoadList.get(listIndex);              // pick the first item from the list
            if (thisU2.canCarry(thisItem, 11000)) {                // if the rocket can add the item
                thisU2.carry(thisItem);                       // load it
                System.out.println("Have loaded " + thisItem.name + " onto rocket number " + numberOfU2s + ".");
                System.out.println("This rocket's load is now " + thisU2.load + " kg.\n");
                listIndex++;                   // increment index
            } else {                                        // otherwise TODO look for lighter item to fit
                arrayU2s.add(thisU2);                        // add the rocket to the arrayList of rockets
                // System.out.println("Size of arrayU2s is : " + arrayU2s.size());
                thisU2 = new U2();                          // and build a new rocket
                numberOfU2s++;                              // add to the count of rockets
                System.out.println("HAVE BUILT ROCKET NUMBER " + numberOfU2s + ".\n");
            }
        }
        arrayU2s.add(thisU2);                        // add the LAST rocket to the arrayList of rockets
        System.out.println("Have built and loaded " + numberOfU2s + " U2 rockets.\n");
        // System.out.println("return arrayU2s");
        // System.out.println("Size of arrayU2s is : " + arrayU2s.size());
        return arrayU2s;
    }

    public int runSimulation(ArrayList<Rocket> rocketList) {
        // System.out.println("runSimulation");
        int budget = 0;
        int rocketsUsed = 0;
        int missionCount = 0;
        for (Rocket thisRocket : rocketList) {               // for each rocket in the list
            boolean deliveryComplete = false;                   // initialise delivery
            missionCount++;                                     // add to mission count
            System.out.println("\nMars delivery # " + missionCount + " prepared.");
            System.out.println("This rocket-- ");
            while (!deliveryComplete) {                                 // keep looping until delivery success
                Rocket cloneRocket = thisRocket;                            // prepare a backup rocket
                if (!thisRocket.launch(thisRocket.load)) {                  // if launch fails
                    System.out.println("  Rocket destroyed on launch!");        // oh bother
                    rocketsUsed++;                                              //  add mission to count of rockets
                    budget += thisRocket.rocketCost;                            // add mission cost to budget
                    thisRocket = cloneRocket;                                   // deploy the backup rocket
                    System.out.println("Replacement rocket prepared.");
                    ArrayList<Rocket> replacements = new ArrayList<Rocket>();   // create a List of replacements
                    replacements.add(thisRocket);                               // add the rocket to the replacements list
                    runSimulation(replacements);                                // recursively launch this rocket
                    replacements.remove(0);                               // removes rocket from replacement list
                    rocketsUsed++;                                              //  add mission to count of rockets
                    budget += thisRocket.rocketCost;                            // add mission cost to budget
                    break;
                } else if (!thisRocket.land(thisRocket.load)) {              // if landing fails
                    System.out.println("  Rocket destroyed on landing!");       // oh bother
                    rocketsUsed++;                                              //  add mission to count of rockets
                    budget += thisRocket.rocketCost;                            // add mission cost to budget
                    thisRocket = cloneRocket;                                   // deploy the backup rocket
                    System.out.println("Replacement rocket prepared.");
                    ArrayList<Rocket> replacements = new ArrayList<Rocket>();   // create a List of replacements
                    replacements.add(thisRocket);                               // add the rocket to the replacements list
                    runSimulation(replacements);                                // recursively launch this rocket
                    replacements.remove(0);                               // removes rocket from replacement list
                    rocketsUsed++;                                              //  add mission to count of rockets
                    budget += thisRocket.rocketCost;                            // add mission cost to budget
                    break;
                } else {                                                // BUT if neither fail
                    System.out.println("Delivery complete.");
                    deliveryComplete = true;                                // delivery is successful (both launch & land)
                    rocketsUsed++;                                          //  add mission to count of rockets
                    budget += thisRocket.rocketCost;                        // add mission cost to budget
                }
            }
            System.out.println("That's rockets: " + rocketsUsed + "  and  $ " + budget + "M  in this phase.\n");
        }
        // System.out.println("return budget");
        return budget;
    }

  /*  public void loadU1Phase1() {
        // System.out.println("loadU1Phase1");
        ArrayList toLoadList = loadItems(1);
        loadU1(toLoadList);
    }

    public void loadU1Phase2() {
        // System.out.println("loadU1Phase2");
        ArrayList toLoadList = loadItems(2);
        loadU1(toLoadList);
    }*/

    public int U1simulation() {
        int expenditure = 0;
        // System.out.println("U1simulation");
        expenditure = runSimulation(arrayU1s);
        return expenditure;
    }

    public int U2simulation() {
        int expenditure = 0;
        // System.out.println("U2simulation");
        expenditure = runSimulation(arrayU2s);
        return expenditure;
    }
}
