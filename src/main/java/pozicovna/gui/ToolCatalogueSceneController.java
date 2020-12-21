package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.ToolCatalogueItem;
import pozicovna.business.ToolCatalogueItemManager;
import pozicovna.business.ToolCatalogueItemManagerImplementation;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

import java.util.List;
import java.util.stream.Collectors;

public class ToolCatalogueSceneController extends LoggedInSceneController {

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
	@FXML
	private Button detailButton;
	@FXML
	private TextField searchTextField;


	ToolCatalogueItemManager toolCatalogueItemManager = new ToolCatalogueItemManagerImplementation();
	Naradie selectedNaradie;
	List<ToolCatalogueItem> allTools;

	public ToolCatalogueSceneController(Pouzivatel pouzivatel) {
		super(pouzivatel);
	}

	@FXML
	void initialize() {
		super.initialize();
		toolCatalogueButton.setDisable(true);
		borrowButton.setDisable(true);
		detailButton.setDisable(true);

		setColumns();

		loadToolCatalogue();

		// https://stackoverflow.com/questions/26424769/javafx8-how-to-create-listener-for-selection-of-row-in-tableview
		toolCatalogueTableView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						borrowButton.setDisable(!newSelection.getNaradie().getJe_dostupne());
						detailButton.setDisable(false);
						selectedNaradie = newSelection.getNaradie();
					}
				});

		searchTextField.textProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				find(newSelection);
			}
		});
	}

	private void find(String substring) {
		List<ToolCatalogueItem> list = substring.equals("")
				? allTools
				: filterAllTools(substring);

		toolCatalogueTableView.setItems(FXCollections.observableArrayList(list));
		borrowButton.setDisable(true);
		detailButton.setDisable(true);
	}

	private List<ToolCatalogueItem> filterAllTools(String substring){
		return allTools.stream().filter(tci -> tci.getNaradie().contains(substring.toLowerCase())).collect(Collectors.toList());
	}

	private void setColumns() {
		druhColumn.setCellValueFactory(new PropertyValueFactory<>("druh"));
		znackaColumn.setCellValueFactory(new PropertyValueFactory<>("znacka"));
		typColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));
		stavColumn.setCellValueFactory(new PropertyValueFactory<>("stav"));
		majitelColumn.setCellValueFactory(new PropertyValueFactory<>("majitel"));
		okresColumn.setCellValueFactory(new PropertyValueFactory<>("okres"));
	}

	private void loadToolCatalogue() {
		allTools = toolCatalogueItemManager.getToolCatalogueItemsNotOwnedBy(pouzivatel.getId());
		toolCatalogueTableView.setItems(FXCollections.observableArrayList(allTools));

		borrowButton.setDisable(true);
		detailButton.setDisable(true);
	}

	@FXML
	void borrowButtonClick(ActionEvent event) {
		selectedNaradie.sendRequest(pouzivatel);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Žiadosť o požicanie náradia vytvorená");
		alert.show();

		loadToolCatalogue();
	}

	@FXML
	void detailButtonClick(ActionEvent event) {
		showToolInfoWindow(selectedNaradie);
	}
}
