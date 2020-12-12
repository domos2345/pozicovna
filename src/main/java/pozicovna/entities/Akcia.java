package pozicovna.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.format.annotation.DateTimeFormat;

public class Akcia {

	private Long id;
	private Long naradie_id;
	private Long vlastnik_id;
	private LocalDateTime ziadost;
	private LocalDateTime pozicanie;
	private LocalDateTime zamietnutie;
	private LocalDateTime vratenie;
	private Pouzivatel komu;

	public Akcia(Long id, Long naradie_id, Long vlastnik_id, LocalDateTime ziadost, LocalDateTime pozicanie,
			LocalDateTime zamietnutie, LocalDateTime vratenie) {
		super();
		this.id = id;
		this.naradie_id = naradie_id;
		this.vlastnik_id = vlastnik_id;
		this.ziadost = ziadost;
		this.pozicanie = pozicanie;
		this.zamietnutie = zamietnutie;
		this.vratenie = vratenie;
	}

	public Akcia(Long id, Long naradie_id, Long vlastnik_id, LocalDateTime ziadost) {
		super();
		this.id = id;
		this.naradie_id = naradie_id;
		this.vlastnik_id = vlastnik_id;
		this.ziadost = ziadost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNaradie_id() {
		return naradie_id;
	}

	public void setNaradie_id(Long naradie_id) {
		this.naradie_id = naradie_id;
	}

	public Long getVlastnik_id() {
		return vlastnik_id;
	}

	public void setVlastnik_id(Long vlastnik_id) {
		this.vlastnik_id = vlastnik_id;
	}

	public LocalDateTime getZiadost() {
		return ziadost;
	}

	public void setZiadost(LocalDateTime ziadost) {
		this.ziadost = ziadost;
	}

	public LocalDateTime getPozicanie() {
		return pozicanie;
	}

	public void setPozicanie(LocalDateTime pozicanie) {
		this.pozicanie = pozicanie;
	}

	public LocalDateTime getZamietnutie() {
		return zamietnutie;
	}

	public void setZamietnutie(LocalDateTime zamietnutie) {
		this.zamietnutie = zamietnutie;
	}

	public LocalDateTime getVratenie() {
		return vratenie;
	}

	public void setVratenie(LocalDateTime vratenie) {
		this.vratenie = vratenie;
	}

	@Override
	public String toString() {
		return "Akcia [id=" + id + ", naradie_id=" + naradie_id + ", vlastnik_id=" + vlastnik_id + ", ziadost="
				+ ziadost + ", pozicanie=" + pozicanie + ", zamietnutie=" + zamietnutie + ", vratenie=" + vratenie
				+ "]";
	}

}
