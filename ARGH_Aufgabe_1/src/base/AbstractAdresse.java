package base;

/**
 * Basic address container class.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public abstract class AbstractAdresse {

	private String strasse;

	private String hausnummer;

	private int plz;

	private String ort;

	private String land;

	public AbstractAdresse(final String strasse, final String hausnummer, final int plz, final String ort,
			final String land) {
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final AbstractAdresse other = (AbstractAdresse) obj;
		if (this.hausnummer == null) {
			if (other.hausnummer != null) {
				return false;
			}
		} else if (!this.hausnummer.equals(other.hausnummer)) {
			return false;
		}
		if (this.land == null) {
			if (other.land != null) {
				return false;
			}
		} else if (!this.land.equals(other.land)) {
			return false;
		}
		if (this.ort == null) {
			if (other.ort != null) {
				return false;
			}
		} else if (!this.ort.equals(other.ort)) {
			return false;
		}
		if (this.plz != other.plz) {
			return false;
		}
		if (this.strasse == null) {
			if (other.strasse != null) {
				return false;
			}
		} else if (!this.strasse.equals(other.strasse)) {
			return false;
		}
		return true;
	}

	public String getHausnummer() {
		return this.hausnummer;
	}

	public String getLand() {
		return this.land;
	}

	public String getOrt() {
		return this.ort;
	}

	public int getPlz() {
		return this.plz;
	}

	public String getStrasse() {
		return this.strasse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.hausnummer == null) ? 0 : this.hausnummer.hashCode());
		result = prime * result + ((this.land == null) ? 0 : this.land.hashCode());
		result = prime * result + ((this.ort == null) ? 0 : this.ort.hashCode());
		result = prime * result + this.plz;
		result = prime * result + ((this.strasse == null) ? 0 : this.strasse.hashCode());
		return result;
	}

	public void setHausnummer(final String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public void setLand(final String land) {
		this.land = land;
	}

	public void setOrt(final String ort) {
		this.ort = ort;
	}

	public void setPlz(final int plz) {
		this.plz = plz;
	}

	public void setStrasse(final String strasse) {
		this.strasse = strasse;
	}
}
