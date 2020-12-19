package pozicovna.business;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request {

    private Naradie naradie;
    private Akcia akcia;

    private String druh;
    private String znacka;
    private String typ;
    private String ziadatel;
    private LocalDateTime datum;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public Request(Naradie naradie, Akcia akcia) {
        this.naradie = naradie;
        this.akcia = akcia;
        this.druh = naradie.getDruhNaradia();
        this.znacka = naradie.getZnacka();
        this.typ = naradie.getTyp();
        this.ziadatel = akcia.getZiadatel().getPriezvisko() + ", " + akcia.getZiadatel().getPriezvisko();
        this.datum = akcia.getZiadost();
    }

    public String getDatum() {
        return datum == null ?"-" :formatter.format(datum);
    }

    public String getDruh() {
        return druh;
    }

    public String getZnacka() {
        return znacka;
    }

    public String getTyp() {
        return typ;
    }

    public Naradie getNaradie() {
        return naradie;
    }

    public String getZiadatel() {
        return ziadatel;
    }

    public Akcia getAkcia() {
        return akcia;
    }
}
