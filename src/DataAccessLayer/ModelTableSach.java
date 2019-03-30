package DataAccessLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DataObject.Sach;
import GuiLayer.PnSach;

/**
 * @author vu
 *
 *         model dữ liệu cho bảng trong panel sách, lấy từ lớp arraylist
 */
public class ModelTableSach extends AbstractTableModel {
	// tên cột
	public static final String[] columnNames = { "Mã sách", "Tên sách", "Tác giả", "Giá bán(đ)", "Chiết khấu(%)", "Thể loại", "Số lượng", "Gian hàng", "Nhà xuất bản", "Giá mua (đ)" };
	// list sách
	private ArrayList<Sach> ls;

	public ModelTableSach(ArrayList<Sach> list) {
		ls = list;
	}

	// lấy tên cột
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// lấy số cột
	public int getColumnCount() {
		return 10;
	}

	// lấy số dòng
	public int getRowCount() {
		return ls.size();
	}

	// lấy giá trị tại 1 ô cho bảng
	public Object getValueAt(int rowIndex, int columnIndex) {
		Sach tmp = ls.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tmp.getID();
		case 1:
			return tmp.getTenSP();
		case 2:
			return tmp.getTacGia();
		case 3:
			return tmp.getGiaBan();
		case 4:
			return tmp.getChietKhau();
		case 5:
			return tmp.getLoai();
		case 6:
			return tmp.getSoLuong();
		case 7:
			return tmp.getGianHang();
		case 8:
			return tmp.getNhaXuatBan();
		case 9:
			if (PnSach.isCurrentUserIsAdmin()) {
				return tmp.getGiaMua();
			} else
				return "";
		default:
			return "";
		}
	}

	// lấy kiểu dữ liệu của 1 cột của bảng
	public Class getColumnClass(int column) {
		if (column == 3 || column == 4 || column == 6 || column == 9) {
			return Integer.class;
		}
		return super.getColumnClass(column);
	}
}