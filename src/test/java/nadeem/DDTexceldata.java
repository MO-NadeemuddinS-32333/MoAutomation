package nadeem;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DDTexceldata {
	@Test
	public void datadriventesting() throws IOException, InterruptedException {
		String excelPath = "C:\\Users\\nadeemuddinsayed\\Desktop\\somu sir\\asder.xlsx";

		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		Thread.sleep(25000);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Cell textfromeexcel = row.getCell(0); // Column B
				if (textfromeexcel != null) {
					String data = textfromeexcel.getStringCellValue();
					System.out.println(data);
				}
			}
		}
		workbook.close();
		fis.close();
	}
}
