package seedu.address.model.role;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.privilege.Privilege;
import seedu.address.model.tag.Tag;

/**
 * Project Director for FOP.
 */
public class ProjectDirector extends Participant {

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public ProjectDirector(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        super.setPrivilege(Privilege.PROJECT_DIRECTOR);
    }
}
