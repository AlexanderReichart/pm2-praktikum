package abb_8_1;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import base.AbstractPartner;

/**
 * Partner container class.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Partner extends AbstractPartner {
	/**
	 * Associated address instance.
	 */
	private final Adresse adresse;

	/**
	 * Creates a new partner from the given data. This constructor is a factory
	 * and will also create the required address instance.
	 *
	 * For simplicity reasons the simple constructor is omitted. See
	 * {@link Adresse#Adresse(Partner, String, String, int, String, String)} for
	 * a simple constructor implementation.
	 *
	 * @param strasse
	 * @param hausnummer
	 * @param plz
	 * @param ort
	 * @param land
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 * @throws OperationNotSupportedException
	 *             if the address instance could not be created correctly.
	 */
	public Partner(final String strasse, final String hausnummer, final int plz, final String ort, final String land,
			final String vorname, final String nachname, final LocalDate geburtsdatum)
			throws OperationNotSupportedException {
		super(vorname, nachname, geburtsdatum);
		this.adresse = new Adresse(this, strasse, hausnummer, plz, ort, land);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Partner other = (Partner) obj;
		if (this.adresse == null) {
			if (other.adresse != null) {
				return false;
			}
		} else if (!this.adresse.equals(other.adresse)) {
			return false;
		}
		return true;
	}

	/**
	 * @return Associated address instance.
	 */
	public Adresse getAdresse() {
		return this.adresse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.adresse == null) ? 0 : this.adresse.hashCode());
		return result;
	}
}
