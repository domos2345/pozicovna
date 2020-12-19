package pozicovna.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pozicovna.entities.Pouzivatel;

public class RequestsSceneController extends LoggedInSceneController{
    @FXML
    private TableView<?> requestsTableView;
    @FXML
    private TableColumn<?, ?> druhColumn;
    @FXML
    private TableColumn<?, ?> znackaColumn;
    @FXML
    private TableColumn<?, ?> typColumn;
    @FXML
    private TableColumn<?, ?> ziadatelColumn;
    @FXML
    private TableColumn<?, ?> datumColumn;
    @FXML
    private Button rejectButton;
    @FXML
    private Button confirmButton;


    public RequestsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        requestsButton.setDisable(true);
    }

    @FXML
    void confirmButtonClick(ActionEvent event) {

    }

    @FXML
    void rejectButtonClick(ActionEvent event) {

    }
}
