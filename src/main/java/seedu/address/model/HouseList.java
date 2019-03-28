package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.grouping.House;

/**
 * Adds House Names to the list
 */
public class HouseList {

    private static ArrayList<House> houses = new ArrayList<>();

    /**
     * Constructs a List for storing house.
     */
    public static ArrayList<House> getHouses() {
        return houses;
    }

    /**
     * Adds a house
     */
    public static void addHouse(String nameToAdd) {
        houses.add(new House(nameToAdd));
    }

    /**
     * Deletes a house
     */
    public static void deleteHouse(String nameToDelete) {
        houses.remove(new House(nameToDelete));
    }

    /**
     * Checks if a house exists
     */
    public static boolean hasHouse (String toFind) {
        return houses.contains(new House(toFind));
    }

    /**
     * Prints the list
     */
    public static void listHouse() {
        for (int i = 0; i < houses.size(); i++) {
            System.out.println(houses.get(i));
            System.out.println();
        }
    }

}
