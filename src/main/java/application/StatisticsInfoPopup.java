package application;

import java.util.LinkedList;

import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import statistics.StatisticsInstance;

public class StatisticsInfoPopup{
	@SuppressWarnings("unchecked")
	public static void displayStatisticsInfoPopup(StatisticsInstance statsInstance) {
		Stage  statsPopup = new Stage();
		String propertyName = statsInstance.getPropertyName().substring(0,(statsInstance.getPropertyName().length()-1));
		LinkedList<Double> propertyValues = statsInstance.getValues();
		statsPopup.setTitle("Info on " + propertyName);
		Pane statsPopupPane  = new Pane();
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        AreaChart<Number,Number> chart = new AreaChart<Number,Number>(x,y);
        XYChart.Series<Number, Number> statsValueSeries = new XYChart.Series<Number, Number>();
        statsValueSeries.setName(propertyName);
        for (int i = 0; i<propertyValues.size(); i++) {statsValueSeries.getData().add(new XYChart.Data<Number, Number>(i, propertyValues.get(i)));}
        chart.getData().addAll(statsValueSeries); //TODO: Do something about the supress unchecked warnings. 
		Scene scene = new Scene(statsPopupPane,520,420);
		statsPopupPane.getChildren().addAll(chart);
		statsPopup.setScene(scene);
		statsPopup.show();
	}
}
