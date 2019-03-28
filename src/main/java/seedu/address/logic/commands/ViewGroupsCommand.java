package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Views all Groups added to the address book.
 */
public class ViewGroupsCommand extends Command {
    public static final String COMMAND_WORD = "view_g";

    public static final String MESSAGE_SUCCESS = "Groups added: \n%1$s";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getGroupNames()));
    }
}
