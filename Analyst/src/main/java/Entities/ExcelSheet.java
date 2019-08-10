package Entities;

import java.util.List;

public class ExcelSheet {

	public String name;
	
	public List<String> header;

	public List<List<String>> rows;

	public ExcelSheet(String name, List<String> header, List<List<String>> rows) {
		this.name = name;
		this.header = header;
		this.rows = rows;
	}

}
