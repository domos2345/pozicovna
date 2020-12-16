package pozicovna.entities;

import pozicovna.storage.AkciaDao;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.NaradieDao;

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

	AkciaDao akciaDao = DaoFactory.INSTANCE.getAkciaDao();
	NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

	public Naradie(Long id, String znacka, String typ, Boolean je_dostupne, String druhNaradia, Pouzivatel vlastnik,
			String popis, List<Akcia> akcie) {
		this.id = id;
		this.znacka = znacka;
		this.typ = typ;
		this.je_dostupne = je_dostupne;
		this.druhNaradia = druhNaradia;
		this.vlastnik = vlastnik;
		this.popis = popis;
		this.akcie = akcie;
	}
	
	

	public Naradie(String znacka, String typ, Boolean je_dostupne, String druhNaradia, Pouzivatel vlastnik,
			String popis, List<Akcia> akcie, AkciaDao akciaDao, NaradieDao naradieDao) {
		this.znacka = znacka;
		this.typ = typ;
		this.je_dostupne = je_dostupne;
		this.druhNaradia = druhNaradia;
		this.vlastnik = vlastnik;
		this.popis = popis;
		this.akcie = akcie;
		this.akciaDao = akciaDao;
		this.naradieDao = naradieDao;
	}



	public void lendTo(Pouzivatel lender) throws NaradieCannotBeLendedException {
		if (!je_dostupne)
			throw new NaradieCannotBeLendedException("Naradie s id " + id + " uz je pozicane");
		Akcia akcia = new Akcia(lender);
		akciaDao.save(akcia, id);
		akcie.add(akcia);
		naradieDao.save(this);
	}

	public void setJe_dostupne(Boolean je_dostupne) {
		this.je_dostupne = je_dostupne;
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
		return "Naradie{" + "id=" + id + ", znacka='" + znacka + '\'' + ", typ='" + typ + '\'' + ", je_dostupne="
				+ je_dostupne + ", druhNaradia='" + druhNaradia + '\'' + ", vlastnik=" + vlastnik + ", popis='" + popis
				+ '\'' + ", akcie=" + akcie + '}';
	}
}
