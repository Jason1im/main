package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.exception.DuplicateUsernameException;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a database of registered user accounts
 */
public class AccountsManager implements ReadOnlyAccountsManager {
    private ObservableList<Account> accountList;


    public AccountsManager() {
        accountList = FXCollections.observableArrayList();
    }

    public boolean checkUsername( String username, Account account) {
        return account.getUsername().equals(username);
    }

    public boolean checkPassword( String password, Account account) {
        return account.getPassword().equals(password);
    }

    public void register(String inputUsername, String inputPassword) throws DuplicateUsernameException {
        requireAllNonNull(inputUsername, inputPassword);
        if(!accountList.isEmpty()) {
            for (Account acc : accountList) {
                if (checkUsername(inputUsername, acc))
                    throw new DuplicateUsernameException();
            }
        }
        Account newAccount = new Account(inputUsername, inputPassword);
        accountList.add(newAccount);
    }

    @Override
    public ObservableList<Account> getAccountList() {
        assert CollectionUtil.elementsAreUnique(accountList);
        return FXCollections.unmodifiableObservableList(accountList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccountsManager // instanceof handles nulls
                && this.accountList.equals(((AccountsManager) other).accountList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountList);
    }
}
