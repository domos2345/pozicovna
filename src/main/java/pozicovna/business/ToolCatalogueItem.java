package pozicovna.business;

import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

public class ToolCatalogueItem {

    private Naradie naradie;
    private String druh;
    private String znacka;
    private String typ;
    private String stav;
    private String majitel;
    private String okres;

    public ToolCatalogueItem(Naradie naradie) {
        this.naradie = naradie;
        this.druh = naradie.getDruhNaradia();
        this.znacka = naradie.getZnacka();
        this.typ = naradie.getTyp();
        this.stav = naradie.getJe_dostupne() ?"dostupné" :"nedostupné";
        Pouzivatel vlastnik = naradie.getVlastnik();
        this.majitel = vlastnik.getPriezvisko() + ", " + vlastnik.getMeno();
        this.okres = vlastnik.getOkres();
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

    public String getStav() {
        return stav;
    }

    public String getMajitel() {
        return majitel;
    }

    public String getOkres() {
        return okres;
    }

    public Naradie getNaradie() {
        return naradie;
    }

    @Override
    public String toString() {
        return "ToolCatalogueItem{" +
                ", druh='" + druh + '\'' +
                ", znacka='" + znacka + '\'' +
                ", typ='" + typ + '\'' +
                ", stav='" + stav + '\'' +
                ", majitel='" + majitel + '\'' +
                ", okres='" + okres + '\'' +
                '}';
    }
}
