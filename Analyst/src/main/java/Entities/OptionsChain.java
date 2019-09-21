package Entities;

import java.util.ArrayList;
import java.util.List;

public class OptionsChain {

	public String symbol;

	public String expiryDate;

	public long timeStamp;
	
	public double callOI;
	
	public double callOIVol;
	
	public double putOI;
	
	public double putOIVol;

	List<OptionsDataRow> callOptions = new ArrayList<OptionsDataRow>();

	List<OptionsDataRow> putOptions = new ArrayList<OptionsDataRow>();
}
