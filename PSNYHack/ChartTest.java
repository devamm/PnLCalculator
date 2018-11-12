
package PSNYHack;

import java.util.ArrayList;
import java.util.List;

import PSNYHack.ChartUtility;

public class ChartTest {
	
	
	public static void main(String[] args) throws Exception {
		
		// Load sample data
		List<String> xAxisValues = getXAxisValues();
		List<Double> yAxisValues = getYAxisValues();
		
		Thread task = new Thread()
        {
            @Override
            public void run()
            {
            	try {
            		List<String> xAxisValues = getXAxisValues();
            		List<Double> yAxisValues = getYAxisValues();
            		
            		ChartUtility.setChartTitle("Profit (Loss)");
            		ChartUtility.setxAxisLabel("Time");
            		ChartUtility.setyAxisLabel("Profit (USD)");
            		ChartUtility.createChart(xAxisValues, yAxisValues);
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            	
            }
        };

        task.start();
        
        Thread.sleep(5000);
        ChartUtility.updateChart(xAxisValues, getYAxisValuesUpdated());
	}
	
	private static List<String> getXAxisValues() {
		List<String> xAxisValues = new ArrayList<String>();
		
		xAxisValues.add(new String("10/8/2018  7:00:00 AM"));
		xAxisValues.add(new String("10/8/2018  8:00:00 AM"));
		xAxisValues.add(new String("10/8/2018  9:00:00 AM"));
		xAxisValues.add(new String("10/8/2018  10:00:00 AM"));
		xAxisValues.add(new String("10/8/2018  11:00:00 AM"));
		xAxisValues.add(new String("10/8/2018  12:00:00 AM"));
		xAxisValues.add(new String("10/8/2018  1:00:00 PM"));
		xAxisValues.add(new String("10/8/2018  2:00:00 PM"));
		xAxisValues.add(new String("10/8/2018  3:00:00 PM"));
		xAxisValues.add(new String("10/8/2018  4:00:00 PM"));
		
		return xAxisValues;
	}
	
	private static List<Double> getYAxisValues() {
		List<Double> yAxisValues = new ArrayList<Double>();
		
		yAxisValues.add(1100.31);
		yAxisValues.add(1157.63);
		yAxisValues.add(1201.11);
		yAxisValues.add(1199.01);
		yAxisValues.add(1211.31);
		yAxisValues.add(-1299.12);
		yAxisValues.add(1302.13);
		yAxisValues.add(1275.14);
		yAxisValues.add(1011.31);
		yAxisValues.add(1055.71);
		
		
		return yAxisValues;
	}
	
	private static List<Double> getYAxisValuesUpdated() {
		List<Double> yAxisValues = new ArrayList<Double>();
		
		yAxisValues.add(800.31);
		yAxisValues.add(857.63);
		yAxisValues.add(901.11);
		yAxisValues.add(999.01);
		yAxisValues.add(911.31);
		yAxisValues.add(999.12);
		yAxisValues.add(802.13);
		yAxisValues.add(875.14);
		yAxisValues.add(811.31);
		yAxisValues.add(855.71);
		
		
		return yAxisValues;
	}
}

