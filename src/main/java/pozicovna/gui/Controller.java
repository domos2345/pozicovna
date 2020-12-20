package pozicovna.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;

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

    public void showToolInfoWindow(Naradie naradie){
        ToolInfoController controller = new ToolInfoController(naradie);
        showToolInfoWindow(controller);
    }

    public void showToolInfoWindow(Naradie naradie, Akcia akcia){
        ToolInfoController controller = new ToolInfoController(naradie, akcia);
        showToolInfoWindow(controller);
    }

    private void showToolInfoWindow(ToolInfoController controller){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ToolInfo.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Informácie o náradí");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
