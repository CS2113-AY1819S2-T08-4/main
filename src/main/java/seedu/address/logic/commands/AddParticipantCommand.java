package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Person;
import seedu.address.model.role.Participant;

/**
 * Adds a freshman to the address book.
 */
public class AddParticipantCommand extends AddCommand {

    public static final String COMMAND_WORD = "add_f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a freshman to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SEX + "SEX "
            + PREFIX_BIRTHDAY + "BIRTHDAY "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_MAJOR + "MAJOR "
            + PREFIX_GROUP + "GROUP"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_SEX + "M "
            + PREFIX_BIRTHDAY + "27071999 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_MAJOR + "Information System "
            + PREFIX_GROUP + "G1 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New freshman added: %1$s";
    private static final String MESSAGE_DUPLICATE_FRESHMAN = "This freshman already exists in the address book";

    private final Participant toAdd;

    /**
     * Creates an AddParticipantCommand to add the specified {@code Participant}
     */

    public AddParticipantCommand(Participant person) {
        super(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FRESHMAN);
        }
        if (toAdd.getGroup().getGroupName().equals("")) {
            model.addPerson(toAdd);
            model.commitAddressBook();
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }

        if (!model.hasGroup(toAdd.getGroup())) {
            throw new CommandException(MESSAGE_NONEXISTENT_GROUP);
        }

        Group updatedGroup = model.getGroup(toAdd.getGroup());
        Person toAddUpdated = new Person(toAdd.getName(), toAdd.getSex(), toAdd.getBirthday(), toAdd.getPhone(),
                toAdd.getEmail(), toAdd.getMajor(), updatedGroup, toAdd.getTags());
        model.addPerson(toAddUpdated);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddUpdated));
    }
}
