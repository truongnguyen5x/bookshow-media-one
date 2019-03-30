package DataObject;

/**
 * @author truong
 */
public class Sach extends SanPham {
	private String nhaXuatBan, tacGia;

	public Sach(String maSP, String tenSP, String loai, String gianHang, int soLuong, int giaMua, int giaBan, int chietKhau, String nhaXuatBan, String tacGia) {
		super(maSP, tenSP, loai, gianHang, soLuong, giaMua, giaBan, chietKhau);
		this.nhaXuatBan = nhaXuatBan;
		this.tacGia = tacGia;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

}
