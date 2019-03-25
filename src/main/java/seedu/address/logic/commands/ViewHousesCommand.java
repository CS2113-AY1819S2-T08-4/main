package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Views all Houses added to the address book.
 */
public class ViewHousesCommand extends Command {
    public static final String COMMAND_WORD = "view_h";

    public static final String MESSAGE_SUCCESS = "Houses: %1$s";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getHousesNames()));
    }
}
