package statistics;

import java.util.LinkedList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class StatisticsInstance {
	private   LinkedList<Double> values = new LinkedList<Double>();
	private   double sumOfValues = 0.0; 
	protected final SimpleStringProperty propertyName = new SimpleStringProperty(); 
	private   final SimpleDoubleProperty currentValue = new SimpleDoubleProperty(0.0);
	private   final SimpleDoubleProperty averageValue = new SimpleDoubleProperty(0.0);
	private   final SimpleDoubleProperty deviationValue = new SimpleDoubleProperty(0.0);

	public LinkedList<Double> getValues() {return values;}
	public String getPropertyName()   {return propertyName.getValue();}
	public double getCurrentValue()   {return currentValue.getValue();}
	public double getAverageValue()   {return averageValue.getValue();} 
	public double getDeviationValue() {return deviationValue.getValue();}
	protected void setPropertyName(String name) {this.propertyName.setValue(name);}
	protected void addValue(Double value) {
		this.values.add(value);
		this.sumOfValues+=value; 
		this.currentValue.set(value);
		this.calculateAverageValue();
		this.calculateStandardDeviation();
	};
	
	private void calculateAverageValue(){averageValue.set(sumOfValues/(values.size()));}
	
	private void calculateStandardDeviation() {
		double deviationsSquared = 0.0;
		for(double value:values){deviationsSquared += Math.pow(value-averageValue.getValue(), 2);}
		deviationValue.set(Math.sqrt(deviationsSquared/values.size()));
	}
	
}