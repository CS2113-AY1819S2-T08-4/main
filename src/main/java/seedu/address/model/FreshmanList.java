package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.role.Participant;

/**
 * Adds Freshmen Names to the list
 */
public class FreshmanList {
    private static ArrayList<String> freshmenNames = new ArrayList<>();
    private static ArrayList<Participant> freshmen = new ArrayList<>();

    /**
     * Constructs a List for storing freshmen.
     */
    public static ArrayList<Participant> getFreshmen() {
        return freshmen;
    }

    /**
     * Adds a freshman
     */
    public static void addFreshman(String nameToAdd) {
        freshmenNames.add(nameToAdd);
    }
    /**
     * Deletes a freshman
     */
    public static void deleteFreshman(String nameToDelete) {
        freshmenNames.remove(nameToDelete);
    }
    /**
     * Checks if a freshman exists
     */
    public static boolean hasFreshman (String toFind) {
        return freshmenNames.contains(toFind);
    }
    /**
     * Prints the list
     */
    public static void listFreshmen() {
        for (int i = 0; i < freshmen.size(); i++) {
            System.out.println(freshmen.get(i));
            System.out.println();
        }
    }
}
