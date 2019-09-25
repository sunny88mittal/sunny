package Entities;

import java.util.ArrayList;
import java.util.List;

public class OptionsChain {

	public String symbol;
	
	public String price;

	public String expiryDate;

	public long timeStamp;
	
	public double callOI;
	
	public double callOIVol;
	
	public double putOI;
	
	public double putOIVol;

	public List<OptionsDataRow> callOptions = new ArrayList<OptionsDataRow>();

	public List<OptionsDataRow> putOptions = new ArrayList<OptionsDataRow>();
}
