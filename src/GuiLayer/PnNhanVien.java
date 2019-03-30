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

import com.toedter.calendar.JDateChooser;

import DataAccessLayer.DataNhanVien;
import DataAccessLayer.ModelTableNhanVien;
import DataObject.NhanVien;

/**
 * @author Thịnh
 *
 *         panel nhân viên
 */
public class PnNhanVien extends JPanel {
	// gui field
	private JTextField tfMaNhanVien, tfTenNhanVien, tfViTri, tfTienLuong, tfSoDT, tfDiaChi, tfSearch;
	private JTextField[] listTextField = new JTextField[6];
	private JTable table;
	private JLabel lbThongBao;
	private JScrollPane scrollPane;
	private JButton btnAdd, btnEdit, btnXoa;
	private JComboBox comboboxAdmin, comboboxGioiTinh, comboboxSearch;
	private JDateChooser dateChooser;

	// data field
	private DataNhanVien data;
	private NhanVien selectedEmployee;
	private ModelTableNhanVien modelTable;
	private String searchKeyword = "";
	private int selectedRow;
	private Vector<String> searchResult = new Vector<>();
	private ArrayList<Integer> indexOfSearchResults = new ArrayList<>();

	public PnNhanVien(DataNhanVien data) {
		// khởi tạo dữ liệu
		this.data = data;
		modelTable = new ModelTableNhanVien(data.getList());

		// tạo giao diện
		//setBorder(new EmptyBorder(3, 3, 3, 3));
		setPreferredSize(new Dimension(950, 650));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// label nhan vien
		JLabel lbHeader = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbHeader.setFont(new Font("Tahoma", Font.PLAIN, 27));
		add(lbHeader);

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

		// panel ma nv
		JPanel pnMaNhanVien = new JPanel();
		pnMaNhanVien.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnMaNhanVien);
		pnMaNhanVien.setLayout(new BorderLayout(0, 0));
		// label ma nv
		JLabel lbMaNhanVien = new JLabel("Mã nhân viên");
		lbMaNhanVien.setPreferredSize(new Dimension(100, 16));
		lbMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnMaNhanVien.add(lbMaNhanVien, BorderLayout.LINE_START);
		// text field ma nv
		tfMaNhanVien = new JTextField();
		pnMaNhanVien.add(tfMaNhanVien, BorderLayout.CENTER);

		// panel ten nv
		JPanel pnTenNhanVien = new JPanel();
		pnTenNhanVien.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnTenNhanVien);
		pnTenNhanVien.setLayout(new BorderLayout(0, 0));
		// label ten nv
		JLabel lnTenNhanVien = new JLabel("Tên nhân viên");
		lnTenNhanVien.setPreferredSize(new Dimension(100, 16));
		lnTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnTenNhanVien.add(lnTenNhanVien, BorderLayout.LINE_START);
		// text field ten nv
		tfTenNhanVien = new JTextField();
		pnTenNhanVien.add(tfTenNhanVien, BorderLayout.CENTER);

		//
		// panel vi tri
		JPanel pnViTri = new JPanel();
		pnViTri.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnViTri);
		pnViTri.setLayout(new BorderLayout(0, 0));
		// label vi tri
		JLabel lnViTri = new JLabel("Vị trí");
		lnViTri.setPreferredSize(new Dimension(100, 16));
		lnViTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnViTri.add(lnViTri, BorderLayout.LINE_START);
		// text field vi tri
		tfViTri = new JTextField();
		pnViTri.add(tfViTri, BorderLayout.CENTER);
		//

		// panel dia chi
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnDiaChi);
		pnDiaChi.setLayout(new BorderLayout(0, 0));
		// label dia chi
		JLabel lbDiaChi = new JLabel("Địa chỉ");
		lbDiaChi.setPreferredSize(new Dimension(100, 16));
		lbDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnDiaChi.add(lbDiaChi, BorderLayout.LINE_START);
		// text field dia chi
		tfDiaChi = new JTextField();
		pnDiaChi.add(tfDiaChi);

		// panel admin
		JPanel pnAdmin = new JPanel();
		pnAdmin.setBorder(new EmptyBorder(3, 10, 10, 20));
		pnLeft.add(pnAdmin);
		pnAdmin.setLayout(new BorderLayout(0, 0));
		// label admin
		JLabel lbAdmin = new JLabel("Quyền admin");
		lbAdmin.setPreferredSize(new Dimension(100, 16));
		lbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnAdmin.add(lbAdmin, BorderLayout.LINE_START);
		// combobox admin
		comboboxAdmin = new JComboBox();
		comboboxAdmin.setModel(new DefaultComboBoxModel(new String[] { "Có", "Không" }));
		pnAdmin.add(comboboxAdmin, BorderLayout.CENTER);

		// panel thông tin bên phải
		JPanel pnRight = new JPanel();
		pnThongTin.add(pnRight);
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		// pane gioi tinh
		JPanel pnGioiTinh = new JPanel();
		pnGioiTinh.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnGioiTinh);
		pnGioiTinh.setLayout(new BorderLayout(0, 0));
		// label gioi tinh
		JLabel lbGioiTinh = new JLabel("Giới tính");
		lbGioiTinh.setPreferredSize(new Dimension(100, 16));
		lbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnGioiTinh.add(lbGioiTinh, BorderLayout.LINE_START);
		// combobox gioi tinh
		comboboxGioiTinh = new JComboBox();
		comboboxGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		pnGioiTinh.add(comboboxGioiTinh, BorderLayout.CENTER);

		// panel ngay sinh
		JPanel pnNgaySinh = new JPanel();
		pnNgaySinh.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnNgaySinh);
		pnNgaySinh.setLayout(new BorderLayout(0, 0));
		// label ngay sinh
		JLabel lbNgaySinh = new JLabel("Ngày sinh");
		lbNgaySinh.setPreferredSize(new Dimension(100, 16));
		lbNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnNgaySinh.add(lbNgaySinh, BorderLayout.LINE_START);
		// date chooser ngay sinh
		dateChooser = new JDateChooser();
		pnNgaySinh.add(dateChooser, BorderLayout.CENTER);

		// panel so dt
		JPanel pnSoDT = new JPanel();
		pnSoDT.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnSoDT);
		pnSoDT.setLayout(new BorderLayout(0, 0));
		// label so dt
		JLabel lbSoDT = new JLabel("Số ĐT");
		lbSoDT.setPreferredSize(new Dimension(100, 16));
		lbSoDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnSoDT.add(lbSoDT, BorderLayout.LINE_START);
		// text field so dt
		tfSoDT = new JTextField();
		pnSoDT.add(tfSoDT, BorderLayout.CENTER);

		// panel tien luong
		JPanel pnLuong = new JPanel();
		pnRight.add(pnLuong);
		pnLuong.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnLuong.setLayout(new BorderLayout(0, 0));
		// label tien luong
		JLabel lbLuong = new JLabel("Tiền lương");
		lbLuong.setPreferredSize(new Dimension(100, 16));
		lbLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnLuong.add(lbLuong, BorderLayout.LINE_START);
		// text field tien luong
		tfTienLuong = new JTextField();
		pnLuong.add(tfTienLuong, BorderLayout.CENTER);

		listTextField[0] = tfMaNhanVien;
		listTextField[1] = tfTenNhanVien;
		listTextField[2] = tfViTri;
		listTextField[3] = tfDiaChi;
		listTextField[4] = tfSoDT;
		listTextField[5] = tfTienLuong;

		// panel null
		JPanel pnNull = new JPanel();
		pnRight.add(pnNull);
		pnNull.setBorder(new EmptyBorder(3, 10, 10, 10));
		pnNull.setLayout(new BorderLayout(0, 0));
		// label null
		JLabel lbNull = new JLabel("  ");
		lbNull.setPreferredSize(new Dimension(100, 16));
		lbNull.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnNull.add(lbNull, BorderLayout.LINE_START);

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

		// nút thêm
		btnAdd = new JButton("Thêm nhân viên");
		btnAdd.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnAddIcon.png")));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnAdd);

		// nút sửa
		btnEdit = new JButton("Sửa nhân viên");
		btnEdit.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnEditIcon.png")));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnEdit);

		// nút xóa
		btnXoa = new JButton("Xóa nhân viên");
		btnXoa.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnDeletetIcon.png")));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnXoa);

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
					selectedEmployee = data.getNhanVien(selectedRow);
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
		// check ID khi gõ trong tf mã nhan vien
		tfMaNhanVien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (data.checkByID(tfMaNhanVien.getText())) {
					lbThongBao.setText("Mã nhân viên đã tồn tại!");
					lbThongBao.setForeground(Color.RED);
					tfMaNhanVien.setForeground(Color.RED);
				} else {
					lbThongBao.setText("Mã nhân viên có thể dùng");
					lbThongBao.setForeground(Color.GREEN);
					tfMaNhanVien.setForeground(Color.BLACK);
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
					selectedEmployee = data.getNhanVien(selectedRow);
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

		// thêm nhan vien
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNhanVien();
			}
		});
		// sửa nhan vien
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaNhanVien();
			}
		});
		// xóa nhan vien
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaNhanVien();
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
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
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
		tfDiaChi.setText("");
		tfMaNhanVien.setText("");
		tfSoDT.setText("");
		tfTenNhanVien.setText("");
		tfTienLuong.setText("");
		tfViTri.setText("");
	}

	/**
	 * hiển thị tất cả nội dung lên textfield
	 */
	private void showToTextfield() {
		lbThongBao.setText(" ");
		tfMaNhanVien.setText(selectedEmployee.getID());
		tfTenNhanVien.setText(selectedEmployee.getHoTen());
		tfViTri.setText(selectedEmployee.getViTri());
		tfDiaChi.setText(selectedEmployee.getDiaChi());
		if (selectedEmployee.isAdmin()) {
			comboboxAdmin.setSelectedIndex(0);
		} else {
			comboboxAdmin.setSelectedIndex(1);
		}
		if (selectedEmployee.isGioiTinhNam()) {
			comboboxGioiTinh.setSelectedIndex(0);
		} else {
			comboboxGioiTinh.setSelectedIndex(1);
		}
		dateChooser.setDate(selectedEmployee.getNgaySinh());
		tfTienLuong.setText(selectedEmployee.getLuong() + "");
		tfSoDT.setText(selectedEmployee.getSoDienThoai() + "");
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
	 * thêm 1 nhan vien vào data
	 */
	private void themNhanVien() {
		boolean male, admin;
		if (comboboxGioiTinh.getSelectedIndex() == 0) {
			male = true;
		} else {
			male = false;
		}
		if (comboboxAdmin.getSelectedIndex() == 0) {
			admin = true;
		} else {
			admin = false;
		}
		NhanVien temp = data.them(tfMaNhanVien.getText(), tfTenNhanVien.getText(), tfViTri.getText(), tfDiaChi.getText(), admin, male, dateChooser.getDate(), tfSoDT.getText(), tfTienLuong.getText());
		if (temp != null) {
			modelTable.fireTableDataChanged();
			selectedEmployee = temp;
			selectedRow = data.getList().size() - 1;
			table.setRowSelectionInterval(table.convertRowIndexToView(selectedRow), table.convertRowIndexToView(selectedRow));
			scrollToSelectedRow();
		}
	}

	/**
	 * sửa 1 nhan vien
	 */
	private void suaNhanVien() {
		boolean male, admin;
		if (comboboxGioiTinh.getSelectedIndex() == 0) {
			male = true;
		} else {
			male = false;
		}
		if (comboboxAdmin.getSelectedIndex() == 0) {
			admin = true;
		} else {
			admin = false;
		}
		NhanVien temp = data.sua(selectedEmployee, tfMaNhanVien.getText(), tfTenNhanVien.getText(), tfViTri.getText(), tfDiaChi.getText(), admin, male, dateChooser.getDate(), tfSoDT.getText(),
				tfTienLuong.getText());
		if (temp != null) {
			modelTable.fireTableDataChanged();
			selectedEmployee = temp;
			table.setRowSelectionInterval(table.convertRowIndexToView(selectedRow), table.convertRowIndexToView(selectedRow));
			scrollToSelectedRow();
		}
	}

	/**
	 * xoa 1 nhan vien trong data
	 */
	private void xoaNhanVien() {
		boolean result = data.xoa(selectedEmployee);
		if (result) {
			selectedEmployee = null;
			clearAllTextfield();
			modelTable.fireTableDataChanged();
		}

	}
}
