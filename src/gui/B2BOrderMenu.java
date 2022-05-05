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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import ctrl.CustomerCtrl;
import ctrl.OrderCtrl;
import exceptions.DataAccessException;
import model.B2BCustomer;

public class B2BOrderMenu extends JFrame {

	private JPanel contentPane;
	private CustomerCtrl customerCtrl;
	private OrderCtrl orderCtrl;

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
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 60, 60, 60, 60, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
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

		init();
	}

	private void init() {
		try {
			customerCtrl = new CustomerCtrl();
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(this, "Kan ikke få adgang til database", "Data access error",
					JOptionPane.OK_OPTION);
//			e.printStackTrace();
		}
		try {
			orderCtrl = new OrderCtrl();
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(this, "Kan ikke få adgang til database", "Data access error",
					JOptionPane.OK_OPTION);
			//e.printStackTrace();
		}
	}

	private void newB2BOrderClicked() {
		String companyName = findCompanyNameByCVR();
		System.out.println("efter findcompanybycvr");
		if(companyName != null) {
			String endDate = JOptionPane.showInputDialog("Indtast slut dato: 'DD-MM-YYYY'");
			if(checkDate(endDate)) {
			B2BOrderGUI orderGUI = new B2BOrderGUI(companyName, endDate, orderCtrl);
			orderGUI.setVisible(true);
			this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "Dato format skal være DD-MM-YYYY, eksempelvis 01-01-2000", "Input fejl",
						JOptionPane.OK_OPTION);
				newB2BOrderClicked();
			}
		}else {
			newB2BOrderClicked();
		}
	}

	private String findCompanyNameByCVR() {
		System.out.println("1");
		String insertCVR = JOptionPane.showInputDialog("Indtast CVR");
		int cvr = -1;
		if (isANumber(insertCVR) && !insertCVR.isBlank()) {
			cvr = Integer.parseInt(insertCVR);
			System.out.println("2");
		} else {
			JOptionPane.showMessageDialog(this, "Antal skal være et tal.", "Input fejl", JOptionPane.OK_OPTION);
			System.out.println("3");
		}

		B2BCustomer currCustomer = null;

		try {
			currCustomer = customerCtrl.findB2BCustomer(cvr);
			System.out.println(currCustomer + "har fundet en customer");
		} catch (DataAccessException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Kan ikke få adgang til database", "Data access error",
					JOptionPane.OK_OPTION);
		}

		if (currCustomer == null) {
			JOptionPane.showMessageDialog(null, "Kunne ikke finde kunde ud fra indtastede cvr.", "Fejlmeddelelse",
					JOptionPane.OK_OPTION);
			System.out.println("4");
			return null;
		}
		System.out.println(currCustomer + "2");
		return currCustomer.getCompanyName();
	}
	
	private Boolean checkDate(String date) {
		//datePattern describes a dd-mm-yyyy date pattern.
		String datePattern = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

		Pattern pattern = Pattern.compile(datePattern);

		Matcher m = pattern.matcher(date);

		if(m.find()) {
			System.out.println(date + " is ok");
			return true;
		}
		else {
			System.out.println(date + " is not ok");
			return false;
		}
		
	}

	private boolean isANumber(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}