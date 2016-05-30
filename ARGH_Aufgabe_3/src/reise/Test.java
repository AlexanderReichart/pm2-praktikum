package reise;

import java.time.ZonedDateTime;

import org.junit.Assert;

/**
 * Test cases f�r die Reise.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */

public class Test {

	final Station station1 = new Station("station1", null, ZonedDateTime.parse("2012-03-29T13:00:00+01:00"));
	final Station station2 = new Station("station2", ZonedDateTime.parse("2012-03-29T14:00:00+01:00"),
			ZonedDateTime.parse("2012-03-29T15:00:00+01:00"));
	final Station station3 = new Station("station3", ZonedDateTime.parse("2012-03-29T18:00:00+01:00"),
			ZonedDateTime.parse("2012-03-30T04:00:00+01:00"));
	final Station station4 = new Station("station4", ZonedDateTime.parse("2012-03-30T10:00:00+01:00"), null);
	final Reise reise1 = new Reise(this.station1);

	@org.junit.Test
	public void testReise() {
		// testing the adding after index
		this.reise1.fuegeHinzuNach(0, this.station3);
		Assert.assertTrue(this.reise1.getStation(1).equals(this.station3));
		// testing the adding before index
		this.reise1.fuegeHinzuVor(1, this.station2);
		Assert.assertTrue(this.reise1.getStation(1).equals(this.station2));
		// testing the adding to end
		Assert.assertTrue(this.reise1.getZiel().equals(this.station3));
		this.reise1.fuegeAnsEndeHinzu(this.station4);
		// testing end and start point getter
		Assert.assertTrue(this.reise1.getZiel().equals(this.station4));
		Assert.assertTrue(this.reise1.getStartpunkt().equals(this.station1));

		// testing amount of stations
		Assert.assertTrue(this.reise1.getAnzahlStationen() == 4);

		// testing start and end time point
		Assert.assertTrue(this.reise1.getStartzeitpunkt().equals(ZonedDateTime.parse("2012-03-29T13:00:00+01:00")));
		Assert.assertTrue(this.reise1.getEndzeitpunkt().equals(ZonedDateTime.parse("2012-03-30T10:00:00+01:00")));
		// testing duration
		Assert.assertTrue(this.reise1.getDauerInStunden() == 21);

		// testing retrieving at certain index, and order of stations at the
		// end.
		Assert.assertTrue(this.reise1.getStation(0).equals(this.station1));
		Assert.assertTrue(this.reise1.getStation(1).equals(this.station2));
		Assert.assertTrue(this.reise1.getStation(2).equals(this.station3));
		Assert.assertTrue(this.reise1.getStation(3).equals(this.station4));

		// testing replacing of station
		this.reise1.ersetzeStation(1, this.station4);
		Assert.assertTrue(this.reise1.getStation(1).equals(this.station4));
	}

	@org.junit.Test
	public void testStationAttributes() {
		// testing that the location names are right
		Assert.assertTrue(this.station1.getOrt().equals("station1"));
		Assert.assertTrue(this.station2.getOrt().equals("station2"));
		Assert.assertFalse(this.station3.getOrt().equals("station5"));
		Assert.assertFalse(this.station4.getOrt().equals("station6"));

		// testing that the departure times are right
		Assert.assertTrue(this.station1.getAbreise().equals(ZonedDateTime.parse("2012-03-29T13:00:00+01:00")));
		Assert.assertTrue(this.station2.getAbreise().equals(ZonedDateTime.parse("2012-03-29T15:00:00+01:00")));
		Assert.assertFalse(this.station3.getAbreise().equals(ZonedDateTime.parse("2012-03-29T15:00:00+01:00")));

		// testing that the arrival times are right
		Assert.assertTrue(this.station2.getAnreise().equals(ZonedDateTime.parse("2012-03-29T14:00:00+01:00")));
		Assert.assertFalse(this.station3.getAnreise().equals(ZonedDateTime.parse("2013-03-29T18:00:00+01:00")));
		Assert.assertFalse(this.station4.getAnreise().equals(ZonedDateTime.parse("2013-03-30T10:00:00+01:00")));

	}
}
