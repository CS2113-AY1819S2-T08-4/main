package seedu.address.model.role;

import java.util.Set;

import seedu.address.model.grouping.Group;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.privilege.Privilege;
import seedu.address.model.tag.Tag;


/**
 * HouseHead for FOP.
 */
public class HouseHead extends Participant {


    /**
     * Every field must be present and not null.
     * @param name
     * @param sex
     * @param birthday
     * @param phone
     * @param email
     * @param major
     * @param group
     * @param tags
     */
    public HouseHead(Name name, Sex sex, Birthday birthday, Phone phone, Email email,
                     Major major, Group group, Set<Tag> tags) {
        super(name, sex, birthday, phone, email, major, group, tags);
        super.setPrivilege(Privilege.HOUSE_HEAD);
    }
}
