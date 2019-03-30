package GuiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import DataAccessLayer.DataAccess;
import DataAccessLayer.DataKhachHang;
import DataAccessLayer.DocSo;
import DataAccessLayer.ModelTableChiTietHoaDon;
import DataObject.ChiTietHoaDon;
import DataObject.DiaNhac;
import DataObject.DiaPhim;
import DataObject.HoaDon;
import DataObject.KhachHang;
import DataObject.NhanVien;
import DataObject.Sach;
import DataObject.SanPham;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.UIManager;

/**
 * @author truong
 *
 *         thanh toán, mua hàng
 */
public class PnThanhToan extends JPanel {
	private MyTable table;
	private JTextField tfMaHoaDon, tfSearch, tfTenKhachHang, tfMaNhanVien, tfTenNhanVien, tfMaSanPham, tfTenSanPham, tfGiaSP, tfChietKhau, tfThanhTien;
	private JLabel lbThongBao;
	private JComboBox comboBox, comboboxSearch;
	private JSpinner spinner;
	private JButton btnThemSP, btnXoaSP, btnMua, btnThemKhachHang;
	private JDateChooser dateChooser;
	private JLabel lbTienBangChu, lbTienBangSo;

	private Vector<String> searchResult = new Vector<>();
	private ArrayList<Integer> indexOfSearchResults = new ArrayList<>();
	private ArrayList<ChiTietHoaDon> listSanPhamMua = new ArrayList<>();
	private ArrayList<ChiTietHoaDon> kqTimSanPham = new ArrayList<>();
	private String messenger, searchKeyword;
	private boolean dataIsValid;
	private DataAccess<Sach> dataSach;
	private DataAccess<DiaNhac> dataNhac;
	private DataAccess<DiaPhim> dataPhim;
	private DataKhachHang dataKhachHang;
	private DataAccess<HoaDon> dataHoaDon;
	private ChiTietHoaDon selectedProduct;
	private int selectedRow, soLuong = 0, tongTien;
	private ModelTableChiTietHoaDon modelTable;
	private NhanVien user;
	private KhachHang khach;
	private HoaDon hoaDon;
	public ThemKhachHangEvent themKhEvent;

	public PnThanhToan(DataAccess<Sach> sach, DataAccess<DiaNhac> nhac, DataAccess<DiaPhim> phim, DataKhachHang kh, DataAccess<HoaDon> hoaDon, NhanVien user) {
		this.dataSach = sach;
		this.dataNhac = nhac;
		this.dataPhim = phim;
		this.dataHoaDon = hoaDon;
		this.user = user;
		this.dataKhachHang = kh;

	//	setBorder(new EmptyBorder(3, 3, 3, 3));
		setPreferredSize(new Dimension(950, 650));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lbThanhToan = new JLabel("Thanh toán");
		lbThanhToan.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lbThanhToan);

		JPanel pnThongTinChung = new JPanel();
		pnThongTinChung.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin chung", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		add(pnThongTinChung);
		pnThongTinChung.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnLeft = new JPanel();
		pnLeft.setBorder(new EmptyBorder(0, 0, 0, 20));
		pnThongTinChung.add(pnLeft);
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

		JPanel pnMaHD = new JPanel();
		pnMaHD.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnMaHD);
		pnMaHD.setLayout(new BorderLayout(0, 0));

		JLabel lbMaHoaDon = new JLabel("Mã hóa đơn");
		lbMaHoaDon.setPreferredSize(new Dimension(95, 16));
		pnMaHD.add(lbMaHoaDon, BorderLayout.LINE_START);

		tfMaHoaDon = new JTextField();
		pnMaHD.add(tfMaHoaDon, BorderLayout.CENTER);
		tfMaHoaDon.setColumns(10);

		JPanel pnNgayMua = new JPanel();
		pnNgayMua.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnNgayMua);
		pnNgayMua.setLayout(new BorderLayout(0, 0));

		JLabel lbNgayMua = new JLabel("Ngày mua");
		lbNgayMua.setPreferredSize(new Dimension(95, 16));
		pnNgayMua.add(lbNgayMua, BorderLayout.WEST);

		dateChooser = new JDateChooser();
		pnNgayMua.add(dateChooser, BorderLayout.CENTER);

		JPanel pnMaNhanVien = new JPanel();
		pnMaNhanVien.setBorder(new EmptyBorder(3, 10, 10, 20));
		pnLeft.add(pnMaNhanVien);
		pnMaNhanVien.setLayout(new BorderLayout(0, 0));

		JLabel lbMaNhanVien = new JLabel("Mã nhân viên");
		lbMaNhanVien.setPreferredSize(new Dimension(95, 16));
		pnMaNhanVien.add(lbMaNhanVien, BorderLayout.WEST);

		tfMaNhanVien = new JTextField();
		tfMaNhanVien.setEditable(false);
		pnMaNhanVien.add(tfMaNhanVien, BorderLayout.CENTER);
		tfMaNhanVien.setColumns(10);

		JPanel pnRight = new JPanel();
		pnThongTinChung.add(pnRight);
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		JPanel pnMaKhachHang = new JPanel();
		pnMaKhachHang.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnMaKhachHang);
		pnMaKhachHang.setLayout(new BorderLayout(0, 0));

		JLabel lbMaKhachHang = new JLabel("Mã khách hàng");
		lbMaKhachHang.setPreferredSize(new Dimension(95, 16));
		pnMaKhachHang.add(lbMaKhachHang, BorderLayout.WEST);

		tfSearch = new JTextField();
		BorderLayout bl_tfSearch = new BorderLayout();
		tfSearch.setLayout(bl_tfSearch);
		comboboxSearch = new JComboBox() {
			public Dimension getPreferredSize() {
				return new Dimension(super.getPreferredSize().width, 0);
			}
		};
		comboboxSearch.setMaximumRowCount(15);
		tfSearch.add(comboboxSearch, BorderLayout.SOUTH);
		pnMaKhachHang.add(tfSearch);

		btnThemKhachHang = new JButton("Thêm");
		btnThemKhachHang.setIcon(new ImageIcon(PnThanhToan.class.getResource("/img/btnAddIconSmall.png")));
		pnMaKhachHang.add(btnThemKhachHang, BorderLayout.EAST);

		JPanel pnTenKhachHang = new JPanel();
		pnTenKhachHang.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnTenKhachHang);
		pnTenKhachHang.setLayout(new BorderLayout(0, 0));

		JLabel lbTenKhachHang = new JLabel("Tên khách hàng");
		lbTenKhachHang.setPreferredSize(new Dimension(95, 16));
		pnTenKhachHang.add(lbTenKhachHang, BorderLayout.LINE_START);

		tfTenKhachHang = new JTextField();
		tfTenKhachHang.setEditable(false);
		tfTenKhachHang.setColumns(10);
		pnTenKhachHang.add(tfTenKhachHang, BorderLayout.CENTER);

		JPanel pnTenNhanVien = new JPanel();
		pnTenNhanVien.setBorder(new EmptyBorder(3, 10, 10, 10));
		pnRight.add(pnTenNhanVien);
		pnTenNhanVien.setLayout(new BorderLayout(0, 0));

		JLabel lbTenNhanVien = new JLabel("Tên nhân viên");
		lbTenNhanVien.setPreferredSize(new Dimension(95, 16));
		pnTenNhanVien.add(lbTenNhanVien, BorderLayout.WEST);

		tfTenNhanVien = new JTextField();
		tfTenNhanVien.setEditable(false);
		pnTenNhanVien.add(tfTenNhanVien, BorderLayout.CENTER);
		tfTenNhanVien.setColumns(10);

		lbThongBao = new JLabel("  ");
		add(lbThongBao);

		JPanel pnThongTinChiTiet = new JPanel();
		pnThongTinChiTiet.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin chi ti\u1EBFt c\u00E1c m\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		add(pnThongTinChiTiet);
		pnThongTinChiTiet.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel pnWest = new JPanel();
		pnThongTinChiTiet.add(pnWest);
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));

		JPanel pnMaSanPham = new JPanel();
		pnMaSanPham.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnWest.add(pnMaSanPham);
		pnMaSanPham.setLayout(new BorderLayout(0, 0));

		JLabel lbMaSanPham = new JLabel("Mã sản phẩm");
		lbMaSanPham.setPreferredSize(new Dimension(90, 16));
		pnMaSanPham.add(lbMaSanPham, BorderLayout.WEST);

		tfMaSanPham = new JTextField();
		pnMaSanPham.add(tfMaSanPham, BorderLayout.CENTER);
		tfMaSanPham.setColumns(10);

		JPanel pnTenSanPham = new JPanel();
		pnTenSanPham.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnWest.add(pnTenSanPham);
		pnTenSanPham.setLayout(new BorderLayout(0, 0));

		JLabel lbTenSanPham = new JLabel("Tên sản phẩm");
		lbTenSanPham.setPreferredSize(new Dimension(90, 16));
		pnTenSanPham.add(lbTenSanPham, BorderLayout.WEST);

		tfTenSanPham = new JTextField();
		tfTenSanPham.setEditable(false);
		pnTenSanPham.add(tfTenSanPham, BorderLayout.CENTER);
		tfTenSanPham.setColumns(10);

		JPanel pnLoai = new JPanel();
		pnLoai.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnWest.add(pnLoai);
		pnLoai.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Loại");
		lblNewLabel.setPreferredSize(new Dimension(90, 16));
		pnLoai.add(lblNewLabel, BorderLayout.WEST);

		comboBox = new JComboBox(new DefaultComboBoxModel());
		pnLoai.add(comboBox, BorderLayout.CENTER);

		JPanel pnMid = new JPanel();
		pnThongTinChiTiet.add(pnMid);
		pnMid.setLayout(new BoxLayout(pnMid, BoxLayout.Y_AXIS));

		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnMid.add(pnSoLuong);
		pnSoLuong.setLayout(new BorderLayout(0, 0));

		JLabel lbSoLuong = new JLabel("Số lượng");
		lbSoLuong.setPreferredSize(new Dimension(90, 16));
		pnSoLuong.add(lbSoLuong, BorderLayout.WEST);

		spinner = new JSpinner();
		pnSoLuong.add(spinner, BorderLayout.CENTER);

		JPanel pnGiaSanPham = new JPanel();
		pnGiaSanPham.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnMid.add(pnGiaSanPham);
		pnGiaSanPham.setLayout(new BorderLayout(0, 0));

		JLabel lbGia = new JLabel("Giá sản phẩm");
		lbGia.setPreferredSize(new Dimension(90, 16));
		pnGiaSanPham.add(lbGia, BorderLayout.WEST);

		tfGiaSP = new JTextField();
		tfGiaSP.setEditable(false);
		pnGiaSanPham.add(tfGiaSP, BorderLayout.CENTER);
		tfGiaSP.setColumns(10);

		JPanel pnNull = new JPanel();
		pnMid.add(pnNull);

		JLabel lbNull = new JLabel(" ");
		pnNull.add(lbNull);

		JPanel pnEast = new JPanel();
		pnThongTinChiTiet.add(pnEast);
		pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));

		JPanel pnChietKhau = new JPanel();
		pnChietKhau.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnEast.add(pnChietKhau);
		pnChietKhau.setLayout(new BorderLayout(0, 0));

		JLabel lbChietKhau = new JLabel("Chiết khấu");
		lbChietKhau.setPreferredSize(new Dimension(90, 16));
		pnChietKhau.add(lbChietKhau, BorderLayout.WEST);

		tfChietKhau = new JTextField();
		tfChietKhau.setEditable(false);
		pnChietKhau.add(tfChietKhau, BorderLayout.CENTER);
		tfChietKhau.setColumns(10);

		JPanel pnThanhTien = new JPanel();
		pnThanhTien.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnEast.add(pnThanhTien);
		pnThanhTien.setLayout(new BorderLayout(0, 0));

		JLabel lbThanhTien = new JLabel("Thành tiền");
		lbThanhTien.setPreferredSize(new Dimension(90, 16));
		pnThanhTien.add(lbThanhTien, BorderLayout.WEST);

		tfThanhTien = new JTextField();
		tfThanhTien.setEditable(false);
		pnThanhTien.add(tfThanhTien, BorderLayout.CENTER);
		tfThanhTien.setColumns(10);

		JPanel pnNull2 = new JPanel();
		pnEast.add(pnNull2);

		JLabel lbNull2 = new JLabel(" ");
		pnNull2.add(lbNull2);

		JPanel pnTable = new JPanel();
		pnTable.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnTable);
		pnTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pnTable.add(scrollPane, BorderLayout.CENTER);

		addTable();
		scrollPane.setViewportView(table);

		dateChooser.setDateFormatString("dd/MM/yyyy HH:mm");
		dateChooser.setDate(new Date());

		JPanel pnTongTien = new JPanel();
		add(pnTongTien);
		pnTongTien.setLayout(new BoxLayout(pnTongTien, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		pnTongTien.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lbTongTien = new JLabel("Tổng tiền bằng chữ:");
		lbTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lbTongTien);

		lbTienBangChu = new JLabel(" ");
		lbTienBangChu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTienBangChu.setForeground(Color.RED);
		panel.add(lbTienBangChu);

		JPanel pnSoTien = new JPanel();
		pnTongTien.add(pnSoTien);
		pnSoTien.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lbSoTien = new JLabel("Tổng tiền bằng số:");
		lbSoTien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnSoTien.add(lbSoTien);

		lbTienBangSo = new JLabel(" ");
		lbTienBangSo.setForeground(Color.RED);
		lbTienBangSo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnSoTien.add(lbTienBangSo);

		JPanel pnThemXoa = new JPanel();
		add(pnThemXoa);
		pnThemXoa.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnControl = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnControl.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		btnThemSP = new JButton("Thêm sản phẩm");
		btnThemSP.setIcon(new ImageIcon(PnThanhToan.class.getResource("/img/btnAddIcon.png")));
		pnControl.add(btnThemSP);

		btnXoaSP = new JButton("Xóa khỏi danh sách");
		btnXoaSP.setIcon(new ImageIcon(PnThanhToan.class.getResource("/img/btnDeletetIcon.png")));
		pnControl.add(btnXoaSP);
		pnThemXoa.add(pnControl);

		JPanel pnMua = new JPanel();
		pnThemXoa.add(pnMua);
		pnMua.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnMua = new JButton("Mua");
		btnMua.setIcon(new ImageIcon(PnThanhToan.class.getResource("/img/btnBuyIcon.png")));
		pnMua.add(btnMua);
		tfMaNhanVien.setText(user.getID());
		tfTenNhanVien.setText(user.getHoTen());

		addListener();

	}

	/**
	 * tạo bảng
	 */
	public void addTable() {
		table = new MyTable();
		modelTable = new ModelTableChiTietHoaDon(listSanPhamMua);
		table.setModel(modelTable);
	}

	/**
	 * bắt sự kiện
	 */
	private void addListener() {
		// them khach hang
		btnThemKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (themKhEvent != null) {
					themKhEvent.themKhachHang();
				}
			}
		});
		// tìm kiếm 1 khách hàng
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				timKiemKhachHang();
			}
		});
		// khi click vào kết quả tìm kiếm khách hàng
		comboboxSearch.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					khach = dataKhachHang.getKhachHang(indexOfSearchResults.get(comboboxSearch.getSelectedIndex()));
					tfSearch.setText(khach.getID());
					tfTenKhachHang.setText(khach.getHoTen());
				} catch (Exception e2) {
				}
			}
		});
		// khi nhập mã hóa đơn
		tfMaHoaDon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (dataHoaDon.checkByID(tfMaHoaDon.getText())) {
					lbThongBao.setText("Mã hóa đơn đã tồn tại!");
					lbThongBao.setForeground(Color.RED);
					tfMaHoaDon.setForeground(Color.RED);
				} else {
					lbThongBao.setText("Mã hóa đơn có thể dùng");
					lbThongBao.setForeground(Color.GREEN);
					tfMaHoaDon.setForeground(Color.BLACK);
				}
			}
		});
		// khi nhập mã sản phẩm
		tfMaSanPham.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						selectedProduct = null;
						comboBox.removeAllItems();
						kqTimSanPham.clear();
						for (Sach sp : dataSach.getList()) {
							if (tfMaSanPham.getText().equals(sp.getID()) && sp.getSoLuong() > 0) {
								kqTimSanPham.add(new ChiTietHoaDon(sp, "Sách", 1));
								comboBox.addItem("Sách");
							}
						}
						for (DiaNhac sp : dataNhac.getList()) {
							if (tfMaSanPham.getText().equals(sp.getID()) && sp.getSoLuong() > 0) {
								kqTimSanPham.add(new ChiTietHoaDon(sp, "Đĩa nhạc", 1));
								comboBox.addItem("Đĩa nhạc");
							}
						}
						for (DiaPhim sp : dataPhim.getList()) {
							if (tfMaSanPham.getText().equals(sp.getID()) && sp.getSoLuong() > 0) {
								kqTimSanPham.add(new ChiTietHoaDon(sp, "Đĩa phim", 1));
								comboBox.addItem("Đĩa phim");
							}
						}
						int size = kqTimSanPham.size();
						if (size == 0) {
							lbThongBao.setText("Chưa tìm thấy sản phẩm");
							lbThongBao.setForeground(Color.red);
						} else {
							lbThongBao.setForeground(Color.BLUE);
							lbThongBao.setText("Tìm thấy " + size + " sản phẩm");
							selectedProduct = kqTimSanPham.get(0);
							comboBox.setSelectedIndex(0);
						}
					}
				});
			}
		});
		// bắt sự kiện click vào 1 dòng của bảng
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				try {
					selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
					selectedProduct = listSanPhamMua.get(selectedRow);
					comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Sách", "Đĩa nhạc", "Phim" }));
					tfMaSanPham.setText(selectedProduct.getMaSanPham());
					comboBox.setSelectedItem(selectedProduct.getLoaiSanPham());
					showToTextfield();
					tinhTien();
				} catch (Exception e) {
				}
			}
		});
		// khi chọn 1 sản phẩm trong combobox
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					selectedProduct = kqTimSanPham.get(comboBox.getSelectedIndex());
					showToTextfield();
				} catch (Exception e2) {
				}
			}
		});
		// khi click button thêm sp vào giỏ
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kiemTraHopLe();
				if (dataIsValid) {
					selectedProduct.setSoLuong(soLuong);
					themSanPhamVaoGio(selectedProduct);
				} else {
					JOptionPane.showMessageDialog(null, messenger, "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				tinhTien();
			}
		});
		// khi click button xóa 1 sản phẩm
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedProduct != null) {
					listSanPhamMua.remove(selectedProduct);
					selectedProduct = null;
					modelTable.fireTableDataChanged();
					clearAllTextfield();
				} else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm nào để xóa", "Lỗi", JOptionPane.YES_OPTION);
				}
				tinhTien();
			}
		});
		// khi click nút mua sản phẩm
		btnMua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kiemTraHopLeHoaDon();
				modelTable.fireTableDataChanged();
				if (dataIsValid) {
					int result = JOptionPane.showConfirmDialog(null, "Bạn muốn mua các sản phẩm trên?", "Xác nhận", JOptionPane.YES_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						mua();
					}
				} else {
					JOptionPane.showMessageDialog(null, messenger, "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				tinhTien();
			}
		});
	}

	/**
	 * hiển thị tất cả nội dung lên textfield
	 */
	private void showToTextfield() {
		SanPham temp = selectedProduct.getSanPham();
		tfTenSanPham.setText(temp.getTenSP());
		tfGiaSP.setText(temp.getGiaBan() + "");
		tfChietKhau.setText(temp.getChietKhau() + "");
		tfThanhTien.setText(selectedProduct.getThanhTien() + "");
		spinner.setValue(selectedProduct.getSoLuong());
	}

	/**
	 * xóa hết textfield
	 */
	private void clearAllTextfield() {
		tfMaSanPham.setText("");
		tfTenSanPham.setText("");
		tfGiaSP.setText("");
		tfChietKhau.setText("");
		tfThanhTien.setText("");
		spinner.setValue(0);
		comboBox.removeAllItems();
	}

	private void mua() {
		for (ChiTietHoaDon sp : listSanPhamMua) {
			int soLuong = sp.getSanPham().getSoLuong();
			sp.getSanPham().setSoLuong(soLuong - sp.getSoLuong());
		}
		hoaDon = new HoaDon(tfMaHoaDon.getText(), user, khach, dateChooser.getDate(), listSanPhamMua);
		try {
			dataHoaDon.getList().add(hoaDon.clone());
		} catch (CloneNotSupportedException e) {
			System.out.println("ghi hóa đơn lỗi");
		}
		dataHoaDon.writeFile();
		dataSach.writeFile();
		dataPhim.writeFile();
		dataNhac.writeFile();
		listSanPhamMua.clear();
		selectedProduct = null;
		clearAllTextfield();
		modelTable.fireTableDataChanged();
	}

	/**
	 * thêm 1 sản phẩm vào giỏ hàng
	 */
	public void themSanPhamVaoGio(ChiTietHoaDon sp) {
		if (sp.getSoLuong() <= sp.getSanPham().getSoLuong()) {
			for (ChiTietHoaDon spTrongGio : listSanPhamMua) {
				if (spTrongGio.getMaSanPham().equals(sp.getMaSanPham()) && spTrongGio.getLoaiSanPham().equals(sp.getLoaiSanPham())) {
					spTrongGio.setSoLuong(sp.getSoLuong());
					clearAllTextfield();
					selectedProduct = null;
					modelTable.fireTableDataChanged();
					tinhTien();
					return;
				}
			}
			listSanPhamMua.add(sp);
			selectedProduct = null;
			modelTable.fireTableDataChanged();
			clearAllTextfield();
			tinhTien();
		} else {
			JOptionPane.showMessageDialog(null, "Số sản phẩm bạn mua phải <= " + sp.getSanPham().getSoLuong(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			sp.setSoLuong(sp.getSanPham().getSoLuong());
		}
	}

	/**
	 * tìm kiếm, gợi ý
	 */
	private void timKiemKhachHang() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				searchKeyword = tfSearch.getText();
				if (searchKeyword.length() == 0) {
					comboboxSearch.hidePopup();
					comboboxSearch.setModel(new DefaultComboBoxModel());
					comboboxSearch.setSelectedItem(null);
				} else {
					dataKhachHang.timKiemKhachHang(searchResult, indexOfSearchResults, searchKeyword);
					if (searchResult.size() == 0) {
						comboboxSearch.hidePopup();
					} else {
						comboboxSearch.setModel(new DefaultComboBoxModel<>(searchResult));
						comboboxSearch.showPopup();
						comboboxSearch.setSelectedItem(null);
					}
				}
			}
		});
	}

	/**
	 * tính tổng số tiền hóa đơn
	 */
	private void tinhTien() {
		tongTien = 0;
		for (ChiTietHoaDon sp : listSanPhamMua) {
			double temp = sp.getSanPham().getGiaBan() * sp.getSoLuong();
			temp = temp - temp * sp.getSanPham().getChietKhau() / 100.0;
			tongTien += (int) temp;
		}
		lbTienBangSo.setText(tongTien + " đ");
		lbTienBangChu.setText(DocSo.docSo(tongTien + ""));
	}

	/**
	 * kiểm tra hợp lệ dữ liệu
	 */
	private void kiemTraHopLe() {
		messenger = "";
		dataIsValid = true;
		if (selectedProduct == null) {
			messenger = "Bạn chưa chọn sản phẩm";
			dataIsValid = false;
			return;
		}
		try {
			soLuong = Integer.parseInt(spinner.getValue().toString());
			if (soLuong < 1) {
				throw new Exception();
			}
		} catch (Exception e2) {
			messenger = "Số lượng không hợp lệ";
			dataIsValid = false;
			return;
		}
	}

	/**
	 * kiểm tra hợp lệ dữ liệu
	 */
	private void kiemTraHopLeHoaDon() {
		messenger = "";
		dataIsValid = true;
		if (tfMaHoaDon.getText().equals("")) {
			messenger = "Mã hóa đơn bị thiếu!";
			dataIsValid = false;
			return;
		}
		if (dataHoaDon.checkByID(tfMaHoaDon.getText())) {
			messenger = "Mã hóa đơn đã tồn tại";
			dataIsValid = false;
			return;
		}
		if (khach == null) {
			messenger = "Bạn chưa chọn khách hàng";
			dataIsValid = false;
			return;
		} else {
			tfTenKhachHang.setText(khach.getHoTen());
			tfSearch.setText(khach.getID());
		}
		if (listSanPhamMua.isEmpty()) {
			messenger = "Bạn chưa mua sản phẩm nào!";
			dataIsValid = false;
			return;
		}
		for (ChiTietHoaDon sp : listSanPhamMua) {
			if (sp.getSoLuong() < 1 || sp.getSoLuong() > sp.getSanPham().getSoLuong()) {
				messenger += "Số lượng của " + sp.getMaSanPham() + " phải >0 và <=" + sp.getSanPham().getSoLuong() + " \n ";
				dataIsValid = false;
			}
		}
		if (!dataIsValid)
			return;
		for (ChiTietHoaDon sp : listSanPhamMua) {
			if (!dataSach.checkByID(sp.getMaSanPham()) && !dataNhac.checkByID(sp.getMaSanPham()) && !dataPhim.checkByID(sp.getMaSanPham())) {
				dataIsValid = false;
				messenger = "Kho hàng đã thay đổi, sản phẩm " + sp.getMaSanPham() + " không còn";
				return;
			}
		}
	}
}
