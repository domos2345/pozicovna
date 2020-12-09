package pozicovna.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoggedOutSceneContoller {
    @FXML
    private TextField searchTextField;


    @FXML
    void initialize() {

    }

    @FXML
    void registrationButtonClick(ActionEvent event) {
        try {
            RegistrationSceneController controller = new RegistrationSceneController();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Register.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registracia");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signInButtonClick(ActionEvent event) {
        try {
            SignInSceneController controller = new SignInSceneController();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("SignIn.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Prihlasovanie");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}