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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Evenly distribute all participants across all groups.";

    public static final String MESSAGE_SUCCESS = "Participants are evenly distributed";
    public static final String MESSAGE_FAILURE = "Unable to distribute participants";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        /*if(false){
            throw new CommandException((MESSAGE_FAILURE));
        }*/
        return new CommandResult(MESSAGE_SUCCESS);
    }

    // Temporary function to be integrated
    public void testRandomize() {

    }
}
