package DataAccessLayer;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import DataObject.DiaNhac;

/**
 * @author thinh
 * 
 *         kiểm tra tính hợp lệ dữ liệu; thực hiện các thao tác thêm, xóa, sửa,
 *         tìm kiếm
 */
public class DataDiaNhac {
	private int soLuong, giaMua, giaBan, chietKhau;
	private String messenger;
	private DataAccess<DiaNhac> data;
	private boolean dataIsValid;

	public DataDiaNhac() {
		data = new DataAccess<>(DiaNhac.class);
		data.readFile();
	}

	/**
	 * @param id
	 * 
	 *            kiểm tra trùng mã với các sp khác, trùng trả về true
	 */
	public boolean checkByID(String id) {
		return data.checkByID(id);
	}

	public DiaNhac getSanPham(int index) {
		return data.getList().get(index);
	}

	public ArrayList<DiaNhac> getList() {
		return data.getList();
	}

	public DataAccess<DiaNhac> getData() {
		return data;
	}

	/**
	 * @return đĩa nhạc vừa được thêm
	 * 
	 *         thêm 1 đĩa nhạc
	 */
	public DiaNhac them(String id, String ten, String loai, String gianHang, String soLuong, String giaMua, String giaBan, String chietKhau, String string1, String string2) {
		kiemTraHopLe(id, ten, soLuong, giaMua, giaBan, chietKhau);
		// kiểm tra trùng mã sp
		boolean idIsValid = !data.checkByID(id);
		if (!idIsValid) {
			messenger = "Mã đĩa nhạc đã tồn tại! \n   ";
		}
		// hỏi về thêm đĩa nhạc
		if (dataIsValid && idIsValid) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm đĩa nhạc: " + ten + " ?\n  ", "Thêm", JOptionPane.YES_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				DiaNhac temp = new DiaNhac(id, ten, loai + "", gianHang, this.soLuong, this.giaMua, this.giaBan, this.chietKhau, string1 + "", string2);
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
	 * @return đĩa nhạc vừa được sửa
	 * 
	 *         sửa 1 đĩa nhạc
	 */
	public DiaNhac sua(DiaNhac sanPham, String id, String ten, String loai, String gianHang, String soLuong, String giaMua, String giaBan, String chietKhau, String string1, String string2) {
		if (sanPham != null) {
			kiemTraHopLe(id, ten, soLuong, giaMua, giaBan, chietKhau);
			boolean idIsValid;
			// kiểm tra mã đĩa nhạc mới có trùng với mã sp khác không
			if (data.checkByID(id) && !id.equals(sanPham.getID())) {
				idIsValid = false;
				messenger = "Mã đĩa nhạc đã tồn tại \n  ";
			} else {
				idIsValid = true;
			}
			// hỏi người dùng sửa đĩa nhạc không
			if (dataIsValid && idIsValid) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa đĩa nhạc mã: " + sanPham.getID() + " ?\n   ", "Sửa", JOptionPane.YES_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					DiaNhac temp = data.getByID(sanPham.getID());
					if (temp != null) {
						temp.setID(id);
						temp.setTenSP(ten);
						temp.setLoai(loai);
						temp.setGianHang(gianHang);
						temp.setSoLuong(this.soLuong);
						temp.setGiaBan(this.giaBan);
						temp.setGiaMua(this.giaMua);
						temp.setChietKhau(this.chietKhau);
						temp.setNhaSanXuat(string1);
						temp.setCaSi(string2);
						data.writeFile();
					}
					return temp;
				}
			} else {
				JOptionPane.showMessageDialog(null, messenger, "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn đĩa nhạc nào để sửa!\n  ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	/**
	 * xóa 1 đĩa nhạc
	 */
	public boolean xoa(DiaNhac sanPham) {
		if (sanPham != null) {
			int results = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn xóa đĩa nhạc: " + sanPham.getTenSP() + "\nMã: " + sanPham.getID() + "\n  ", "Xóa", JOptionPane.YES_OPTION);
			if (results == JOptionPane.OK_OPTION) {
				data.delete(sanPham.getID());
				data.writeFile();
				return true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn đĩa nhạc nào để xóa!\n  ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	/**
	 * kiểm tra tính hợp lệ của dữ liệu
	 */
	public void kiemTraHopLe(String ma, String ten, String soLuong, String giaMua, String giaBan, String chietKhau) {
		messenger = "";
		dataIsValid = true;
		// kiểm tra mã sp
		if (ma.equals("")) {
			messenger = "Mã đĩa nhạc bị thiếu \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra tên sp
		if (ten.equals("")) {
			messenger = "Tên đĩa nhạc bị thiếu \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra số lượng
		try {
			this.soLuong = Integer.parseInt(soLuong);
			if (this.soLuong < 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			messenger = "Số lượng sai \nVui lòng sửa lại!\n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra giá mua
		try {
			this.giaMua = Integer.parseInt(giaMua);
			if (this.giaMua < 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			messenger = "Giá mua sai. \nVui lòng sửa lại!\n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra giá bán
		try {
			this.giaBan = Integer.parseInt(giaBan);
			if (this.giaBan <= 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			messenger = "Giá bán sai \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra chiết khấu
		try {
			this.chietKhau = Integer.parseInt(chietKhau);
			if (this.chietKhau < 0 || this.chietKhau >= 100) {
				throw new Exception();
			}
		} catch (Exception e) {
			messenger = "Mức chiết khấu sai\nVui lòng sửa lại!\n  ";
			dataIsValid = false;
			return;
		}
	}

	/**
	 * tìm kiếm với keyWord trong mọi thuộc tính mọi đĩa nhạc
	 */
	public void timKiemSanPham(Vector<String> results, ArrayList<Integer> index, String keyWord) {
		ArrayList<DiaNhac> list = data.getList();
		results.clear();
		index.clear();
		String temp;
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i).getID();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("(" + temp + ") " + list.get(i).getTenSP());
				index.add(i);
			}
			temp = list.get(i).getTenSP();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add(temp + " (" + list.get(i).getID() + ")");
				index.add(i);
			}
			temp = list.get(i).getLoai();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("Thể loại: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getTenSP() + ")");
				index.add(i);
			}
			temp = list.get(i).getGianHang();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("Gian hàng: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getTenSP() + ")");
				index.add(i);
			}
			temp = list.get(i).getNhaSanXuat();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("Nhà SX: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getTenSP() + ")");
				index.add(i);
			}
			temp = list.get(i).getCaSi();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("Ca sĩ: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getTenSP() + ")");
				index.add(i);
			}
		}
	}
}