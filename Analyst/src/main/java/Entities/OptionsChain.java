package Entities;

import java.util.ArrayList;
import java.util.List;

public class OptionsChain {

	public String symbol;

	public String expiryDate;

	public long timeStamp;
	
	public float callOI;
	
	public float callOIVol;
	
	public float putOI;
	
	public float putOIVol;

	List<OptionsDataRow> callOptions = new ArrayList<OptionsDataRow>();

	List<OptionsDataRow> putOptions = new ArrayList<OptionsDataRow>();
}
