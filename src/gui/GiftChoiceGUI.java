package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.OrderCtrl;

public class GiftChoiceGUI extends JFrame {

	private JPanel contentPane;
	private OrderCtrl orderCtrl;

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

	public GiftChoiceGUI(OrderCtrl orderCtrl) {
		this();
		init(orderCtrl);
	}
	
	/**
	 * Create the frame. 
	 */
	public GiftChoiceGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void init(OrderCtrl orderCtrl){
		this.orderCtrl = orderCtrl;
		
	}

}
