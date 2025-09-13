package pageobjects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class ElementTextExtractor {
    
    public static void extractAllElementsText(AndroidDriver driver, String filePath) throws IOException {
        // Find all elements on the screen
        List<WebElement> allElements = driver.findElements(By.xpath("//*"));
        
        // Create Excel workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ElementTexts");
        Row row = sheet.createRow(0);
        
        int columnIndex = 0;
        
        // Extract text from each element and save to Excel
        for (WebElement element : allElements) {
            try {
                String text = element.getText();
                if (text != null && !text.trim().isEmpty()) {
                    row.createCell(columnIndex).setCellValue(text);
                    columnIndex++;
                }
            } catch (Exception e) {
                // Skip elements that can't be accessed
                continue;
            }
        }
        
        // Save Excel file
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        
        System.out.println("Extracted " + columnIndex + " element texts to: " + filePath);
    }
}