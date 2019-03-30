package DataObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author thịnh
 */
public class NhanVien implements Serializable, InterfaceHaveID, Cloneable {
	private String maNhanVien, hoTen, viTri, diaChi, matKhau;
	private boolean admin, gioiTinhNam;
	private Date ngaySinh;
	private long soDienThoai;
	private int luong;

	public NhanVien() {
		super();
	}

	public NhanVien(String maNhanVien, String hoTen, String viTri, String diaChi, String matKhau, boolean admin, boolean gioiTinhNam, Date ngaySinh, long soDienThoai, int luong) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.viTri = viTri;
		this.diaChi = diaChi;
		this.matKhau = matKhau;
		this.admin = admin;
		this.gioiTinhNam = gioiTinhNam;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.luong = luong;
	}

	/**
	 * tạo nhân viên với mật khẩu mặc định là password
	 */
	public NhanVien(String maNhanVien, String hoTen, String viTri, String diaChi, boolean admin, boolean gioiTinhNam, Date ngaySinh, long soDienThoai, int luong) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.viTri = viTri;
		this.diaChi = diaChi;
		this.admin = admin;
		this.gioiTinhNam = gioiTinhNam;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.luong = luong;
		this.matKhau = "password";
	}

	public String getID() {
		return maNhanVien;
	}

	public void setID(String maNV) {
		this.maNhanVien = maNV;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getHoTen() {
		return hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public String getStringNgaySinh() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(ngaySinh);
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public long getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(long soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public int getLuong() {
		return luong;
	}

	public void setLuong(int luong) {
		this.luong = luong;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

	public boolean isGioiTinhNam() {
		return gioiTinhNam;
	}

	public void setGioiTinhNam(boolean gioiTinhNam) {
		this.gioiTinhNam = gioiTinhNam;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
