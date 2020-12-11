package pozicovna.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pozicovna.entities.Pouzivatel;

import java.io.IOException;

public class LoggedInSceneController extends Controller{
    @FXML
    Button requestsButton;
    @FXML
    Button lendedToolsButton;
    @FXML
    private Label credentailsLabel;
    @FXML
    Button myToolsButton;
    @FXML
    Button toolCatalogueButton;
    @FXML
    Button borrowedToolsButton;

    Pouzivatel pouzivatel;

    public LoggedInSceneController(Pouzivatel pouzivatel){
        this.pouzivatel = pouzivatel;
    }

    @FXML
    void initialize() {
        credentailsLabel.setText(pouzivatel.getMeno() + " " + pouzivatel.getPriezvisko());
    }

    // sprava pouzivatela

    @FXML
    void logOutButtonClick(ActionEvent event) {
        changeScene(new LoggedOutSceneController(), "LoggedOut.fxml");
    }

    @FXML
    void editUserButtonClick(ActionEvent event) {
        try {
            EditPouzivatelSceneController controller = new EditPouzivatelSceneController(pouzivatel);
            // aby som mal po editacii aktualne info
            controller.pouzivatelProperty().addListener((x, y, pouzivatel) -> { this.pouzivatel = pouzivatel; });

            FXMLLoader loader = new FXMLLoader(App.class.getResource("EditPouzivatel.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editácia");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // "menu"

    @FXML
    void toolCatalogueButtonClick(ActionEvent event) {
        changeScene(new ToolCatalogueSceneController(pouzivatel), "ToolCatalogue.fxml");
    }

    @FXML
    void borrowedToolsButtonClick(ActionEvent event) {
        changeScene(new BorrowedToolsSceneController(pouzivatel), "BorrowedTools.fxml");
    }

    @FXML
    void myToolsButtonClick(ActionEvent event) {
        changeScene(new MyToolsSceneController(pouzivatel), "MyTools.fxml");
    }

    @FXML
    void requestsButtonClick(ActionEvent event) {
        changeScene(new RequestsSceneController(pouzivatel), "Requests.fxml");
    }

    @FXML
    void lendedToolsButtonClick(ActionEvent event) {
        changeScene(new LendedToolsSceneController(pouzivatel), "LendedTools.fxml");
    }
}