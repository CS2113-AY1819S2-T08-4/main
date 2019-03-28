package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;

/**
 * Adds a group to an existing house in the address book.
 */

public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "add_g";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a group into a House. Parameters: "
            + "groupName + houseName";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_NONEXISTENT_HOUSE = "This House does not exist. Create House first!";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists";

    private static String groupName;
    private static String houseName;

    public AddGroupCommand(String groupName, String houseName) {
        requireNonNull(groupName, houseName);
        this.groupName = groupName;
        this.houseName = houseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getHouseName() {
        return houseName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        Group toAddGroup = new Group(groupName, houseName);

        if (!model.hasHouse(new House(houseName))) {
            throw new CommandException(MESSAGE_NONEXISTENT_HOUSE);
        }

        House baseHouse = model.getHouse(new House(houseName));

        if (baseHouse.hasGroup(groupName)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(new Group(groupName, houseName));
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName));
    }
}
