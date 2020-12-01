package pozicovna.entities;

public class Pouzivatel {
	private Long id;
	private String meno;
	private String priezvisko;
	private String email;
	private String tel_cislo;
	private String heslo;
	private String mesto;
	private String ulica;
	private String cislo_domu;
	private String psc;
	private String okres;

	public Pouzivatel(Long id, String meno, String priezvisko, String email, String tel_cislo, String heslo,
			String mesto, String ulica, String cislo_domu, String psc, String okres) {
		super();
		this.id = id;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.email = email;
		this.tel_cislo = tel_cislo;
		this.heslo = heslo;
		this.mesto = mesto;
		this.ulica = ulica;
		this.cislo_domu = cislo_domu;
		this.psc = psc;
		this.okres = okres;
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

	public String getHeslo() {
		return heslo;
	}

	public void setHeslo(String heslo) {
		this.heslo = heslo;
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
				+ ", tel_cislo=" + tel_cislo + ", heslo=" + heslo + ", mesto=" + mesto + ", ulica=" + ulica
				+ ", cislo_domu=" + cislo_domu + ", psc=" + psc + ", okres=" + okres + "]";
	}
}
