package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.BorrowedTool;
import pozicovna.business.BorrowedToolManager;
import pozicovna.business.BorrowedToolManagerImplementation;
import pozicovna.entities.Pouzivatel;

import java.util.List;
import java.util.stream.Collectors;

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
    @FXML
    private Button detailButton;
    @FXML
    private TextField searchTextField;


    BorrowedToolManager lendedToolManager = new BorrowedToolManagerImplementation();
    List<BorrowedTool> allTools;
    BorrowedTool selectedTool;

    public LendedToolsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        lendedToolsButton.setDisable(true);
        detailButton.setDisable(true);

        setColumns();

        loadToolCatalogue();

        lendedToolsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                detailButton.setDisable(false);
                selectedTool = newSelection;
            }
        });

        searchTextField.textProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                find(newSelection);
            }
        });
    }

    private void find(String substring) {
        List<BorrowedTool> list = substring.equals("")
                ? allTools
                : filterAllTools(substring);

        lendedToolsTableView.setItems(FXCollections.observableArrayList(list));
        detailButton.setDisable(true);
    }

    private List<BorrowedTool> filterAllTools(String substring){
        return allTools.stream().filter(borrowedTool -> borrowedTool.getNaradie().contains(substring.toLowerCase())).collect(Collectors.toList());
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
        allTools = lendedToolManager.getLendedTools(pouzivatel.getId());
        lendedToolsTableView.setItems(FXCollections.observableArrayList(allTools));
    }

    @FXML
    void detailButtonClick(ActionEvent event) {
        showToolInfoWindow(selectedTool.getNaradie(), selectedTool.getAkcia());
    }
}
