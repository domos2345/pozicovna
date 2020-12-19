package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.*;
import pozicovna.entities.NaradieCannotBeLendedException;
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
    Request selectedRequest;

    public RequestsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        requestsButton.setDisable(true);

        setColumns();

        loadRequests();

        requestsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                confirmButton.setDisable(false);
                rejectButton.setDisable(false);
                selectedRequest = newSelection;
            }
        });
    }

    private void setColumns(){
        druhColumn.setCellValueFactory( new PropertyValueFactory<>("druh"));
        znackaColumn.setCellValueFactory( new PropertyValueFactory<>("znacka"));
        typColumn.setCellValueFactory( new PropertyValueFactory<>("typ"));
        ziadatelColumn.setCellValueFactory( new PropertyValueFactory<>("ziadatel"));
        datumColumn.setCellValueFactory( new PropertyValueFactory<>("datum"));
    }

    private void loadRequests(){
        requestsTableView.setItems(
                FXCollections.observableArrayList(
                        requestsManager.getRequestsForNaradieOfPouzivatel(pouzivatel.getId())
                )
        );
    }

    @FXML
    void confirmButtonClick(ActionEvent event) {
        try {
            selectedRequest.getNaradie().lendTo(selectedRequest.getAkcia().getZiadatel());
        } catch (NaradieCannotBeLendedException e) {
            e.printStackTrace();
        }
        loadRequests();
    }

    @FXML
    void rejectButtonClick(ActionEvent event) {
        selectedRequest.getAkcia().reject(selectedRequest.getNaradie().getId());
        loadRequests();
    }
}
