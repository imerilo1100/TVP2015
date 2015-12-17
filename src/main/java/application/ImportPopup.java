package application;

import java.io.File;
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
import tree.TreeParser;

public class ImportPopup {
	static TextField fileLocation = new TextField("SpanningTree.txt");
	static Button browseButton = new Button("Browse");
	static Button importButton = new Button("Import");
	
	public static void displayImportPopup(Main main) {
		Stage importPopup = new Stage();
		importPopup.setTitle("Import a tree from text file");
		GridPane importPopupPane  = new GridPane();
		Scene scene = new Scene(importPopupPane,238,52);
		browseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select a text file to be imported");
				File newFile = fileChooser.showOpenDialog(importPopup);
				fileLocation.setText(newFile.toString());
			}
        });	
		importButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				Tree importedTree = TreeParser.parseTree(fileLocation.getText());
				if (importedTree!=null) {
					Main.tree = importedTree;
					Main.stats = new Statistics();
					Main.stats.collectData(Main.tree, StatisticsBase.getStatsContent());
					DrawingUtils.drawEntireTree(Main.tree, main.nodeCanvas, main.edgeCanvas);
				}
				else {
					//TODO: tell the user that the file was not found or faulty. 
				}
				importPopup.close();
			}
        });
		GridPane.setConstraints(fileLocation, 0, 0);
		GridPane.setConstraints(browseButton, 1, 0);
		GridPane.setConstraints(importButton, 0, 1);
		importPopupPane.setPrefSize(300, 80);
		importPopupPane.getChildren().addAll(fileLocation, browseButton, importButton);
		importPopup.setScene(scene);
		importPopup.show();
	}

}