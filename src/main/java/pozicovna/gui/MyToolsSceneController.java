package pozicovna.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	@FXML
	private Button addToolButton;

	@FXML
	private Button editToolButton;

	@FXML
	private Button deleteToolButton;

	@FXML
	void addToolButtonClick(ActionEvent event) {
		try {
			AddNewToolSceneController controller = new AddNewToolSceneController(pouzivatel);
			FXMLLoader loader = new FXMLLoader(App.class.getResource("addNewTool.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Pridávanie náradia");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void deleteToolButtonClick(ActionEvent event) {

	}

	@FXML
	void editToolButtonClick(ActionEvent event) {

	}

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
