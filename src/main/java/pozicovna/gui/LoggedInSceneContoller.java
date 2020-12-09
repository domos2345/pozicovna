package pozicovna.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pozicovna.entities.Pouzivatel;

public class LoggedInSceneContoller {
    @FXML
    private Label credentailsLabel;

    Pouzivatel pouzivatel;

    public LoggedInSceneContoller(Pouzivatel pouzivatel){
        this.pouzivatel = pouzivatel;
    }

    @FXML
    void initialize() {
        credentailsLabel.setText(pouzivatel.getMeno() + " " + pouzivatel.getPriezvisko());
    }

    @FXML
    void logOutButtonCLick(ActionEvent event) {
        credentailsLabel.getScene().getWindow().hide();
    }
}
