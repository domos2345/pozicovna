package pozicovna.gui;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pozicovna.business.ToolCatalogueItem;
import pozicovna.business.ToolCatalogueItemManager;
import pozicovna.business.ToolCatalogueItemManagerImplementation;

public class LoggedOutSceneController extends Controller {
    @FXML
    private TextField searchTextField;
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
    List<ToolCatalogueItem> allTools;

    @FXML
    void initialize() {
        setColumns();

        allTools = toolCatalogueItemManager.getAllToolCatalogueItems();
        toolCatalogueTableView.setItems(FXCollections.observableArrayList(allTools));

        searchTextField.textProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                find(newSelection);
            }
        });
    }

    private void setColumns() {
        druhColumn.setCellValueFactory(new PropertyValueFactory<>("druh"));
        znackaColumn.setCellValueFactory(new PropertyValueFactory<>("znacka"));
        typColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));
        stavColumn.setCellValueFactory(new PropertyValueFactory<>("stav"));
        majitelColumn.setCellValueFactory(new PropertyValueFactory<>("majitel"));
        okresColumn.setCellValueFactory(new PropertyValueFactory<>("okres"));
    }

    private void find(String substring) {
        List<ToolCatalogueItem> list = substring.equals("")
                ? allTools
                : filterAllTools(substring);

        toolCatalogueTableView.setItems(FXCollections.observableArrayList(list));
    }

    private List<ToolCatalogueItem> filterAllTools(String substring){
        return allTools.stream().filter(tci -> tci.getNaradie().contains(substring.toLowerCase())).collect(Collectors.toList());
    }

    @FXML
    void registrationButtonClick(ActionEvent event) {
        try {
            EditPouzivatelSceneController controller = new EditPouzivatelSceneController();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("EditPouzivatel.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registracia");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signInButtonClick(ActionEvent event) {
        try {
            SignInSceneController controller = new SignInSceneController();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("SignIn.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Prihlasovanie");
            stage.setScene(scene);
            stage.show();

            ((SignInSceneController)loader.getController()).pouzivatelProperty().addListener((x, y, pouzivatel) -> {
                changeScene(new ToolCatalogueSceneController(pouzivatel), "ToolCatalogue.fxml");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}