package PSNYHack;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class Portfolio {
	HashMap <String, Region> Regions = new HashMap<String, Region>();
	double totalValue = 0;
	
	public Portfolio(String fileName){
	
		//read EOD file
		PSNYHackCSVFileUtil procfile = new PSNYHackCSVFileUtil();
	      List<PortfolioVO> results = procfile.processFile(fileName);
	      for(int i = 0; i < results.size(); i++){
	    	  PortfolioVO singlePortfolio = results.get(i);
	    	  String[] locations = singlePortfolio.Sector.split("/");
	    	  String region = locations[2];
	    	  String sector = locations[3];
	    	  
	    	  //For each portfolio, check if region exists
	    	  Region tmpRegion = Regions.get(region);
	    	  if(tmpRegion == null){
	    		  //CREATE NEW REGION
	    		  	tmpRegion = new Region(region);
	    	  }
	    	  //check if region contains sector
	    	  Sector tmpSector = tmpRegion.Sectors.get(sector);
	    	  if(tmpSector == null){
	    		  tmpSector = new Sector(sector);
	    	  }
	    	  tmpSector.addHolding(singlePortfolio);
	    	  //get value 
	    	  double value = singlePortfolio.UnitsofHolding * singlePortfolio.Price;
	    	  value = value / FXConvert.convertFXtoUSD(singlePortfolio.Currency);
	    	  totalValue += value;
	    	 
	      }
	    
	}
	
	

	public static void main(String[] args) {
		//1. populate portfolio with EOD data
		//get current value of EOD
	    //System.out.println("Listening for real-time updates"); 
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		Portfolio Global = new Portfolio("PSNYHack_Portfolio_PREV_EOD.csv");
	    double startVal = Global.totalValue;
	    //System.out.print("Initial Value: "+startVal);
	    System.out.println("Initial value of portfolio in USD: $"+decimalFormat.format(Global.totalValue)+"\n");



	     
	}

}
