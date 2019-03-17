package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.storage.OglList;

/**
 * Lists all persons in the address book to the user.
 */
public class ListOglCommand extends Command {

    public static final String COMMAND_WORD = "list_ogl";

    public static final String MESSAGE_SUCCESS = "Listed all OGLs";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        OglList.listOgl();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}