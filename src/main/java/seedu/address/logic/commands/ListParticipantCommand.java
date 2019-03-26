package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.FreshmanList;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListParticipantCommand extends Command {

    public static final String COMMAND_WORD = "list_f";

    public static final String MESSAGE_SUCCESS = "Listed all freshmen";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        FreshmanList.listFreshmen();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
