package db;

import java.sql.SQLException;

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
 *          Dette interface har alle metoderne ProductDB SKAL have.
 *
 */
public interface ProductDBIF {
	/**
	 * Metoden finder en pakke ud fra en stregkode.
	 * @param barcode er stregkoden på pakken man gerne vil finde.
	 * @return den pakke der bliver fundet.
	 * @throws DataAccessException kastes hvis der ikke kan trækkes data ud fra databasen.
	 */
	Pack findByProductBarcode(String barcode) throws DataAccessException;
	
	/**
	 * Metoden finder en pakke ud fra et productID.
	 * @param id er det productID som pakken har.
	 * @return den pakke der bliver fundet.
	 * @throws DataAccessException kastes hvis der ikke kan trækkes data ud fra databasen.
	 */
	Pack findByProductId(int id) throws DataAccessException;
	
	/**
	 * Metoden finder ProductID ud fra en stregkoden.
	 * @param barcode er stregkoden på produktet man gerne vil finde ProductID ud fra.
	 * @return en int som er ProductID.
	 * @throws SQLException kastes hvis der er fejl med databasen.
	 * @throws DataAccessException kastes hvis der ikke kan trækkes data ud fra databasen.
	 */
	public int findProductIdByBarcode(String barcode) throws SQLException, DataAccessException;
	
	/**
	 * Metoden tager en stregkode og updatere lagerbeholdning ud fra et valg taget i GUIen.
	 * @param barcode er stregkoden på produktet.
	 * @throws SQLException kastes hvis der er fejl med databasen.
	 */
	public void updateStockByBarcode(String barcode) throws SQLException;
}
