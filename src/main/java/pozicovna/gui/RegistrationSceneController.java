package pozicovna.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.PouzivatelDao;

import java.util.Arrays;
import java.util.List;

public class RegistrationSceneController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordConfirmationField;
    @FXML
    private TextField houseNumberTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField districtTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Label errorLabel;


    PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
    PouzivatelFxModel pouzivatelModel;
    List<TextField> mandatoryFields;

    @FXML
    void initialize() {
        pouzivatelModel =  new PouzivatelFxModel();
        bindUser();
        mandatoryFields = Arrays.asList(nameTextField, surnameTextField, emailTextField, passwordField, passwordConfirmationField, phoneNumberTextField, districtTextField);
    }

    private void bindUser(){
        nameTextField.textProperty().bindBidirectional(pouzivatelModel.menoProperty());
        surnameTextField.textProperty().bindBidirectional(pouzivatelModel.priezviskoProperty());
        emailTextField.textProperty().bindBidirectional(pouzivatelModel.emailProperty());
        passwordField.textProperty().bindBidirectional(pouzivatelModel.hesloProperty());
        districtTextField.textProperty().bindBidirectional(pouzivatelModel.okresProperty());
        cityTextField.textProperty().bindBidirectional(pouzivatelModel.mestoProperty());
        streetTextField.textProperty().bindBidirectional(pouzivatelModel.ulicaProperty());
        houseNumberTextField.textProperty().bindBidirectional(pouzivatelModel.cisloDomuProperty());
        postalCodeTextField.textProperty().bindBidirectional(pouzivatelModel.pscProperty());
        phoneNumberTextField.textProperty().bindBidirectional(pouzivatelModel.telCisloProperty());
    }

    @FXML
    void registerButtonClick(ActionEvent event) {
        if( !mandatoryFieldsFilled() || !passwordConfirmed())
            return;

        pouzivatelDao.save(pouzivatelModel.getPouzivatel());
        nameTextField.getScene().getWindow().hide();
    }

    private boolean mandatoryFieldsFilled(){
        for(TextField tf: mandatoryFields){
            System.out.println(tf.getText());
            if(tf.getText() == null || tf.getText().isEmpty()){
                System.out.println("_" + tf.getText() + "_");
                tf.setStyle("-fx-background-color: lightcoral");
                errorLabel.setText("Nevyplnené povinné políčka");
                errorLabel.setStyle("-fx-text-fill: lightcoral");
                return false;
            }else{
                tf.setStyle("-fx-background-color: white");
            }
        }
        return true;
    }

    private boolean passwordConfirmed(){
        if(!passwordField.getText().equals(passwordConfirmationField.getText())){
            errorLabel.setText("Heslá sa nezhodujú");
            passwordField.setText("");
            passwordConfirmationField.setText("");
            passwordField.setStyle("-fx-background-color: lightcoral");
            passwordConfirmationField.setStyle("-fx-background-color: lightcoral");
            return false;
        }
        return true;
    }
}

