package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Shows pir charts presenting the percentage of camp's participant by age, sex and major.
 */

public class StatCommand extends Command {
    public static final String COMMAND_WORD = "stat";
    public static final String MESSAGE_SUCCESS = "The percentage of camp's participant by age, sex and major.";
    public static final String MESSAGE_NO_PERSON = "There is no information in the app.";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (model.isEmpty()) {
            throw new CommandException(MESSAGE_NO_PERSON);
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
