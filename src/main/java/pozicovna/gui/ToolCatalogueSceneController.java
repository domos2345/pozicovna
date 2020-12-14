package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.ToolCatalogueItem;
import pozicovna.business.ToolCatalogueItemManager;
import pozicovna.business.ToolCatalogueItemManagerImplementation;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.AkciaDao;
import pozicovna.storage.DaoFactory;
import pozicovna.entities.NaradieCannotBeLendedException;

public class ToolCatalogueSceneController extends LoggedInSceneController{

    @FXML
    private TableView<ToolCatalogueItem> toolCatalogueTableView;
    @FXML
    private TableColumn<ToolCatalogueItem, String> druhColumn;
    @FXML
    private TableColumn<ToolCatalogueItem, String> znackaColumn;
    @FXML
    private TableColumn<ToolCatalogueItem, String> typColumn;
    @FXML
    private TableColumn<ToolCatalogueItem, String> stavColumn;
    @FXML
    private TableColumn<ToolCatalogueItem, String> majitelColumn;
    @FXML
    private TableColumn<ToolCatalogueItem, String> okresColumn;
    @FXML
    private Button borrowButton;


    ToolCatalogueItemManager toolCatalogueItemManager = new ToolCatalogueItemManagerImplementation();
    Naradie selectedNaradie;
    AkciaDao akciaDao = DaoFactory.INSTANCE.getAkciaDao();

    public ToolCatalogueSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        toolCatalogueButton.setDisable(true);
        borrowButton.setDisable(true);

        setColumns();

        loadToolCatalogue();

        // https://stackoverflow.com/questions/26424769/javafx8-how-to-create-listener-for-selection-of-row-in-tableview
        toolCatalogueTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                borrowButton.setDisable(false);
                selectedNaradie = newSelection.getNaradie();
            }
        });
    }

    private void setColumns(){
        druhColumn.setCellValueFactory( new PropertyValueFactory<>("druh"));
        znackaColumn.setCellValueFactory( new PropertyValueFactory<>("znacka"));
        typColumn.setCellValueFactory( new PropertyValueFactory<>("typ"));
        stavColumn.setCellValueFactory( new PropertyValueFactory<>("stav"));
        majitelColumn.setCellValueFactory( new PropertyValueFactory<>("majitel"));
        okresColumn.setCellValueFactory( new PropertyValueFactory<>("okres"));
    }

    private void loadToolCatalogue(){
        toolCatalogueTableView.setItems(
                FXCollections.observableArrayList(
                        toolCatalogueItemManager.getToolCatalogueItems()
                )
        );
    }

    @FXML
    void borrowButtonClick(ActionEvent event) {
        try {
            selectedNaradie.lendTo(pouzivatel);
        } catch (NaradieCannotBeLendedException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Toto naradia je uz pozicane");
            alert.show();
        }
        loadToolCatalogue();
    }
}
