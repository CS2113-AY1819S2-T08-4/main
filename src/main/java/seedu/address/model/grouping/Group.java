package seedu.address.model.grouping;

/**
 * Represents a person's camp grouping
 * Can only be created within a house that has already been created.
 */

public class Group {

    public static final String MESSAGE_CONSTRAINTS =
            "Group can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String groupName;
    private String houseName;

    public Group (String groupName) {
        this.groupName = groupName;
    }


    public Group (String groupName, String houseName) {
        this.houseName = houseName;
        this.groupName = groupName;
    }

    public String getGroupName () {
        return groupName;
    }

    public String getHouseName() {
        return houseName;
    }

    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.groupName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && groupName.equals(((Group) other).groupName)); // state check
    }
}
