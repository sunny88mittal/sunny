package File;

import java.io.FileOutputStream;
import java.io.IOException;
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

import Entities.ExcelSheet;

public class XLSCreator {

	public static void generateXLS(List<ExcelSheet> excelSheets, String fileLocation) throws IOException {
		// File Location
		fileLocation = fileLocation + ".xlsx";

		// Create Workbook
		Workbook workbook = new XSSFWorkbook();

		// Create header CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle.setFont(getHeaderFont(workbook));

		// Data Cells Style
		CellStyle dataCellStyle = workbook.createCellStyle();
		dataCellStyle.setAlignment(HorizontalAlignment.CENTER);

		for (ExcelSheet excelSheet : excelSheets) {
			int rowIndex = 0;

			// Create a Sheet
			Sheet sheet = workbook.createSheet(excelSheet.name);

			// Create a Row
			Row headerRow = sheet.createRow(rowIndex++);
			List<String> headerCells = excelSheet.header;
			for (int j = 0; j < headerCells.size(); j++) {
				Cell cell = headerRow.createCell(j);
				cell.setCellValue(headerCells.get(j));
				cell.setCellStyle(headerCellStyle);
			}

			// Add Columns
			for (List<String> row : excelSheet.rows) {
				Row contentRow = sheet.createRow(rowIndex++);
				for (int i = 0; i < row.size(); i++) {
					Cell cell = contentRow.createCell(i);
					cell.setCellValue(row.get(i));
					cell.setCellStyle(dataCellStyle);
				}
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < headerCells.size(); i++) {
				sheet.autoSizeColumn(i);
			}
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
}
