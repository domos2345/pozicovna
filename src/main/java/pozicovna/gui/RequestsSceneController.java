package pozicovna.gui;

import javafx.fxml.FXML;
import pozicovna.entities.Pouzivatel;

public class RequestsSceneController extends LoggedInSceneController{

    public RequestsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        requestsButton.setDisable(true);
    }
}
