package pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;

public class ResusableMethods {
	AndroidDriver Driver;

	public ResusableMethods(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	public static void tapWithActions(AndroidDriver Driver, int x, int y) throws InterruptedException {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		Thread.sleep(100);
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Driver.perform(Collections.singletonList(tap));
	}

	public static void horizontalSwipetillElement(AndroidDriver Driver, WebElement ElementToFind, int swipeCount,
			int maxSwipes, int startX, int endX, int centerY) {

		boolean elementFound = false;
		while (!elementFound && swipeCount < maxSwipes) {
			try {
				WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
						.until(ExpectedConditions.visibilityOf(ElementToFind));
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1)
						.addAction(finger
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY))
						.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
								endX, centerY))
						.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				Driver.perform(Arrays.asList(swipe));
				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}

		}

	}

	public static void verticalswipetillElement(AndroidDriver Driver, WebElement ElementToFind, int swipeCount,
			int maxSwipes, int centerX, int startY, int endY) {

		boolean elementFound = false;

		while (!elementFound && swipeCount < maxSwipes) {
			try {
				WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
						.until(ExpectedConditions.visibilityOf(ElementToFind));
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1)
						.addAction(finger
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY))
						.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
								centerX, endY))
						.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				Driver.perform(Arrays.asList(swipe));

				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}
		}
	}

	public static void swipeCorinates(AndroidDriver Driver, int startX, int startY, int endX, int endY,
			int swipeCount) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		for (int i = 1; i <= swipeCount; i++) {
			Sequence swipe = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
							.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			Driver.perform(Arrays.asList(swipe));

			System.out.println("Performed swipe #" + i);
		}
	}

	public static void longpressElement(AndroidDriver Driver, WebElement elementtolongpress) {
		int elementX = elementtolongpress.getRect().getX() + (elementtolongpress.getRect().getWidth() / 2);
		int elementY = elementtolongpress.getRect().getY() + (elementtolongpress.getRect().getHeight() / 2);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence longPress = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), elementX, elementY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Press down
				.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(), elementX,
						elementY)) // Hold for 2 seconds
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release
		Driver.perform(Arrays.asList(longPress));

	}

	public static void cleartextandenterinput(AndroidDriver Driver, WebElement textbox, String text) {
		textbox.click();
		textbox.clear();
		textbox.sendKeys(text);
		Driver.hideKeyboard();
	}

	public static void longPressWithActions(AndroidDriver Driver, int x, int y, int time) throws InterruptedException {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		Thread.sleep(time);
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		Driver.perform(Collections.singletonList(tap));
	}

	public static void captureScreenshot(AndroidDriver Driver, String text) {
		try {
			File screenshot = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String filePath = "C:/Users/nadeemuddinsayed/Desktop/somu sir/" + text + "_" + timestamp + ".png";
			FileUtils.copyFile(screenshot, new File(filePath));
			System.out.println("Screenshot saved: " + filePath);
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
	}

	private static int rowCounter = 0;

	public static void logTableStart(String tableName) {
		Reporter.log("<h3>" + tableName + "</h3>", true);
		Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
		Reporter.log("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th><th>Time Taken (ms)</th></tr>", true);
	}

	public static void logTableRow(String testCase, String status, long timeTaken) {
		rowCounter++;
		String statusColor = "";
		String statusTextStyle = "color: white; font-weight: bold;";

		if ("Fail".equalsIgnoreCase(status)) {
			statusColor = "background-color: red;";
		} else if ("Pass".equalsIgnoreCase(status)) {
			statusColor = "background-color: green;";
		}

		Reporter.log("<tr><td>" + rowCounter + "</td><td>" + testCase + "</td><td style='" + statusColor
				+ statusTextStyle + "'>" + status + "</td><td>" + timeTaken + "</td></tr>", true);
	}

	public static void logTableEnd() {
		Reporter.log("</table>", true);
	}

	public static String getCellData(String excelPath, int sheetNumber, int rowNumber, int colNumber)
			throws IOException {

		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(colNumber);
		String cellValue = "";
		if (cell != null) {
			cellValue = cell.toString();
		}
		workbook.close();
		fis.close();

		System.out.println("Cell Data is: " + cellValue);
		return cellValue;
	}

	public static void writeCellData(String excelPath, int sheetNumber, int rowNumber, int colNumber, String value)
			throws IOException {
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		if (row == null) {
			row = sheet.createRow(rowNumber);
		}
		Cell cell = row.getCell(colNumber);
		if (cell == null) {
			cell = row.createCell(colNumber);
		}
		cell.setCellValue(value);
		fis.close();
		FileOutputStream fos = new FileOutputStream(excelPath);
		workbook.write(fos);
		fos.close();
		workbook.close();
		System.out.println(value + " is written to Excel successfully.......");
	}

	public static String getBelowCellValue(String excelPath, String key, int sheetNumber, int rowIndex)
			throws IOException {
		String value = null;
		try (FileInputStream fis = new FileInputStream(excelPath); Workbook workbook = WorkbookFactory.create(fis)) {
			if (sheetNumber >= workbook.getNumberOfSheets()) {
				throw new IllegalArgumentException("Invalid sheet index: " + sheetNumber);
			}
			Sheet sheet = workbook.getSheetAt(sheetNumber);
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				throw new IllegalArgumentException("Row " + rowIndex + " is empty or does not exist.");
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell != null && cell.toString().equalsIgnoreCase(key)) {
					Row nextRow = sheet.getRow(rowIndex + 1);
					if (nextRow != null) {
						Cell nextCell = nextRow.getCell(j);

						if (nextCell != null) {
							value = nextCell.toString();
						}
					}
					break;
				}
			}
		}
		System.out.println("Below cell value to Key " + key + " is: " + value);
		return value;
	}

	public static String getAdjacentCellValue(String excelPath, String key, int sheetNumber, int columnNumber)
			throws IOException {
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		String result = null;

		for (Row row : sheet) {
			Cell cell = row.getCell(columnNumber);

			if (cell != null) {
				String cellValue = cell.toString().trim();

				if (cellValue.equalsIgnoreCase(key)) {
					Cell adjacentCell = row.getCell(columnNumber + 1);
					if (adjacentCell != null) {
						result = adjacentCell.toString();
					}
					break;
				}
			}
		}
		workbook.close();
		fis.close();
		System.out.println("Adjacent cell value to Key " + key + " is: " + result);
		return result;
	}

}
