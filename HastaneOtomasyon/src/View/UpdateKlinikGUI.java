package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Klinik;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateKlinikGUI extends JFrame {

	private JPanel contentPane;
	private JTextField Txtklinikadi;
	private static Klinik klinik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateKlinikGUI frame = new UpdateKlinikGUI(klinik);
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
	public UpdateKlinikGUI(Klinik klinik) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 237, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(74, 19, 118, 27);
		contentPane.add(lblNewLabel_1_2);
		
		Txtklinikadi = new JTextField();
		Txtklinikadi.setColumns(10);
		Txtklinikadi.setBounds(37, 56, 174, 27);
		Txtklinikadi.setText(klinik.getName());
		contentPane.add(Txtklinikadi);
		
		JButton btnDuzenle = new JButton("Düzenle");
		btnDuzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						klinik.updateKlinik(Txtklinikadi.getText(), klinik.getId());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDuzenle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDuzenle.setBounds(37, 97, 174, 29);
		contentPane.add(btnDuzenle);
	}
}
