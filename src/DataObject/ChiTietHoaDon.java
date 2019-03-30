package DataObject;

import java.io.Serializable;

/**
 * @author tai
 *
 *         một mặt hàng trong hóa đơn
 */
public class ChiTietHoaDon implements Serializable, Cloneable {
	private SanPham sanPham;
	private int soLuong, thanhTien;
	private String loaiSanPham;

	public ChiTietHoaDon(SanPham sanPham, String loai, int soLuong) {
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		loaiSanPham = loai;
		thanhTien = getGiaBan() * soLuong;
		thanhTien = (int) (thanhTien - getChietKhau() * thanhTien / 100.0);
	}

	public String getMaSanPham() {
		return sanPham.getID();
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.sanPham.setID(maSanPham);
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
		thanhTien = getGiaBan() * soLuong;
		thanhTien = (int) (thanhTien - getChietKhau() * thanhTien / 100.0);
	}

	public int getGiaBan() {
		return sanPham.getGiaBan();
	}

	public int getChietKhau() {
		return sanPham.getChietKhau();
	}

	public int getThanhTien() {
		thanhTien = getGiaBan() * soLuong;
		thanhTien = (int) (thanhTien - getChietKhau() * thanhTien / 100.0);
		return thanhTien;
	}

	public String getLoaiSanPham() {
		return loaiSanPham;
	}

	public ChiTietHoaDon clone() throws CloneNotSupportedException {
		ChiTietHoaDon result = (ChiTietHoaDon) super.clone();
		result.sanPham = (SanPham) this.sanPham.clone();
		return result;
	}
}
