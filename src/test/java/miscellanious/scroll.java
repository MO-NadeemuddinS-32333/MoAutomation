package miscellanious;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class scroll {
	static AndroidDriver driver;

	@Test
	public void scrollwithcordinates() {
		int swipeCount = 5; // Initialize with actual value
		int startX = 500, startY = 1000, endX = 1000, endY = 200; // Initialize coordinates

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		for (int i = 1; i <= swipeCount; i++) {
			Sequence swipe = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
							.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Arrays.asList(swipe));

			System.out.println("Performed swipe #" + i);
		}
	}

	@Test
	public void tapWithActions() throws InterruptedException { // Removed static
		int x = 500;
		int y = 1000;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		Thread.sleep(100);
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(tap));
	}

	@Test
	public static void horizontalSwipetillElement() {
		int startX = 1000, endX = 90, centerY = 80, swipeCount = 1, maxSwipes = 5;
		WebElement ElementToFind = driver.findElement(By.xpath("//android.widget.TextView[@text='India']"));

		boolean elementFound = false;
		while (!elementFound && swipeCount < maxSwipes) {
			try {
				WebElement element = new WebDriverWait(driver, Duration.ofSeconds(1))
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

				driver.perform(Arrays.asList(swipe));
				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}
		}
	}

	@Test
	public static void verticalswipetillElement() {
		int centerX = 1000, startY = 90, endY = 80, swipeCount = 1, maxSwipes = 5;
		WebElement ElementToFind = driver.findElement(By.xpath("//android.widget.TextView[@text='India']"));

		boolean elementFound = false;

		while (!elementFound && swipeCount < maxSwipes) {
			try {
				WebElement element = new WebDriverWait(driver, Duration.ofSeconds(1))
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

				driver.perform(Arrays.asList(swipe));

				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}
		}
	}

	@Test
	public static void captureScreenshot() {
		String text = "abc";
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String filePath = "C:/Users/nadeemuddinsayed/Desktop/somu sir/" + text + "_" + timestamp + ".png";
			FileUtils.copyFile(screenshot, new File(filePath));
			System.out.println("Screenshot saved: " + filePath);
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
	}

	@Test
	public static void cleartextandenterinput() {
		WebElement textbox = driver.findElement(By.xpath("//android.widget.TextView[@text='India']"));
		String text = "abc";

		textbox.click();
		textbox.clear();
		textbox.sendKeys(text);
	}

}