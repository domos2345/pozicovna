package pozicovna.business;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BorrowedTool {

    private Naradie naradie;
    private Akcia akcia;
    private String druh;
    private String znacka;
    private String typ;
    private LocalDateTime poziadanie;
    private LocalDateTime zamietnutie;
    private LocalDateTime pozicanie;
    private LocalDateTime vratenie;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public BorrowedTool(Naradie naradie, Akcia akcia) {
        this.naradie = naradie;
        this.akcia = akcia;
        this.druh = naradie.getDruhNaradia();
        this.znacka = naradie.getZnacka();
        this.typ = naradie.getTyp();
        this.poziadanie = akcia.getZiadost();
        this.zamietnutie = akcia.getZamietnute();
        this.pozicanie = akcia.getPozicane();
        this.vratenie = akcia.getVratene();
    }

    public String getPoziadanie() {
        return poziadanie == null ?"-" :formatter.format(poziadanie);
    }

    public String getZamietnutie() {
        return zamietnutie == null ?"-" :formatter.format(zamietnutie);
    }

    public String getPozicanie() {
        return pozicanie == null ?"-" :formatter.format(pozicanie);
    }

    public String getVratenie() {
        return vratenie == null ?"-" :formatter.format(vratenie);
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

    public Akcia getAkcia() {
        return akcia;
    }
}
