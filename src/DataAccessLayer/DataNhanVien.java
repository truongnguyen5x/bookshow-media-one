package DataAccessLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import DataObject.NhanVien;

/**
 * @author thinh
 * 
 *         kiểm tra tính hợp lệ dữ liệu; thực hiện các thao tác thêm, xóa, sửa,
 *         tìm kiếm
 */
public class DataNhanVien {
	private long soDienThoai;
	private int luong;
	private String messenger;
	private DataAccess<NhanVien> data;
	private boolean dataIsValid;

	public DataNhanVien() {
		data = new DataAccess<>(NhanVien.class);
		data.readFile();
	}

	/**
	 * @param id
	 * 
	 *            kiểm tra trùng mã với các nv khác, trùng trả về true
	 */
	public boolean checkByID(String id) {
		return data.checkByID(id);
	}

	public NhanVien getNhanVien(int index) {
		return data.getList().get(index);
	}

	public ArrayList<NhanVien> getList() {
		return data.getList();
	}

	public DataAccess<NhanVien> getData() {
		return data;
	}

	/**
	 * @return nv vừa được thêm
	 * 
	 *         thêm 1 nv
	 */

	public NhanVien them(String id, String ten, String viTri, String diaChi, boolean isAdmin, boolean isNam, Date ngaySinh, String soDienThoai, String tienLuong) {
		kiemTraHopLe(id, ten, soDienThoai, tienLuong);
		// kiểm tra trùng mã nhân viên
		boolean idIsValid = !data.checkByID(id);
		if (!idIsValid) {
			messenger = "Mã nhân viên đã tồn tại! \n   ";
		}
		// hỏi về thêm nhân viên
		if (dataIsValid && idIsValid) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm nhân viên: " + ten + " ?\n  ", "Thêm", JOptionPane.YES_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				NhanVien temp = new NhanVien(id, ten, viTri, diaChi, isAdmin, isNam, ngaySinh, this.soDienThoai, this.luong);
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
	 * @return nhân viên vừa được sửa
	 * 
	 *         sửa 1 nhân viên
	 */
	public NhanVien sua(NhanVien nhanVien, String id, String ten, String viTri, String diaChi, boolean isAdmin, boolean isNam, Date ngaySinh, String soDienThoai, String tienLuong) {
		if (nhanVien != null) {
			kiemTraHopLe(id, ten, soDienThoai, tienLuong);
			boolean idIsValid;
			// kiểm tra mã nhân viên mới có trùng với mã nhân viên khác không
			if (data.checkByID(id) && !id.equals(nhanVien.getID())) {
				idIsValid = false;
				messenger = "Mã nhân viên đã tồn tại \n  ";
			} else {
				idIsValid = true;
			}
			// hỏi người dùng sửa nhân viên không
			if (dataIsValid && idIsValid) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa nhân viên mã: " + nhanVien.getID() + " ?\n   ", "Sửa", JOptionPane.YES_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					NhanVien temp = data.getByID(nhanVien.getID());
					if (temp != null) {
						temp.setID(id);
						temp.setHoTen(ten);
						temp.setViTri(viTri);
						temp.setDiaChi(diaChi);
						temp.setAdmin(isAdmin);
						temp.setGioiTinhNam(isNam);
						temp.setNgaySinh(ngaySinh);
						temp.setSoDienThoai(this.soDienThoai);
						temp.setLuong(this.luong);
						data.writeFile();
					}
					return temp;
				}
			} else {
				JOptionPane.showMessageDialog(null, messenger, "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên nào để sửa!\n  ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	/**
	 * xóa 1 nhân viên
	 */
	public boolean xoa(NhanVien nhanVien) {
		if (nhanVien != null) {
			int results = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn xóa nhân viên: " + nhanVien.getHoTen() + "\nMã: " + nhanVien.getID() + "\n  ", "Xóa", JOptionPane.YES_OPTION);
			if (results == JOptionPane.OK_OPTION) {
				data.delete(nhanVien.getID());
				data.writeFile();
				return true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên nào để xóa!\n  ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	/**
	 * kiểm tra tính hợp lệ của dữ liệu
	 */
	public void kiemTraHopLe(String ma, String ten, String soDienThoai, String luong) {
		messenger = "";
		dataIsValid = true;
		// kiểm tra mã nhân viên
		if (ma.equals("")) {
			messenger = "Mã nhân viên bị thiếu \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra tên nhân viên
		if (ten.equals("")) {
			messenger = "Tên nhân viên bị thiếu \nVui lòng sửa lại! \n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra số điện thoại
		try {
			this.soDienThoai = Long.parseLong(soDienThoai);
			if (this.soDienThoai < 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			messenger = "Số điện thoại sai \nVui lòng sửa lại!\n  ";
			dataIsValid = false;
			return;
		}
		// kiểm tra tiền lương
		try {
			this.luong = Integer.parseInt(luong);
			if (this.luong < 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			messenger = "Tiền lương sai \nVui lòng sửa lại!\n  ";
			dataIsValid = false;
			return;
		}
	}

	/**
	 * tìm kiếm với keyWord trong mọi thuộc tính mọi nhân viên
	 */
	public void timKiemSanPham(Vector<String> results, ArrayList<Integer> index, String keyWord) {
		ArrayList<NhanVien> list = data.getList();
		results.clear();
		index.clear();
		String temp;
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
			temp = list.get(i).getViTri();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("Vị trí: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getViTri() + ")");
				index.add(i);
			}
			temp = list.get(i).getDiaChi();
			if (temp.toLowerCase().contains(keyWord.toLowerCase())) {
				results.add("Địa chỉ: " + temp + "   (" + list.get(i).getID() + "-" + list.get(i).getDiaChi() + ")");
				index.add(i);
			}
		}
	}
}