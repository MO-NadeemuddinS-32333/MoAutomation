package nadeem;

import java.io.IOException;

import pageobjects.ResusableMethods;

public class aademo {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\nadeemuddinsayed\\Desktop\\somu sir\\asder.xlsx";

		ResusableMethods.getCellData(path, 0, 3, 1);
		System.out.println("========================");

		/*
		 * String b = "Kushboo"; ResusableMethods.writeCellData(path, 0, 4, 1, b);
		 * System.out.println("========================");
		 */

		ResusableMethods.getBelowCellValue(path, "O", 0, 3);
		System.out.println("========================");

		ResusableMethods.getAdjacentCellValue(path, "I", 0, 0);
		System.out.println("========================");
		
	}
}
