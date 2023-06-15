package textExcel;

public class TextCell implements Cell {
	
	private String str;
	
	public TextCell(String str) {
		this.str = str;
	}
	
	public String abbreviatedCellText() {
		String s = String.format("%-10.10s", str.substring(1, str.length() - 1));
		return s; // text for spreadsheet cell display, must be exactly length 10
		
	}
	public String fullCellText() {
		return str;
		// text for individual cell inspection, not truncated or padded
	}
}