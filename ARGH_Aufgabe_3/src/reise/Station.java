package reise;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasse f�r eine Station einer Reise.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Station {
	/**
	 * Ortsname der Station.
	 */
	private final String ort;
	/**
	 * Anreisezeitpunkt (ZonedDateTime um Reisen �ber mehrere Zeitzonen einfach
	 * zu erm�glichen).
	 */
	private final ZonedDateTime anreise;
	/**
	 * Abreisezeitpunkt (ZonedDateTime um Reisen �ber mehrere Zeitzonen einfach
	 * zu erm�glichen).
	 */
	private final ZonedDateTime abreise;

	/**
	 * Erstellt eine Neue Station aus den angegebenen Werten. F�r Anreise oder
	 * Abreise darf f�r den Startpunkt oder das Ziel der Reise <code>null</code>
	 * gesetzt werden.
	 *
	 * @param ort
	 *            Ortsname der Station.
	 * @param anreise
	 *            Zeitpunkt der Anreise.
	 * @param abreise
	 *            Zeitpunkt der Abreise.
	 */
	public Station(final String ort, final ZonedDateTime anreise, final ZonedDateTime abreise) {
		this.ort = ort;
		this.anreise = anreise;
		this.abreise = abreise;
	}

	/**
	 * Gibt den Zeitpunkt der Abreise zur�ck.
	 *
	 * @return Zeitpunkt der Abreise.
	 */
	public ZonedDateTime getAbreise() {
		return this.abreise;
	}

	/**
	 * Gibt den Zeitpunkt der Anreise zur�ck.
	 *
	 * @return Zeitpunkt der Anreise.
	 */
	public ZonedDateTime getAnreise() {
		return this.anreise;
	}

	/**
	 * Gibt den Ortsnamen zur�ck.
	 *
	 * @return Ortsnamen.
	 */
	public String getOrt() {
		return this.ort;
	}

	/**
	 * Gibt eine kurze Übersicht �ber diese Station zur�ck.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		this.addUbersichtText(builder);
		return builder.toString();
	}

	/**
	 * F�gt den Übersichtstext f�r diese Reise zu dem �bergebenen StringBuilder
	 * hinzu.
	 *
	 * @param builder
	 *            StringBuilder in den der Text eingef�gt werden soll.
	 */
	void addUbersichtText(final StringBuilder builder) {
		builder.append(this.ort);
		builder.append(System.lineSeparator());
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss (O)");
		if (this.anreise != null) {
			builder.append("    Ankunft: ");
			builder.append(this.anreise.format(formatter));
			builder.append(System.lineSeparator());
		}
		if (this.abreise != null) {
			builder.append("    Abreise: ");
			builder.append(this.abreise.format(formatter));
			builder.append(System.lineSeparator());
		}
	}
}
