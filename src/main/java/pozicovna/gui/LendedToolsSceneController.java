package pozicovna.gui;

import javafx.fxml.FXML;
import pozicovna.entities.Pouzivatel;

public class LendedToolsSceneController extends LoggedInSceneController{

    public LendedToolsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        lendedToolsButton.setDisable(true);
    }
}
