package DataAccessLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DataObject.DiaNhac;
import GuiLayer.PnDiaNhac;

/**
 * @author thinh
 * 
 *         model dữ liệu cho bảng đĩa nhạc, lấy từ dataAccess
 */
public class ModelTableDiaNhac extends AbstractTableModel {
	// tên cột
	public static final String[] columnNames = { "Mã đĩa nhạc", "Tên đĩa nhạc", "Ca Sĩ", "Giá bán(đ)", "Chiết khấu(%)", "Thể loại", "Số lượng", "Gian hàng", "Nhà sản xuất", "Giá mua (đ)" };
	// list đĩa nhạc
	private ArrayList<DiaNhac> ls;

	public ModelTableDiaNhac(ArrayList<DiaNhac> list) {
		ls = list;
	}

	// lấy tên cột
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
		DiaNhac tmp = ls.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tmp.getID();
		case 1:
			return tmp.getTenSP();
		case 2:
			return tmp.getCaSi();
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
			return tmp.getNhaSanXuat();
		case 9:
			if (PnDiaNhac.isCurrentUserIsAdmin()) {
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