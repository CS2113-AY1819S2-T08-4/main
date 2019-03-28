package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Birthday;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Major;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Person;
import seedu.address.model.participant.Phone;
import seedu.address.model.participant.Sex;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleParticipantsUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alicia Pauline";
    public static final String DEFAULT_SEX = "F";
    public static final String DEFAULT_BIRTHDAY = "12345679";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_MAJOR = "Computer Science";
    public static final String DEFAULT_GROUP = "";


    private Name name;
    private Sex sex;
    private Birthday birthday;
    private Phone phone;
    private Email email;
    private Major major;
    private Group group;
    private Set<Tag> tags;

    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        sex = new Sex(DEFAULT_SEX);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        major = new Major(DEFAULT_MAJOR);
        group = new Group(DEFAULT_GROUP);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        sex = personToCopy.getSex();
        birthday = personToCopy.getBirthday();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        major = personToCopy.getMajor();
        group = personToCopy.getGroup();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Person} that we are building.
     */
    public PersonBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleParticipantsUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Group} of the {@code Person} that we are building.
     */
    public PersonBuilder withGroup(String group) {
        this.group = new Group(group);
        return this;
    }

    public Person build() {
        return new Person(name, sex, birthday, phone, email, major, group, tags);
    }

}
