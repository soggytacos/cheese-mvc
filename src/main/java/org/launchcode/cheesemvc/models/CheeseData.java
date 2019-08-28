package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<Cheese>();

    //getAll
    public static ArrayList<Cheese> getAll() {
        return cheeses;
    }

    // add
    public static void add(Cheese newCheese) {
        cheeses.add(newCheese);
    }

    // remove
    public static void remove(int id) {
        Cheese cheeseToRemove = getById(id);
        cheeses.remove(cheeseToRemove);
    }

    //getById retrieve the ID of the cheese in order to remove it. Even cheeses with the same name will have unique IDs
    public static Cheese getById(int id) {

        Cheese theCheese = null;
        for (Cheese candidateCheese : cheeses) {
            if (candidateCheese.getCheeseId() == id) {
                theCheese = candidateCheese;
            }
        }
        return theCheese;

    }

}
