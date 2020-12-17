package pozicovna.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;

public class NaradieFxModel {
	Pouzivatel pouzivatel;
	Naradie naradie;

	private Long id;
	private Boolean jeDostupne;
	private Long vlastnikId;
	private StringProperty brand;
	private StringProperty type;
	private StringProperty kind;
	private StringProperty description;

	// editacia náradia
	public NaradieFxModel(Naradie naradie) {
		this.naradie = naradie;

		this.id = naradie.getId();
		this.jeDostupne = naradie.getJe_dostupne();
		this.vlastnikId = naradie.getVlastnik().getId();

		this.brand = new SimpleStringProperty(naradie.getZnacka());
		this.type = new SimpleStringProperty(naradie.getTyp());
		this.kind = new SimpleStringProperty(naradie.getDruhNaradia());
		this.description = new SimpleStringProperty(naradie.getPopis());

	}

	public NaradieFxModel(Pouzivatel pouzivatel) {
		// NOVE NARADIE

		this.pouzivatel = pouzivatel;
		this.vlastnikId = this.pouzivatel.getId();

		this.brand = new SimpleStringProperty();
		this.type = new SimpleStringProperty();
		this.kind = new SimpleStringProperty();
		this.description = new SimpleStringProperty();

	}

	Naradie getNaradie() {
		if (id == null) // PRIDAVANIE NOVEHO NARADIA
			return new Naradie(getBrand(), getType(), true, getKind(), pouzivatel, getDescription(),
					new ArrayList<Akcia>(), DaoFactory.INSTANCE.getAkciaDao(), DaoFactory.INSTANCE.getNaradieDao());
		else // EDITACIA
			return new Naradie(id, getBrand(), getType(), jeDostupne, getKind(), naradie.getVlastnik(),
					getDescription(), naradie.getAkcie());
	}

	public String getBrand() {
		return brand.get();
	}

	public StringProperty brandProperty() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand.set(brand);
	}

	public String getType() {
		return type.get();
	}

	public StringProperty typeProperty() {
		return type;
	}

	public void setType(String type) {
		this.type.set(type);
	}

	public String getKind() {
		return kind.get();
	}

	public StringProperty kindProperty() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind.set(kind);
	}

	public String getDescription() {
		return description.get();
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public void setPopis(String popis) {
		this.description.set(popis);
	}

	@Override
	public String toString() {
		return "NaradieFxModel: id=" + id + ", jeDostupne=" + jeDostupne + ", vlastnikId=" + vlastnikId + ", znacka="
				+ getBrand() + ", typ=" + getType() + ", druh=" + getKind() + ", popis=" + getDescription();
	}

}
