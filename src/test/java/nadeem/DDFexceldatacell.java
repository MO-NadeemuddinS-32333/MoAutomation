package nadeem;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DDFexceldatacell {
	@Test
	public void celldataextraction() throws IOException {

		String excelPath = "C:\\Users\\nadeemuddinsayed\\Desktop\\somu sir\\asder.xlsx";

		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);

		Row row = sheet.getRow(4); // Row 5 (0-indexed)
		Cell cell = row.getCell(3); // Column D (0-indexed)
		String cellValue = cell.getStringCellValue();
		System.out.println(cellValue);

		workbook.close();
		fis.close();
	}
}