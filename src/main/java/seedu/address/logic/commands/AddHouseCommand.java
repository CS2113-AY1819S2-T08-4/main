package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.House;

/**
 * Adds a House in the address book.
 */

public class AddHouseCommand extends Command {

    public static final String COMMAND_WORD = "add_h";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new House. Parameters: houseName";

    public static final String MESSAGE_SUCCESS = "New House added: %1$s";
    public static final String MESSAGE_DUPLICATE_HOUSE = "This House already exists";

    private String houseName;

    public AddHouseCommand(String houseName) {
        requireNonNull(houseName);
        this.houseName = houseName;
    }

    public String getHouseName() {
        return houseName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasHouse(new House(houseName))) {
            throw new CommandException(MESSAGE_DUPLICATE_HOUSE);
        }

        model.addHouse(new House(houseName));
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, houseName));
    }
}
