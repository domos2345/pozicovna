package pozicovna.gui;

import javafx.fxml.FXML;
import pozicovna.entities.Pouzivatel;

public class ToolCatalogueSceneController extends LoggedInSceneController{

    public ToolCatalogueSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        toolCatalogueButton.setDisable(true);
    }
}
