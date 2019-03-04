package seedu.address.model.role;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Participant(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
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
