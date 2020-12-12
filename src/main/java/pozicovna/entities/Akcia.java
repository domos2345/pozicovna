package pozicovna.entities;

import java.util.Date;

public class Akcia {
    private Long id;
    private Pouzivatel komu; // cez join v AkciaDao
    private Date ziadost;
    private Date zamietnute;
    private Date pozicane;
    private Date vratene;

    public Akcia(Long id, Pouzivatel komu, Date ziadost, Date zamietnute, Date pozicane, Date vratene) {
        this.id = id;
        this.komu = komu;
        this.ziadost = ziadost;
        this.zamietnute = zamietnute;
        this.pozicane = pozicane;
        this.vratene = vratene;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKomu(Pouzivatel komu) {
        this.komu = komu;
    }

    public void setZiadost(Date ziadost) {
        this.ziadost = ziadost;
    }

    public void setZamietnute(Date zamietnute) {
        this.zamietnute = zamietnute;
    }

    public void setPozicane(Date pozicane) {
        this.pozicane = pozicane;
    }

    public void setVratene(Date vratene) {
        this.vratene = vratene;
    }

    public Long getId() {
        return id;
    }

    public Pouzivatel getKomu() {
        return komu;
    }

    public Date getZiadost() {
        return ziadost;
    }

    public Date getZamietnute() {
        return zamietnute;
    }

    public Date getPozicane() {
        return pozicane;
    }

    public Date getVratene() {
        return vratene;
    }

    @Override
    public String toString() {
        return "Akcia{" +
                "id=" + id +
                ", komu=" + komu +
                ", ziadost=" + ziadost +
                ", zamietnute=" + zamietnute +
                ", pozicane=" + pozicane +
                ", vratene=" + vratene +
                '}';
    }
}
