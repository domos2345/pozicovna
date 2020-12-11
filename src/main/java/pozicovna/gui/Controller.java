package pozicovna.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller {
    @FXML
    private AnchorPane rootPane;

    protected void changeScene(Controller controller, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlFile));
            loader.setController(controller);
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
