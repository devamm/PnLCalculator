
package PSNYHack;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChartUtility extends Application {
	private static String chartTitle;
	private static String xAxisLabel;
	private static String yAxisLabel;
	private static List<String> xAxisValues;
	private static List<Double> yAxisValues;

	public static void setChartTitle(String chartTitle) {
		ChartUtility.chartTitle = chartTitle;
	}

	public static void setxAxisLabel(String xAxisLabel) {
		ChartUtility.xAxisLabel = xAxisLabel;
	}

	public static void setyAxisLabel(String yAxisLabel) {
		ChartUtility.yAxisLabel = yAxisLabel;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			LineChart<String, Number> chart = getChartObject();

			Scene scene = new Scene(chart,800,600);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			// TEST
			Thread thread = new Thread(() -> {
	            try {
	            	while (true) {
	            		Thread.sleep(500);
		                Platform.runLater(() -> updateChartObject(chart));
	            	}
	            } catch (InterruptedException exc) {
	                throw new Error("Unexpected interruption", exc);
	            }        
	        });
			thread.setDaemon(true);
	        thread.start();
			// TEST
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateChartObject(LineChart chart) {
		
		XYChart.Series series = new XYChart.Series<>();
		for (int i=0; i < xAxisValues.size(); i++) {
			String xAxisValue = xAxisValues.get(i);
			Double yAxisValue = yAxisValues.get(i);
			series.getData().add(new XYChart.Data(xAxisValue, yAxisValue));
		}
		chart.getData().clear();
		chart.getData().add(series);
		
		for (Object entryObj : series.getData()) {      
			Data<String, Double> entry = (Data<String, Double>) entryObj;
            Tooltip t = new Tooltip(entry.getYValue().toString());
            Tooltip.install(entry.getNode(), t);
        }
	}
	
	private LineChart<String,Number> getChartObject() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		if (xAxisLabel != null) {			
			xAxis.setLabel(xAxisLabel);
		}
		if (yAxisLabel != null) {
			yAxis.setLabel(yAxisLabel);
		}
		LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
		lineChart.setAnimated(false);
		yAxis.setForceZeroInRange(false);
		lineChart.setLegendVisible(false);
		
		if (ChartUtility.chartTitle != null) {
			lineChart.setTitle(ChartUtility.chartTitle);
		}
		
		XYChart.Series series = new XYChart.Series<>();
		
		for (int i=0; i < xAxisValues.size(); i++) {
			String xAxisValue = xAxisValues.get(i);
			Double yAxisValue = yAxisValues.get(i);
			series.getData().add(new XYChart.Data(xAxisValue, yAxisValue));
		}
		
		lineChart.getData().add(series);
		
		for (Object entryObj : series.getData()) {      
			Data<String, Double> entry = (Data<String, Double>) entryObj;
            Tooltip t = new Tooltip(entry.getYValue().toString());
            Tooltip.install(entry.getNode(), t);
        }
		
		return lineChart;
	}
	
	public static void createChart(List<String> xAxisValues, List<Double> yAxisValues) throws Exception {
		
		if (areInputsValid(xAxisValues, yAxisValues)) {			
			ChartUtility.xAxisValues = xAxisValues;
			ChartUtility.yAxisValues = yAxisValues;
			
			launch(null);
		} else {
			throw new Exception("Inputs are invalid.");
		}
	}
	
	public static void updateChart(List<String> xAxisValues, List<Double> yAxisValues) throws Exception {
		if (areInputsValid(xAxisValues, yAxisValues)) {			
			ChartUtility.xAxisValues = xAxisValues;
			ChartUtility.yAxisValues = yAxisValues;
		} else {
			throw new Exception("Inputs are invalid.");
		}
	}
	 
	private static boolean areInputsValid(List<String> xAxisValues, List<Double> yAxisValues) {
		boolean areInputsValid = false;
		
		if (xAxisValues != null && yAxisValues != null) {
			if (xAxisValues.size() == yAxisValues.size()) {
				areInputsValid = true;
			}
		}
		
		return areInputsValid;
 	}
}
