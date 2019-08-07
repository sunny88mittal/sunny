package Entities;

import java.util.List;

public class ExcelSheet {

	public List<String> header;

	public List<List<String>> rows;

	public ExcelSheet(List<String> header, List<List<String>> rows) {
		super();
		this.header = header;
		this.rows = rows;
	}

}
