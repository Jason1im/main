package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

public class LoginCommand {
    public static final String COMMAND_WORD = "login";

    public static final String COMMAND_SYNTAX = COMMAND_WORD + " "
            + PREFIX_USERNAME + " "
            + PREFIX_PASSWORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Logs the user into contactHero."
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD\n"
            + "Example: " + COMMAND_WORD+ " "
            + PREFIX_USERNAME + "JohnDoe "
            + PREFIX_PASSWORD + "98765432 ";
    public static final String MESSAGE_SUCCESS = "You have successfully logined!\n";

    @Override
    public CommandResult execute() {

    }
}
