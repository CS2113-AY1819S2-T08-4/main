package seedu.address.model.participant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.Year;
import java.util.Calendar;


/**
 * Represents a Participant's birthday in the address book
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should contain eight numbers in the format of DDMMYYYY and must be a valid date.";
    public static final String VALIDATION_REGEX = "\\d{8,}";
    public final String value;

    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        value = birthday;
    }

    /**
     * Return true if a given string is a valid sex
     */
    public static boolean isValidBirthday(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        int date = Integer.parseInt(test.substring(0, 2));
        int month = Integer.parseInt(test.substring(2, 4));
        int year = Integer.parseInt(test.substring(4));
        if (date > 31 || date < 1) { //invalid date
            return false;
        }
        if (month > 12 || month < 1) { //invalid month
            return false;
        }
        /**
         * Valid DOB is based on the following:
         * - Nobody is born in the future
         * - No undergraduate is older than 50 years old
         */
        if (year > Year.now().getValue() || year < (Year.now().getValue() - 50)) { //invalid year
            return false;
        }
        return true;
    }

    public String getAge() {
        int yearOfBirth = Integer.parseInt(value.substring(4));
        return Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth);
    }

    public String getFormattedBirthday () {
        return value.substring(0, 2) + "/" + value.substring(2, 4) + "/" + value.substring(4, 8);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }
}
