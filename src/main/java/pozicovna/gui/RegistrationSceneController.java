package pozicovna.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrationSceneController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField passwordConfirmationTextField;

    @FXML
    private TextField houseNumberTextField;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField phoneNumberTextField;


    @FXML
    void initialize() {

    }

    @FXML
    void registerButtonClick(ActionEvent event) {
        //ToDo: overenie udajov
        //ToDo: ulozenie usera

        nameTextField.getScene().getWindow().hide();
    }
}

