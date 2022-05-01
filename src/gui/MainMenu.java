package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.OrderCtrl;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnNewOrder = new JButton("Ny ordre");
		btnNewOrder.addActionListener((e -> newOrderClicked()));
		panel.add(btnNewOrder);
		
		JButton btnFindOrder = new JButton("Find ordre");
		btnFindOrder.setEnabled(false);
		panel.add(btnFindOrder);
		
		JButton btnFindProduct = new JButton("Find produkter");
		btnFindProduct.setEnabled(false);
		panel.add(btnFindProduct);

	}

	private void newOrderClicked() {
		OrderGUI orderGui = new OrderGUI();
		orderGui.setVisible(true);
		this.dispose();
	}

}
