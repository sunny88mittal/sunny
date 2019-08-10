package Trading;

import java.util.ArrayList;
import java.util.List;

import Entities.ExcelSheet;

public class TradeUtil {

	public static List<ExcelSheet> getExcelSheetContent(List<TradesCollector> statsCollectorList) {
		List<String> header = getHeader(statsCollectorList);
		List<List<String>> rows = new ArrayList<List<String>>();

		// Add data
		for (int i = 0; i < statsCollectorList.size(); i++) {
			List<String> row = new ArrayList<String>();
			TradesCollector statisticsCollector = statsCollectorList.get(i);
			row.addAll(statisticsCollector.statsMeta.values());
			row.addAll(statisticsCollector.statistics.getStatsAsMap().values());
			rows.add(row);
		}

		ExcelSheet sheet = new ExcelSheet(header, rows);
		List<ExcelSheet> sheets = new ArrayList<ExcelSheet>();
		sheets.add(sheet);
		return sheets;
	}

	private static List<String> getHeader(List<TradesCollector> statsCollectorList) {
		List<String> headerColumns = new ArrayList<String>();
		TradesCollector statisticsCollector = statsCollectorList.get(0);
		headerColumns.addAll(statisticsCollector.statsMeta.keySet());
		headerColumns.addAll(statisticsCollector.statistics.getStatsAsMap().keySet());
		return headerColumns;
	}
}
