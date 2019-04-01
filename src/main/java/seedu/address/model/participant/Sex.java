package seedu.address.model.participant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Person's sex in the address book
 * Can only accept two values M or F
 */
public class Sex {

    public static final String MESSAGE_CONSTRAINTS =
            "Sex should be either M, F or O, which stands for Male, Female, and Other";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private static final ArrayList<String> validSex = new ArrayList<>(Arrays.asList("MALE", "FEMALE", "OTHER",
            "M", "F", "O"));
    public final String value;

    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        String cleanSex = "";
        if (sex.equalsIgnoreCase("F") || sex.equalsIgnoreCase("FEMALE")) {
            cleanSex = "Female";
        } else if (sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("MALE")) {
            cleanSex = "Male";
        } else {
            cleanSex = "Other";
        }

        value = cleanSex;
    }

    /**
     * Return true if a given string is a valid sex
     */
    public static boolean isValidSex(String test) {
        return test.matches(VALIDATION_REGEX) && validSex.contains(test.toUpperCase());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sex // instanceof handles nulls
                && value.equals(((Sex) other).value)); // state check
    }
}
