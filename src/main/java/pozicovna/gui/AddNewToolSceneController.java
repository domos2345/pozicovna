package pozicovna.gui;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pozicovna.entities.DruhNaradia;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.DruhNaradiaDao;

public class AddNewToolSceneController {
	DruhNaradiaDao druhNaradiaDao = DaoFactory.INSTANCE.getDruhNaradiaDao();
	Pouzivatel pouzivatel;
	List<DruhNaradia> druhyNaradia;

	public AddNewToolSceneController(Pouzivatel pouzivatel) {
		this.pouzivatel = pouzivatel;
		// TODO Auto-generated constructor stub
	}

	@FXML
	private Button addNewToolButton;
	@FXML
	private Label titleLabel;
	@FXML
	private Label errorLabel;
	@FXML
	private TextField brandTextField;
	@FXML
	private TextField typeTextField;
	@FXML
	private TextField descriptionTextField;
	@FXML
	private ComboBox<DruhNaradia> kindComboBox;
	@FXML
	private TextField addNewKindTextField;
	@FXML
	private Button addNewKindButton;

	private ObjectProperty<DruhNaradia> selectedDruhNaradia = new SimpleObjectProperty<DruhNaradia>(null);

	@FXML
	void initialize() {
		druhyNaradia = druhNaradiaDao.getAll();
		kindComboBox.setItems(FXCollections.observableArrayList(druhyNaradia));
		kindComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DruhNaradia>() {

			@Override
			public void changed(ObservableValue<? extends DruhNaradia> observable, DruhNaradia oldValue,
					DruhNaradia newValue) {
				selectedDruhNaradia.setValue(newValue);
			}
		});
		selectedDruhNaradia.addListener(new ChangeListener<DruhNaradia>() {

			@Override
			public void changed(ObservableValue<? extends DruhNaradia> observable, DruhNaradia oldValue,
					DruhNaradia newValue) {
				if (newValue == null) {
					kindComboBox.getSelectionModel().clearSelection();
				} else {
					kindComboBox.getSelectionModel().select(newValue);
				}

			}
		});
	}

	@FXML
	void addNewKindButtonClick(ActionEvent event) {

		selectedDruhNaradia.setValue(null);
	}

	@FXML
	void addNewToolButtonClick(ActionEvent event) {

	}

	@FXML
	void cancelButtonClick(ActionEvent event) {
		brandTextField.getParent().getScene().getWindow().hide();

	}

}
