package pozicovna.gui;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.EntityNotFoundException;
import pozicovna.storage.PouzivatelDao;

public class SignInSceneController extends Controller {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button signInButton;


    private Pouzivatel pouzivatel;
    private PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
    private ObjectProperty<Pouzivatel> pouzivatelOP =  new SimpleObjectProperty<>();

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
        pouzivatelOP.setValue(pouzivatel);
        emailTextField.getScene().getWindow().hide();
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

    public ObjectProperty<Pouzivatel> pouzivatelProperty() {
        return pouzivatelOP;
    }
}

