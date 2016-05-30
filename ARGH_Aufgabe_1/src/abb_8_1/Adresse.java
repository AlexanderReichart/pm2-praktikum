package abb_8_1;

import javax.naming.OperationNotSupportedException;

import base.AbstractAdresse;

/**
 * Address container class.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Adresse extends AbstractAdresse {
	/**
	 * Associated partner instance.
	 */
	private final Partner partner;

	/**
	 * Creates a new address with the given data.
	 *
	 * For simplicity reasons the factory constructor is omitted. See
	 * {@link Partner#Partner(String, String, int, String, String, String, String, java.time.LocalDate)}
	 * for a factory constructor implementation.
	 *
	 * @param partner
	 * @param strasse
	 * @param hausnummer
	 * @param plz
	 * @param ort
	 * @param land
	 * @throws OperationNotSupportedException
	 *             if no partner instance was given or if the given partner
	 *             instance is invalid.
	 */
	public Adresse(final Partner partner, final String strasse, final String hausnummer, final int plz,
			final String ort, final String land) throws OperationNotSupportedException {
		super(strasse, hausnummer, plz, ort, land);
		// if there is no partner or if the partner already has an address, the
		// operation is not supported.
		if (partner == null || partner.getAdresse() != null) {
			throw new OperationNotSupportedException();
		}
		this.partner = partner;
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
		final Adresse other = (Adresse) obj;
		if (this.partner == null) {
			if (other.partner != null) {
				return false;
			}
		} else if (!this.partner.equals(other.partner)) {
			return false;
		}
		return true;
	}

	/**
	 * @return Associated partner.
	 */
	public Partner getPartner() {
		return this.partner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.partner == null) ? 0 : this.partner.hashCode());
		return result;
	}
}
