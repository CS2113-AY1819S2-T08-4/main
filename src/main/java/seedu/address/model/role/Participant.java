package seedu.address.model.role;

import java.util.Set;

import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Birthday;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Major;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Person;
import seedu.address.model.participant.Phone;
import seedu.address.model.participant.Sex;
import seedu.address.model.privilege.Privilege;
import seedu.address.model.tag.Tag;

/**
 * Participant for FOP.
 *
 * By default everybody will be a participant unless assigned.
 */
public class Participant extends Person {

    private Privilege privilege;

    /**
     * Every field must be present and not null.
     *  @param name
     * @param sex
     * @param birthday
     * @param phone
     * @param email
     * @param major
     * @param group
     * @param tags
     */
    public Participant(Name name, Sex sex, Birthday birthday, Phone phone, Email email,
                       Major major, Group group, Set<Tag> tags) {
        super(name, sex, birthday, phone, email, major, group, tags);
        // By default everybody is a participant
        this.privilege = Privilege.PARTICIPANTS;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Privilege getPrivilege() {
        return this.privilege;
    }
}
