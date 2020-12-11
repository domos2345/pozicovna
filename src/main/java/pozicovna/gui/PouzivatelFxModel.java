package pozicovna.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pozicovna.entities.Pouzivatel;

public class PouzivatelFxModel {

    private Long id;
    private StringProperty meno;
    private StringProperty priezvisko;
    private StringProperty email;
    private StringProperty telCislo;
    private String solHash;
    private String hesloHash;
    private StringProperty heslo;
    private StringProperty mesto;
    private StringProperty ulica;
    private StringProperty cisloDomu;
    private StringProperty psc;
    private StringProperty okres;

    public PouzivatelFxModel() {
        this.meno = new SimpleStringProperty();
        this.priezvisko = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.telCislo = new SimpleStringProperty();
        this.heslo = new SimpleStringProperty();
        this.mesto = new SimpleStringProperty();
        this.ulica = new SimpleStringProperty();
        this.cisloDomu = new SimpleStringProperty();
        this.psc = new SimpleStringProperty();
        this.okres = new SimpleStringProperty();
    }

    // editacia pouzivatela
    public PouzivatelFxModel(Pouzivatel pouzivatel) {
        this.id = pouzivatel.getId();
        this.solHash = pouzivatel.getSol_hash();
        this.hesloHash = pouzivatel.getHeslo_hash();

        this.meno = new SimpleStringProperty(pouzivatel.getMeno());
        this.priezvisko = new SimpleStringProperty(pouzivatel.getPriezvisko());
        this.email = new SimpleStringProperty(pouzivatel.getEmail());
        this.telCislo = new SimpleStringProperty(pouzivatel.getTel_cislo());
        this.heslo = new SimpleStringProperty("");
        this.mesto = new SimpleStringProperty(pouzivatel.getMesto());
        this.ulica = new SimpleStringProperty(pouzivatel.getUlica());
        this.cisloDomu = new SimpleStringProperty(pouzivatel.getUlica());
        this.psc = new SimpleStringProperty(pouzivatel.getPsc());
        this.okres = new SimpleStringProperty(pouzivatel.getOkres());
    }

    Pouzivatel getPouzivatel(){
        if (id == null) // registracia
            return new Pouzivatel(  getMeno(), getPriezvisko(), getEmail(),
                                    getTelCislo(), getHeslo(), getMesto(),
                                    getUlica(), getCisloDomu(), getPsc(), getOkres());
        else if (heslo.getValue().equals("")) // heslo nebolo zmenene
            return new Pouzivatel(  id, getMeno(), getPriezvisko(),
                                    getEmail(), getTelCislo(), solHash,
                                    hesloHash ,getMesto(), getUlica(),
                                    getCisloDomu(), getPsc(), getOkres());
        else // menil som heslo
            return new Pouzivatel(  id, getMeno(), getPriezvisko(),
                                    getEmail(), getTelCislo(), solHash,
                                    BCrypt.hashpw(getHeslo(), solHash),getMesto(), getUlica(),
                                    getCisloDomu(), getPsc(), getOkres());
    }

    public String getMeno() {
        return meno.get();
    }

    public StringProperty menoProperty() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno.set(meno);
    }

    public String getPriezvisko() {
        return priezvisko.get();
    }

    public StringProperty priezviskoProperty() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko.set(priezvisko);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelCislo() {
        return telCislo.get();
    }

    public StringProperty telCisloProperty() {
        return telCislo;
    }

    public void setTelCislo(String tel_cislo) {
        this.telCislo.set(tel_cislo);
    }

    public String getHeslo() {
        return heslo.get();
    }

    public StringProperty hesloProperty() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo.set(heslo);
    }

    public String getMesto() {
        return mesto.get();
    }

    public StringProperty mestoProperty() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto.set(mesto);
    }

    public String getUlica() {
        return ulica.get();
    }

    public StringProperty ulicaProperty() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica.set(ulica);
    }

    public String getCisloDomu() {
        return cisloDomu.get();
    }

    public StringProperty cisloDomuProperty() {
        return cisloDomu;
    }

    public void setCisloDomu(String cisloDomu) {
        this.cisloDomu.set(cisloDomu);
    }

    public String getPsc() {
        return psc.get();
    }

    public StringProperty pscProperty() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc.set(psc);
    }

    public String getOkres() {
        return okres.get();
    }

    public StringProperty okresProperty() {
        return okres;
    }

    public void setOkres(String okres) {
        this.okres.set(okres);
    }

    @Override
    public String toString() {
        return "PouzivatelFxModel{" +
                "id=" + id +
                ", meno=" + getMeno() +
                ", priezvisko=" + getPriezvisko() +
                ", email=" + getEmail() +
                ", telCislo=" + getTelCislo() +
                ", heslo=" + getHeslo() +
                ", mesto=" + getMeno() +
                ", ulica=" + getUlica() +
                ", cisloDomu=" + getCisloDomu() +
                ", psc=" + getPsc() +
                ", okres=" + getOkres() +
                '}';
    }

}
