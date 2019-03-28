package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddParticipantCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Birthday;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Major;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Phone;
import seedu.address.model.participant.Sex;
import seedu.address.model.role.Participant;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddParticipantCommand object
 */
public class AddParticipantCommandParser extends AddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddParticipantCommand
     * and returns an AddParticipantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddParticipantCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SEX, PREFIX_BIRTHDAY, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_MAJOR, PREFIX_GROUP, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SEX, PREFIX_BIRTHDAY, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_MAJOR, PREFIX_GROUP) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddParticipantCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Sex sex = ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Major major = ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get());
        Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Participant person = new Participant(name, sex, birthday, phone, email, major, group, tagList);
        return new AddParticipantCommand(person);
    }
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

