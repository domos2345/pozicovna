package pozicovna.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

import java.time.format.DateTimeFormatter;

public class ToolInfoController {
    @FXML
    private Label druhLabel;
    @FXML
    private Label znackaLabel;
    @FXML
    private Label typLabel;
    @FXML
    private TextArea popisTextArea;
    @FXML
    private Label majitelPriezviskoLabel;
    @FXML
    private Label majitelMenoLabel;
    @FXML
    private Label majitelCisloLabel;
    @FXML
    private Label majitelEmailLabel;
    @FXML
    private Label majitelOkresLabel;
    @FXML
    private Label majitelAdresaLabel;
    @FXML
    private Label ziadatelPriezviskoLabel;
    @FXML
    private Label ziadatelAdresaLabel;
    @FXML
    private Label ziadatelMenoLabel;
    @FXML
    private Label ziadatelCisloLabel;
    @FXML
    private Label ziadatelEmailLabel;
    @FXML
    private Label ziadatelOkresLabel;
    @FXML
    private Label stavLabel;
    @FXML
    private Label ziadostLabel;
    @FXML
    private Label zamietnutieLabel;
    @FXML
    private Label pozicanieLabel;
    @FXML
    private Label vratenieLabel;


    private Naradie naradie;
    private Akcia akcia;

    public ToolInfoController(Naradie naradie) {
        this.naradie = naradie;
    }

    public ToolInfoController(Naradie naradie, Akcia akcia) {
        this.naradie = naradie;
        this.akcia = akcia;
    }

    @FXML
    void initialize() {
        nastavVseobecneInfo();
        nastavMajitela(naradie.getVlastnik());

        if(akcia == null)
            return;

        nastavZiadatela(akcia.getZiadatel());
        nastavDatumy();
    }

    private void nastavVseobecneInfo(){
        this.druhLabel.setText(naradie.getDruhNaradia());
        this.znackaLabel.setText(naradie.getZnacka());
        this.typLabel.setText(naradie.getTyp());
        this.stavLabel.setText(naradie.getJe_dostupne()? "Dostupné": "Požičané");
        this.popisTextArea.setText(naradie.getPopis());
    }

    private void nastavMajitela(Pouzivatel pouzivatel){
        this.majitelPriezviskoLabel.setText(pouzivatel.getMeno());
        this.majitelMenoLabel.setText(pouzivatel.getPriezvisko());
        this.majitelCisloLabel.setText(pouzivatel.getTel_cislo());
        this.majitelEmailLabel.setText(pouzivatel.getEmail());
        this.majitelOkresLabel.setText(pouzivatel.getOkres());
        String adresa = pouzivatel.getUlica() + " " + pouzivatel.getCislo_domu() + ",\n" + pouzivatel.getPsc()  + " \n" + pouzivatel.getMesto();
        this.majitelAdresaLabel.setText(adresa);
    }

    private void nastavZiadatela(Pouzivatel pouzivatel){
        this.ziadatelPriezviskoLabel.setText(pouzivatel.getMeno());
        this.ziadatelMenoLabel.setText(pouzivatel.getPriezvisko());
        this.ziadatelCisloLabel.setText(pouzivatel.getTel_cislo());
        this.ziadatelEmailLabel.setText(pouzivatel.getEmail());
        this.ziadatelOkresLabel.setText(pouzivatel.getOkres());
        String adresa = pouzivatel.getUlica() + " " + pouzivatel.getCislo_domu() + ",\n" + pouzivatel.getPsc()  + " \n" + pouzivatel.getMesto();
        this.ziadatelAdresaLabel.setText(adresa);
    }

    private void nastavDatumy(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.ziadostLabel.setText(akcia.getZiadost() == null ?"-" :formatter.format(akcia.getZiadost()));
        this.zamietnutieLabel.setText(akcia.getZamietnute() == null ?"-" :formatter.format(akcia.getZamietnute()));
        this.pozicanieLabel.setText(akcia.getPozicane() == null ?"-" :formatter.format(akcia.getPozicane()));
        this.vratenieLabel.setText(akcia.getVratene() == null ?"-" :formatter.format(akcia.getVratene()));
    }
}
