package pozicovna.entities;

public class Naradie {
	private Long id;
	private String znacka;
	private String typ;
	private Boolean je_dostupne;
	private Long druh_naradia_id;
	private Long vlastnik_id;
	private String popis;

	public Naradie(Long id, String znacka, String typ, Boolean je_dostupne, Long druh_naradia_id, Long vlastnik_id,
			String popis) {
		super();
		this.id = id;
		this.znacka = znacka;
		this.typ = typ;
		this.je_dostupne = je_dostupne;
		this.druh_naradia_id = druh_naradia_id;
		this.vlastnik_id = vlastnik_id;
		this.popis = popis;
	}

	public Naradie(String znacka, String typ, Long druh_naradia_id, Long vlastnik_id, String popis) {
		// CREATE NEW
		super();
		this.znacka = znacka;
		this.typ = typ;
		this.je_dostupne = true;
		this.druh_naradia_id = druh_naradia_id;
		this.vlastnik_id = vlastnik_id;
		this.popis = popis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZnacka() {
		return znacka;
	}

	public void setZnacka(String znacka) {
		this.znacka = znacka;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Boolean getJe_dostupne() {
		return je_dostupne;
	}

	public void setJe_dostupne(Boolean je_dostupne) {
		this.je_dostupne = je_dostupne;
	}

	public Long getDruh_naradia_id() {
		return druh_naradia_id;
	}

	public void setDruh_naradia_id(Long druh_naradia_id) {
		this.druh_naradia_id = druh_naradia_id;
	}

	public Long getVlastnik_id() {
		return vlastnik_id;
	}

	public void setVlastnik_id(Long vlastnik_id) {
		this.vlastnik_id = vlastnik_id;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	@Override
	public String toString() {
		return "Naradie [id=" + id + ", znacka=" + znacka + ", typ=" + typ + ", druh_naradia_id=" + druh_naradia_id
				+ ", vlastnik_id=" + vlastnik_id + ", popis=" + popis + "]";
	}

}
