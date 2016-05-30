package base;

import java.time.LocalDate;

/**
 * Basic partner container class.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public abstract class AbstractPartner {

	private String vorname;

	private String nachname;

	private final LocalDate geburtsdatum;

	public AbstractPartner(final String vorname, final String nachname, final LocalDate geburtsdatum) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
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
		final AbstractPartner other = (AbstractPartner) obj;
		if (this.geburtsdatum == null) {
			if (other.geburtsdatum != null) {
				return false;
			}
		} else if (!this.geburtsdatum.equals(other.geburtsdatum)) {
			return false;
		}
		if (this.nachname == null) {
			if (other.nachname != null) {
				return false;
			}
		} else if (!this.nachname.equals(other.nachname)) {
			return false;
		}
		if (this.vorname == null) {
			if (other.vorname != null) {
				return false;
			}
		} else if (!this.vorname.equals(other.vorname)) {
			return false;
		}
		return true;
	}

	public LocalDate getGeburtsdatum() {
		return this.geburtsdatum;
	}

	public String getNachname() {
		return this.nachname;
	}

	public String getVorname() {
		return this.vorname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.geburtsdatum == null) ? 0 : this.geburtsdatum.hashCode());
		result = prime * result + ((this.nachname == null) ? 0 : this.nachname.hashCode());
		result = prime * result + ((this.vorname == null) ? 0 : this.vorname.hashCode());
		return result;
	}

	public void setNachname(final String nachname) {
		this.nachname = nachname;
	}

	public void setVorname(final String vorname) {
		this.vorname = vorname;
	}
}
