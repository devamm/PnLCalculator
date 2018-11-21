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
	    	  totalValue = value;
	    	 
	      }
	     
	      if(fileName == "PSNYHack_Portfilio_PREV_EOD.csv"){
	    	  DecimalFormat decimalFormat = new DecimalFormat("#.00");
	    	  System.out.println("Current value of portfolio in USD: $"+decimalFormat.format(totalValue)+"\n");
	      } else {
	    	  System.out.println("NEW VAL "+totalValue);
	      }
	}
	
	

	public String getPnL(){
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return decimalFormat.format(eodValue-currentValue);
		
		
	}

	public static void main(String[] args) {
		//1. populate portfolio with EOD data
		//get current value of EOD
	    System.out.println("Listening for real-time updates"); 
	    Portfolio Global = new Portfolio("");
	    double startVal = Global.totalValue;
		

		boolean valid = true;
		do {
			
			WatchKey watchKey;
			String fileName = "";
			try {
				Path dropFolder = Paths.get("./");
				WatchService watchService = FileSystems.getDefault().newWatchService();
				dropFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
				watchKey = watchService.take();
				
				for (WatchEvent event : watchKey.pollEvents()) {
					WatchEvent.Kind kind = event.kind();
					if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
						fileName = event.context().toString();
						//System.out.println("File Created:" + fileName);
						Thread.sleep(4000);
						//new file detected, update portfolio values and calculate PnL
						
						
					}
				}
				valid = watchKey.reset();
				
			} catch (Exception e) {
				
				//e.printStackTrace();
			} finally {
				Portfolio newPort = new Portfolio(fileName);
				//System.out.println("VALUE OF NEW PORTFOLIO " + newPort.currentValue);
				System.out.println("Change in Portfolio value: $ "+ newPort.getPnL());
			}


		} while (valid);

	     
	}

}
