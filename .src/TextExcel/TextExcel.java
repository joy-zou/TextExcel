package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextExcel
{

	public static void main(String[] args)
	{
		Spreadsheet newSpreadsheet = new Spreadsheet();

		Scanner input = new Scanner(System.in);
		
		String nextLine = input.nextLine();
		
		while (!nextLine.equals("quit")) {
			System.out.println(newSpreadsheet.processCommand(nextLine));
			nextLine = input.nextLine();
		}
	}
}
