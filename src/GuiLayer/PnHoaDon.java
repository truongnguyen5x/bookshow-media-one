package GuiLayer;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import DataAccessLayer.ModelTableChiTietHoaDon;
import DataAccessLayer.DataAccess;
import DataAccessLayer.ModelTableHoaDon;
import DataObject.HoaDon;
import DataObject.NhanVien;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * @author Tài
 *
 *         xem và xóa hóa đơn
 */
public class PnHoaDon extends JPanel {
	private JTextField tfMaHoaDon, tfMaKhachHang, tfNgayBan, tfTenKhachHang, tfMaNhanVien, tfTenNhanVien, tfTongTien;
	private JTextField[] listTextField = new JTextField[7];
	private JButton btnXoa;

	private MyTable tableSanPham, tableHoaDon;
	private ModelTableHoaDon modelHoaDon;
	private DataAccess<HoaDon> data;
	private int selectedRow;
	private HoaDon selectedHoaDon;
	private ModelTableChiTietHoaDon modelTableChiTietHoaDon;

	// state field
	public static boolean currentUserIsAdmin = true;

	public PnHoaDon(NhanVien user, DataAccess<HoaDon> data) {
		this.data = data;
		modelHoaDon = new ModelTableHoaDon(data.getList());
		currentUserIsAdmin = user.isAdmin();

	//	setBorder(new EmptyBorder(3, 3, 3, 3));
		setPreferredSize(new Dimension(950, 650));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lbHoaDon = new JLabel("Hóa đơn");
		lbHoaDon.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lbHoaDon);

		JPanel pnThongTinChung = new JPanel();
		pnThongTinChung.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin chung", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		add(pnThongTinChung);
		pnThongTinChung.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnLeft = new JPanel();
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

		tfNgayBan = new JTextField();
		pnNgayMua.add(tfNgayBan, BorderLayout.CENTER);
		tfNgayBan.setColumns(10);

		JPanel pnMaNhanVien = new JPanel();
		pnMaNhanVien.setBorder(new EmptyBorder(3, 10, 3, 20));
		pnLeft.add(pnMaNhanVien);
		pnMaNhanVien.setLayout(new BorderLayout(0, 0));

		JLabel lbMaNhanVien = new JLabel("Mã nhân viên");
		lbMaNhanVien.setPreferredSize(new Dimension(95, 16));
		pnMaNhanVien.add(lbMaNhanVien, BorderLayout.WEST);

		tfMaNhanVien = new JTextField();
		pnMaNhanVien.add(tfMaNhanVien, BorderLayout.CENTER);
		tfMaNhanVien.setColumns(10);

		JPanel pnTongTien = new JPanel();
		pnTongTien.setBorder(new EmptyBorder(3, 10, 10, 20));
		pnLeft.add(pnTongTien);
		pnTongTien.setLayout(new BorderLayout(0, 0));

		JLabel lbTongTien = new JLabel("Tổng tiền");
		lbTongTien.setPreferredSize(new Dimension(95, 16));
		pnTongTien.add(lbTongTien, BorderLayout.WEST);

		tfTongTien = new JTextField();
		pnTongTien.add(tfTongTien, BorderLayout.CENTER);
		tfTongTien.setColumns(10);

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

		tfMaKhachHang = new JTextField();
		pnMaKhachHang.add(tfMaKhachHang, BorderLayout.CENTER);
		tfMaKhachHang.setColumns(10);

		JPanel pnTenKhachHang = new JPanel();
		pnTenKhachHang.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnTenKhachHang);
		pnTenKhachHang.setLayout(new BorderLayout(0, 0));

		JLabel lbTenKhachHang = new JLabel("Tên khách hàng");
		lbTenKhachHang.setPreferredSize(new Dimension(95, 16));
		pnTenKhachHang.add(lbTenKhachHang, BorderLayout.LINE_START);

		tfTenKhachHang = new JTextField();
		tfTenKhachHang.setColumns(10);
		pnTenKhachHang.add(tfTenKhachHang, BorderLayout.CENTER);

		JPanel pnTenNhanVien = new JPanel();
		pnTenNhanVien.setBorder(new EmptyBorder(3, 10, 3, 10));
		pnRight.add(pnTenNhanVien);
		pnTenNhanVien.setLayout(new BorderLayout(0, 0));

		JLabel lbTenNhanVien = new JLabel("Tên nhân viên");
		lbTenNhanVien.setPreferredSize(new Dimension(95, 16));
		pnTenNhanVien.add(lbTenNhanVien, BorderLayout.WEST);

		tfTenNhanVien = new JTextField();
		pnTenNhanVien.add(tfTenNhanVien, BorderLayout.CENTER);
		tfTenNhanVien.setColumns(10);

		JPanel pnNull = new JPanel();
		pnRight.add(pnNull);

		JLabel lbNull = new JLabel("  ");
		pnNull.add(lbNull);

		JPanel pnChiTietSP = new JPanel();
		pnChiTietSP.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chi ti\u1EBFt c\u00E1c m\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		add(pnChiTietSP);
		pnChiTietSP.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneSP = new JScrollPane();
		pnChiTietSP.add(scrollPaneSP, BorderLayout.CENTER);

		JPanel pnTableHoaDon = new JPanel();
		pnTableHoaDon.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u1EA3ng h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		add(pnTableHoaDon);
		pnTableHoaDon.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneHoaDon = new JScrollPane();
		addTable();
		scrollPaneSP.setViewportView(tableSanPham);
		scrollPaneHoaDon.setViewportView(tableHoaDon);

		pnTableHoaDon.add(scrollPaneHoaDon, BorderLayout.CENTER);

		JPanel pnXoa = new JPanel();
		if (currentUserIsAdmin) {
			add(pnXoa);
		}
		pnXoa.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(PnSach.class.getResource("/img/btnDeletetIcon.png")));
		pnXoa.add(btnXoa);

		// tạo 1 list text field
		listTextField[0] = tfMaHoaDon;
		listTextField[1] = tfNgayBan;
		listTextField[2] = tfMaNhanVien;
		listTextField[3] = tfTenNhanVien;
		listTextField[4] = tfMaKhachHang;
		listTextField[5] = tfTenKhachHang;
		listTextField[6] = tfTongTien;

		addListener();

	}

	/**
	 * tạo bảng
	 */
	private void addTable() {
		tableSanPham = new MyTable();
		tableHoaDon = new MyTable();
		tableHoaDon.setModel(modelHoaDon);
		tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableHoaDon.getColumnModel().getColumn(1).setPreferredWidth(140);
		tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableHoaDon.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableHoaDon.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableHoaDon.getColumnModel().getColumn(5).setPreferredWidth(120);
		tableHoaDon.getColumnModel().getColumn(6).setPreferredWidth(70);
	}

	/**
	 * thêm sự kiện
	 */
	private void addListener() {
		// bắt sự kiện click vào 1 dòng của bảng
		tableHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				try {
					selectedRow = tableHoaDon.convertRowIndexToModel(tableHoaDon.getSelectedRow());
					selectedHoaDon = data.getList().get(selectedRow);
					modelTableChiTietHoaDon = new ModelTableChiTietHoaDon(selectedHoaDon.getList());
					tableSanPham.setModel(modelTableChiTietHoaDon);
					showToTextfield();
				} catch (Exception e) {
				}
			}
		});
		// xóa hóa đơn
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedHoaDon != null) {
					int results = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn xóa hóa đơn: " + selectedHoaDon.getMaHoaDon() + "\n  ", "Xóa", JOptionPane.YES_OPTION);
					if (results == JOptionPane.OK_OPTION) {
						data.delete(selectedHoaDon.getID());
						data.writeFile();
						selectedHoaDon = null;
						tableSanPham.setModel(new DefaultTableModel());
						modelHoaDon.fireTableDataChanged();
						clearAllTextfield();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn hóa đơn nào để xóa!\n  ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * xóa tất cả chữ
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
		for (int i = 0; i < listTextField.length; i++) {
			listTextField[i].setText(modelHoaDon.getValueAt(selectedRow, i).toString());
			listTextField[i].setCaretPosition(0);
		}
	}
}
