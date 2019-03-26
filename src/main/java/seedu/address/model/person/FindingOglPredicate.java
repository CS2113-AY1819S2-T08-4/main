package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindingOglPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FindingOglPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword-> StringUtil.containsWordIgnoreCase(person.getStringTags(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindingOglPredicate // instanceof handles nulls
                && keywords.equals(((FindingOglPredicate) other).keywords)); // state check
    }

}
