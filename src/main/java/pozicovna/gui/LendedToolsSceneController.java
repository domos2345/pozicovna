package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.BorrowedTool;
import pozicovna.business.BorrowedToolManager;
import pozicovna.business.BorrowedToolManagerImplementation;
import pozicovna.entities.Pouzivatel;

public class LendedToolsSceneController extends LoggedInSceneController{
    @FXML
    private TableView<BorrowedTool> lendedToolsTableView;
    @FXML
    private TableColumn<BorrowedTool, String> druhColumn;
    @FXML
    private TableColumn<BorrowedTool, String> znackaColumn;
    @FXML
    private TableColumn<BorrowedTool, String> typColumn;
    @FXML
    private TableColumn<BorrowedTool, String> ziadatelColumn;
    @FXML
    private TableColumn<BorrowedTool, String> pozicanieDateColumn;
    @FXML
    private TableColumn<BorrowedTool, String> vratenieDateColumn;


    BorrowedToolManager lendedToolManager = new BorrowedToolManagerImplementation();

    public LendedToolsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        lendedToolsButton.setDisable(true);

        setColumns();

        loadToolCatalogue();
    }

    private void setColumns() {
        druhColumn.setCellValueFactory(new PropertyValueFactory<>("druh"));
        znackaColumn.setCellValueFactory(new PropertyValueFactory<>("znacka"));
        typColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));
        ziadatelColumn.setCellValueFactory(new PropertyValueFactory<>("ziadatel"));
        pozicanieDateColumn.setCellValueFactory(new PropertyValueFactory<>("pozicanie"));
        vratenieDateColumn.setCellValueFactory(new PropertyValueFactory<>("vratenie"));
    }

    private void loadToolCatalogue() {
            lendedToolsTableView.setItems(
                    FXCollections.observableArrayList(lendedToolManager.getLendedTools(pouzivatel.getId())));
    }
}
