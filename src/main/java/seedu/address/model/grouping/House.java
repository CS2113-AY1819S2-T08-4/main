package seedu.address.model.grouping;

import java.util.ArrayList;

/**
 * Represents houses in a camp.
 * A House has multiple groups within it.
 */

public class House {

    private String houseName;
    private ArrayList<Group> groups;

    /**
     * Constructs an {@code House}.
     */
    public House (String name) {
        houseName = name;
        groups = new ArrayList<>();
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    // ========== Group Operations ==========

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
        if (hasGroup(groupName)) {
            return;
        }
        groups.add(new Group(groupName, getHouseName()));
    }

    /**
     * Remove a group from the house
     */
    public void removeGroup(String groupName) {
        groups.remove(new Group(groupName, getHouseName()));
    }

    /**
     * Returns true if Group exists in House
     */

    public boolean hasGroup(String groupName) {
        return groups.contains(new Group(groupName, getHouseName()));
    }

    /**
     * Returns true if both houses of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two houses.
     */
    public boolean isSameHouse(House otherHouse) {
        if (otherHouse == this) {
            return true;
        }

        return otherHouse != null
                && otherHouse.equals(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof House // instanceof handles nulls
                && this.getHouseName().equals(((House) obj).getHouseName())); // state check
    }
}
