package GuiLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DataAccessLayer.DataAccess;
import DataAccessLayer.DataDiaNhac;
import DataAccessLayer.DataDiaPhim;
import DataAccessLayer.DataKhachHang;
import DataAccessLayer.DataNhanVien;
import DataAccessLayer.DataSach;
import DataObject.ChiTietHoaDon;
import DataObject.HoaDon;
import DataObject.NhanVien;

/**
 * @author truong
 * 
 *         cửa sổ chính
 */
public class FrmMain extends JFrame {

	// các thành phần giao diện
	private JPanel contentPane, pnSach, pnDiaNhac, pnKH, pnHoaDon, pnDoiThongTin, pnBanHang, pnDiaPhim, pnNhanVien, pnThongKe, pnHelp, pnAbout;
	private JMenuBar menuMain;
	private JMenu menuKhoHang, menuBanHang, menuThongKe, menuKhachHang, menuNhanVien, menuHelp, menuTaiKhoan;
	private JMenuItem menuSach, menuNhac, menuPhim, menuHoaDon, menDoiThongTin, menuLogout;

	// thành phần dữ liệu
	private DataSach dataSach;
	private DataDiaNhac dataNhac;
	private DataDiaPhim dataPhim;
	private DataKhachHang dataKhachHang;
	private DataNhanVien dataNhanVien;
	private DataAccess<HoaDon> dataHoaDon = new DataAccess<>(HoaDon.class);
	private NhanVien currentUser;
	public LogoutEvent logoutEvent;
	private JMenuItem menuThongKeDoanhThu;
	private JMenu mnAbout;

	/**
	 * Create the frame.
	 */
	public FrmMain(NhanVien user) {
		// set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setTitle("Cửa hàng Media One");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Image backgroundImage = new ImageIcon(getClass().getResource("/img/backgroundMain.jpg")).getImage();
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, null); // vẽ hình nền
			}
		};
		// contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// set vị trí
		setPreferredSize(new Dimension(950, 650));
		setSize(new Dimension(900, 700));
		setLocationRelativeTo(null);

		// tạo các lớp cần thiết để truy cập dữ liệu
		dataNhac = new DataDiaNhac();
		dataSach = new DataSach();
		dataPhim = new DataDiaPhim();
		dataKhachHang = new DataKhachHang();
		currentUser = user;
		dataHoaDon.readFile();
		dataNhanVien = new DataNhanVien();

		// tạo các panel chức năng
		pnSach = new PnSach(currentUser, dataSach);
		pnDiaNhac = new PnDiaNhac(currentUser, dataNhac);
		pnKH = new PnKhachHang(dataKhachHang);
		pnHoaDon = new PnHoaDon(currentUser, dataHoaDon);
		pnDiaPhim = new PnDiaPhim(currentUser, dataPhim);
		pnDoiThongTin = new PnDoiThongTin(currentUser);
		pnNhanVien = new PnNhanVien(dataNhanVien);
		pnThongKe = new PnReport(dataHoaDon, dataNhanVien);
		pnAbout = new PnAbout();
		pnHelp = new PnHelp();
		pnBanHang = new PnThanhToan(dataSach.getData(), dataNhac.getData(), dataPhim.getData(), dataKhachHang, dataHoaDon, currentUser);

		// menu chinh
		menuMain = new JMenuBar();
		setJMenuBar(menuMain);

		// menu kho hàng
		menuKhoHang = new JMenu("Kho hàng  ");
		menuMain.add(menuKhoHang);
		menuSach = new JMenuItem("Kho sách");
		menuSach.setIcon(new ImageIcon(FrmMain.class.getResource("/img/iconKhoSach.png")));
		menuKhoHang.add(menuSach);
		menuNhac = new JMenuItem("Kho đĩa nhạc");
		menuNhac.setIcon(new ImageIcon(FrmMain.class.getResource("/img/iconNhac.png")));
		menuKhoHang.add(menuNhac);
		menuPhim = new JMenuItem("Kho đĩa phim");
		menuPhim.setIcon(new ImageIcon(FrmMain.class.getResource("/img/iconPhim.png")));
		menuKhoHang.add(menuPhim);

		// thêm các menu khác
		menuBanHang = new JMenu("Bán hàng  ");
		menuMain.add(menuBanHang);
		menuThongKe = new JMenu("Thống kê  ");
		menuMain.add(menuThongKe);

		menuHoaDon = new JMenuItem("Hóa đơn");
		menuHoaDon.setIcon(new ImageIcon(FrmMain.class.getResource("/img/iconBill.png")));
		menuThongKe.add(menuHoaDon);

		menuThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
		menuThongKeDoanhThu.setIcon(new ImageIcon(FrmMain.class.getResource("/img/iconChart.png")));

		menuKhachHang = new JMenu("Khách hàng  ");
		menuMain.add(menuKhachHang);
		menuNhanVien = new JMenu("Nhân viên  ");
		if (currentUser.isAdmin()) {
			menuThongKe.add(menuThongKeDoanhThu);
			menuMain.add(menuNhanVien);
		}
		menuHelp = new JMenu("Trợ giúp  ");
		menuMain.add(menuHelp);
		menuTaiKhoan = new JMenu("Tài khoản  ");
		menuMain.add(menuTaiKhoan);

		menDoiThongTin = new JMenuItem("Đổi thông tin");
		menDoiThongTin.setIcon(new ImageIcon(FrmMain.class.getResource("/img/iconChangeInfo.png")));
		menuTaiKhoan.add(menDoiThongTin);

		menuLogout = new JMenuItem("Đăng xuất");
		menuLogout.setIcon(new ImageIcon(FrmMain.class.getResource("/img/iconLogout.png")));
		menuTaiKhoan.add(menuLogout);

		mnAbout = new JMenu(" About");
		menuMain.add(mnAbout);
		addListener();
		pack();
	}

	/**
	 * bắt sự kiện
	 */
	private void addListener() {
		// khi cần thêm 1 khách hàng ngay trong panel bán hàng
		((PnThanhToan) pnBanHang).themKhEvent = new ThemKhachHangEvent() {
			@Override
			public void themKhachHang() {
				contentPane.removeAll();
				contentPane.add(pnKH, BorderLayout.CENTER);
				validate();
				repaint();
			}
		};
		// menu kho hàng tự drop down khi đưa chuột qua
		menuKhoHang.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				menuKhoHang.doClick();
			}
		});
		// mở panel nhân viên
		menuNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.removeAll();
				contentPane.add(pnNhanVien, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		// mở panel sách
		menuSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// thêm panel sách vào giao diên chương trình
				contentPane.removeAll();
				contentPane.add(pnSach, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		// mở panel kho nhạc
		menuNhac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// thêm panel đĩa nhạc vào giao diên chương trình
				contentPane.removeAll();
				contentPane.add(pnDiaNhac, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		// mở panel xem hóa đơn
		menuHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(pnHoaDon, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		// mở panel khách hàng
		menuKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// thêm panel khách hàng vào giao diên chương trình
				contentPane.removeAll();
				contentPane.add(pnKH, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		// mở panel đổi thông tin nhân viên hiện tại
		menDoiThongTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(pnDoiThongTin, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		// mở panel thanh toán
		menuBanHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.removeAll();
				contentPane.add(pnBanHang, BorderLayout.CENTER);
				validate();
				repaint();

			}
		});
		// mở panel đĩa phim
		menuPhim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(pnDiaPhim, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		// khi click mua 1 sản phẩm trong panel sách
		((PnSach) pnSach).buyListener = new BuyEvent() {
			@Override
			public void onBuyProduct(ChiTietHoaDon sp) {
				((PnThanhToan) pnBanHang).themSanPhamVaoGio(sp);
			}
		};
		// khi click mua 1 sản phẩm trong panel đĩa phim
		((PnDiaPhim) pnDiaPhim).buyListener = new BuyEvent() {
			@Override
			public void onBuyProduct(ChiTietHoaDon sp) {
				((PnThanhToan) pnBanHang).themSanPhamVaoGio(sp);
			}
		};
		// khi click mua 1 sản phẩm trong panel đĩa nhạc
		((PnDiaNhac) pnDiaNhac).buyListener = new BuyEvent() {
			@Override
			public void onBuyProduct(ChiTietHoaDon sp) {
				((PnThanhToan) pnBanHang).themSanPhamVaoGio(sp);

			}
		};
		// khi click menu đăng xuất
		menuLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logoutEvent != null) {
					logoutEvent.logout();
				}
			}
		});
		// khi click menu thống kê
		menuThongKeDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(pnThongKe, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		mnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.removeAll();
				contentPane.add(pnAbout, BorderLayout.CENTER);
				validate();
				repaint();
			}
		});
		menuHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					JPanel pn = (JPanel) contentPane.getComponents()[0];
					contentPane.removeAll();
					contentPane.add(pnHelp, BorderLayout.CENTER);
					((PnHelp) pnHelp).showPanelHelpFor(pn);
					validate();
					repaint();
				} catch (Exception e) {
					// TODO: handle exception
					contentPane.removeAll();
					contentPane.add(pnHelp, BorderLayout.CENTER);
					validate();
					repaint();

				}
			}
		});
	}
}
