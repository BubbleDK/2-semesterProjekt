package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Pack;
import model.Price;
import model.Product;

public class ProductDB implements ProductDBIF {
	private static final String FIND_BY_BARCODE_Q = "SELECT * FROM kk_AbstractProduct WHERE barcode = ?";
	private static PreparedStatement findByBarcodePS;
	private static final String FIND_PRICEHISTORY_BY_PRODUCTID_Q = "SELECT TOP 1 * FROM kk_Pricehistory WHERE productID = ? ORDER BY historyDate DESC";
	private static PreparedStatement findPriceHistoryPS;
	
	public ProductDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByBarcodePS = con.prepareStatement(FIND_BY_BARCODE_Q);
			findPriceHistoryPS = con.prepareStatement(FIND_PRICEHISTORY_BY_PRODUCTID_Q);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		} 
	}

	@Override
	public AbstractProduct findByProductBarcode(String barcode) throws DataAccessException {
		AbstractProduct currProduct = null;
		try {
			findByBarcodePS.setString(1, barcode);
			ResultSet rs = findByBarcodePS.executeQuery();
			if(rs.next()) {
				currProduct = buildPackObject(rs);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return currProduct;
	}
	
	private AbstractProduct buildPackObject(ResultSet rs) throws DataAccessException {
		AbstractProduct currProduct = new Pack();
		try {
			currProduct.setName(rs.getString("productName"));
			currProduct.setBarcode(rs.getString("barcode"));
			currProduct.setProductDescription(rs.getString("productdescription"));
			currProduct.setStock(rs.getInt("stock"));
			findPriceHistoryPS.setInt(1, rs.getInt("id"));
			ResultSet res = findPriceHistoryPS.executeQuery();
			if(res.next()) {
				String p = res.getString("price");
				Price price = new Price(Double.parseDouble(p));
				currProduct.setPrice(price);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currProduct;
	}

}
