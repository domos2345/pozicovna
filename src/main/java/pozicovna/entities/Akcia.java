package pozicovna.entities;

import java.time.LocalDateTime;

import pozicovna.storage.DaoFactory;

public class Akcia {
	private Long id;
	private Pouzivatel ziadatel; // cez join v AkciaDao
	private LocalDateTime ziadost;
	private LocalDateTime zamietnute;
	private LocalDateTime pozicane;
	private LocalDateTime vratene;

	public Akcia(Long id, Pouzivatel ziadatel, LocalDateTime ziadost, LocalDateTime zamietnute, LocalDateTime pozicane,
			LocalDateTime vratene) {
		this.id = id;
		this.ziadatel = ziadatel;
		this.ziadost = ziadost;
		this.zamietnute = zamietnute;
		this.pozicane = pozicane;
		this.vratene = vratene;
	}

	public Akcia(Pouzivatel ziadatel, LocalDateTime ziadost, LocalDateTime zamietnute, LocalDateTime pozicane,
			LocalDateTime vratene) {
		this.ziadatel = ziadatel;
		this.ziadost = ziadost;
		this.zamietnute = zamietnute;
		this.pozicane = pozicane;
		this.vratene = vratene;
	}

	public Akcia(Pouzivatel ziadatel) {
		this.ziadatel = ziadatel;
		this.ziadost = LocalDateTime.now();
	}

	public void reject(long idNaradie) {
		zamietnute = LocalDateTime.now();
		DaoFactory.INSTANCE.getAkciaDao().save(this, idNaradie);
	}

	public boolean mozemVratit() {
		return pozicane != null && vratene == null;
	}

	public void setZiadatel(Pouzivatel ziadatel) {
		this.ziadatel = ziadatel;
	}

	public void setZiadost(LocalDateTime ziadost) {
		this.ziadost = ziadost;
	}

	public void setZamietnute(LocalDateTime zamietnute) {
		this.zamietnute = zamietnute;
	}

	public void setPozicane(LocalDateTime pozicane) {
		this.pozicane = pozicane;
	}

	public void setVratene(LocalDateTime vratene) {
		this.vratene = vratene;
	}

	public Long getId() {
		return id;
	}

	public Pouzivatel getZiadatel() {
		return ziadatel;
	}

	public LocalDateTime getZiadost() {
		return ziadost;
	}

	public LocalDateTime getZamietnute() {
		return zamietnute;
	}

	public LocalDateTime getPozicane() {
		return pozicane;
	}

	public LocalDateTime getVratene() {
		return vratene;
	}

	@Override
	public String toString() {
		return "Akcia{" + "id=" + id + ", ziadatel=" + ziadatel + ", ziadost=" + ziadost + ", zamietnute=" + zamietnute
				+ ", pozicane=" + pozicane + ", vratene=" + vratene + '}';
	}
}
