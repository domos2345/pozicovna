package pozicovna.gui;

import javafx.fxml.FXML;
import pozicovna.entities.Pouzivatel;

public class MyToolsSceneController extends LoggedInSceneController{

    public MyToolsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        myToolsButton.setDisable(true);
    }
}
