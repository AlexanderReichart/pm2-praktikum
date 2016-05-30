package ui;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import reise.Reise;
import reise.Station;

/**
 * Controller for App.fxml
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class AppController implements Initializable {

	/**
	 * Extends the Reise class to notify an AppController if the Reise itself is
	 * modified.
	 *
	 * Group: ARGH
	 *
	 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
	 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
	 */
	private class ObervableReise extends Reise {

		/**
		 * Creates a new Instance from the given starting point for the given
		 * controller.
		 *
		 * @param startpunkt
		 *            Starting point for the Reise.
		 */
		public ObervableReise(final Station startpunkt) {
			super(startpunkt);
		}

		@Override
		public Station entferneStation(final int index) {
			final Station res = super.entferneStation(index);
			AppController.this.list.getItems().remove(index);
			return res;
		}

		@Override
		public Station ersetzeStation(final int index, final Station element) {
			final Station res = super.ersetzeStation(index, element);
			AppController.this.list.getItems().set(index, element);
			return res;
		}

		@Override
		public void fuegeAnsEndeHinzu(final Station station) {
			super.fuegeAnsEndeHinzu(station);
			AppController.this.list.getItems().add(station);
		}

		@Override
		public void fuegeHinzuVor(final int index, final Station element) {
			super.fuegeHinzuVor(index, element);
			AppController.this.list.getItems().add(index, element);
		}
	}

	/**
	 * Input field for arrival date.
	 */
	@FXML
	private DatePicker arrivalDate;
	/**
	 * Input field for departure date.
	 */
	@FXML
	private DatePicker depatureDate;
	/**
	 * Input field for city name.
	 */
	@FXML
	private TextField city;
	/**
	 * Input field for arrival time.
	 */
	@FXML
	private TextField arrivalTime;
	/**
	 * Input field for depature time.
	 */
	@FXML
	private TextField depatureTime;
	/**
	 * Reise list view.
	 */
	@FXML
	private ListView<Station> list;
	/**
	 * Selected time zone.
	 */
	@FXML
	private ChoiceBox<ZoneId> timeZone;
	/**
	 * Reise instance.
	 */
	private Reise reise = null;

	/**
	 * Removes the selected element.
	 */
	public void deleteElement() {
		final Integer selectedIndex = this.list.getSelectionModel().getSelectedIndex();
		this.reise.entferneStation(selectedIndex);
	}

	/**
	 * Loads timezone list and registers select handler for list.
	 */
	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		final List<ZoneId> zoneIds = new LinkedList<>();
		for (final String id : ZoneId.getAvailableZoneIds()) {
			zoneIds.add(ZoneId.of(id));
		}
		final List<ZoneId> items = this.timeZone.getItems();
		items.addAll(zoneIds);
		// select current time zone as default
		this.timeZone.setValue(ZoneId.systemDefault());
		// when selecting an item, update form
		this.list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// new item selected: update form
			final ZonedDateTime arrival = newValue.getAnreise();
			final ZonedDateTime depature = newValue.getAbreise();
			// update fields
			this.arrivalDate.setValue(arrival.toLocalDate());
			this.arrivalTime.setText(arrival.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));

			this.depatureDate.setValue(depature.toLocalDate());
			this.depatureTime.setText(depature.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));

			this.timeZone.setValue(arrival.getZone());

			this.city.setText(newValue.getOrt());
		});
	}

	/**
	 * Inserts a new element from input data.
	 */
	public void insertElement() {
		this.save((index, element) -> {
			this.reise.fuegeHinzuVor(index, element);
		});
	}

	/**
	 * Saves the input data into the slot of the currently selected element.
	 */
	public void saveElement() {
		this.save((index, element) -> {
			if (index < 0) {
				this.reise.fuegeAnsEndeHinzu(element);
			} else {
				this.reise.ersetzeStation(index, element);
			}
		});
	}

	/**
	 * Internal save method.
	 *
	 * @param saveAction
	 *            Insert/Update action to be performed.
	 */
	private void save(final BiConsumer<Integer, Station> saveAction) {
		final ZoneId timeZoneId = this.timeZone.getValue();
		final ZonedDateTime arrival = ZonedDateTime.of(this.arrivalDate.getValue(),
				LocalTime.parse(this.arrivalTime.getText()), timeZoneId);
		final ZonedDateTime departure = ZonedDateTime.of(this.depatureDate.getValue(),
				LocalTime.parse(this.depatureTime.getText()), timeZoneId);
		final Station element = new Station(this.city.getText(), arrival, departure);
		if (this.reise == null) {
			// create Reise if needed
			this.reise = new ObervableReise(element);
		} else {
			// perform given save action otherwise
			final Integer selectedIndex = this.list.getSelectionModel().getSelectedIndex();
			saveAction.accept(selectedIndex, element);
		}
	}
}
