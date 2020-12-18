package pozicovna.gui;

import java.util.Arrays;
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
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.DruhNaradiaDao;
import pozicovna.storage.NaradieDao;

public class AddNewToolSceneController {
	DruhNaradiaDao druhNaradiaDao = DaoFactory.INSTANCE.getDruhNaradiaDao();

	NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

	Pouzivatel pouzivatel;

	List<DruhNaradia> druhyNaradia;

	NaradieFxModel naradieModel;

	List<TextField> mandatoryFields;

	private ObjectProperty<DruhNaradia> selectedDruhNaradia = new SimpleObjectProperty<DruhNaradia>(null);

	private ObjectProperty<Naradie> naradie;

	@FXML
	private Button saveButton;
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

	public AddNewToolSceneController(Pouzivatel pouzivatel) {
		this.pouzivatel = pouzivatel;
		this.naradie = new SimpleObjectProperty<Naradie>(null);
		this.naradieModel = new NaradieFxModel(pouzivatel);

	}

	public AddNewToolSceneController(Pouzivatel pouzivatel, Naradie naradie) {
		this.pouzivatel = pouzivatel;
		this.naradie = new SimpleObjectProperty<Naradie>(naradie);
		this.naradieModel = new NaradieFxModel(naradie);
	}

	@FXML
	void initialize() {
		druhyNaradia = druhNaradiaDao.getAll();
		chooseRegistrationOrEditing();
		bindTool();
		mandatoryFields = Arrays.asList(brandTextField, typeTextField);
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

	private void chooseRegistrationOrEditing() {
		if (naradie.get() == null) {
			titleLabel.setText("Pridávanie nového náradia");
		} else {
			titleLabel.setText("Editácia náradia");
			// pre pripad ze sa nemeni heslo aby ta nieco bolo

		}
	}

	private void bindTool() {
		brandTextField.textProperty().bindBidirectional(naradieModel.brandProperty());
		typeTextField.textProperty().bindBidirectional(naradieModel.typeProperty());
		descriptionTextField.textProperty().bindBidirectional(naradieModel.descriptionProperty());
		selectedDruhNaradia.bindBidirectional(naradieModel.kindProperty());
	}

	private boolean mandatoryFieldsFilled() {
		for (TextField tf : mandatoryFields) {
			// ak editujem tak nemusim menit hesla
			if (naradie.get() != null)
				continue;

			if (tf.getText() == null || tf.getText().isBlank()) {
				tf.setStyle("-fx-background-color: lightcoral");
				errorLabel.setText("Nevyplnené povinné políčka");
				errorLabel.setStyle("-fx-text-fill: lightcoral");
				return false;

			} else {
				tf.setStyle("-fx-background-color: white");
			}
		}
		if (kindComboBox.getValue() == null && addNewKindTextField.getText().isBlank()) {
			kindComboBox.setStyle("-fx-background-color: lightcoral");
			errorLabel.setText("Vyberte (alebo pridajte) druh");
			errorLabel.setStyle("-fx-text-fill: lightcoral");
			return false;
		}
		return true;
	}

	@FXML
	void addNewKindButtonClick(ActionEvent event) {

		selectedDruhNaradia.setValue(null);
	}

	@FXML
	void saveButtonClick(ActionEvent event) {
		if (!mandatoryFieldsFilled())
			return;

		naradie.set(naradieDao.save(naradieModel.getNaradie()));

		brandTextField.getParent().getScene().getWindow().hide();

	}

	@FXML
	void cancelButtonClick(ActionEvent event) {
		brandTextField.getParent().getScene().getWindow().hide();

	}

}
