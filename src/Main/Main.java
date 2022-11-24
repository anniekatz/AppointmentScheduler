/**
 * @author Annie Katz
 * Appointment Scheduler
 */

package Main;

import Database.ConnectDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This is the Main class used to start the Appointment application.
 */
public class Main extends Application {

    /**
     * Start method initializes JavaFX application with login view and resource bundles.
     * @param stage JavaFX Stage to be initialized
     * @throws Exception in case any errors while loading the login page
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent rootScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/LoginPage.fxml")));
        stage.setTitle(ResourceBundle.getBundle("Languages/Lang", Locale.getDefault()).getString("Title"));
        stage.setScene(new Scene(rootScene, 840, 600));
        stage.show();
    }

    /**
     * Main method is used to start the application.
     * It connects to the database upon open and disconnects upon close.
     * @param args String array of args.
     */
    public static void main(String[] args) {
        ConnectDB.dbConnect();
        launch(args);
        ConnectDB.dbDisconnect();
    }
}
