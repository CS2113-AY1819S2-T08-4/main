package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ParticipantAddressBook;
import seedu.address.model.ReadOnlyAddressBook;

import seedu.address.model.grouping.Group;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ParticipantAddressBook} with sample data.
 */
public class SampleParticipantDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Sex("M"), new Birthday("01021996"),
                    new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Major("Computer Science"), new Group("G1"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Sex("M"), new Birthday("01021996"),
                    new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Major("Information System"), new Group("G1"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Sex("M"), new Birthday("01021996"),
                    new Phone("93210283"),
                    new Email("charlotte@example.com"), new Major("Computer Engineering"), new Group("G1"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Sex("M"), new Birthday("01021996"),
                    new Phone("91031282"),
                    new Email("lidavid@example.com"), new Major("Business Analytic"), new Group("G1"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Sex("M"), new Birthday("01021996"),
                    new Phone("92492021"),
                    new Email("irfan@example.com"), new Major("Information Security"), new Group("G1"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Sex("M"), new Birthday("01021996"),
                    new Phone("92624417"),
                    new Email("royb@example.com"), new Major("Computer Science"), new Group("G1"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        ParticipantAddressBook sampleAb = new ParticipantAddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
