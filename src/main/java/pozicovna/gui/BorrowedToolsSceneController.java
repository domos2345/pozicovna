package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.BorrowedTool;
import pozicovna.business.BorrowedToolManager;
import pozicovna.business.BorrowedToolManagerImplementation;
import pozicovna.business.ToolCatalogueItem;
import pozicovna.entities.NaradieCannotBeReturnedException;
import pozicovna.entities.Pouzivatel;

import java.util.List;
import java.util.stream.Collectors;

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
	@FXML
	private Button detailButton;
	@FXML
	private TextField searchTextField;


	BorrowedToolManager borrowedToolManager = new BorrowedToolManagerImplementation();
	List<BorrowedTool> allTools;
	BorrowedTool selectedBorrowedTool;

	public BorrowedToolsSceneController(Pouzivatel pouzivatel) {
		super(pouzivatel);
	}

	@FXML
	void initialize() {
		super.initialize();
		borrowedToolsButton.setDisable(true);
		returnButton.setDisable(true);
		detailButton.setDisable(true);

		setColumns();

		loadToolCatalogue();

		borrowedToolsTableView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						returnButton.setDisable(!newSelection.getAkcia().mozemVratit());
						selectedBorrowedTool = newSelection;
						detailButton.setDisable(false);
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

		borrowedToolsTableView.setItems(FXCollections.observableArrayList(list));
		returnButton.setDisable(true);
		detailButton.setDisable(true);
	}

	private List<BorrowedTool> filterAllTools(String substring){
		return allTools.stream().filter(borrowedTool -> borrowedTool.getNaradie().contains(substring.toLowerCase())).collect(Collectors.toList());
	}

	private void setColumns() {
		druhColumn.setCellValueFactory(new PropertyValueFactory<>("druh"));
		znackaColumn.setCellValueFactory(new PropertyValueFactory<>("znacka"));
		typColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));
		poziadanieDateColumn.setCellValueFactory(new PropertyValueFactory<>("poziadanie"));
		zamietnutieDateColumn.setCellValueFactory(new PropertyValueFactory<>("zamietnutie"));
		pozicanieDateColumn.setCellValueFactory(new PropertyValueFactory<>("pozicanie"));
		vratenieDateColumn.setCellValueFactory(new PropertyValueFactory<>("vratenie"));
	}

	private void loadToolCatalogue() {
		allTools = borrowedToolManager.getBorrowedTools(pouzivatel.getId());
		borrowedToolsTableView.setItems(FXCollections.observableArrayList(allTools));

		returnButton.setDisable(true);
		detailButton.setDisable(true);
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
	}

	@FXML
	void detailButtonClick(ActionEvent event) {
		showToolInfoWindow(selectedBorrowedTool.getNaradie(), selectedBorrowedTool.getAkcia());
	}
}
