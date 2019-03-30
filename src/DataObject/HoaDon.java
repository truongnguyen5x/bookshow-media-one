package DataObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author tai
 *
 *         hóa đơn mua hàng
 */
public class HoaDon implements Serializable, InterfaceHaveID, Cloneable {
	private String maHoaDon;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private Date ngayBan;
	private int tongTien;
	private ArrayList<ChiTietHoaDon> list;

	public HoaDon(String maHDBan, NhanVien nvBanHang, KhachHang khachMua, Date ngayBan, ArrayList<ChiTietHoaDon> list) {
		super();
		this.maHoaDon = maHDBan;
		this.nhanVien = nvBanHang;
		this.khachHang = khachMua;
		this.ngayBan = ngayBan;
		this.list = list;
		tongTien = 0;
		for (ChiTietHoaDon chiTietHoaDon : list) {
			tongTien += chiTietHoaDon.getThanhTien();
		}
	}

	public void setID(String maHDBan) {
		this.maHoaDon = maHDBan;
	}

	public String getID() {
		return maHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHDBan) {
		this.maHoaDon = maHDBan;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nvBanHang) {
		this.nhanVien = nvBanHang;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachMua) {
		this.khachHang = khachMua;
	}

	public Date getNgayBan() {
		return ngayBan;
	}

	public String getStringNgayBan() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return df.format(ngayBan);
	}

	public void setNgayBan(Date ngayBan) {
		this.ngayBan = ngayBan;
	}

	public int getTongTien() {
		return tongTien;
	}

	public ArrayList<ChiTietHoaDon> getList() {
		return list;
	}

	public void setList(ArrayList<ChiTietHoaDon> listSP) {
		this.list = listSP;
	}

	public HoaDon clone() throws CloneNotSupportedException {
		HoaDon result= (HoaDon) super.clone();
		result.khachHang=(KhachHang) this.khachHang.clone();
		result.nhanVien= (NhanVien) this.nhanVien.clone();
		result.list= new ArrayList<>();
		for (ChiTietHoaDon chiTietHoaDon : list) {
			result.list.add(chiTietHoaDon.clone());
		}
		return result;
	}
}
