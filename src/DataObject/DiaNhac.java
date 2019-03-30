package DataObject;

/**
 * @author Thịnh
 */
public class DiaNhac extends SanPham {
	private String caSi, nhaSanXuat;

	public DiaNhac(String maSP, String tenSP, String loai, String gianHang, int soLuong, int giaMua, int giaBan, int chietKhau, String nhaSanXuat, String caSi) {
		super(maSP, tenSP, loai, gianHang, soLuong, giaMua, giaBan, chietKhau);
		this.caSi = caSi;
		this.nhaSanXuat = nhaSanXuat;
	}

	public String getCaSi() {
		return caSi;
	}

	public void setCaSi(String caSi) {
		this.caSi = caSi;
	}

	public String getNhaSanXuat() {
		return nhaSanXuat;
	}

	public void setNhaSanXuat(String nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}

}
