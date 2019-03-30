package GuiLayer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DataAccessLayer.DataAccess;
import DataObject.NhanVien;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

/**
 * @author truong
 *
 *         sự kiện đăng nhập thành công, truyền đi nhân viên vừa đăng nhập
 */
interface LoginEvent {
	void onLoginSuccess(NhanVien user);
}

/**
 * @author truong
 *
 *         frame đăng nhập
 */
public class FrmLogin extends JFrame {
	// sự kiện login thành công
	public LoginEvent loginEvent;

	// gui field
	private JPanel contentPane;
	private JTextField tfName;
	private JPasswordField tfPassword;
	private JLabel lbThongBao;
	private Image backgroundImage;
	private Point mouseDownCompCoords;

	// data field
	private DataAccess<NhanVien> data;

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		// khởi tạo
		data = new DataAccess<>(NhanVien.class);
		data.readFile();
		backgroundImage = new ImageIcon(getClass().getResource("/img/loginBackground.png")).getImage();
		// set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
		}
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponents(g);
				g.drawImage(backgroundImage, 0, 0, null); // vẽ nền
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(43, 332, 116, 25);
		contentPane.add(btnLogin);

		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(207, 332, 97, 25);
		contentPane.add(btnExit);

		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				lbThongBao.setText(" ");
				// nếu ấn enter kiểm tra đăng nhập
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLogin();
				}
			}
		});
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfName.setBounds(145, 147, 159, 25);
		contentPane.add(tfName);
		tfName.setColumns(10);

		JLabel lbTaiKhoan = new JLabel("Mã nhân viên");
		lbTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTaiKhoan.setBounds(43, 149, 97, 21);
		contentPane.add(lbTaiKhoan);

		JLabel lbPassword = new JLabel("Mật khẩu");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbPassword.setBounds(43, 221, 90, 25);
		contentPane.add(lbPassword);

		tfPassword = new JPasswordField();
		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				lbThongBao.setText(" ");
				// nếu ấn enter kiểm tra đăng nhập
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLogin();
				}
			}
		});
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPassword.setBounds(145, 221, 159, 25);
		contentPane.add(tfPassword);

		lbThongBao = new JLabel("");
		lbThongBao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbThongBao.setBounds(43, 269, 277, 25);
		contentPane.add(lbThongBao);

		// kéo và thả cửa sổ
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseDownCompCoords = e.getPoint();
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
			}
		});
		setLocationRelativeTo(null);
	}

	/**
	 * kiểm tra tài khoản và mật khẩu có đúng không
	 */
	private void checkLogin() {
		boolean result = false;
		NhanVien user = data.getByID(tfName.getText());
		if (user != null) {
			if (user.getMatKhau().equals(tfPassword.getText())) {
				result = true;
			} else
				result = false;
		} else
			result = false;

		// đăng nhập
		if (loginEvent != null) {
			if (result) {
				// phát sự kiện đăng nhập thành công
				loginEvent.onLoginSuccess(user);
				setVisible(false);
			} else {
				lbThongBao.setText("Tài khoản hoặc mật khẩu không đúng!");
				tfName.requestFocus();
				tfName.selectAll();
				tfPassword.selectAll();
			}
		}
	}
}
