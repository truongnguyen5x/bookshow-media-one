package DataObject;

import java.io.Serializable;

/**
 * @author vu
 */
public class KhachHang implements Serializable, InterfaceHaveID, Cloneable {
	private String maKhachHang, hoTen, diaChi;
	private long soDienThoai;

	public KhachHang(String maKhachHang, String hoTen, String diaChi, long soDienThoai) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public String getID() {
		return maKhachHang;
	}

	public void setID(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public long getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(long soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
