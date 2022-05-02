package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Pack;
import model.Product;

public class ProductDB implements ProductDBIF {
	private AbstractProduct currProduct;
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
			buildPackObject(rs, barcode);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
//			e.printStackTrace();
		}
		return null;
	}
	
	private AbstractProduct buildPackObject(ResultSet rs, String barcode) throws DataAccessException {
		currProduct = new Pack();
		try {
			if(rs.next()) {
				currProduct.setName(rs.getString("name"));
				currProduct.setBarcode(rs.getString("barcode"));
				currProduct.setProductDescription(rs.getString("productdescription"));
				currProduct.setStock(rs.getInt("stock"));
				currProduct.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
//			e.printStackTrace();
		}
		return currProduct;
	}

}
