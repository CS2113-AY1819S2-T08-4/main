package seedu.address.model.grouping;

import java.util.ArrayList;

/**
 * Represents houses in a camp.
 * A House has multiple groups within it.
 */

public class House {

    private String houseName;
    private ArrayList<Group> groups = new ArrayList<>();

    /**
     * Constructs an {@code House}.
     */
    public House (String name) {
        houseName = name;
    }

    public String getHouseName() {
        return houseName;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String getGroupNames() {
        String groupNames = "";

        if (groups.isEmpty()) {
            groupNames += getHouseName() + ": empty";
        } else {
            groupNames += getHouseName() + ":";
        }

        for (Group temp : groups) {
            groupNames += " " + temp.getGroupName();
        }
        return groupNames;
    }

    /**
     * Adds a new group to the house
     */
    public void addGroup (String groupName) {
        Group newGroup = new Group(groupName, houseName);
        groups.add(newGroup);
    }

    /**
     * Returns true if Group exists in House
     */
    public boolean hasGroup (String groupName) {
        for (Group test : groups) {
            if (test.getGroupName().equalsIgnoreCase(groupName)) {
                return true;
            }
        }
        return false;
    }
}
