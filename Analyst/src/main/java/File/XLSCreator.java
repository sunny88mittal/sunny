package File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Constants.FileConstants;
import Statistics.StatisticsCollector;

public class XLSCreator {

	public static void generateXLS(List<StatisticsCollector> statsCollectorList, String fileName) throws IOException {
		if (fileName == null) {
			fileName = System.currentTimeMillis() + "";
		}

		// File Location
		String fileLocation = FileConstants.ANALYSIS_FILE_BASE_PATH + "\\" + fileName + ".xlsx";

		// Create Workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Statistics");

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle.setFont(getHeaderFont(workbook));

		// Create a Row
		Row headerRow = sheet.createRow(0);
		List<String> headerCells = getHeader(statsCollectorList);
		for (int i = 0; i < headerCells.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headerCells.get(i));
			cell.setCellStyle(headerCellStyle);
		}

		// Add data
		CellStyle dataCellStyle = workbook.createCellStyle();
		dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
		for (int i = 0; i < statsCollectorList.size(); i++) {
			StatisticsCollector statisticsCollector = statsCollectorList.get(i);

			Row row = sheet.createRow(i + 1);

			int j = 0;
			Collection<String> statsMetaValues = statisticsCollector.statsMeta.values();
			for (String value : statsMetaValues) {
				Cell cell = row.createCell(j++);
				cell.setCellValue(value);
				cell.setCellStyle(dataCellStyle);
			}

			Collection<String> statisticsVales = statisticsCollector.statistics.getStatsAsMap().values();
			for (String value : statisticsVales) {
				Cell cell = row.createCell(j++);
				cell.setCellValue(value);
				cell.setCellStyle(dataCellStyle);
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < headerCells.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(fileLocation);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();

		// Closing the workbook
		workbook.close();
	}

	private static Font getHeaderFont(Workbook workbook) {
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 11);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		return headerFont;
	}

	private static List<String> getHeader(List<StatisticsCollector> statsCollectorList) {
		List<String> headerColumns = new ArrayList<String>();
		StatisticsCollector statisticsCollector = statsCollectorList.get(0);
		headerColumns.addAll(statisticsCollector.statsMeta.keySet());
		headerColumns.addAll(statisticsCollector.statistics.getStatsAsMap().keySet());
		return headerColumns;
	}
}
