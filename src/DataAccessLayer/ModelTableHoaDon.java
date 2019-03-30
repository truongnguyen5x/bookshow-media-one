package DataAccessLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DataObject.HoaDon;

/**
 * @author tai
 *
 *         model table cho bảng hóa đơn
 */
public class ModelTableHoaDon extends AbstractTableModel {
	// tên cột
	public static final String[] columnNames = { "Mã hóa đơn", "Ngày bán", "Mã nhân viên", "Tên nhân viên", "Mã khách hàng", "Tên khách hàng", "Tổng tiền(đ)" };
	// list hóa đơn
	private ArrayList<HoaDon> ls;

	public ModelTableHoaDon(ArrayList<HoaDon> list) {
		ls = list;
	}

	// lấy tên cột
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return ls.size();
	}

	// lấy giá trị tại 1 ô cho bảng
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		HoaDon tmp = ls.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tmp.getID();
		case 1:
			return tmp.getStringNgayBan();
		case 2:
			return tmp.getNhanVien().getID();
		case 3:
			return tmp.getNhanVien().getHoTen();
		case 4:
			return tmp.getKhachHang().getID();
		case 5:
			return tmp.getKhachHang().getHoTen();
		case 6:
			return tmp.getTongTien();
		default:
			return "";
		}
	}

	// lấy kiểu dữ liệu của 1 cột của bảng
	public Class getColumnClass(int column) {
		if (column == 6) {
			return Integer.class;
		}
		return super.getColumnClass(column);
	}
}
