package application;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GenerateTreePopup {
	
	public static void displayGenerateTreePopup(Main main) {
		Stage generateTreePopup = new Stage();
		generateTreePopup.setTitle("Options for generating a tree");
		GridPane generateTreePopupPane  = new GridPane();
		Scene scene = new Scene(generateTreePopupPane,238,52);
		generateTreePopupPane.setPrefSize(300, 80);
		generateTreePopupPane.getChildren().addAll();
		generateTreePopup.setScene(scene);
		generateTreePopup.show();
	}

}
