package DataAccessLayer;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import DataObject.ChiTietHoaDon;

/**
 * @author tai
 *
 *         model dữ liệu cho bảng trong panel hóa đơn, lấy từ lớp arraylist
 */
public class ModelTableChiTietHoaDon extends AbstractTableModel {
	// tên cột
	public static final String[] columnNames = { "Mã sản phẩm", "Tên sản phẩm", "Loại", "Giá sản phẩm(đ)", "Chiết khấu(%)", "Số lượng", "Thành tiền(đ)" };
	// list sản phẩm trong hóa đơn
	private ArrayList<ChiTietHoaDon> ls;

	public ModelTableChiTietHoaDon(ArrayList<ChiTietHoaDon> list) {
		ls = list;
	}

	// lấy tên cột
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// lấy số cột
	public int getColumnCount() {
		return 7;
	}

	// lấy số dòng
	public int getRowCount() {
		return ls.size();
	}

	// lấy giá trị tại 1 ô cho bảng
	public Object getValueAt(int rowIndex, int columnIndex) {
		ChiTietHoaDon tmp = ls.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return tmp.getMaSanPham();
		case 1:
			return tmp.getSanPham().getTenSP();
		case 2:
			return tmp.getLoaiSanPham();
		case 3:
			return tmp.getGiaBan();
		case 4:
			return tmp.getChietKhau();
		case 5:
			return tmp.getSoLuong();
		case 6:
			return tmp.getThanhTien();
		default:
			return "";
		}
	}

	// lấy kiểu dữ liệu của 1 cột của bảng
	public Class getColumnClass(int column) {
		if (column >= 3 && column <= 6) {
			return Integer.class;
		}
		return super.getColumnClass(column);
	}
}