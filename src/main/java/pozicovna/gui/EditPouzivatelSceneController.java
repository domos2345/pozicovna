package pozicovna.gui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.PouzivatelDao;

import java.util.Arrays;
import java.util.List;

public class EditPouzivatelSceneController {
    @FXML
    private Button saveButton;
    @FXML
    private Label titleLabel;
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
    ObjectProperty<Pouzivatel> pouzivatel;
    PouzivatelFxModel pouzivatelModel;
    List<TextField> mandatoryFields;

    public EditPouzivatelSceneController() {
        pouzivatelModel = new PouzivatelFxModel();
        this.pouzivatel =  new SimpleObjectProperty<>(null);
    }

    public EditPouzivatelSceneController(Pouzivatel pouzivatel) {
        this.pouzivatel = new SimpleObjectProperty<>(pouzivatel);
        pouzivatelModel = new PouzivatelFxModel(pouzivatel);
    }

    @FXML
    void initialize() {
        chooseRegistrationOrEditing();
        bindUser();
        mandatoryFields = Arrays.asList(nameTextField, surnameTextField, emailTextField, passwordField, passwordConfirmationField, phoneNumberTextField, districtTextField);
    }

    private void chooseRegistrationOrEditing(){
        if (pouzivatel.get() == null){
            titleLabel.setText("Registrácia");
            saveButton.setText("Registrovať");
        } else {
            titleLabel.setText("Editácia používateľa");
            saveButton.setText("Uložiť");
            passwordField.setText("");
            passwordConfirmationField.setText("");
            // pre pripad ze sa nemeni heslo aby ta nieco bolo

        }
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
    void saveButtonClick(ActionEvent event) {
        if( !mandatoryFieldsFilled() || !passwordConfirmed())
            return;

        pouzivatel.set(pouzivatelDao.save(pouzivatelModel.getPouzivatel()));
        nameTextField.getScene().getWindow().hide();
    }

    @FXML
    void cancelButtonClick(ActionEvent event) {
        nameTextField.getParent().getScene().getWindow().hide();
    }

    private boolean mandatoryFieldsFilled(){
        for(TextField tf: mandatoryFields){
            // ak editujem tak nemusim menit hesla
            if(pouzivatel.get() != null && (tf == passwordField || tf == passwordConfirmationField))
                continue;

            if(tf.getText() == null || tf.getText().isEmpty()){
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

    public ObjectProperty<Pouzivatel> pouzivatelProperty() {
        return pouzivatel;
    }
}

