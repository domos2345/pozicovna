package pozicovna.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInSceneController {
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;


    @FXML
    void initialize() {

    }

    @FXML
    void signInButtonClick(ActionEvent event) {
        //ToDo: overenie udajov
        //ToDo: ulozenie usera

        emailTextField.getScene().getWindow().hide();
        //ToDo: hide aj LoggedOutScene
        //  asi cez konstruktor si poslat scenu

        try {
            SignInSceneController controller = new SignInSceneController();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("LoggedIn.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pozicovna");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

