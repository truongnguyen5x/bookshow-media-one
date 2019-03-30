package DataObject;

import java.io.Serializable;

/**
 * @author vu
 * 
 *         sản phẩm: sách đĩa nhạc, đĩa phim
 */
public class SanPham implements Serializable, InterfaceHaveID, Cloneable {
	protected String maSP, tenSP, loai, gianHang;
	protected int soLuong, giaMua, giaBan, chietKhau;

	public SanPham() {

	}

	public SanPham(String maSP, String tenSP, String loai, String gianHang, int soLuong, int giaMua, int giaBan, int chietKhau) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loai = loai;
		this.gianHang = gianHang;
		this.soLuong = soLuong;
		this.giaMua = giaMua;
		this.giaBan = giaBan;
		this.chietKhau = chietKhau;
	}

	public String getID() {
		return maSP;
	}

	public void setID(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public String getGianHang() {
		return gianHang;
	}

	public void setGianHang(String gianHang) {
		this.gianHang = gianHang;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getGiaMua() {
		return giaMua;
	}

	public void setGiaMua(int giaMua) {
		this.giaMua = giaMua;
	}

	public int getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}

	public int getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(int chietKhau) {
		this.chietKhau = chietKhau;
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
