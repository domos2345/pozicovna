package pozicovna.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.EntityNotFoundException;
import pozicovna.storage.PouzivatelDao;

import java.io.IOException;

public class SignInSceneController {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button signInButton;


    Pouzivatel pouzivatel;
    PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();

    @FXML
    void initialize() {}

    @FXML
    void cancelButtonClick(ActionEvent event) {
        emailTextField.getScene().getWindow().hide();
    }

    @FXML
    void signInButtonClick(ActionEvent event) {
        if(!fieldsFilledCorrectly())
            return;

        emailTextField.getScene().getWindow().hide();

        //ToDo: hide aj LoggedOutScene
        //  asi cez konstruktor si poslat scenu

        showLoggedInWindow();
    }

    public boolean fieldsFilledCorrectly(){
        errorLabel.setStyle("-fx-text-fill: lightcoral");
        emailTextField.setStyle("-fx-background-color: white");
        passwordField.setStyle("-fx-background-color: white");

        try {
            pouzivatel = pouzivatelDao.getByEmail(emailTextField.getText());
        } catch (EntityNotFoundException e) {
            errorLabel.setText("Neplatný email");
            emailTextField.setStyle("-fx-background-color: lightcoral");
            return false;
        }

        if(!pouzivatel.getHeslo_hash().equals(BCrypt.hashpw(passwordField.getText(), pouzivatel.getSol_hash()))){
            errorLabel.setText("Neplatné heslo");
            passwordField.setText("");
            passwordField.setStyle("-fx-background-color: lightcoral");
            return false;
        }
        return true;
    }

    public void showLoggedInWindow(){
        try {
            LoggedInSceneContoller controller = new LoggedInSceneContoller(pouzivatel);
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

