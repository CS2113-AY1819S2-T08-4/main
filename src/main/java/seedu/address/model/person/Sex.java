package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's sex in the address book
 * Can only accept two values M or F
 */
public class Sex {

    public static final String MESSAGE_CONSTRAINTS =
            "Sex should be either M or F, which stands for Male or Female";
    public static final String VALIDATION_REGEX = "[MF]";
    public final String value;

    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        value = sex;
    }

    /**
     * Return true if a given string is a valid sex
     */
    public static boolean isValidSex(String test) {
        return test.matches(VALIDATION_REGEX);
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
