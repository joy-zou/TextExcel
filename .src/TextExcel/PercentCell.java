package textExcel;

public class PercentCell extends RealCell {
	public PercentCell(String s) {
		super(s);
	}

	public String abbreviatedCellText() {
		return String.format("%-10.10s", (int)super.getDoubleValue() + "%");
	}

	public String fullCellText() {
		return getDoubleValue() + "";
	}
	
	public double getDoubleValue() {
		return super.getDoubleValue()/100.0;
	}
	
	
	// A1 = 8.92259265958979%
	// store as 0.0892259265958979
	// print as 8%
}
