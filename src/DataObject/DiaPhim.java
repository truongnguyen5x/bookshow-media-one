package DataObject;

/**
 * @author Tài
 */
public class DiaPhim extends SanPham {
	private String daoDien, dienVien;

	public DiaPhim(String maSP, String tenSP, String loai, String gianHang, int soLuong, int giaMua, int giaBan, int chietKhau, String daoDien, String dienVien) {
		super(maSP, tenSP, loai, gianHang, soLuong, giaMua, giaBan, chietKhau);
		this.daoDien = daoDien;
		this.dienVien = dienVien;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public String getDienVien() {
		return dienVien;
	}

	public void setDienVien(String dienVien) {
		this.dienVien = dienVien;
	}

}
