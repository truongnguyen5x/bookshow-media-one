package DataAccessLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import DataObject.NhanVien;

/**
 * @author thinh
 *
 *         model cho bảng nhân viên
 */
public class ModelTableNhanVien extends AbstractTableModel {
	// tên cột
	public static final String[] columnNames = { "Mã nhân viên", "Họ tên", "Vị trí", "Địa chỉ", "Quyền admin", "Giới tính", "Ngày sinh", "Số điện thoại", "Lương" };
	// list nhan vien
	private ArrayList<NhanVien> ls;

	public ModelTableNhanVien(ArrayList<NhanVien> list) {
		ls = list;
	}

	// lấy tên cột
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// lấy số cột
	public int getColumnCount() {
		return 9;
	}

	// lấy số dòng
	public int getRowCount() {
		return ls.size();
	}

	// lấy giá trị tại 1 ô cho bảng
	public Object getValueAt(int rowIndex, int columnIndex) {
		NhanVien tmp = ls.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tmp.getID();
		case 1:
			return tmp.getHoTen();
		case 2:
			return tmp.getViTri();
		case 3:
			return tmp.getDiaChi();
		case 4:
			if (tmp.isAdmin()) {
				return "Có";
			} else {
				return "Không";
			}
		case 5:
			if (tmp.isGioiTinhNam()) {
				return "Nam";
			} else {
				return "Nữ";
			}
		case 6:
			return tmp.getStringNgaySinh();
		case 7:
			return tmp.getSoDienThoai();
		case 8:
			return tmp.getLuong();
		default:
			return "";
		}
	}

	// lấy kiểu dữ liệu của 1 cột của bảng
	public Class getColumnClass(int column) {
		if (column == 8 || column == 7) {
			return Integer.class;
		}
		return super.getColumnClass(column);

	}
}
