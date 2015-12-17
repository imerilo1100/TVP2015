package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import tree.Tree;

public class SavingPopup {
	static TextField fileLocation = new TextField("SpanningTreeSave");
	static Button browseButton = new Button("Browse");
	static Button saveButton = new Button("Save");
	
	public static void displaySavingPopup(Tree tree) {
		Stage savingPopup = new Stage();
		savingPopup.setTitle("Saving a tree");
		GridPane savingPopupPane  = new GridPane();
		Scene scene = new Scene(savingPopupPane,238,52);
		browseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select location or file to be overwritten");
				fileChooser.setInitialFileName("SpanningTreeSave.dat");
				File newFile = fileChooser.showSaveDialog(savingPopup);
				fileLocation.setText(newFile.toString());
			}
        });	
		saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				ObjectOutputStream out;
				try {
					String filename = fileLocation.getText();
					if (!filename.endsWith(".dat")) filename += ".dat";
					out = new ObjectOutputStream(new FileOutputStream(new File(filename)));
					out.writeObject(tree);
					out.close(); 
				} catch (Exception e) {
					//TODO: proper error message to user. 
					e.printStackTrace();
				} 
				savingPopup.close();
			}
        });	
		GridPane.setConstraints(fileLocation, 0, 0);
		GridPane.setConstraints(browseButton, 1, 0);
		GridPane.setConstraints(saveButton, 0, 1);
		savingPopupPane.setPrefSize(300, 80);
		savingPopupPane.getChildren().addAll(fileLocation, browseButton, saveButton);
		savingPopup.setScene(scene);
		savingPopup.show();
	}
}