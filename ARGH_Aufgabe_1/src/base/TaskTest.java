package base;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite for partner-address relation tests.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class TaskTest {

	@Test
	public void test_task1() {
		// create valid Partner instance
		abb_8_1.Partner partner;
		try {
			partner = new abb_8_1.Partner("Straße", "Hausnummer", 1337, "Ort", "Land", "Vorname", "Nachname",
					LocalDate.of(2000, 12, 24));
		} catch (final OperationNotSupportedException e) {
			Assert.fail();
			return;
		}

		// check if the created Adresse maps to the correct Partner instance
		final abb_8_1.Adresse adresse = partner.getAdresse();
		Assert.assertTrue(adresse.getPartner().equals(partner));

		// create instances for compare check
		abb_8_1.Partner partner2;
		try {
			partner2 = new abb_8_1.Partner("", "", 0, "", "", "", "", LocalDate.of(2000, 1, 1));
		} catch (final OperationNotSupportedException e) {
			Assert.fail();
			return;
		}
		final abb_8_1.Adresse adresse2 = partner2.getAdresse();

		// check equals
		Assert.assertTrue(partner.equals(partner));
		Assert.assertFalse(partner.equals(partner2));
		Assert.assertTrue(adresse.equals(adresse));
		Assert.assertFalse(adresse.equals(adresse2));

		// try to create invalid Adresse (Partner already used for other
		// Adresse)
		try {
			new abb_8_1.Adresse(partner, "Straße", "Hausnummer", 1337, "Ort", "Land");
			Assert.fail();
		} catch (final OperationNotSupportedException e) {
			// nothing to do
		}

		// try to create invalid Adresse (without Partner)
		try {
			new abb_8_1.Adresse(null, "Straße", "Hausnummer", 1337, "Ort", "Land");
			Assert.fail();
		} catch (final OperationNotSupportedException e) {
			// nothing to do
		}
	}

	@Test
	public void test_task2() {
		// create valid Partner instance
		abb_8_2.Partner partner;
		try {
			partner = new abb_8_2.Partner("Vorname", "Nachname", LocalDate.of(2000, 12, 24));
			partner.addAdresse(new abb_8_2.Adresse(partner, "Straße", "Hausnummer", 1337, "Ort", "Land"));
		} catch (final OperationNotSupportedException e) {
			Assert.fail();
			return;
		}

		// create some Adresse instances and add them
		for (int i = 0; i < 5; i++) {
			try {
				partner.addAdresse(new abb_8_2.Adresse(partner, "Straße", "Hausnummer", i, "Ort", "Land"));
			} catch (final OperationNotSupportedException e) {
				Assert.fail();
			}
		}

		// check if all created Adress instances map to the correct Partner
		// instance
		for (final abb_8_2.Adresse adresse : partner) {
			Assert.assertTrue(adresse.getPartner().equals(partner));
		}

		abb_8_2.Adresse adresse = partner.iterator().next();

		// create instances for compare test
		abb_8_2.Partner partner2;
		try {
			partner2 = new abb_8_2.Partner("", "", LocalDate.of(2000, 1, 1));
			partner2.addAdresse(new abb_8_2.Adresse(partner2, "", "", 0, "", ""));
		} catch (final OperationNotSupportedException e) {
			Assert.fail();
			return;
		}
		final abb_8_2.Adresse adresse2 = partner2.iterator().next();

		// check equals
		Assert.assertTrue(partner.equals(partner));
		Assert.assertFalse(partner.equals(partner2));
		Assert.assertTrue(adresse.equals(adresse));
		Assert.assertFalse(adresse.equals(adresse2));

		// create new valid Adresse instance for existing Partner
		adresse = null;
		try {
			adresse = new abb_8_2.Adresse(partner, "Straße", "Hausnummer", 1337, "Ort", "Land");
		} catch (final OperationNotSupportedException e) {
			Assert.fail();
		}

		// check if the created Adresse instance is accessible via the Partner
		// instance
		boolean success = false;
		for (final abb_8_2.Adresse element : partner) {
			if (element.equals(adresse)) {
				success = true;
				break;
			}
		}
		Assert.assertTrue(success);

		// try to add Adresse instance that is already associated with a Partner
		// instance to a different Partner instance
		Assert.assertFalse(partner2.addAdresse(adresse));

		// check if the Adresse instance wasn't added
		success = true;
		for (final abb_8_2.Adresse element : partner2) {
			if (element.equals(adresse)) {
				success = false;
				break;
			}
		}
		Assert.assertTrue(success);

		// try to create invalid Adresse (without Partner)
		try {
			new abb_8_2.Adresse(null, "Straße", "Hausnummer", 1337, "Ort", "Land");
			Assert.fail();
		} catch (final OperationNotSupportedException e) {
			// nothing to do
		}
	}

	@Test
	public void test_task3() {
		// create valid Partner instance
		abb_8_3.Partner partner = new abb_8_3.Partner("Vorname", "Nachname", LocalDate.of(2000, 12, 24));
		partner.addAdresse(new abb_8_3.Adresse("Straße", "Hausnummer", 1337, "Ort", "Land"));

		// create some Adresse instances and add them
		for (int i = 0; i < 5; i++) {
			partner.addAdresse(new abb_8_3.Adresse("Straße", "Hausnummer", i, "Ort", "Land"));
		}

		// check if all created Adress instances map to the correct Partner
		// instance
		for (final abb_8_3.Adresse adresse : partner) {
			Assert.assertTrue(adresse.hasPartner(partner));
		}

		abb_8_3.Adresse adresse = partner.iterator().next();

		// create instances for compare test
		final abb_8_3.Partner partner2 = new abb_8_3.Partner("", "", LocalDate.of(2000, 1, 1));
		partner2.addAdresse(new abb_8_3.Adresse("", "", 0, "", ""));
		final abb_8_3.Adresse adresse2 = partner2.iterator().next();

		// check equals
		Assert.assertTrue(partner.equals(partner));
		Assert.assertFalse(partner.equals(partner2));
		Assert.assertTrue(adresse.equals(adresse));
		Assert.assertFalse(adresse.equals(adresse2));

		// create new valid Partner instance for existing Adresse
		partner = new abb_8_3.Partner("Vorname", "Nachname", LocalDate.of(2000, 12, 24));
		partner.addAdresse(adresse);

		// check if the created Partner is accessible via the Adresse
		Assert.assertTrue(adresse.hasPartner(partner));

		// create new valid Adresse instance for existing Partner
		adresse = new abb_8_3.Adresse("Straße", "Hausnummer", 1337, "Ort", "Land");
		adresse.addPartner(partner);

		// check if the created Adresse instance is accessible via the Partner
		// instance
		Assert.assertTrue(partner.hasAdresse(adresse));
	}
}
