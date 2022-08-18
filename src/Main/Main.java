/* @author Annie Katz
 * Appointment Scheduler
 */

package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/LoginPage.fxml")));
        stage.setTitle("Appointment Scheduler");
        stage.setScene(new Scene(root, 840, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}