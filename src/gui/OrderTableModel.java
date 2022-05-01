package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.B2BOrderLine;

public class OrderTableModel extends DefaultTableModel {

	private static final String[] COLUMN_NAMES = { "Produkt", "Antal", "Pris" };
	private ArrayList<B2BOrderLine> elements;

	public OrderTableModel() {
		elements = new ArrayList<>();
	}

	public void setModelData(List<B2BOrderLine> data) {
		elements.clear();
		elements.addAll(data);
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
		B2BOrderLine currList = elements.get(row);
		String res = "UNKNOWN";
		switch (column) {
		case 0:
			res = currList.getProduct().getName();
			break;
		case 1:
			res = Double.toString(currList.getQuantity());
			break;
		case 2:
			res = Double.toString(currList.getProduct().getPrice() * currList.getQuantity());
			break;
		}
		return res;
	}
	
	public B2BOrderLine getElementAtIndex(int selectedRow) {
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
