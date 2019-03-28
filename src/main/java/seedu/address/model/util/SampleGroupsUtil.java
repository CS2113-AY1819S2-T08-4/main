package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.grouping.Group;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleGroupsUtil {

    public static Group[] getSampleGroups() {
        return new Group[]{
            new Group("R1", "Red"),
            new Group("R2", "Red"),
            new Group("B1", "Blue"),
            new Group("B2", "Blue"),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }
        return sampleAb;
    }

}
