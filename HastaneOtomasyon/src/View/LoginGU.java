package View;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;

public class LoginGU extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField txtTc;
	private JTextField txtSifre;
	private JTextField txttcdoktor;
	private JPasswordField txtsifredoktor;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGU frame = new LoginGU();
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
	public LoginGU() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 588);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("4.jpg")));
		lbl_logo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_logo.setBounds(245, 10, 138, 125);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastaneminizin Yönetim Sistemine Hoş Geldiniz");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel.setBounds(102, 142, 449, 39);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(32, 191, 568, 270);
		w_pane.add(w_tabpane);
		
		JPanel pnlHastaneKabul = new JPanel();
		pnlHastaneKabul.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Kabulu", null, pnlHastaneKabul, null);
		pnlHastaneKabul.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaranız:");
		lblTcNumaranz.setForeground(Color.BLACK);
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblTcNumaranz.setBounds(23, 23, 146, 39);
		pnlHastaneKabul.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setForeground(Color.BLACK);
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblifre.setBounds(121, 72, 65, 39);
		pnlHastaneKabul.add(lblifre);
		
		txtTc = new JTextField();
		txtTc.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtTc.setBounds(196, 23, 286, 34);
		pnlHastaneKabul.add(txtTc);
		txtTc.setColumns(10);
		
		txtSifre = new JTextField();
		txtSifre.setColumns(10);
		txtSifre.setBounds(196, 72, 286, 36);
		pnlHastaneKabul.add(txtSifre);
		
		JButton btnKayıtOl = new JButton("Kayıt Ol");
		btnKayıtOl.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnKayıtOl.setBounds(34, 166, 185, 55);
		pnlHastaneKabul.add(btnKayıtOl);
		
		JButton btnGirisYap = new JButton("Giriş Yap");
		btnGirisYap.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGirisYap.setBounds(313, 166, 185, 55);
		pnlHastaneKabul.add(btnGirisYap);
		
		JPanel pnlDoktorLog = new JPanel();
		pnlDoktorLog.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, pnlDoktorLog, null);
		pnlDoktorLog.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaranız:");
		lblTcNumaranz_1.setForeground(Color.BLACK);
		lblTcNumaranz_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblTcNumaranz_1.setBounds(47, 20, 146, 39);
		pnlDoktorLog.add(lblTcNumaranz_1);
		
		txttcdoktor = new JTextField();
		txttcdoktor.setFont(new Font("Tahoma", Font.BOLD, 17));
		txttcdoktor.setColumns(10);
		txttcdoktor.setBounds(220, 20, 286, 34);
		pnlDoktorLog.add(txttcdoktor);
		
		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setForeground(Color.BLACK);
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblifre_1.setBounds(145, 69, 65, 39);
		pnlDoktorLog.add(lblifre_1);
		
		JButton btnGirisYapDoktor = new JButton("Giriş Yap");
		btnGirisYapDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txttcdoktor.getText().length() == 0 || txtsifredoktor.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con = conn.conDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM hastane");
						while(rs.next()) {
							if(txttcdoktor.getText().equals(rs.getString("tcno")) && txtsifredoktor.getText().equals(rs.getString("password"))) {}
							Bashekim bshekim = new Bashekim();
							bshekim.setId(rs.getInt("id"));
							bshekim.setPassword("password");
							bshekim.setTcno(rs.getString("tcno"));
							bshekim.setName(rs.getString("name"));
							bshekim.setType(rs.getString("type"));
							BashekimGUI bGUI = new BashekimGUI(bshekim);
							bGUI.setVisible(true);
							dispose();
						}
						
					} catch (SQLException e1) {
					
						e1.printStackTrace();
					}
				}
			}
		});
		btnGirisYapDoktor.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGirisYapDoktor.setBounds(38, 146, 468, 55);
		pnlDoktorLog.add(btnGirisYapDoktor);
		
		txtsifredoktor = new JPasswordField();
		txtsifredoktor.setBounds(220, 77, 286, 34);
		pnlDoktorLog.add(txtsifredoktor);
	}
}
