package DataAccessLayer;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import DataObject.KhachHang;

/**
 * @author vu
 * 
 *         kiểm tra tính hợp lệ dữ liệu; thực hiện các thao tác thêm, xóa, sửa,
 *         tìm kiếm
 */
public class DataKhachHang {
	private long soDienThoai;
	private String messenger;
	private DataAccess<KhachHang> data;
	private boolean dataIsValid;

	public DataKhachHang() {
		data = new DataAccess<>(KhachHang.class);
		data.readFile();
	}

	/**
	 * @param id
	 * 
	 *            kiểm tra trùng mã với các khách hàng khác, trùng trả về true
	 */
	public boolean checkByID(String id) {
		return data.checkByID(id);
	}

	public KhachHang getKhachHang(int index) {
		return data.getList().get(index);
	}

	public ArrayList<KhachHang> getList() {
		return data.getList();
	}

	public DataAccess<KhachHang> getData() {
		return data;
	}

	/**
	 * @return khách hàng vừa được thêm
	 * 
	 *         thêm 1 sản phẩm
	 */
	public KhachHang them(String id, String ten, String diaChi, String soDienThoai) {
		kiemTraHopLe(id, ten, soDienThoai);
		// kiểm tra trùng mã khách hàng
		boolean idIsValid = !data.checkByID(id);
		if (!idIsValid) {
			messenger = "Mã khách đã tồn tại! \n   ";
		}
		// hỏi về thêm khách hàng
		if (dataIsValid && idIsValid) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm khách hàng: " + ten + " ?\n  ", "Thêm", JOptionPane.YES_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				KhachHang temp = new KhachHang(id, ten, diaChi, this.soDienThoai);
				data.insert(temp);
				data.writeFile();
				return temp;
			}
		} else {
			JOptionPane.showMessageDialog(null, messenger, "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}

	/**
	 * @return khách hàng vừa được sửa
	 * 
	 *         sửa 1 khách
	 */
	public KhachHang sua(KhachHang khachHang, String id, String ten, String diaChi, String soDienThoai) {
		if (khachHang != null) {
			kiemTraHopLe(id, ten, soDienThoai);
			boolean idIsValid;
			// kiểm tra mã sản khách hàng mới có trùng với mã kh khác không
			if (data.checkByID(id) && !id.equals(khachHang.getID())) {
				idIsValid = false;
				messenger = "Mã khách hàng đã tồn tại \n  ";
			} else {
				idIsValid = true;
			}
			// hỏi người dùng sửa khách hàng không
			if (dataIsValid && idIsValid) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa khách mã: " + khachHang.getID() + " ?\n   ", "Sửa", JOptionPane.YES_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					KhachHang temp = data.getByID(khachHang.getID());
					if (temp != null) {
						temp.setID(id);
						temp.setHoTen(ten);
						temp.setDiaChi(diaChi);
						temp.setSoDienThoai(this.soDienThoai);
						data.writeFile();
					}
					return temp;
				}
			} else {
				JOptionPane.showMessageDialog(null, messenger, "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng nào để sửa!\n  ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	/**
	 * xóa 1 khách
	 */
	public boolean xoa(KhachHang khachHang) {
		if (khachHang != null) {
			int results = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn xóa khách hàng: " + khachHang.getHoTen() + "\nMã khách: " + khachHang.getID() + "\n  ", "Xóa", JOptionPane.YES_OPTION);
			if (results == JOptionPane.OK_OPTION) {
				data.delete(khachHang.getID());
				data.writeFile();
				return true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng nào để xóa!\n  ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	/**
	 * kiểm tra tính hợp lệ của dữ liệu
	 */
	public void kiemTraHopLe(String ma, String ten, String soDienThoai) {
		messenger = "";
		dataIsValid = true;
		// kiểm tra mã kh
		if (ma.equals("")) {
			messenger = "Mã khách hàng bị thiếu \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra tên kh
		if (ten.equals("")) {
			messenger = "Tên khách hàng bị thiếu \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra sdt
		try {
			if (soDienThoai.equals("")) {
				this.soDienThoai = 0;
			} else {
				this.soDienThoai = Integer.parseInt(soDienThoai);
				if (this.soDienThoai <= 0) {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			messenger = "Số điện thoại sai \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
	}

	/**
	 * tìm kiếm với keyWord trong mọi thuộc tính mọi khách hàng
	 */
	public void timKiemKhachHang(Vector<String> results, ArrayList<Integer> index, String keyWord) {
		ArrayList<KhachHang> list = data.getList();
		results.clear();
		index.clear();
		String temp = null;
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i).getID();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("(" + temp + ") " + list.get(i).getHoTen());
				index.add(i);
			}
			temp = list.get(i).getHoTen();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add(temp + " (" + list.get(i).getID() + ")");
				index.add(i);
			}
			temp = list.get(i).getDiaChi();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("Địa chỉ: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getHoTen() + ")");
				index.add(i);
			}
			temp = list.get(i).getSoDienThoai() + "";
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("SĐT: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getHoTen() + ")");
				index.add(i);
			}
		}
	}
}