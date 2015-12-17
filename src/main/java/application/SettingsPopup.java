package application;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsPopup {

	public static void displaySettingsPopup(Main main) {
		Stage settingsPopup = new Stage();
		settingsPopup.setTitle("Settings");
		GridPane settingsPopupPane  = new GridPane();
		Scene scene = new Scene(settingsPopupPane,238,52);
		settingsPopupPane.setPrefSize(300, 80);
		settingsPopupPane.getChildren().addAll();
		settingsPopup.setScene(scene);
		settingsPopup.show();
	}
	
}