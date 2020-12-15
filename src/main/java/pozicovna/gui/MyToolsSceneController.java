package pozicovna.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pozicovna.business.ToolCatalogueItem;
import pozicovna.business.ToolCatalogueItemManager;
import pozicovna.business.ToolCatalogueItemManagerImplementation;
import pozicovna.entities.Pouzivatel;

public class MyToolsSceneController extends LoggedInSceneController {

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

	ToolCatalogueItemManager toolCatalogueItemManager = new ToolCatalogueItemManagerImplementation();

	public MyToolsSceneController(Pouzivatel pouzivatel) {
		super(pouzivatel);
	}

	@FXML
	void initialize() {
		myToolsButton.setDisable(true);

		druhColumn.setCellValueFactory(new PropertyValueFactory<>("druh"));
		znackaColumn.setCellValueFactory(new PropertyValueFactory<>("znacka"));
		typColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));
		stavColumn.setCellValueFactory(new PropertyValueFactory<>("stav"));

		toolCatalogueTableView.setItems(FXCollections
				.observableArrayList(toolCatalogueItemManager.getOwnedToolCatalogueItems(pouzivatel.getId())));
	}
}
