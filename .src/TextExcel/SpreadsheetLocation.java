package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int row;
	private int col;
	
    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.col;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        this.col = cellName.charAt(0) - 'A';
        this.row = Integer.parseInt(cellName.substring(1)) - 1;
    }

}
