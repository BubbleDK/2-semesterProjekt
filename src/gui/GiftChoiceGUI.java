package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.OrderCtrl;
import model.B2BOrderLine;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiftChoiceGUI extends JFrame {
	private OrderChoiceOrderTableModel orderChoiceOrderTableModel;
	private OrderChoiceTableModel orderChoiceTableModel;
	private JPanel contentPane;
	private OrderCtrl orderCtrl;
	private JTextField txtOrderNumber;
	private JTextField txtB2BCustomer;
	private JTextField txtGiftCode;
	private JTextField txtEmail;
	private JTable tblChoices;
	private JTable tblB2BOrder;
	private boolean choice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private OrderCtrl orderCtrl;

			public void run() {
				try {
					GiftChoiceGUI frame = new GiftChoiceGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GiftChoiceGUI(OrderCtrl orderCtrl, String giftNo) {
		this();
		init(orderCtrl, giftNo);
	}
	
	/**
	 * Create the frame. 
	 */
	public GiftChoiceGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		panel.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(new BoxLayout(desktopPane, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		desktopPane.add(scrollPane);
		
		tblChoices = new JTable();
		scrollPane.setViewportView(tblChoices);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		desktopPane.add(scrollPane_1);
		
		tblB2BOrder = new JTable();
		scrollPane_1.setViewportView(tblB2BOrder);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{325, 329, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{120, 89, 0};
		gbl_panel_3.rowHeights = new int[]{23, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnChoice = new JButton("Tilføj");
		btnChoice.addActionListener((e ->  { 
			choiceClicked();
		}));
		GridBagConstraints gbc_btnChoice = new GridBagConstraints();
		gbc_btnChoice.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnChoice.gridx = 1;
		gbc_btnChoice.gridy = 0;
		panel_3.add(btnChoice, gbc_btnChoice);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 0;
		panel_2.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{89, 0};
		gbl_panel_4.rowHeights = new int[]{23, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		//TODO: Færdiggør orderLineID på login + stock og quantity i db
		JButton btnUpdateOrder = new JButton("Gem");
		btnUpdateOrder.addActionListener((e -> {
			 this.dispose();
			
		}));
		btnUpdateOrder.setAlignmentX(Component.RIGHT_ALIGNMENT);
		GridBagConstraints gbc_btnUpdateOrder = new GridBagConstraints();
		gbc_btnUpdateOrder.anchor = GridBagConstraints.EAST;
		gbc_btnUpdateOrder.gridx = 0;
		gbc_btnUpdateOrder.gridy = 0;
		panel_4.add(btnUpdateOrder, gbc_btnUpdateOrder);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{19, 91, 258, 0, 0, 46, 0};
		gbl_panel_1.rowHeights = new int[]{0, 14, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblOrderNumber = new JLabel("Ordrenummer");
		GridBagConstraints gbc_lblOrderNumber = new GridBagConstraints();
		gbc_lblOrderNumber.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblOrderNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrderNumber.gridx = 1;
		gbc_lblOrderNumber.gridy = 0;
		panel_1.add(lblOrderNumber, gbc_lblOrderNumber);
		
		txtOrderNumber = new JTextField();
		txtOrderNumber.setEditable(false);
		GridBagConstraints gbc_txtOrderNumber = new GridBagConstraints();
		gbc_txtOrderNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrderNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderNumber.gridx = 2;
		gbc_txtOrderNumber.gridy = 0;
		panel_1.add(txtOrderNumber, gbc_txtOrderNumber);
		txtOrderNumber.setColumns(10);
		
		JLabel lblGiftCode = new JLabel("Gavekode");
		GridBagConstraints gbc_lblGiftCode = new GridBagConstraints();
		gbc_lblGiftCode.anchor = GridBagConstraints.WEST;
		gbc_lblGiftCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblGiftCode.gridx = 4;
		gbc_lblGiftCode.gridy = 0;
		panel_1.add(lblGiftCode, gbc_lblGiftCode);
		
		txtGiftCode = new JTextField();
		txtGiftCode.setEditable(false);
		GridBagConstraints gbc_txtGiftCode = new GridBagConstraints();
		gbc_txtGiftCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtGiftCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGiftCode.gridx = 5;
		gbc_txtGiftCode.gridy = 0;
		panel_1.add(txtGiftCode, gbc_txtGiftCode);
		txtGiftCode.setColumns(10);
		
		JLabel lblB2BCustomer = new JLabel("Kunde");
		GridBagConstraints gbc_lblB2BCustomer = new GridBagConstraints();
		gbc_lblB2BCustomer.insets = new Insets(0, 0, 0, 5);
		gbc_lblB2BCustomer.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblB2BCustomer.gridx = 1;
		gbc_lblB2BCustomer.gridy = 1;
		panel_1.add(lblB2BCustomer, gbc_lblB2BCustomer);
		
		txtB2BCustomer = new JTextField();
		txtB2BCustomer.setEditable(false);
		GridBagConstraints gbc_txtB2BCustomer = new GridBagConstraints();
		gbc_txtB2BCustomer.insets = new Insets(0, 0, 0, 5);
		gbc_txtB2BCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtB2BCustomer.gridx = 2;
		gbc_txtB2BCustomer.gridy = 1;
		panel_1.add(txtB2BCustomer, gbc_txtB2BCustomer);
		txtB2BCustomer.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 0, 5);
		gbc_lblEmail.gridx = 4;
		gbc_lblEmail.gridy = 1;
		panel_1.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 5;
		gbc_txtEmail.gridy = 1;
		panel_1.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
	}
	
	private void choiceClicked() {
		int selected = tblChoices.getSelectedRow();
		if(selected >= 0 && !choice) {
		updateQuantity(selected);
		choice = true;
		refresh();
		}
	}

//	private void updateOrder(int selected) {
//		orderChoiceOrderTableModel.getElementAtIndex(selected);
//		updateQuantity();
//	}

	private void updateQuantity(int selected) {
		orderCtrl.getOrder().getOrderLines().get(selected).addQuantity(1);
	}

	public void init(OrderCtrl orderCtrl, String giftNo){
		choice = false;
		this.orderCtrl = orderCtrl;
		orderChoiceTableModel = new OrderChoiceTableModel();
		this.tblChoices.setModel(orderChoiceTableModel);
		this.txtOrderNumber.setText(Integer.toString(orderCtrl.getOrder().getOrderNo()));
		this.txtB2BCustomer.setText(orderCtrl.getOrder().getB2BCustomer().getCompanyName());
		this.txtGiftCode.setText(giftNo);
		for(String email : orderCtrl.getOrder().getEmailGiftNo().keySet()) {
			System.out.println(orderCtrl.getOrder().getEmailGiftNo().get(email));
			if(orderCtrl.getOrder().getEmailGiftNo().get(email).equals(giftNo)){
				this.txtEmail.setText(email);
			}
		}
		orderChoiceOrderTableModel = new OrderChoiceOrderTableModel();
		this.tblB2BOrder.setModel(orderChoiceOrderTableModel);
		refresh();
		
	}

	private void refresh() {
		List<B2BOrderLine> currOrderLines = orderCtrl.getOrder().getOrderLines();
		this.orderChoiceTableModel.setModelData(currOrderLines);
		this.orderChoiceOrderTableModel.setModelData(currOrderLines);
		
	}
}