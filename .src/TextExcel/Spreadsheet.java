package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell[][] arr;
	private int rows = 20;
	private int cols = 12;
	
	public Spreadsheet() {
		this.arr = new Cell[rows][cols];
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j++) {
				arr[i][j] = new EmptyCell();
			}
		}
	}
	
	public String processCommand(String command)
	{
		String[] s = command.split(" ", 3);
		
		if (command.equals("")) {
			return "";
		}
		else if ((s[0].toUpperCase()).equals("CLEAR") && s.length == 1) { // clear
			for (int i = 0; i < rows; i ++) {
				for (int j = 0; j < cols; j ++) {
					arr[i][j] = new EmptyCell();
				}
			}
			return getGridText();
		}
		else if (s.length == 3) { // A1 = [something]
			SpreadsheetLocation loc = new SpreadsheetLocation(s[0].toUpperCase());
			
			if (s[2].indexOf("\"") != -1) { // A1 = "hello"
				arr[loc.getRow()][loc.getCol()] = new TextCell(s[2]);
			}
			else if(s[2].indexOf("%") != -1) { // A1 = 5.2% (percent)
				arr[loc.getRow()][loc.getCol()] = new PercentCell(s[2].substring(0, s[2].length() - 1));
			}
			else if(s[2].indexOf("(") != -1) { // A1 = (formula)
				arr[loc.getRow()][loc.getCol()] = new FormulaCell(s[2], this);
			}
			else { // A1 = 5.2 (real)
				arr[loc.getRow()][loc.getCol()] = new ValueCell(s[2]);
			}
			return getGridText();
		}
		else if (s.length == 2 && (s[0].toUpperCase()).equals("CLEAR")) { // clear A1
			SpreadsheetLocation loc = new SpreadsheetLocation(s[1].toUpperCase());
			arr[loc.getRow()][loc.getCol()] = new EmptyCell();
			return getGridText();
		}
		else { // A1
			SpreadsheetLocation loc = new SpreadsheetLocation(s[0].toUpperCase());
			if (getCell(loc) == null) {	
				return "";
			}
			else {
				return (getCell(loc)).fullCellText();
			}
		}
	}

	public int getRows()
	{
		return this.rows;
	}

	public int getCols()
	{
		return this.cols;
	}

	public Cell getCell(Location loc)
	{
		return arr[loc.getRow()][loc.getCol()];
	}

	public String getGridText()
	{
		String s = "   ";
        for (char c = 'A'; c <= 'L'; c++)
        {
            s += "|" + c + "         ";
        }
        s += "|";
        for (int i = 1; i < 21; i++)
        {
        	s+= "\n" + String.format("%-3d|", i);
            for (int j = 0; j < 12; j++)
            {
            	s += arr[i-1][j].abbreviatedCellText() + "|";
            }
        }
        return s + "\n";
	}

}
