package pozicovna.gui;

import javafx.fxml.FXML;
import pozicovna.entities.Pouzivatel;

public class BorrowedToolsSceneController extends LoggedInSceneController {

    public BorrowedToolsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        borrowedToolsButton.setDisable(true);
    }
}
