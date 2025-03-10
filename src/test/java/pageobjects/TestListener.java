package pageobjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	// List to store test details
	private final List<String[]> testResults = new ArrayList<>();
	private int serialNo = 1; // Counter for Serial Number

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("🚀 Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("✅ Test Passed: " + result.getName());
		testResults.add(new String[] { String.valueOf(serialNo++), result.getName(),
				"<span style='color: green; font-weight: bold;'>PASS</span>",
				result.getEndMillis() - result.getStartMillis() + " ms" });
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("❌ Test Failed: " + result.getName());
		testResults.add(new String[] { String.valueOf(serialNo++), result.getName(),
				"<span style='color: red; font-weight: bold;'>FAIL</span>",
				result.getEndMillis() - result.getStartMillis() + " ms" });
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("⚠️ Test Skipped: " + result.getName());
		testResults.add(new String[] { String.valueOf(serialNo++), result.getName(),
				"<span style='color: orange; font-weight: bold;'>SKIPPED</span>",
				result.getEndMillis() - result.getStartMillis() + " ms" });
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("📌 Test Execution Finished: " + context.getName());
		generateHtmlReport();
	}

	// Method to generate HTML Report
	private void generateHtmlReport() {
		String filePath = "index.html";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write("<html><head><title>Test Report</title>");
			writer.write("<style>");
			writer.write("table {width: 100%; border-collapse: collapse;}");
			writer.write("th, td {border: 1px solid black; padding: 10px; text-align: left;}");
			writer.write("th {background-color: #f2f2f2;}");
			writer.write("</style>");
			writer.write("</head><body>");
			writer.write("<h2>Test Execution Report</h2>");
			writer.write("<table>");
			writer.write("<tr><th>Serial No</th><th>Test Case</th><th>Status</th><th>Execution Time</th></tr>");

			// Append test results to the table
			for (String[] result : testResults) {
				writer.write("<tr>");
				for (String data : result) {
					writer.write("<td>" + data + "</td>");
				}
				writer.write("</tr>");
			}

			writer.write("</table>");
			writer.write("</body></html>");
			System.out.println("✅ Test Report generated: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
