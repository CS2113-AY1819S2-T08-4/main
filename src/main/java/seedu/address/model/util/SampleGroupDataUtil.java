package seedu.address.model.util;

import seedu.address.model.grouping.Group;

/**
 * Sample group data
 */
public class SampleGroupDataUtil {
    public static Group[] getSampleGroup() {
        return new Group[]{
                new Group("R1", "Red"),
                new Group("R2", "Red"),
                new Group("B1", "Blue"),
                new Group("B2", "Blue"),
        };
    }
}
