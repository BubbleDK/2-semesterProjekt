package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ctrl.CustomerCtrl;
import exceptions.DataAccessException;
import model.B2BCustomer;

public class B2BOrderMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B2BOrderMenu frame = new B2BOrderMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public B2BOrderMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{60, 60, 60, 60, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNyB2BOrdre = new JButton("Ny B2B Ordre");
		btnNyB2BOrdre.addActionListener((e -> {
			newB2BOrderClicked();
		}));
		GridBagConstraints gbc_btnNyB2BOrdre = new GridBagConstraints();
		gbc_btnNyB2BOrdre.insets = new Insets(0, 0, 5, 0);
		gbc_btnNyB2BOrdre.fill = GridBagConstraints.BOTH;
		gbc_btnNyB2BOrdre.gridx = 0;
		gbc_btnNyB2BOrdre.gridy = 0;
		panel.add(btnNyB2BOrdre, gbc_btnNyB2BOrdre);
		
		JButton btnNewButton = new JButton("B2B Gavevalg");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 3;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
	}

	private void newB2BOrderClicked() {
		String companyName = createPopup();
		String endDate = JOptionPane.showInputDialog("Indtast slut dato: 'DD-MM-YYYY'");
		B2BOrderGUI orderGUI = new B2BOrderGUI(companyName, endDate);
		orderGUI.setVisible(true);
		this.dispose();
		
	}
	
	private String createPopup() {
		String insertCVR = JOptionPane.showInputDialog("Indtast CVR");
		int cvr = Integer.parseInt(insertCVR);
		CustomerCtrl customerCtrl = new CustomerCtrl();
		B2BCustomer currCustomer = null;
		try {
			currCustomer = customerCtrl.findB2BCustomer(cvr);
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(currCustomer == null) {
				JOptionPane.showMessageDialog(null, "Prøv Igen");
				newB2BOrderClicked();
			}
		return currCustomer.getCompanyName();
	}
}