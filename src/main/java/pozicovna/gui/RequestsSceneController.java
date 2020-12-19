package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.*;
import pozicovna.entities.Pouzivatel;

public class RequestsSceneController extends LoggedInSceneController{
    @FXML
    private TableView<Request> requestsTableView;
    @FXML
    private TableColumn<Request, String> druhColumn;
    @FXML
    private TableColumn<Request, String> znackaColumn;
    @FXML
    private TableColumn<Request, String> typColumn;
    @FXML
    private TableColumn<Request, String> ziadatelColumn;
    @FXML
    private TableColumn<Request, String> datumColumn;
    @FXML
    private Button rejectButton;
    @FXML
    private Button confirmButton;


    RequestsManager requestsManager = new RequestsManagerImplementation();

    public RequestsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        requestsButton.setDisable(true);

        setColumns();

        loadToolCatalogue();
    }

    private void setColumns(){
        druhColumn.setCellValueFactory( new PropertyValueFactory<>("druh"));
        znackaColumn.setCellValueFactory( new PropertyValueFactory<>("znacka"));
        typColumn.setCellValueFactory( new PropertyValueFactory<>("typ"));
        ziadatelColumn.setCellValueFactory( new PropertyValueFactory<>("ziadatel"));
        datumColumn.setCellValueFactory( new PropertyValueFactory<>("datum"));
    }

    private void loadToolCatalogue(){
        requestsTableView.setItems(
                FXCollections.observableArrayList(
                        requestsManager.GetRequestsForNaradieOfPouzivatel(pouzivatel.getId())
                )
        );
    }

    @FXML
    void confirmButtonClick(ActionEvent event) {

    }

    @FXML
    void rejectButtonClick(ActionEvent event) {

    }
}
