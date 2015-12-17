package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import statistics.Statistics;
import tree.Tree;

public class LoadingPopup {
	static TextField fileLocation = new TextField("SpanningTreeSave.dat");
	static Button browseButton = new Button("Browse");
	static Button loadButton = new Button("Load");

	public static void displayLoadingPopup(Main main) {
		Stage loadingPopup = new Stage();
		loadingPopup.setTitle("Loading a tree");
		GridPane loadingPopupPane  = new GridPane();
		Scene scene = new Scene(loadingPopupPane,238,52);
		browseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select saved tree to be opened");
				File newFile = fileChooser.showOpenDialog(loadingPopup);
				fileLocation.setText(newFile.toString());
			}
        });	
		loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				ObjectInputStream in;
				try {
					in = new ObjectInputStream(new FileInputStream(new File(fileLocation.getText())));
					Main.tree = (Tree)(in.readObject());
					Main.stats = new Statistics();
					Main.stats.collectData(Main.tree, StatisticsBase.getStatsContent());
					DrawingUtils.drawEntireTree(Main.tree, main.nodeCanvas, main.edgeCanvas);
					in.close(); 
				} catch (Exception e) {
					//TODO: proper error message to user. 
					e.printStackTrace();
				} 
				loadingPopup.close();
			}
        });
		GridPane.setConstraints(fileLocation, 0, 0);
		GridPane.setConstraints(browseButton, 1, 0);
		GridPane.setConstraints(loadButton, 0, 1);
		loadingPopupPane.setPrefSize(300, 80);
		loadingPopupPane.getChildren().addAll(fileLocation, browseButton, loadButton);
		loadingPopup.setScene(scene);
		loadingPopup.show();
	}
}