package PSNYHack;

import java.util.HashMap;

public class Region {
	HashMap <String, Sector> Sectors = null;
	String name;
	
	public Region(String name){
		this.name = name;
		Sectors = new HashMap<String, Sector>();
	}
}
