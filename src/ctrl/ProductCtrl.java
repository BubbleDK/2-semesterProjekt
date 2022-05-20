package ctrl;

import java.sql.SQLException;
import db.ProductDB;
import db.ProductDBIF;
import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Pack;
import model.Product;

/**
 * 
 * @authors Rasmus Gudiksen, Jakob Kjeldsteen, Emil Tolstrup Petersen, Christan
 *          Funder og Mark Drongesen
 * 
 *          <p>
 *          Denne klasse er styre alt der har med ordre at gøre
 *
 */
public class ProductCtrl {

	private ProductDBIF productDB;

	/**
	 * Constructoren til klassen, som instansiere et ProductDB objekt.
	 * 
	 * @throws DataAccessException kastes hvis der ikke kan trækkes data ud fra
	 *                             databasen.
	 */
	public ProductCtrl() throws DataAccessException {
		this.productDB = new ProductDB();
	}

	/**
	 * Metoden finder en pakke ud fra en stregkode.
	 * 
	 * @param barcode er stregkoden på pakken der skal findes.
	 * @return Pakken der findes ud fra stregkoden.
	 * @throws DataAccessException kastes hvis der ikke kan trækkes data ud fra
	 *                             databasen.
	 */
	public Pack findPack(String barcode) throws DataAccessException {
		Pack currPack = productDB.findByProductBarcode(barcode);
		return currPack;
	}

	/**
	 * Metoden finder et produkt id ud fra en stregkode.
	 * 
	 * @param barcode er stregkoden på produktet man gerne vil finde product id'et
	 *                på.
	 * @return Produkt id'et som en int.
	 * @throws SQLException        kastes hvis der er fejl med databasen.
	 * @throws DataAccessException kastes hvis der ikke kan trækkes data ud fra
	 *                             databasen.
	 */
	public int findProductIdByBarcode(String barcode) throws SQLException, DataAccessException {
		return productDB.findProductIdByBarcode(barcode);
	}

	/**
	 * Metoden opdatere lagerbeholdningen på et proukt ud fra en stregkode.
	 * 
	 * @param barcode er stregkoden på produktet der skal have opdateret sin
	 *                lagerbeholdning.
	 * @throws SQLException kastes hvis der er fejl med databasen.
	 */
	public void updateStock(String barcode) throws SQLException {
		productDB.updateStockByBarcode(barcode);
	}

}
