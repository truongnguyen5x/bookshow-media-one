package DataAccessLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import DataObject.DiaPhim;
import GuiLayer.PnDiaPhim;

/**
 * @author tai
 *
 *         model dữ liệu cho bảng đĩa phim
 */
public class ModelTableDiaPhim extends AbstractTableModel {
	// tên cột
	public static final String[] columnNames = { "Mã đĩa phim", "Tên đĩa phim", "Diễn viên", "Giá bán(đ)", "Chiết khấu(%)", "Thể loại", "Số lượng", "Gian hàng", "Đạo diễn", "Giá mua (đ)" };
	// list đĩa phim
	private ArrayList<DiaPhim> ls;

	public ModelTableDiaPhim(ArrayList<DiaPhim> list) {
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
		DiaPhim tmp = ls.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tmp.getID();
		case 1:
			return tmp.getTenSP();
		case 2:
			return tmp.getDienVien();
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
			return tmp.getDaoDien();
		case 9:
			if (PnDiaPhim.isCurrentUserIsAdmin()) {
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
