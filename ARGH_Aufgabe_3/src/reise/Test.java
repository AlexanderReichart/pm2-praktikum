package reise;

import static org.junit.Assert.*;

import java.time.OffsetDateTime;

/**
 * Test cases für die Reise.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */

public class Test {

	final Station station1 = new Station("station1", null, OffsetDateTime.parse("2012-03-29T13:00:00+01:00")); 
	final Station station2 = new Station("station2", OffsetDateTime.parse("2012-03-29T14:00:00+01:00"), OffsetDateTime.parse("2012-03-29T15:00:00+01:00"));    	
	final Station station3 = new Station("station3", OffsetDateTime.parse("2012-03-29T18:00:00+01:00"), OffsetDateTime.parse("2012-03-30T04:00:00+01:00"));    	
	final Station station4 = new Station("station4", OffsetDateTime.parse("2012-03-30T10:00:00+01:00"), null);    	
	final Reise reise1 = new Reise(station1);

	@org.junit.Test
	public void testStationAttributes() {
		//testing that the location names are right
		assertTrue(station1.getOrt().equals("station1"));
		assertTrue(station2.getOrt().equals("station2"));
		assertFalse(station3.getOrt().equals("station5"));
		assertFalse(station4.getOrt().equals("station6"));
		

		//testing that the departure times are right
		assertTrue(station1.getAbreise().equals(OffsetDateTime.parse("2012-03-29T13:00:00+01:00")));
		assertTrue(station2.getAbreise().equals(OffsetDateTime.parse("2012-03-29T15:00:00+01:00")));
		assertFalse(station3.getAbreise().equals(OffsetDateTime.parse("2012-03-29T15:00:00+01:00")));

		//testing that the arrival times are right
		assertTrue(station2.getAnreise().equals(OffsetDateTime.parse("2012-03-29T14:00:00+01:00")));
		assertFalse(station3.getAnreise().equals(OffsetDateTime.parse("2013-03-29T18:00:00+01:00")));
		assertFalse(station4.getAnreise().equals(OffsetDateTime.parse("2013-03-30T10:00:00+01:00")));

	}

	@org.junit.Test
	public void testReise() {
		//testing the adding after index
		reise1.fuegeHinzuNach(0,station3);
		assertTrue(reise1.getStation(1).equals(station3));		
		//testing the adding before index
		reise1.fuegeHinzuVor(1, station2);
		assertTrue(reise1.getStation(1).equals(station2));		
		//testing the adding to end
		assertTrue(reise1.getZiel().equals(station3));
		reise1.fuegeAnsEndeHinzu(station4);
		//testing end and start point getter
		assertTrue(reise1.getZiel().equals(station4));
		assertTrue(reise1.getStartpunkt().equals(station1));
		
		//testing amount of stations
		assertTrue(reise1.getAnzahlStationen()==4);

		//testing start and end time point
		assertTrue(reise1.getStartzeitpunkt().equals(OffsetDateTime.parse("2012-03-29T13:00:00+01:00")));				
		assertTrue(reise1.getEndzeitpunkt().equals(OffsetDateTime.parse("2012-03-30T10:00:00+01:00")));		
		//testing duration
		assertTrue(reise1.getDauerInStunden()==21);

		
		//testing retrieving at certain index, and order of stations at the end.
		assertTrue(reise1.getStation(0).equals(station1));
		assertTrue(reise1.getStation(1).equals(station2));
		assertTrue(reise1.getStation(2).equals(station3));
		assertTrue(reise1.getStation(3).equals(station4));
		
		//testing replacing of station
		reise1.ersetzeStation(1, station4);
		assertTrue(reise1.getStation(1).equals(station4));	
	}
}
