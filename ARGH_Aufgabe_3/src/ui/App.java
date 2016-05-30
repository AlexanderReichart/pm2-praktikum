package ui;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * App main class.
 * 
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class App extends Application {

	@Override
	public void start(final Stage stage) throws Exception {
		final URL uri = App.class.getClassLoader().getResource("ui/App.fxml");
		final Parent root = FXMLLoader.load(uri);
		final Scene scene = new Scene(root, 300, 275);
		stage.setTitle("ARGH Aufgabe 3");
		stage.setScene(scene);
		stage.show();
	}

}
