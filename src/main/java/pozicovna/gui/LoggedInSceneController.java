package pozicovna.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pozicovna.entities.Pouzivatel;

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