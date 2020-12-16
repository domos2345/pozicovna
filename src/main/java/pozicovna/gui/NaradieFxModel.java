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
	private StringProperty znacka;
	private StringProperty typ;
	private StringProperty druh;
	private StringProperty popis;

	// editacia náradia
	public NaradieFxModel(Naradie naradie) {
		this.naradie = naradie;

		this.id = naradie.getId();
		this.jeDostupne = naradie.getJe_dostupne();
		this.vlastnikId = naradie.getVlastnik().getId();

		this.znacka = new SimpleStringProperty(naradie.getZnacka());
		this.typ = new SimpleStringProperty(naradie.getTyp());
		this.druh = new SimpleStringProperty(naradie.getDruhNaradia());
		this.popis = new SimpleStringProperty(naradie.getPopis());

	}

	public NaradieFxModel(Pouzivatel pouzivatel) {
		// NOVE NARADIE

		this.pouzivatel = pouzivatel;
		this.vlastnikId = this.pouzivatel.getId();

		this.znacka = new SimpleStringProperty();
		this.typ = new SimpleStringProperty();
		this.druh = new SimpleStringProperty();
		this.popis = new SimpleStringProperty();

	}

	Naradie getNaradie() {
		if (id == null) // PRIDAVANIE NOVEHO NARADIA
			return new Naradie(getZnacka(), getTyp(), true, getDruh(), pouzivatel, getPopis(), new ArrayList<Akcia>(),
					DaoFactory.INSTANCE.getAkciaDao(), DaoFactory.INSTANCE.getNaradieDao());
		else // EDITACIA
			return new Naradie(id, getZnacka(), getTyp(), jeDostupne, getDruh(), naradie.getVlastnik(), getPopis(),
					naradie.getAkcie());
	}

	public String getZnacka() {
		return znacka.get();
	}

	public StringProperty znackaProperty() {
		return znacka;
	}

	public void setZnacka(String znacka) {
		this.znacka.set(znacka);
	}

	public String getTyp() {
		return typ.get();
	}

	public StringProperty typProperty() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ.set(typ);
	}

	public String getDruh() {
		return druh.get();
	}

	public StringProperty druhProperty() {
		return druh;
	}

	public void setDruh(String druh) {
		this.druh.set(druh);
	}

	public String getPopis() {
		return popis.get();
	}

	public StringProperty popisProperty() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis.set(popis);
	}

	@Override
	public String toString() {
		return "NaradieFxModel: id=" + id + ", jeDostupne=" + jeDostupne + ", vlastnikId=" + vlastnikId + ", znacka="
				+ getZnacka() + ", typ=" + getTyp() + ", druh=" + getDruh() + ", popis=" + getPopis();
	}

}
