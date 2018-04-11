package seedu.address.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui {
    /**
     * Starts the UI (and the App).
     * @param type determines if this method is use for test purpose or not.
     */
    void start(Stage primaryStage, int type);

    /** Stops the UI. */
    void stop();

}
