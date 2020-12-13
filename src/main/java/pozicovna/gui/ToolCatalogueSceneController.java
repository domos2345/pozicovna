package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.ToolCatalogueItem;
import pozicovna.business.ToolCatalogueItemManager;
import pozicovna.business.ToolCatalogueItemManagerImplementation;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

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

    ToolCatalogueItemManager toolCatalogueItemManager = new ToolCatalogueItemManagerImplementation();

    public ToolCatalogueSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        toolCatalogueButton.setDisable(true);

        druhColumn.setCellValueFactory( new PropertyValueFactory<>("druh"));
        znackaColumn.setCellValueFactory( new PropertyValueFactory<>("znacka"));
        typColumn.setCellValueFactory( new PropertyValueFactory<>("typ"));
        stavColumn.setCellValueFactory( new PropertyValueFactory<>("stav"));
        majitelColumn.setCellValueFactory( new PropertyValueFactory<>("majitel"));
        okresColumn.setCellValueFactory( new PropertyValueFactory<>("okres"));

        toolCatalogueTableView.setItems(
                FXCollections.observableArrayList(
                    toolCatalogueItemManager.getToolCatalogueItems()
                )
        );
    }
}
