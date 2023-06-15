package textExcel;

public class FormulaCell extends RealCell {
	private Spreadsheet sheet;

	public FormulaCell(String s, Spreadsheet sheet) {
		super(s);
		this.sheet = sheet;
	}
	
	public double getDoubleValue() {
		String[] split = ((fullCellText().substring(2, fullCellText().length() - 2))).toUpperCase().split(" ");
		double result = 0;
		
		//split[0] = split[0].toUpperCase();
		
		// L14 = ( SUM B6-C12 )
		// C12 = ( AVG A1-A5 )
		if (split[0].equals("SUM") || split[0].equals("AVG")) {
			String[] endpoints = split[1].split("-");
			SpreadsheetLocation corner1 = new SpreadsheetLocation(endpoints[0]);
			SpreadsheetLocation corner2 = new SpreadsheetLocation(endpoints[1]);
			
			int numCells = 0;
			
			for (int c = corner1.getCol(); c <= corner2.getCol(); c++) {
				for (int r = corner1.getRow(); r <= corner2.getRow(); r++) {
					numCells++;
					SpreadsheetLocation tempLoc = new SpreadsheetLocation( String.valueOf((char)('A' + c)) + Integer.toString(r + 1));
					result += ((RealCell)(sheet.getCell(tempLoc))).getDoubleValue();
				}
			}
			
			if (split[0].equals("AVG")) {
				result /= numCells;
			}
			
		}
		else {
			result = getNumValue(split, 0);
			
			
			for (int i = 2; i < split.length; i += 2) {
				double n = getNumValue(split, i);
				
				if(split[i-1].equals("-")) {
					result -= n;
				}
				else if (split[i-1].equals("+")) {
					result += n;
				}
				else if (split[i-1].equals("*")) {
					result *= n;
				}
				else {
					result /= n;
				}
			}
		}
		
		return result;
	}
	
	public double getNumValue(String[] arr, int i) {
		if (arr[i].charAt(0) >= 'A' && arr[i].charAt(0) <= 'Z') {
			SpreadsheetLocation loc = new SpreadsheetLocation(arr[i].toUpperCase());
			return ((RealCell)(sheet.getCell(loc))).getDoubleValue();
		}
		else {
			return Double.parseDouble(arr[i]);
		}
	}

	/*
	Write the getDoubleValue() method of the FormulaCell class such that it contains all the parsing and
	calculation logic required to generate a numeric result for an arithmetic formula that does not
	contain cell references.
	Remember that formulas can be as long as the user likes. Unlike FracCalc, which only allowed one
	operator, there could theoretically be 50 or 100 or even more operators in a formula. The split
	method on the String class may be useful in parsing your formulas.
	*/
	
	public String abbreviatedCellText() {
		return String.format("%-10.10s", getDoubleValue() + "");
	}
}
