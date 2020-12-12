package pozicovna.entities;

import java.util.List;

public class Naradie {
	private Long id;
	private String znacka;
	private String typ;
	private Boolean je_dostupne;
	private String druhNaradia;
	private Pouzivatel vlastnik;
	private String popis;
	private List<Akcia> akcie;


	public Naradie(Long id, String znacka, String typ, Boolean je_dostupne, String druhNaradia, Pouzivatel vlastnik, String popis, List<Akcia> akcie) {
		this.id = id;
		this.znacka = znacka;
		this.typ = typ;
		this.je_dostupne = je_dostupne;
		this.druhNaradia = druhNaradia;
		this.vlastnik = vlastnik;
		this.popis = popis;
		this.akcie = akcie;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setZnacka(String znacka) {
		this.znacka = znacka;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public void setJe_dostupne(Boolean je_dostupne) {
		this.je_dostupne = je_dostupne;
	}

	public void setDruhNaradia(String druhNaradia) {
		this.druhNaradia = druhNaradia;
	}

	public void setVlastnik(Pouzivatel vlastnik) {
		this.vlastnik = vlastnik;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	public void setAkcie(List<Akcia> akcie) {
		this.akcie = akcie;
	}

	public Long getId() {
		return id;
	}

	public String getZnacka() {
		return znacka;
	}

	public String getTyp() {
		return typ;
	}

	public Boolean getJe_dostupne() {
		return je_dostupne;
	}

	public String getDruhNaradia() {
		return druhNaradia;
	}

	public Pouzivatel getVlastnik() {
		return vlastnik;
	}

	public String getPopis() {
		return popis;
	}

	public List<Akcia> getAkcie() {
		return akcie;
	}

	@Override
	public String toString() {
		return "Naradie{" +
				"id=" + id +
				", znacka='" + znacka + '\'' +
				", typ='" + typ + '\'' +
				", je_dostupne=" + je_dostupne +
				", druhNaradia='" + druhNaradia + '\'' +
				", vlastnik=" + vlastnik +
				", popis='" + popis + '\'' +
				", akcie=" + akcie +
				'}';
	}
}
