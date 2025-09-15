package nadeem;

import java.io.IOException;

import pageobjects.ResusableMethods;

public class aademo {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\nadeemuddinsayed\\Desktop\\somu sir\\asder.xlsx";
		String a = ResusableMethods.getCellData(path, 0, 3, 1);
		System.out.println(a);

		String b = "Nadeem muntaha anu";
		ResusableMethods.writeCellData(path, 0, 3, 3, b);

		String c = ResusableMethods.getbelowCellValue(path, "Bharat", 0, 0);
		System.out.println(c);

		String d = ResusableMethods.getAdjacentCellValue(path, "atmaram", 0, 0);
		System.out.println(d);
	}
}
