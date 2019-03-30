package DataAccessLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DataObject.KhachHang;

/**
 * @author vu
 * 
 *         model dữ liệu cho bảng khách hàng, lấy từ dataAccess
 */
public class ModelTableKhachHang extends AbstractTableModel {
	// tên cột
	public static final String[] columnNames = { "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại" };
	// list khách hàng
	private ArrayList<KhachHang> ls;

	public ModelTableKhachHang(ArrayList<KhachHang> list) {
		ls = list;
	}

	// lấy tên cột
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// lấy số cột
	public int getColumnCount() {
		return 4;
	}

	// lấy số dòng
	public int getRowCount() {
		return ls.size();
	}

	// lấy giá trị tại 1 ô cho bảng
	public Object getValueAt(int rowIndex, int columnIndex) {
		KhachHang tmp = ls.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tmp.getID();
		case 1:
			return tmp.getHoTen();
		case 2:
			return tmp.getDiaChi();
		case 3:
			return "0" + tmp.getSoDienThoai();
		default:
			return "";
		}
	}
}
