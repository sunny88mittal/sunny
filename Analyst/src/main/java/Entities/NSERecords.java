package Entities;

import java.util.List;

public class NSERecords {

	public List<String> expiryDates;

	public List<NSEOptionsDataRow> data;

	public String timestamp;

	public String underlyingValue;

	public List<String> strikePrices;
	
	public NSEIndex index;
}
