package seedu.address.storage;

import java.util.ArrayList;

import seedu.address.model.grouping.House;

/**
 * Stores the list of Houses that have been created, with its (if any) constituent groups.
 */
public class HouseStorage {

    //Stores house names, and houses of type House.
    //Indices of the same houses will always match.
    private static ArrayList<String> houseNames = new ArrayList<>();
    private static ArrayList<House> houses = new ArrayList<>();

    /**
     * Constructs a HouseStorage object.
     */
    public HouseStorage() {
    }

    public static ArrayList<House> getHouses() {
        return houses;
    }

    /**
     * Adds a house that does not yet exist
     */
    public static void addHouse(String nameToAdd) {
        houseNames.add(nameToAdd);
        houses.add(new House(nameToAdd));
    }

    /**
     * Returns true if House exists
     * Group can be created
     */
    public static boolean hasHouse (String toFind) {
        return houseNames.contains(toFind);
    }

    /**
     * Returns house object
     */
    public static House getHouse(String toGet) {
        return houses.get(houseNames.indexOf(toGet));
    }

    /**
     * Adds a valid group to the House specified
     */
    public static void addGroup(String groupName, String houseName) {
        int houseIndex = houseNames.indexOf(houseName);
        House tempStorage = houses.get(houseIndex);
        tempStorage.addGroup(groupName);
        houses.set(houseIndex, tempStorage);
    }
}
