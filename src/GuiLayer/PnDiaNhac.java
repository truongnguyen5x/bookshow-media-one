package GuiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DataAccessLayer.DataDiaNhac;
import DataAccessLayer.ModelTableDiaNhac;
import DataObject.ChiTietHoaDon;
import DataObject.DiaNhac;
import DataObject.NhanVien;

/**
 * @author thịnh
 *
 *         panel kho đĩa phim
 */
public class PnDiaNhac extends JPanel {
	// gui field
	private JTextField tfMaSP, tfTenSP, tfCaSi, tfNhaSX, tfTheLoai, tfGianHang, tfSoLuong, tfGiaBan, tfGiaMua, tfChietKhau, tfSearch;
	private JTextField[] listTextField = new JTextField[10];
	private JTable table;
	private JLabel lbThongBao;
	private JScrollPane scrollPane;
	private JComboBox comboboxSearch;
	private JButton btnAdd, btnEdit, btnXoa, btnMua;

	// data field
	private DataDiaNhac data;
	private DiaNhac selectedProduct;
	private ModelTableDiaNhac modelTable;
	private String searchKeyword = "";
	private int selectedRow;
	private Vector<String> searchResult = new Vector<>();
	private ArrayList<Integer> indexOfSearchResults = new ArrayList<>();

	// state field
	public static boolean currentUserIsAdmin = true;

	// sự kiện
	public BuyEvent buyListener;

	public PnDiaNhac(NhanVien user, DataDiaNhac data) {
		// khởi tạo dữ liệu
		this.data = data;
		currentUserIsAdmin = user.isAdmin();
		modelTable = new ModelTableDiaNhac(data.getList());

		// tạo giao diện
		//setBorder(new EmptyBorder(1, 1, 1, 1));
		setPreferredSize(new Dimension(950, 650));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// label kho dia nhac
		JLabel lbKhoSach = new JLabel("KHO ĐĨA NHẠC");
		lbKhoSach.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbKhoSach.setFont(new Font("Tahoma", Font.PLAIN, 27));
		add(lbKhoSach);

		// -------panel tim kiem--------
		JPanel pnSearch = new JPanel();
		add(pnSearch);

		// textfield tìm kiem
		tfSearch = new JTextField();
		tfSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSearch.setColumns(30);
		tfSearch.setLayout(new BorderLayout());
		comboboxSearch = new JComboBox() {
			public Dimension getPreferredSize() {
				return new Dimension(super.getPreferredSize().width, 0);
			}
		};
		comboboxSearch.setMaximumRowCount(15);
		comboboxSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSearch.add(comboboxSearch, BorderLayout.SOUTH);
		pnSearch.add(tfSearch);

		// label tìm kiếm
		JLabel lbSearch = new JLabel("");
		lbSearch.setIcon(new ImageIcon(PnSach.class.getResource("/img/iconSearch.png")));
		lbSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSearch.add(lbSearch);

		// ---------panel thong tin-------
		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		add(pnThongTin);
		pnThongTin.setLayout(new GridLayout(0, 2, 0, 0));

		// panel thông tin bên trái
		JPanel pnLeft = new JPanel();
		pnThongTin.add(pnLeft);
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

		// panel ma dia nhac
		JPanel pnMaSP = new JPanel();
		pnMaSP.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnMaSP);
		pnMaSP.setLayout(new BorderLayout(0, 0));
		// label ma dia nhac
		JLabel lbMaSP = new JLabel("Mã đĩa nhạc");
		lbMaSP.setPreferredSize(new Dimension(100, 16));
		lbMaSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnMaSP.add(lbMaSP, BorderLayout.LINE_START);
		// text field ma dia nhac
		tfMaSP = new JTextField();
		pnMaSP.add(tfMaSP, BorderLayout.CENTER);

		// panel ten dia nhac
		JPanel pnTenSP = new JPanel();
		pnTenSP.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnTenSP);
		pnTenSP.setLayout(new BorderLayout(0, 0));
		// label ten dia nhac
		JLabel lbTenSP = new JLabel("Tên đĩa nhạc");
		lbTenSP.setPreferredSize(new Dimension(100, 16));
		lbTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnTenSP.add(lbTenSP, BorderLayout.LINE_START);
		// text field ten dia nhac
		tfTenSP = new JTextField();
		pnTenSP.add(tfTenSP, BorderLayout.CENTER);

		//
		// panel ca si
		JPanel pnCaSi = new JPanel();
		pnCaSi.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnCaSi);
		pnCaSi.setLayout(new BorderLayout(0, 0));
		// label tac gia
		JLabel lnCaSi = new JLabel("Ca sĩ");
		lnCaSi.setPreferredSize(new Dimension(100, 16));
		lnCaSi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnCaSi.add(lnCaSi, BorderLayout.LINE_START);
		// text field ten tac gia
		tfCaSi = new JTextField();
		pnCaSi.add(tfCaSi, BorderLayout.CENTER);
		//

		// panel gia ban
		JPanel pnGiaBan = new JPanel();
		pnGiaBan.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnGiaBan);
		pnGiaBan.setLayout(new BorderLayout(0, 0));
		// label gia ban
		JLabel lbGiaBan = new JLabel("Giá bán (đ)");
		lbGiaBan.setPreferredSize(new Dimension(100, 16));
		lbGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnGiaBan.add(lbGiaBan, BorderLayout.LINE_START);
		// text field gia ban
		tfGiaBan = new JTextField();
		pnGiaBan.add(tfGiaBan);

		// panel chiet khau
		JPanel pnChietKhau = new JPanel();
		pnChietKhau.setBorder(new EmptyBorder(3, 10, 10, 20));
		pnLeft.add(pnChietKhau);
		pnChietKhau.setLayout(new BorderLayout(0, 0));
		// label chiet khau
		JLabel lbChietKhau = new JLabel("Chiết khấu(%)");
		lbChietKhau.setPreferredSize(new Dimension(100, 16));
		lbChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnChietKhau.add(lbChietKhau, BorderLayout.LINE_START);
		// tf chiet khau
		tfChietKhau = new JTextField();
		pnChietKhau.add(tfChietKhau, BorderLayout.CENTER);

		// panel thông tin bên phải
		JPanel pnRight = new JPanel();
		pnThongTin.add(pnRight);
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		// pane the loai
		JPanel pnTheLoai = new JPanel();
		pnTheLoai.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnTheLoai);
		pnTheLoai.setLayout(new BorderLayout(0, 0));
		// label the loai
		JLabel lbTheLoai = new JLabel("Thể loại");
		lbTheLoai.setPreferredSize(new Dimension(100, 16));
		lbTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnTheLoai.add(lbTheLoai, BorderLayout.LINE_START);
		// text field the loai tfTheLoai = new JTextField();
		tfTheLoai = new JTextField();
		pnTheLoai.add(tfTheLoai, BorderLayout.CENTER);

		// panel so luong
		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnSoLuong);
		pnSoLuong.setLayout(new BorderLayout(0, 0));
		// label so luong
		JLabel lbSoLuong = new JLabel("Số lượng");
		lbSoLuong.setPreferredSize(new Dimension(100, 16));
		lbSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnSoLuong.add(lbSoLuong, BorderLayout.LINE_START);
		// text field so luong
		tfSoLuong = new JTextField();
		pnSoLuong.add(tfSoLuong, BorderLayout.CENTER);

		// panel gian hàng
		JPanel pnGianHang = new JPanel();
		pnGianHang.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnGianHang);
		pnGianHang.setLayout(new BorderLayout(0, 0));
		// label gia sach
		JLabel lbGianHang = new JLabel("Gian hàng");
		lbGianHang.setPreferredSize(new Dimension(100, 16));
		lbGianHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnGianHang.add(lbGianHang, BorderLayout.LINE_START);
		// text field gia sach
		tfGianHang = new JTextField();
		pnGianHang.add(tfGianHang, BorderLayout.CENTER);

		//
		// panel nha sx
		JPanel pnNhaSX = new JPanel();
		pnRight.add(pnNhaSX);
		pnNhaSX.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnNhaSX.setLayout(new BorderLayout(0, 0));
		// label nxb
		JLabel lnNhaSX = new JLabel("Nhà sản xuất");
		lnNhaSX.setPreferredSize(new Dimension(100, 16));
		lnNhaSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnNhaSX.add(lnNhaSX, BorderLayout.LINE_START);
		// text field nxb
		tfNhaSX = new JTextField();
		pnNhaSX.add(tfNhaSX, BorderLayout.CENTER);
		//

		// panel gia mua
		JPanel pnGiaMua = new JPanel();
		pnRight.add(pnGiaMua);
		pnGiaMua.setBorder(new EmptyBorder(3, 10, 10, 10));
		pnGiaMua.setLayout(new BorderLayout(0, 0));
		// label gia mua
		JLabel lbGiaMua = new JLabel("Giá mua (đ)");
		lbGiaMua.setPreferredSize(new Dimension(100, 16));
		lbGiaMua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnGiaMua.add(lbGiaMua, BorderLayout.LINE_START);
		// text field gia mua
		tfGiaMua = new JTextField();
		pnGiaMua.add(tfGiaMua, BorderLayout.CENTER);

		// tạo 1 list text field
		listTextField[0] = tfMaSP;
		listTextField[1] = tfTenSP;
		listTextField[2] = tfCaSi;
		listTextField[3] = tfGiaBan;
		listTextField[4] = tfChietKhau;
		listTextField[5] = tfTheLoai;
		listTextField[6] = tfSoLuong;
		listTextField[7] = tfGianHang;
		listTextField[8] = tfNhaSX;
		listTextField[9] = tfGiaMua;

		// -------panel thong bao-------------
		JPanel pnNoti = new JPanel();
		add(pnNoti);
		// lable thong bao
		lbThongBao = new JLabel(" ");
		lbThongBao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbThongBao.setForeground(Color.RED);
		pnNoti.add(lbThongBao);

		// -----panel table--------------
		JPanel pnTable = new JPanel();
		pnTable.setBorder(new EmptyBorder(3, 3, 3, 3));
		add(pnTable);
		pnTable.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		pnTable.add(scrollPane, BorderLayout.CENTER);

		// -----------table--------------
		setupTable();
		scrollPane.setViewportView(table);

		// ---------panel control--------------
		JPanel pnControl = new JPanel();
		add(pnControl);
		pnControl.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pnButton = new JPanel();
		pnControl.add(pnButton);
		pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		// nếu người dùng là admin mới có quyền thêm, xóa, sửa
		if (currentUserIsAdmin) {

			// nút thêm
			btnAdd = new JButton("Thêm đĩa nhạc");
			btnAdd.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnAddIcon.png")));
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnButton.add(btnAdd);

			// nút sửa
			btnEdit = new JButton("Sửa đĩa nhạc");
			btnEdit.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnEditIcon.png")));
			btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnButton.add(btnEdit);

			// nút xóa
			btnXoa = new JButton("Xóa đĩa nhạc");
			btnXoa.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnDeletetIcon.png")));
			btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			pnButton.add(btnXoa);
		}
		btnMua = new JButton("Mua");
		btnMua.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnBuyIcon.png")));
		btnMua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnMua);
		addListener();
	}

	/**
	 * thêm các sự kiện cho giao diện
	 */
	private void addListener() {
		// khi click vào kết quả tìm kiếm
		comboboxSearch.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					selectedRow = indexOfSearchResults.get(comboboxSearch.getSelectedIndex());
					selectedProduct = data.getSanPham(selectedRow);
					table.setRowSelectionInterval(table.convertRowIndexToView(selectedRow), table.convertRowIndexToView(selectedRow));
					scrollToSelectedRow();
					showToTextfield();
				} catch (Exception e2) {
				}
			}
		});
		// gõ phím trong khi tìm kiếm
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				search();
			}
		});
		// click chuột vào thanh tìm kiếm
		tfSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				search();
			}
		});
		// check ID khi gõ trong tf mã đĩa nhạc
		tfMaSP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (data.checkByID(tfMaSP.getText())) {
					lbThongBao.setText("Mã đĩa nhạc đã tồn tại!");
					lbThongBao.setForeground(Color.RED);
					tfMaSP.setForeground(Color.RED);
				} else {
					lbThongBao.setText("Mã đĩa nhạc có thể dùng");
					lbThongBao.setForeground(Color.GREEN);
					tfMaSP.setForeground(Color.BLACK);
				}
			}
		});
		// bôi đen các textfield khi được tab đến
		for (int i = 0; i < listTextField.length; i++) {
			listTextField[i].addFocusListener(new FocusAdapter() {
				// khi được focus
				@Override
				public void focusGained(FocusEvent e) {
					JTextField tf = (JTextField) e.getSource();
					tf.selectAll();
				}
			});
		}
		// bắt sự kiện click vào 1 dòng của bảng
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				try {
					selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
					selectedProduct = data.getSanPham(selectedRow);
					showToTextfield();
				} catch (Exception e) {
				}
			}
		});
		// bắt sự kiện click vào header của bảng =>sắp xếp
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollToSelectedRow();
			}
		});
		if (currentUserIsAdmin) {
			// thêm đĩa nhạc
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					themSanPham();
				}
			});
			// sửa đĩa nhạc
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					suaSanPham();
				}
			});
			// xóa đĩa nhạc
			btnXoa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					xoaSanPham();
				}
			});
		}
		// mua 1 đĩa nhạc
		btnMua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buyListener != null && selectedProduct!=null) {
					buyListener.onBuyProduct(new ChiTietHoaDon(selectedProduct, "Đĩa nhạc", 1));
					JOptionPane.showMessageDialog(null, "đĩa nhạc bạn chọn đã được thêm vào giỏ");
				} else {
					JOptionPane.showMessageDialog(null, "bạn chưa chọn sản phẩm nào");
				}
			}
		});
	}

	/**
	 * khởi tạo bảng
	 */
	private void setupTable() {
		table = new MyTable();
		table.setModel(modelTable);
		// độ rộng của cột trong bảng
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(90);
	}

	public static boolean isCurrentUserIsAdmin() {
		return currentUserIsAdmin;
	}

	/**
	 * tìm kiếm, gợi ý
	 */
	private void search() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				searchKeyword = tfSearch.getText();
				if (searchKeyword.length() == 0) {
					comboboxSearch.hidePopup();
					comboboxSearch.setModel(new DefaultComboBoxModel());
					comboboxSearch.setSelectedItem(null);
				} else {
					data.timKiemSanPham(searchResult, indexOfSearchResults, searchKeyword);
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
	 * xóa hết textfield
	 */
	private void clearAllTextfield() {
		for (int i = 0; i < listTextField.length; i++) {
			listTextField[i].setText("");
		}
	}

	/**
	 * hiển thị tất cả nội dung lên textfield
	 */
	private void showToTextfield() {
		lbThongBao.setText(" ");
		for (int i = 0; i < listTextField.length; i++) {
			listTextField[i].setText(modelTable.getValueAt(selectedRow, i).toString());
			listTextField[i].setCaretPosition(0);
		}
	}

	/**
	 * cuộn bảng tới vị trí được bôi đen
	 */
	private void scrollToSelectedRow() {
		JViewport viewport = (JViewport) table.getParent();
		Rectangle rect = table.getCellRect(table.getSelectedRow(), table.getSelectedColumn(), true);
		table.scrollRectToVisible(rect);
	}

	/**
	 * thêm 1 đĩa nhạc vào data
	 */
	private void themSanPham() {
		DiaNhac temp = data.them(tfMaSP.getText(), tfTenSP.getText(), tfTheLoai.getText() + "", tfGianHang.getText(), tfSoLuong.getText(), tfGiaMua.getText(), tfGiaBan.getText(), tfChietKhau.getText(),
				tfNhaSX.getText() + "", tfCaSi.getText());
		if (temp != null) {
			modelTable.fireTableDataChanged();
			selectedProduct = temp;
			selectedRow = data.getList().size() - 1;
			table.setRowSelectionInterval(table.convertRowIndexToView(selectedRow), table.convertRowIndexToView(selectedRow));
			scrollToSelectedRow();
		}
	}

	/**
	 * sửa 1 đĩa nhạc
	 */
	private void suaSanPham() {
		DiaNhac temp = data.sua(selectedProduct, tfMaSP.getText(), tfTenSP.getText(), tfTheLoai.getText() + "", tfGianHang.getText(), tfSoLuong.getText(), tfGiaMua.getText(), tfGiaBan.getText(),
				tfChietKhau.getText(), tfNhaSX.getText() + "", tfCaSi.getText());
		if (temp != null) {
			modelTable.fireTableDataChanged();
			selectedProduct = temp;
			table.setRowSelectionInterval(table.convertRowIndexToView(selectedRow), table.convertRowIndexToView(selectedRow));
			scrollToSelectedRow();
		}
	}

	/**
	 * xoa 1 dia nhac trong data
	 */
	private void xoaSanPham() {
		boolean result = data.xoa(selectedProduct);
		if (result) {
			selectedProduct = null;
			clearAllTextfield();
			modelTable.fireTableDataChanged();
		}

	}
}
