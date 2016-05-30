package abb_8_3;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import base.AbstractPartner;

/**
 * Partner container class.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Partner extends AbstractPartner implements Iterable<Adresse> {
	/**
	 * Associated address instances.
	 *
	 * The {@link CopyOnWriteArraySet} is used to ensure the exposed iterator
	 * can't modify the protected address list.
	 */
	private final Set<Adresse> adressen = new CopyOnWriteArraySet<Adresse>();

	/**
	 * Creates a new partner without addresses.
	 *
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 */
	public Partner(final String vorname, final String nachname, final LocalDate geburtsdatum) {
		super(vorname, nachname, geburtsdatum);
	}

	/**
	 * Adds an address to this partner.
	 *
	 * @param adresse
	 */
	public void addAdresse(final Adresse adresse) {
		if (adresse != null && !this.hasAdresse(adresse)) {
			this.adressen.add(adresse);
			adresse.addPartner(this);
		}
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
		if (this.adressen == null) {
			if (other.adressen != null) {
				return false;
			}
		} else if (!this.adressen.equals(other.adressen)) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the given address is associated with this partner.
	 *
	 * @param adresse
	 * @return
	 */
	public boolean hasAdresse(final Adresse adresse) {
		return this.adressen.contains(adresse);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.adressen == null) ? 0 : this.adressen.hashCode());
		return result;
	}

	@Override
	public Iterator<Adresse> iterator() {
		return this.adressen.iterator();
	}
}
