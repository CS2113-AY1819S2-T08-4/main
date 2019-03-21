package seedu.address.model.participant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.grouping.Group;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Sex sex;
    private final Birthday birthday;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Major major;
    private final Group group;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Sex sex, Birthday birthday, Phone phone, Email email,
                  Major major, Group group, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, major, tags);
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.major = major;
        this.group = group;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Major getMajor() {
        return major;
    }

    public Group getGroup() {
        return group;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone())
                || otherPerson.getEmail().equals(getEmail())
                || otherPerson.getBirthday().equals(getBirthday()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getSex().equals(getSex())
                && otherPerson.getBirthday().equals((getBirthday()))
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getMajor().equals(getMajor())
                && otherPerson.getGroup().equals(getGroup())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, sex, birthday, phone, email, major, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Sex: ")
                .append(getSex())
                .append(" Birthday: ")
                .append(getBirthday())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Major: ")
                .append(getMajor())
                .append(" Group: ")
                .append(getGroup())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
