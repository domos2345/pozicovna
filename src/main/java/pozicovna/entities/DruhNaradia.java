package pozicovna.entities;

public class DruhNaradia {
	private Long id;
	private String meno;

	public DruhNaradia(Long id, String meno) {
		super();
		this.id = id;
		this.meno = meno;
	}

	public DruhNaradia(String meno) {
		super();
		this.meno = meno;
	}

	public Long getId() {
		return id;
	}

	public void setDruh_naraia_id(Long id) {
		this.id = id;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	@Override
	public String toString() {
		return "DruhNaradia [id=" + id + ", meno=" + meno + "]";
	}

}
