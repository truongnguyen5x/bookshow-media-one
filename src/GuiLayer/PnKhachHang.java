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

import DataAccessLayer.DataKhachHang;
import DataAccessLayer.ModelTableKhachHang;
import DataObject.KhachHang;

/**
 * @author Vũ
 *
 *         panel khách hàng
 */
public class PnKhachHang extends JPanel {
	// gui field
	private JTextField tfMaKhachHang, tfTenKhachHang, tfDiaChi, tfSoDienThoai, tfSearch;
	private JTextField[] listTextField = new JTextField[4];
	private JTable table;
	private JLabel lbThongBao;
	private JScrollPane scrollPane;
	private JComboBox comboboxSearch;
	private JButton btnAdd, btnEdit, btnXoa;

	// data field
	private DataKhachHang data;
	private KhachHang selectedKhachHang;
	private ModelTableKhachHang modelTable;
	private String searchKeyword = "";
	private int selectedRow;
	private Vector<String> searchResult = new Vector<>();
	private ArrayList<Integer> indexOfSearchResults = new ArrayList<>();

	// sự kiện
	public BuyEvent buyListener;

	public PnKhachHang(DataKhachHang data) {
		// khởi tạo dữ liệu
		this.data = data;
		modelTable = new ModelTableKhachHang(data.getList());

		// tạo giao diện
	//	setBorder(new EmptyBorder(3, 3, 3, 3));
		setPreferredSize(new Dimension(950, 650));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// label khách hang
		JLabel lbHeader = new JLabel("QUẢN LÝ KHÁCH HÀNG");
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

		// panel ma kh
		JPanel pnMaKhachHang = new JPanel();
		pnMaKhachHang.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnMaKhachHang);
		pnMaKhachHang.setLayout(new BorderLayout(0, 0));
		// label ma khach hang
		JLabel lnMaKhachHang = new JLabel("Mã khách hàng");
		lnMaKhachHang.setPreferredSize(new Dimension(100, 16));
		lnMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnMaKhachHang.add(lnMaKhachHang, BorderLayout.LINE_START);
		// text field ma san pham
		tfMaKhachHang = new JTextField();
		pnMaKhachHang.add(tfMaKhachHang, BorderLayout.CENTER);

		// panel ten kh
		JPanel pnTenKhachHang = new JPanel();
		pnTenKhachHang.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnTenKhachHang);
		pnTenKhachHang.setLayout(new BorderLayout(0, 0));
		// label ten kh
		JLabel lbTenKhachHang = new JLabel("Tên khách hàng");
		lbTenKhachHang.setPreferredSize(new Dimension(100, 16));
		lbTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnTenKhachHang.add(lbTenKhachHang, BorderLayout.LINE_START);
		// text field ten kh
		tfTenKhachHang = new JTextField();
		pnTenKhachHang.add(tfTenKhachHang, BorderLayout.CENTER);

		// panel thông tin bên phải
		JPanel pnRight = new JPanel();
		pnThongTin.add(pnRight);
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		// panel dia chi
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnRight.add(pnDiaChi);
		pnDiaChi.setLayout(new BorderLayout(0, 0));
		// label dia chi
		JLabel lbDiaChi = new JLabel("Địa chỉ");
		lbDiaChi.setPreferredSize(new Dimension(100, 16));
		lbDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnDiaChi.add(lbDiaChi, BorderLayout.LINE_START);
		// text field dia chi
		tfDiaChi = new JTextField();
		pnDiaChi.add(tfDiaChi, BorderLayout.CENTER);

		// panel so dien thoai
		JPanel pnSoDienThoai = new JPanel();
		pnSoDienThoai.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnRight.add(pnSoDienThoai);
		pnSoDienThoai.setLayout(new BorderLayout(0, 0));
		// label so luong
		JLabel lbSoDienThoai = new JLabel("Số điện thoại");
		lbSoDienThoai.setPreferredSize(new Dimension(100, 16));
		lbSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnSoDienThoai.add(lbSoDienThoai, BorderLayout.LINE_START);
		// text field so luong
		tfSoDienThoai = new JTextField();
		pnSoDienThoai.add(tfSoDienThoai, BorderLayout.CENTER);

		// tạo 1 list text field
		listTextField[0] = tfMaKhachHang;
		listTextField[1] = tfTenKhachHang;
		listTextField[2] = tfDiaChi;
		listTextField[3] = tfSoDienThoai;

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
		btnAdd = new JButton("Thêm khách hàng");
		btnAdd.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnAddIcon.png")));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnAdd);

		// nút sửa
		btnEdit = new JButton("Sửa khách hàng");
		btnEdit.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnEditIcon.png")));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnEdit);

		// nút xóa
		btnXoa = new JButton("Xóa khách hàng");
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
					selectedKhachHang = data.getKhachHang(selectedRow);
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
		// check ID khi gõ trong tf mã khách hàng
		tfMaKhachHang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (data.checkByID(tfMaKhachHang.getText())) {
					lbThongBao.setText("Mã khách hàng đã tồn tại!");
					lbThongBao.setForeground(Color.RED);
					tfMaKhachHang.setForeground(Color.RED);
				} else {
					lbThongBao.setText("Mã khách hàng có thể dùng");
					lbThongBao.setForeground(Color.GREEN);
					tfMaKhachHang.setForeground(Color.BLACK);
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
					selectedKhachHang = data.getKhachHang(selectedRow);
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
		// thêm khách hàng
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themKhachHang();
			}
		});
		// sửa khách hàng
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaKhachHang();
			}
		});
		// xóa khách hàng
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaKhachHang();
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
					data.timKiemKhachHang(searchResult, indexOfSearchResults, searchKeyword);
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
	 * thêm 1 khách hàng vào data
	 */
	private void themKhachHang() {
		KhachHang temp = data.them(tfMaKhachHang.getText(), tfTenKhachHang.getText(), tfDiaChi.getText(), tfSoDienThoai.getText());
		if (temp != null) {
			modelTable.fireTableDataChanged();
			selectedKhachHang = temp;
			selectedRow = data.getList().size() - 1;
			table.setRowSelectionInterval(table.convertRowIndexToView(selectedRow), table.convertRowIndexToView(selectedRow));
			scrollToSelectedRow();
		}
	}

	/**
	 * sửa 1 khách hàng
	 */
	private void suaKhachHang() {
		KhachHang temp = data.sua(selectedKhachHang, tfMaKhachHang.getText(), tfTenKhachHang.getText(), tfDiaChi.getText(), tfSoDienThoai.getText());
		if (temp != null) {
			modelTable.fireTableDataChanged();
			selectedKhachHang = temp;
			table.setRowSelectionInterval(table.convertRowIndexToView(selectedRow), table.convertRowIndexToView(selectedRow));
			scrollToSelectedRow();
		}
	}

	/**
	 * xoa 1 khách hàng trong data
	 */
	private void xoaKhachHang() {
		boolean result = data.xoa(selectedKhachHang);
		if (result) {
			selectedKhachHang = null;
			clearAllTextfield();
			modelTable.fireTableDataChanged();
		}

	}
}
