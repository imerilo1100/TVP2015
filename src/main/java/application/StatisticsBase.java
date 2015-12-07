package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import statistics.StatisticsInstance;

public class StatisticsBase {
    private ObservableList<StatisticsInstance> statsContent = FXCollections.observableArrayList();
    private TableView<StatisticsInstance> currentTreeStats = new TableView<StatisticsInstance>();
    private TableView<StatisticsInstance> generalTreeStats = new TableView<StatisticsInstance>();
    
	public ObservableList<StatisticsInstance> getStatsContent() {return statsContent;}
    
	StatisticsBase(Pane statsPane) {
		//Labels
		final Label currentTreeStatsLabel = new Label("Current tree statistics:");
		currentTreeStatsLabel.setFont(new Font("Arial", 20));
		final Label overallTreeStatsLabel = new Label("Statistics over all trees:");
		overallTreeStatsLabel.setFont(new Font("Arial", 20));
		//Columns
		TableColumn<StatisticsInstance, String> propertyCol = new TableColumn<StatisticsInstance, String>("");
		TableColumn<StatisticsInstance, Double> valueCol     = new TableColumn<StatisticsInstance, Double>("Property value");
		TableColumn<StatisticsInstance, Double> averageCol   = new TableColumn<StatisticsInstance, Double>("Average");
		TableColumn<StatisticsInstance, Double> deviationCol = new TableColumn<StatisticsInstance, Double>("Standard\ndeviation");
		//Add parameters to columns
		propertyCol.setPrefWidth(160);
		valueCol.setPrefWidth(160);
		averageCol.setPrefWidth(80);
		deviationCol.setPrefWidth(80);
		//Add cell value factories to columns
		propertyCol.setCellValueFactory(new PropertyValueFactory<StatisticsInstance, String>("propertyName"));
		valueCol.setCellValueFactory(new PropertyValueFactory<StatisticsInstance, Double>("currentValue"));
		averageCol.setCellValueFactory(new PropertyValueFactory<StatisticsInstance, Double>("averageValue"));
		deviationCol.setCellValueFactory(new PropertyValueFactory<StatisticsInstance, Double>("deviationValue"));
		//populate columns. 
		currentTreeStats.setItems(statsContent);
		generalTreeStats.setItems(statsContent);
		//Add columns to tables. 
		currentTreeStats.getColumns().add(propertyCol);
		currentTreeStats.getColumns().add(valueCol);
		generalTreeStats.getColumns().add(propertyCol); 
		generalTreeStats.getColumns().add(averageCol);
		generalTreeStats.getColumns().add(deviationCol);
		//Add mouseclick function to general tree statistics. 
		generalTreeStats.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				StatisticsInstance selectedItem = generalTreeStats.getSelectionModel().getSelectedItem(); 
				StatisticsInfoPopup.displayStatisticsInfoPopup(selectedItem);
			}
        });
		//Add to pane.
		statsPane.getChildren().addAll(currentTreeStatsLabel,
									   currentTreeStats, 
									   overallTreeStatsLabel,
									   generalTreeStats);
	}

}