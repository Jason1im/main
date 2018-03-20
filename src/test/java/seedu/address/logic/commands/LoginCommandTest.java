package seedu.address.logic.commands;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class LoginCommandTest {

    private Model model;

    @Test
    public void execute_validInput_loginSuccessful() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        LoginCommand command = new LoginCommand("JohnDoe", "12345");

        assertExecutionSuccessful(command, String.format(LoginCommand.MESSAGE_SUCCESS, "JohnDoe"));
    }

    @Test
    public void execute_invalidInput_loginFailure() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        //test for invalid username
        LoginCommand command1 = new LoginCommand("...",  "12345");
        assertExecutionFailure(command1, Messages.MESSAGE_INVALID_USERNAME);

        //test for invalid password
        LoginCommand command2 = new LoginCommand("JohnDoe",  "...");
        assertExecutionFailure(command1, Messages.MESSAGE_INVALID_PASSWORD);

    }

    @Test
    public void execute_multipleLogin_loginFailure() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        LoginCommand command = new LoginCommand("JohnDoe", "12345");
        command.execute();
        assertExecutionFailure(command, String.format(LoginCommand.MESSAGE_SUCCESS, "JohnDoe"));
    }

    /**
     * Executes a {@code LoginCommand} and checks that the {@code expectedMessage}
     * is displayed.
     */
    public void assertExecutionSuccessful(LoginCommand command, String expectedMessage) {
        try {
            CommandResult commandResult = command.execute();
            assertEquals(commandResult.feedbackToUser, expectedMessage);
        } catch (CommandException ce) {
            fail("This should not fail");
        }
    }

    /**
     * Executes a {@code LoginCommand} and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    public void assertExecutionFailure(LoginCommand command, String expectedMessage) {
        try {
            command.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException ce) {
            assertEquals(ce.getMessage(), expectedMessage);
        }
    }
}
