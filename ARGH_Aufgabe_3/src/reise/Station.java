package reise;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasse für eine Station einer Reise.
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
	 * Anreisezeitpunkt (OffsetDateTime um Reisen über mehrere Zeitzonen einfach
	 * zu ermöglichen).
	 */
	private final OffsetDateTime anreise;
	/**
	 * Abreisezeitpunkt (OffsetDateTime um Reisen über mehrere Zeitzonen einfach
	 * zu ermöglichen).
	 */
	private final OffsetDateTime abreise;

	/**
	 * Erstellt eine Neue Station aus den angegebenen Werten. Für Anreise oder
	 * Abreise darf für den Startpunkt oder das Ziel der Reise <code>null</code>
	 * gesetzt werden.
	 *
	 * @param ort
	 *            Ortsname der Station.
	 * @param anreise
	 *            Zeitpunkt der Anreise.
	 * @param abreise
	 *            Zeitpunkt der Abreise.
	 */
	public Station(final String ort, final OffsetDateTime anreise, final OffsetDateTime abreise) {
		this.ort = ort;
		this.anreise = anreise;
		this.abreise = abreise;
	}

	/**
	 * Gibt den Zeitpunkt der Abreise zurück.
	 *
	 * @return Zeitpunkt der Abreise.
	 */
	public OffsetDateTime getAbreise() {
		return this.abreise;
	}

	/**
	 * Gibt den Zeitpunkt der Anreise zurück.
	 *
	 * @return Zeitpunkt der Anreise.
	 */
	public OffsetDateTime getAnreise() {
		return this.anreise;
	}

	/**
	 * Gibt den Ortsnamen zurück.
	 *
	 * @return Ortsnamen.
	 */
	public String getOrt() {
		return this.ort;
	}

	/**
	 * Gibt eine kurze Ãœbersicht über diese Station zurück.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		this.addUbersichtText(builder);
		return builder.toString();
	}

	/**
	 * Fügt den Ãœbersichtstext für diese Reise zu dem übergebenen StringBuilder
	 * hinzu.
	 *
	 * @param builder
	 *            StringBuilder in den der Text eingefügt werden soll.
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
