package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LoginCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        SignupCommand command = new SignupCommand("John", "123");
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        command.execute();
    }


    @Test
    public void constructor_nullValues_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new LoginCommand(null, "123");
        new LoginCommand("John", null);
    }

    @Test
    public void execute_loginSuccessful() throws Exception {
        String inputUsername = "John";
        String inputPassword = "123";

        CommandResult commandResult = new LoginCommand(inputUsername, inputPassword).execute();

        assertEquals(SignupCommand.MESSAGE_SUCCESS, commandResult.feedbackToUser);
    }

    @Test
    public void execute_invalidInputs_loginFail() throws Exception {

        // incorrect username
        CommandResult commandResult = new LoginCommand("Jane", "123").execute();
        assertEquals(LoginCommand.IN, commandResult.feedbackToUser);

        // incorrect password
        CommandResult commandResult = new LoginCommand("John", "234").execute();
        assertEquals(LoginCommand.MESSAGE_IN, commandResult.feedbackToUser);
    }

    @Test
    public void execute_MultipleLogin_throwsCommandException() throws Exception {
        String inputUsername = "John";
        String inputPassword = "123";

        thrown.expect(CommandException.class);
        thrown.expectMessage(LoginCommand.MESSAGE_MULTIPLE_LOGIN);

        new LoginCommand(inputUsername, inputPassword).execute();
        new LoginCommand(inputUsername, inputPassword).execute();
    }

}