package PSNYHack;

import java.util.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PSNYHackCSVFileUtil {

public List<PortfolioVO> processFile(String filePath) {
		System.out.println("Opening file: "+filePath);
  	    //filePath = "PSNYHack_Portfolio_PREV_EOD.csv";
        String line = "";
        boolean skipHeader = true;
        String sepChar = ",";
        List<PortfolioVO> portfolioList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {

            if(!skipHeader) {
                
                String[] lineItem = line.split(sepChar);

                portfolioList.add(new PortfolioVO(
                                    Integer.parseInt(lineItem[0]),
                                    lineItem[1],
                                    lineItem[2],
                                    lineItem[3],
                                    lineItem[4],
                                    Integer.parseInt(lineItem[5]),
                                    lineItem[6],
                                    Double.parseDouble(lineItem[7])
                                      ));
            }

            skipHeader = false;

          }

          //System.out.println("Total Number of records processed successfully: " + portfolioList.size());


        } catch (IOException e) {
        	//System.out.println("hi");
        	e.printStackTrace();
        }

    return portfolioList;
}
          
} 