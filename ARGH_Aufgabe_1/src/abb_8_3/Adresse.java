package abb_8_3;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import base.AbstractAdresse;

/**
 * Address container class.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Adresse extends AbstractAdresse implements Iterable<Partner> {
	/**
	 * Associated partner instances.
	 *
	 * The {@link CopyOnWriteArraySet} is used to ensure the exposed iterator
	 * can't modify the protected partner list.
	 */
	private final Set<Partner> partner = new CopyOnWriteArraySet<Partner>();

	/**
	 * Creates a new address without partners.
	 *
	 * @param strasse
	 * @param hausnummer
	 * @param plz
	 * @param ort
	 * @param land
	 */
	public Adresse(final String strasse, final String hausnummer, final int plz, final String ort, final String land) {
		super(strasse, hausnummer, plz, ort, land);
	}

	/**
	 * Adds a partner to this address.
	 *
	 * @param partner
	 */
	public void addPartner(final Partner partner) {
		if (partner != null && !this.hasPartner(partner)) {
			this.partner.add(partner);
			partner.addAdresse(this);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.partner == null) ? 0 : this.partner.hashCode());
		return result;
	}

	/**
	 * Checks if the given partner is associated with this address.
	 *
	 * @param partner
	 * @return
	 */
	public boolean hasPartner(final Partner partner) {
		return this.partner.contains(partner);
	}

	@Override
	public Iterator<Partner> iterator() {
		return this.partner.iterator();
	}
}
