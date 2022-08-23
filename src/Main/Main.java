/* @author Annie Katz
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

// Main class to start the Appointment application
public class Main extends Application {

    // Method to initialize JavaFX application with login view and resource bundles
    @Override
    public void start(Stage stage) throws Exception {
        Parent rootScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/LoginPage.fxml")));
        stage.setTitle(ResourceBundle.getBundle("Languages/Lang", Locale.getDefault()).getString("Title"));
        stage.setScene(new Scene(rootScene, 840, 600));
        stage.show();
    }

    // Main method connects to database and closes connection when application is closed
    public static void main(String[] args) {
        ConnectDB.dbConnect();
        launch(args);
        ConnectDB.dbDisconnect();
    }
}