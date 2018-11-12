package PSNYHack;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone; 

public class PortfolioVO {

  public int RowNum;
  public Date DateTimeEST;
  public String Sector;
  public String Ticker;
  public String CompanyName;
  public int UnitsofHolding;
  public String Currency;
  public double Price;

  private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");

  public PortfolioVO(int rownum, String datetimeest, String sector, 
                          String ticker, String companyname, int unitsofholding,
                                  String currency, double price) {
        try {

            TimeZone.setDefault(TimeZone.getTimeZone("EST"));
            formatter.setTimeZone(TimeZone.getTimeZone("EST"));

            this.RowNum = rownum;
            this.DateTimeEST = formatter.parse(datetimeest);
            this.Sector = sector;
            this.Ticker = ticker;
            this.CompanyName = companyname;
            this.UnitsofHolding = unitsofholding;
            this.Currency = currency;
            this.Price = price;

            } catch (ParseException e) {
            e.printStackTrace();
        }

  }

}