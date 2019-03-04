package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Randomly assigns all participants to all available groups.
 * Evenly distributes the participants (number differs from either 1 or 2).
 */
public class RandomizeCommand extends Command {

    public static final String COMMAND_WORD = "randomize";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }

    // This is a test function
    public void testFunction() {
        // test
    }
}
