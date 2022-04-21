package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ctrl.DataAccessException;
import model.Product;

public class ProductDB implements ProductDBIF {
	private Product currProduct;
	private static final String FIND_BY_BARCODE_Q = "SELECT * FROM KK_Product WHERE barcode = ?";
	private static PreparedStatement findByBarcodePS;
	
	public ProductDB() {
		try {
			findByBarcodePS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_BARCODE_Q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Product findByProductBarcode(String barcode) throws DataAccessException {
		currProduct = null;
		try {
			findByBarcodePS.setString(1, barcode);
			ResultSet rs = findByBarcodePS.executeQuery();
			if(rs.next()) {
				currProduct = buildPackObject(rs,barcode);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
//			e.printStackTrace();
		}
		return null;
	}
	
	public Product buildPackObject(ResultSet rs, String barcode) {
		Product res = null;
		
		return res;
	}

}
