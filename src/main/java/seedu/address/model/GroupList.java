package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.grouping.Group;

/**
 * Adds Group Names to the list
 */
public class GroupList {

    private static ArrayList<Group> groups = new ArrayList<>();

    /**
     * Constructs a List for storing group.
     */
    public static ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Adds a group
     */
    public static void addGroup(String nameToAdd) {
        groups.add(new Group(nameToAdd));
    }

    /**
     * Deletes a group
     */
    public static void deleteGroup(String nameToDelete) {
        groups.remove(new Group(nameToDelete));
    }

    /**
     * Checks if a group exists
     */
    public static boolean hasGroup (String toFind) {
        return groups.contains(new Group(toFind));
    }

    /**
     * Prints the list
     */
    public static void listGroup() {
        for (int i = 0; i < groups.size(); i++) {
            System.out.println(groups.get(i));
            System.out.println();
        }
    }

}
