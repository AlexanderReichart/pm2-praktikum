package reise;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasse für eine Reise.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Reise {

	/**
	 * Zweifach verkettete Liste für die einzelnen Stationen der Reise. Diese
	 * Struktur ist notwendig um einfach Elemente in der Mitte einfügen zu
	 * können.
	 */
	private final LinkedList<Station> stationen = new LinkedList<>();

	/**
	 * Erstellt eine neue Reise von dem übergebenen Startpunkt aus.
	 *
	 * @param startpunkt
	 *            Startpunkt der Reise.
	 */
	public Reise(final Station startpunkt) {
		this.fuegeAnsEndeHinzu(startpunkt);
	}

	/**
	 * Entfernt die Station mit dem angegebenen Index aus der Reise.
	 *
	 * @param index
	 *            Index der Station, die entfernt werden soll.
	 * @return Die entfernte Station.
	 */
	public Station entferneStation(final int index) {
		return this.stationen.remove(index);
	}

	/**
	 * Ersetzt die Station an der übergebenen Stelle.
	 *
	 * @param index
	 *            Stelle der Station die ersetzt werden soll.
	 * @param element
	 *            Neue Station.
	 * @return Die alte Station an der übergebenen Stelle.
	 */
	public Station ersetzeStation(final int index, final Station element) {
		return this.stationen.set(index, element);
	}

	/**
	 * Fügt einen neue Station ans Ende der Reise hinzu.
	 *
	 * @param station
	 *            Neue Station.
	 */
	public void fuegeAnsEndeHinzu(final Station station) {
		this.stationen.addLast(station);
	}

	/**
	 * Fügt eine neue Station nach der Station mit dem übergebenen Index hinzu.
	 *
	 * @param index
	 *            Stelle nach der die neue Station eingefügt werden soll.
	 * @param element
	 *            Neue Station.
	 */
	public void fuegeHinzuNach(final int index, final Station element) {
		// letztes Element in der Liste?
		if (index + 1 == this.stationen.size()) {
			this.fuegeAnsEndeHinzu(element);
		} else {
			this.fuegeHinzuVor(index + 1, element);
		}
	}

	/**
	 * Fügt eine neue Station vor der Station mit dem übergebenen Index hinzu.
	 *
	 * @param index
	 *            Stelle vor der die neue Station eingefügt werden soll.
	 * @param element
	 *            Neue Station.
	 */
	public void fuegeHinzuVor(final int index, final Station element) {
		this.stationen.add(index, element);
	}

	/**
	 * Gibt die gesammte Anzahl der Stationen dieser Reise (mit Startpunkt und
	 * Ziel) zurück.
	 *
	 * @return Anzahl Stationen dieser Reise.
	 */
	public int getAnzahlStationen() {
		return this.stationen.size();
	}

	public long getDauerInStunden() {
		return ChronoUnit.HOURS.between(this.getStartzeitpunkt(), this.getEndzeitpunkt());
	}

	/**
	 * Gibt die Dauer der Reise in Tagen (Abreise Zeitpunkt am Startpunkt bis
	 * Anreisezeitpunkt am Ziel) zurück.
	 *
	 * @return Dauer der Reise in Tagen.
	 */
	public long getDauerInTagen() {
		return ChronoUnit.DAYS.between(this.getStartzeitpunkt(), this.getEndzeitpunkt());
	}

	/**
	 * Gibt den Zeitpunkt zurück an dem die Reise vorbei ist (Anreisezeitpunkt
	 * am Ziel).
	 *
	 * @return Endzeitpunkt der Reise.
	 */
	public ZonedDateTime getEndzeitpunkt() {
		// final Station endpunkt = this.getStartpunkt();
		final Station endpunkt = this.stationen.getLast();
		if (endpunkt != null) {
			return endpunkt.getAnreise();
		}
		return null;
	}

	/**
	 * Gibt den Startpunkt der Reise zurück.
	 *
	 * @return Startpunkt der Reise.
	 */
	public Station getStartpunkt() {
		return this.stationen.peekFirst();
	}

	/**
	 * Gibt den Zeitpunkt zurück an dem die Reise beginnt (Abreisezeitpunkt am
	 * Startpunkt).
	 *
	 * @return Startzeitpunkt der Reise.
	 */
	public ZonedDateTime getStartzeitpunkt() {
		final Station startpunkt = this.getStartpunkt();
		if (startpunkt != null) {
			return startpunkt.getAbreise();
		}
		return null;
	}

	/**
	 * Gibt die Station an dem übergebenen Index zurück.
	 *
	 * @param index
	 *            Index der Station.
	 * @return Station an dem Übergebenen Index.
	 */
	public Station getStation(final int index) {
		return this.stationen.get(index);
	}

	/**
	 * Gibt eine Liste mit allen Stationen zurück. Die zurückgegebene Liste kann
	 * nicht modifiziert werden.
	 *
	 * @return Liste mit allen Stationen.
	 */
	public List<Station> getStationen() {
		return Collections.unmodifiableList(this.stationen);
	}

	/**
	 * Gibt das Ziel der Reise zurück.
	 *
	 * @return Ziel der Reise.
	 */
	public Station getZiel() {
		return this.stationen.peekLast();
	}

	/**
	 * Gibt einen Übersichtsplan der Reise zurück.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		final String startOrt = this.getStartpunkt().getOrt();
		final String zielOrt = this.getZiel().getOrt();

		// Überschrift
		builder.append("Von ");
		builder.append(startOrt);
		builder.append(" nach ");
		builder.append(zielOrt);
		builder.append(System.lineSeparator());
		// Die beiden festen Texte haben zusammen die Länge 10
		final int laenge = 10 + startOrt.length() + zielOrt.length();
		for (int i = 0; i < laenge; i++) {
			builder.append('=');
		}
		builder.append(System.lineSeparator());

		// Informationen über die Dauer
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss (O)");
		builder.append("    Abreise: ");
		builder.append(this.getStartzeitpunkt().format(formatter));
		builder.append(System.lineSeparator());
		builder.append("    Ankunft: ");
		builder.append(this.getEndzeitpunkt().format(formatter));
		builder.append(System.lineSeparator());

		// Einzelne Stationen hinzufügen
		for (final Station station : this.stationen) {
			station.addUbersichtText(builder);
		}
		return builder.toString();
	}
}
