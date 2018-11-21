package PSNYHack;

import java.util.HashMap;

public class Sector {
	HashMap<String, PortfolioVO> holdings; 
	String name;
	
	public Sector(String name){
		this.name = name;
		this.holdings = new HashMap<String, PortfolioVO>();
	}
	public void addHolding(PortfolioVO holding){
		this.holdings.put(holding.Ticker, holding);
	}
}
