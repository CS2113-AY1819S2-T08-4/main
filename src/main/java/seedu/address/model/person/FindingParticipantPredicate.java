package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.participant.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindingParticipantPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FindingParticipantPredicate(List<String> keywords) {
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
                || (other instanceof FindingParticipantPredicate // instanceof handles nulls
                && keywords.equals(((FindingParticipantPredicate) other).keywords)); // state check
    }

}
