package textExcel;

public class RealCell implements Cell{
	private String input;
	
	public RealCell(String s) {
		input = s;
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(input);
	}

	public String abbreviatedCellText() {
		return String.format("%-10.10s", input);
	}

	public String fullCellText() {
		return input;
	}
}
