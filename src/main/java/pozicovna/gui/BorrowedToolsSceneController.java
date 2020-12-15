package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.BorrowedTool;
import pozicovna.business.BorrowedToolManager;
import pozicovna.business.BorrowedToolManagerImplementation;
import pozicovna.entities.Naradie;
import pozicovna.entities.NaradieCannotBeReturnedException;
import pozicovna.entities.Pouzivatel;

public class BorrowedToolsSceneController extends LoggedInSceneController {

    @FXML
    private TableView<BorrowedTool> borrowedToolsTableView;
    @FXML
    private TableColumn<BorrowedTool, String> druhColumn;
    @FXML
    private TableColumn<BorrowedTool, String> znackaColumn;
    @FXML
    private TableColumn<BorrowedTool, String> typColumn;
    @FXML
    private TableColumn<BorrowedTool, String> poziadanieDateColumn;
    @FXML
    private TableColumn<BorrowedTool, String> zamietnutieDateColumn;
    @FXML
    private TableColumn<BorrowedTool, String> pozicanieDateColumn;
    @FXML
    private TableColumn<BorrowedTool, String> vratenieDateColumn;
    @FXML
    private Button returnButton;


    BorrowedToolManager borrowedToolManager = new BorrowedToolManagerImplementation();
    BorrowedTool selectedBorrowedTool;

    public BorrowedToolsSceneController(Pouzivatel pouzivatel) {
        super(pouzivatel);
    }

    @FXML
    void initialize() {
        super.initialize();
        borrowedToolsButton.setDisable(true);
        returnButton.setDisable(true);

        setColumns();

        loadToolCatalogue();

        borrowedToolsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                returnButton.setDisable(false);
                selectedBorrowedTool = newSelection;
            }
        });
    }

    private void setColumns(){
        druhColumn.setCellValueFactory( new PropertyValueFactory<>("druh"));
        znackaColumn.setCellValueFactory( new PropertyValueFactory<>("znacka"));
        typColumn.setCellValueFactory( new PropertyValueFactory<>("typ"));
        poziadanieDateColumn.setCellValueFactory( new PropertyValueFactory<>("poziadanie"));
        zamietnutieDateColumn.setCellValueFactory( new PropertyValueFactory<>("zamietnutie"));
        pozicanieDateColumn.setCellValueFactory( new PropertyValueFactory<>("pozicanie"));
        vratenieDateColumn.setCellValueFactory( new PropertyValueFactory<>("vratenie"));
    }

    private void loadToolCatalogue(){
        System.out.println(borrowedToolManager.getBorrowedTools(pouzivatel.getId()).size());
        borrowedToolsTableView.setItems(
                FXCollections.observableArrayList(
                        borrowedToolManager.getBorrowedTools(pouzivatel.getId())
                )
        );
    }

    @FXML
    void returnButtonClick(ActionEvent event) {
        try {
            selectedBorrowedTool.getNaradie().returnNaradie(selectedBorrowedTool.getAkcia());
            loadToolCatalogue();
        } catch (NaradieCannotBeReturnedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Toto naradie momentalne nemate pozicane");
            alert.show();
        }
        returnButton.setDisable(true);
    }
}
