package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ctrl.CustomerCtrl;
import ctrl.OrderCtrl;
import db.DBConnection;
import db.DBMessages;
import exceptions.DataAccessException;
import model.B2BOrder;

class TC1 {
	private B2BOrder currOrder;
	private static final String FIND_ORDER_Q = "select * from kk_Orders WHERE orderNo = ?";
	private PreparedStatement findOrderPS;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	
	}

	@Test
	void testCreateOrderSameAsSavedOrder() throws DataAccessException, SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		OrderCtrl orderCtrl = new OrderCtrl();
		CustomerCtrl customerCtrl = new CustomerCtrl();
		orderCtrl.registerB2BOrder("20-05-2022", 123456789);
		orderCtrl.addPackage("P1234");
		orderCtrl.addB2BEmployee("Gudiksen@gmail.com");
		currOrder = orderCtrl.endOrder();
		
		
		findOrderPS = con.prepareStatement(FIND_ORDER_Q);
		findOrderPS.setInt(1, currOrder.getOrderNo());
		ResultSet rs = findOrderPS.executeQuery();
		if(rs.next()) {
		System.out.println(rs.getInt("id"));
		System.out.println(currOrder.getOrderNo());
		assertEquals(currOrder.getOrderNo(), rs.getInt("orderNo"));
		}
		
	}


}
