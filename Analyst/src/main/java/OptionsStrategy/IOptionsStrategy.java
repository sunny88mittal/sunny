package OptionsStrategy;

import java.util.List;

public interface IOptionsStrategy {

	public List<Trade> execute (String date);
	
	public String getName();
}
