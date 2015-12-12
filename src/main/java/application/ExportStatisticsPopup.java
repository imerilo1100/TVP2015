package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import statistics.Statistics;

public class ExportStatisticsPopup {
	static Text helpText = new Text("Choose file location and name\nExtension will be added automatically");
	static TextField fileLocation = new TextField("Statistics");
	static Button browseButton = new Button("Browse");
	static Button currentTreeButton = new Button("Statistics for current tree");
	static Button allTreesButton = new Button("Statistics for all trees");

	public static void displayExportPopup() {
		Stage exportPopup = new Stage();
		exportPopup.setTitle("Export statistics");
		GridPane exportPopupPane  = new GridPane();
		Scene scene = new Scene(exportPopupPane,250,110);
		
		browseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select location or file to be overwritten");
				fileChooser.setInitialFileName("statistics.csv");
				File newFile = fileChooser.showSaveDialog(exportPopup);
				fileLocation.setText(newFile.toString());
			}
        });	
		currentTreeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				try {
					Statistics.generateCSVCurrentTree(fileLocation.getText()+".csv");
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
				exportPopup.close();
				}
			}
        });	
		allTreesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				try {
					Statistics.generateCSVAllTrees(fileLocation.getText()+".csv");
					
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
				exportPopup.close();
				}
			}
			
        });	
		GridPane.setConstraints(helpText, 0, 0);
		GridPane.setConstraints(fileLocation, 0, 1);
		GridPane.setConstraints(browseButton, 1, 1);
		GridPane.setConstraints(currentTreeButton, 0, 2);
		GridPane.setConstraints(allTreesButton, 0, 3);
		exportPopupPane.setPrefSize(300, 80);
		exportPopupPane.getChildren().addAll(helpText, fileLocation, browseButton, currentTreeButton, allTreesButton);
		exportPopup.setScene(scene);
		exportPopup.show();	
		
	}
}
