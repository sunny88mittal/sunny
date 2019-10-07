package Entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OptionsChainMini {

	public String symbol;

	public String price;
	
	public LocalTime time;

	public List<OptionsDataRowMini> callOptions = new ArrayList<OptionsDataRowMini>();

	public List<OptionsDataRowMini> putOptions = new ArrayList<OptionsDataRowMini>();
}
