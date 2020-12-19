package pozicovna.entities;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Objects;

public class Pouzivatel {
	private Long id;
	private String meno;
	private String priezvisko;
	private String email;
	private String tel_cislo;
	private String sol_hash;
	private String heslo_hash;
	private String mesto;
	private String ulica;
	private String cislo_domu;
	private String psc;
	private String okres;

	public Pouzivatel(Long id, String meno, String priezvisko, String email, String tel_cislo, String sol_hash,
			String heslo_hash, String mesto, String ulica, String cislo_domu, String psc, String okres) {
		super();
		this.id = id;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.email = email;
		this.tel_cislo = tel_cislo;
		this.sol_hash = sol_hash;
		this.heslo_hash = heslo_hash;
		this.mesto = mesto;
		this.ulica = ulica;
		this.cislo_domu = cislo_domu;
		this.psc = psc;
		this.okres = okres;
	}

	public Pouzivatel(String meno, String priezvisko, String email, String tel_cislo, String heslo_hash, String mesto,
			String ulica, String cislo_domu, String psc, String okres) {
		super();
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.email = email;
		this.tel_cislo = tel_cislo;
		this.sol_hash = BCrypt.gensalt();
		this.heslo_hash = BCrypt.hashpw(heslo_hash, sol_hash);
		this.mesto = mesto;
		this.ulica = ulica;
		this.cislo_domu = cislo_domu;
		this.psc = psc;
		this.okres = okres;
		// NA REGISTRACIU
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getPriezvisko() {
		return priezvisko;
	}

	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel_cislo() {
		return tel_cislo;
	}

	public void setTel_cislo(String tel_cislo) {
		this.tel_cislo = tel_cislo;
	}

	public String getSol_hash() {
		return sol_hash;
	}

	public void setSol_hash(String sol_hash) {
		this.sol_hash = sol_hash;
	}

	public String getHeslo_hash() {
		return heslo_hash;
	}

	public void setHeslo_hash(String heslo_hash) {
		this.heslo_hash = heslo_hash;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getCislo_domu() {
		return cislo_domu;
	}

	public void setCislo_domu(String cislo_domu) {
		this.cislo_domu = cislo_domu;
	}

	public String getPsc() {
		return psc;
	}

	public void setPsc(String psc) {
		this.psc = psc;
	}

	public String getOkres() {
		return okres;
	}

	public void setOkres(String okres) {
		this.psc = okres;
	}

	@Override
	public String toString() {
		return "Pouzivatel [id=" + id + ", meno=" + meno + ", priezvisko=" + priezvisko + ", email=" + email
				+ ", tel_cislo=" + tel_cislo + ", sol_hash=" + sol_hash + ", heslo_hash=" + heslo_hash + ", mesto="
				+ mesto + ", ulica=" + ulica + ", cislo_domu=" + cislo_domu + ", psc=" + psc + ", okres=" + okres + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pouzivatel that = (Pouzivatel) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
