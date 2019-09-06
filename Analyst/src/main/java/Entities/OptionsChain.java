package Entities;

import java.util.ArrayList;
import java.util.List;

public class OptionsChain {

	public String symbol;

	public String expiryDate;

	public String timeStamp;
	
	public String callOI;
	
	public String putOI;

	List<OptionsDataRow> callOptions = new ArrayList<OptionsDataRow>();

	List<OptionsDataRow> putOptions = new ArrayList<OptionsDataRow>();
}
