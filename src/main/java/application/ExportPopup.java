package application;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;

import statistics.Statistics;

public class ExportPopup {
	static TextField fileLocation = new TextField("ExportedData");
	static Button browseButton = new Button("Browse");
	static Button exportButton = new Button("Export");

	public static void displayExportPopup() {
		Stage exportPopup = new Stage();
		exportPopup.setTitle("Export statistics or tree");
		GridPane exportPopupPane  = new GridPane();
	    ToggleGroup exportOptions = new ToggleGroup();
	    RadioButton currentTreeStatsExport = new RadioButton("Export statistics for current tree");
	    currentTreeStatsExport.setToggleGroup(exportOptions);
	    currentTreeStatsExport.setSelected(true);
	    RadioButton allTreeStatsExport = new RadioButton("Export statistics for all trees");
	    allTreeStatsExport.setToggleGroup(exportOptions);
	    RadioButton treeExport = new RadioButton("Export the current tree");
	    treeExport.setToggleGroup(exportOptions);
		Scene scene = new Scene(exportPopupPane,250,110);
		browseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select location or file to be overwritten");
				fileChooser.setInitialFileName("ExportedData");
				File newFile = fileChooser.showSaveDialog(exportPopup);
				fileLocation.setText(newFile.toString());
			}
        });	
		exportButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				try {
					String filename = fileLocation.getText();
					if (treeExport.isSelected()) {
						if (!filename.endsWith(".txt")) filename += ".txt";
						FileWriter writer = new FileWriter(filename);
						writer.append(Main.tree.toString());
						writer.flush();
						writer.close();
					}
					else if (!filename.endsWith(".csv")) filename += ".csv";
					if (currentTreeStatsExport.isSelected()) {Statistics.generateCSVCurrentTree(filename);}
					else if (allTreeStatsExport.isSelected()) {Statistics.generateCSVAllTrees(filename);}
				} catch (Exception e) {e.printStackTrace();}
				finally{exportPopup.close();}
			}
        });
		GridPane.setConstraints(currentTreeStatsExport, 0, 0, 2, 1);
		GridPane.setConstraints(allTreeStatsExport, 0, 1, 2, 1);
		GridPane.setConstraints(treeExport, 0, 2, 2, 1);
		GridPane.setConstraints(fileLocation, 0, 3);
		GridPane.setConstraints(browseButton, 1, 3);
		GridPane.setConstraints(exportButton, 0, 4);
		exportPopupPane.setPrefSize(300, 80);
		exportPopupPane.getChildren().addAll(currentTreeStatsExport, allTreeStatsExport, treeExport, fileLocation, browseButton, exportButton);
		exportPopup.setScene(scene);
		exportPopup.show();	
	}
	
}