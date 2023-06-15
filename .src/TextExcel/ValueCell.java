package textExcel;

public class ValueCell extends RealCell {
	
	public ValueCell (String s) {
		super(s);
	}

	public String abbreviatedCellText() {
		return String.format("%-10.10s", getDoubleValue() + "");
	}
}
