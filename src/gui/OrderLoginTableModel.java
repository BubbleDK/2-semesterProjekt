package gui;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public class OrderLoginTableModel extends DefaultTableModel{
	private static final String[] COLUMN_NAMES = { "Email"};
	private ArrayList<String> elements;

	public OrderLoginTableModel() {
		elements = new ArrayList<>();
	}

	public void setModelData(HashMap currLogins) {
		elements.clear();
		elements.addAll(currLogins.keySet());
		super.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		int res = 0;
		if (elements != null) {
			res = elements.size();
		}
		return res;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		String currList = elements.get(row);
		String res = "UNKNOWN";
		switch (column) {
		case 0:
			res = currList;
			break;
		}
		return res;
	}
	
	public String getElementAtIndex(int selectedRow) {
		if(elements.size() < 1) {
			return null;
		}
		return elements.get(selectedRow);
	}
	@Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
}
