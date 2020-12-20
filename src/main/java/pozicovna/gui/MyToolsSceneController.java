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
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.NaradieDao;

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
	private Button detailButton;

	ToolCatalogueItemManager toolCatalogueItemManager = new ToolCatalogueItemManagerImplementation();

	Naradie selectedNaradie;

	NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

	public MyToolsSceneController(Pouzivatel pouzivatel) {
		super(pouzivatel);
	}

	@FXML
	void initialize() {
		myToolsButton.setDisable(true);
		editToolButton.setDisable(true);
		deleteToolButton.setDisable(true);
		detailButton.setDisable(true);

		setColumns();
		loadToolCatalogue();

		toolCatalogueTableView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						editToolButton.setDisable(false);
						deleteToolButton.setDisable(false);
						detailButton.setDisable(false);
						selectedNaradie = newSelection.getNaradie();
					}
				});

	}

	private void setColumns() {
		druhColumn.setCellValueFactory(new PropertyValueFactory<>("druh"));
		znackaColumn.setCellValueFactory(new PropertyValueFactory<>("znacka"));
		typColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));
		stavColumn.setCellValueFactory(new PropertyValueFactory<>("stav"));

	}

	private void loadToolCatalogue() {
		toolCatalogueTableView.setItems(FXCollections
				.observableArrayList(toolCatalogueItemManager.getOwnedToolCatalogueItems(pouzivatel.getId())));
		editToolButton.setDisable(true);
		deleteToolButton.setDisable(true);
		detailButton.setDisable(true);
	}

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
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadToolCatalogue();
	}

	@FXML
	void deleteToolButtonClick(ActionEvent event) {
		naradieDao.delete(selectedNaradie.getId());
		loadToolCatalogue();
	}

	@FXML
	void editToolButtonClick(ActionEvent event) {
		try {
			AddNewToolSceneController controller = new AddNewToolSceneController(pouzivatel, selectedNaradie);
			FXMLLoader loader = new FXMLLoader(App.class.getResource("addNewTool.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Pridávanie náradia");
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadToolCatalogue();

	}

	@FXML
	void detailButtonClick(ActionEvent event) {
		showToolInfoWindow(selectedNaradie);
	}
}
