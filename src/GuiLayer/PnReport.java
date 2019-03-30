package GuiLayer;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import DataAccessLayer.DataAccess;
import DataAccessLayer.DataNhanVien;
import DataObject.ChiTietHoaDon;
import DataObject.HoaDon;
import DataObject.NhanVien;

import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.text.SimpleDateFormat;
import java.awt.GridLayout;

/**
 * @author truong
 *
 *         vẽ biểu đồ thống kê doanh thu, lãi. số sp bán được
 */
public class PnReport extends JPanel {

	// GUI field
	private JDateChooser date1 = new JDateChooser(), date2 = new JDateChooser();
	private JComboBox cbNhomThoiGian, cbNhomDoanhThu, cbNhomSanPham;
	private JButton btnThongKe;
	private JFreeChart chart;
	private ChartPanel pnChart;
	private JPanel panel1;
	private JScrollPane scrollPane;
	private JLabel lbTongDoanhThu, lbTongLoiNhuan, lbTongSPbanRa;

	// data field
	private DataAccess<HoaDon> data;
	private DataNhanVien nv;
	private Calendar calendar = Calendar.getInstance();
	private SimpleDateFormat formatDDMM = new SimpleDateFormat("dd/MM");
	private Date dateStart, dateEnd;
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private int count, countMax = 19;
	private int[][] tongCong = new int[3][4];
	private ColumChart[] soLieu=new ColumChart[0];
	private String[] loai = new String[] { "Sách", "Đĩa nhạc", "Đĩa phim" };
	private String[] loaiSP = new String[] { "Sách", "Đĩa nhạc", "Đĩa phim", "Tất cả sản phẩm" };
	private String[] loaiBD = new String[] { "Doanh thu", "Số sản phẩm bán ra", "Lợi nhuận" };
	private JLabel labelTongLuong;

	/**
	 * Create the panel.
	 */
	public PnReport(DataAccess<HoaDon> data, DataNhanVien nv) {
		this.data = data;
		this.nv=nv;
//		setPreferredSize(new Dimension(800, 600));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Thống kê tài chính");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewLabel);

		JPanel panel = new JPanel();
		add(panel);

		JLabel lb1 = new JLabel("  Từ ngày  ");
		panel.add(lb1);
		panel.add(date1);

		JLabel bl2 = new JLabel("  Đến ngày  ");
		panel.add(bl2);
		panel.add(date2);

		JLabel lb3 = new JLabel("  Chia theo  ");
		panel.add(lb3);

		cbNhomThoiGian = new JComboBox();
		panel.add(cbNhomThoiGian);

		JLabel lb4 = new JLabel("  Nội dung hiển thị");
		panel.add(lb4);

		cbNhomDoanhThu = new JComboBox();
		panel.add(cbNhomDoanhThu);

		btnThongKe = new JButton("Vẽ biểu đồ");
		panel.add(btnThongKe);

		panel1 = new JPanel();
		add(panel1);
		panel1.setLayout(new GridLayout(1, 0, 0, 0));

		scrollPane = new JScrollPane();
		panel1.add(scrollPane);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(null, "T\u1ED5ng h\u1EE3p", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		add(panel2);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		JPanel panel12 = new JPanel();
		panel12.setBorder(new EmptyBorder(0, 30, 0, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panel12.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel2.add(panel12);
		
		JLabel lb78 = new JLabel("Tổng tiền lương nhân viên");
		lb78.setPreferredSize(new Dimension(180, 18));
		lb78.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel12.add(lb78);
		
		labelTongLuong = new JLabel("");
		labelTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel12.add(labelTongLuong);

		JPanel panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(0, 30, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panel3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel2.add(panel3);

		JLabel lb5 = new JLabel("Tổng hợp theo");
		lb5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb5.setPreferredSize(new Dimension(180, 17));
		panel3.add(lb5);

		cbNhomSanPham = new JComboBox();
		cbNhomSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel3.add(cbNhomSanPham);

		JPanel panel8 = new JPanel();
		panel2.add(panel8);
		panel8.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel4 = new JPanel();
		panel8.add(panel4);
		panel4.setBorder(new EmptyBorder(0, 30, 0, 0));
		FlowLayout flowLayout11 = (FlowLayout) panel4.getLayout();
		flowLayout11.setAlignment(FlowLayout.LEFT);

		JLabel lb6 = new JLabel("Tổng doanh thu");
		lb6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel4.add(lb6);
		lb6.setPreferredSize(new Dimension(180, 17));

		lbTongDoanhThu = new JLabel("  ");
		lbTongDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel4.add(lbTongDoanhThu);

		JPanel panel5 = new JPanel();
		panel8.add(panel5);
		panel5.setBorder(new EmptyBorder(0, 30, 0, 0));
		FlowLayout flowLayout2 = (FlowLayout) panel5.getLayout();
		flowLayout2.setAlignment(FlowLayout.LEFT);

		JLabel lb7 = new JLabel("Tổng lợi nhuận");
		lb7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel5.add(lb7);
		lb7.setPreferredSize(new Dimension(180, 17));

		lbTongLoiNhuan = new JLabel("  ");
		lbTongLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel5.add(lbTongLoiNhuan);

		JPanel panel9 = new JPanel();
		panel2.add(panel9);
		panel9.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel6 = new JPanel();
		panel9.add(panel6);
		panel6.setBorder(new EmptyBorder(0, 30, 0, 0));
		FlowLayout flowLayout3 = (FlowLayout) panel6.getLayout();
		flowLayout3.setAlignment(FlowLayout.LEFT);

		JLabel lb9 = new JLabel("Tổng sản phẩm bán ra");
		lb9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel6.add(lb9);
		lb9.setPreferredSize(new Dimension(180, 17));

		lbTongSPbanRa = new JLabel("  ");
		lbTongSPbanRa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel6.add(lbTongSPbanRa);

		JPanel panel7 = new JPanel();
		panel9.add(panel7);
		panel7.setBorder(new EmptyBorder(0, 30, 0, 0));
		FlowLayout flowLayout4 = (FlowLayout) panel7.getLayout();
		flowLayout4.setAlignment(FlowLayout.LEFT);
		cbNhomThoiGian.setModel(new DefaultComboBoxModel(new String[] { "Ngày", "Tuần", "Tháng" }));
		cbNhomSanPham.setModel(new DefaultComboBoxModel(loaiSP));
		cbNhomDoanhThu.setModel(new DefaultComboBoxModel(loaiBD));

		chart = ChartFactory.createBarChart3D("", "Nhóm sản phẩm", "", new DefaultCategoryDataset());
		pnChart = new ChartPanel(chart);
		pnChart.setPreferredSize(new Dimension(800, 300));
		scrollPane.setViewportView(pnChart);		

		labelTongLuong.setText(tinhTongTienLuong()+" VNĐ");
		addListener();
	}

	/**
	 * bắt các sự kiện trên giao diện
	 */
	private void addListener() {
		cbNhomDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tinhToan();
			}
		});
		cbNhomSanPham.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				thongKeTatCa();
			}
		});
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tinhToan();
			}
		});
	}

	/**
	 * chọn cách tính doanh thu theo ngày, hay tuần, tháng
	 */
	private void tinhToan() {
		boolean isValid = chooserDate();
		if (dateStart != null && dateEnd != null && isValid) {
			int index = cbNhomThoiGian.getSelectedIndex();
			if (index == 0) {
				chiaTheoNgay();
			} else if (index == 1) {
				chiaTheoTuan();
			} else if (index == 2) {
				chiaTheoThang();
			}
			inBieuDo();
			thongKeTatCa();
		}
	}

	/**
	 * thêm biểu đồ vào panel
	 */
	private void inBieuDo() {
		chart = taoBieuDoCot();
		pnChart = new ChartPanel(chart);
		int width = count * 62;
		if (width > 1200) {
			width = 1200;
		} else if (width < 800) {
			width = 800;
		}
		pnChart.setPreferredSize(new Dimension(width, 300));
		scrollPane.setViewportView(pnChart);
		panel1.removeAll();
		panel1.add(scrollPane);
		validate();
		repaint();
	}

	/**
	 * kiểm tra hơp lệ ngày tháng
	 */
	private boolean chooserDate() {
		try {
			dateStart = setTo0hour(date1.getDate());
			dateEnd = setTo23Hour(date2.getDate());
			if (dateStart.compareTo(dateEnd) > 0) {
				throw new Exception();
			}
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bạn cần chọn ngày bắt đầu nhỏ hơn ngày kết thúc", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	/**
	 * tính toán các số liệu tài chính theo ngày
	 */
	private void chiaTheoNgay() {
		Date end = setTo0hour(dateEnd);
		Date start = setTo0hour(dateStart);
		count = (int) ((end.getTime() - start.getTime()) / 86400000.0) + 1;
		soLieu = new ColumChart[count];
		for (int i = 0; i < soLieu.length; i++) {
			calendar.setTime(end);
			calendar.add(Calendar.DATE, -i);
			Date date = calendar.getTime();
			String label = formatDDMM.format(date);
			soLieu[i] = new ColumChart();
			soLieu[i].setLabel(label);
		}
		for (HoaDon hoaDon : data.getList()) {
			Date date = hoaDon.getNgayBan();
			if (date.compareTo(dateStart) >= 0 && date.compareTo(dateEnd) <= 0) {
				Date temp = setTo0hour(date);
				int index = (int) ((end.getTime() - temp.getTime()) / 86400000.0);
				for (ChiTietHoaDon sp : hoaDon.getList()) {
					for (int i = 0; i < 3; i++) {
						if (sp.getLoaiSanPham().equals(loai[i])) {
							soLieu[index].getData()[0][i] += sp.getThanhTien();
							soLieu[index].getData()[1][i] += sp.getSoLuong();
							int tienLai = sp.getThanhTien() - sp.getSoLuong() * sp.getSanPham().getGiaMua();
							soLieu[index].getData()[2][i] += tienLai;
						}
					}
				}
			}
		}
	}

	/**
	 * tính toán các số liệu tài chính theo tuần
	 */
	private void chiaTheoTuan() {
		Date end = setToMonday(dateEnd);
		Date start = setToMonday(dateStart);
		count = (int) ((end.getTime() - start.getTime()) / 86400000.0 / 7.0) + 1;
		soLieu = new ColumChart[count];
		for (int i = 0; i < soLieu.length; i++) {
			calendar.setTime(end);
			calendar.add(Calendar.DATE, -i * 7);
			Date date = calendar.getTime();
			int week = calendar.get(Calendar.WEEK_OF_YEAR);
			soLieu[i] = new ColumChart();
			soLieu[i].setLabel("W" + week);
		}
		for (HoaDon hoaDon : data.getList()) {
			Date date = hoaDon.getNgayBan();
			if (date.compareTo(dateStart) >= 0 && date.compareTo(dateEnd) <= 0) {
				Date temp = setToMonday(date);
				int index = (int) ((end.getTime() - temp.getTime()) / 86400000.0 / 7.0);
				for (ChiTietHoaDon sp : hoaDon.getList()) {
					for (int i = 0; i < 3; i++) {
						if (sp.getLoaiSanPham().equals(loai[i])) {
							soLieu[index].getData()[0][i] += sp.getThanhTien();
							soLieu[index].getData()[1][i] += sp.getSoLuong();
							int tienLai = sp.getThanhTien() - sp.getSoLuong() * sp.getSanPham().getGiaMua();
							soLieu[index].getData()[2][i] += tienLai;
						}
					}
				}
			}
		}
	}

	/**
	 * tính toán các số liệu tài chính theo tháng
	 */
	private void chiaTheoThang() {
		Date start = setToDayOne(dateStart);
		Date end = setToDayOne(dateEnd);
		count = monthsBetweenTwoDate(start, end) + 1;
		soLieu = new ColumChart[count];
		for (int i = 0; i < soLieu.length; i++) {
			calendar.setTime(end);
			calendar.add(Calendar.MONTH, -i);
			Date date = calendar.getTime();
			int month = calendar.get(Calendar.MONTH) + 1;
			soLieu[i] = new ColumChart();
			soLieu[i].setLabel("Th" + month);
		}
		for (HoaDon hoaDon : data.getList()) {
			Date date = hoaDon.getNgayBan();
			if (date.compareTo(dateStart) >= 0 && date.compareTo(dateEnd) <= 0) {
				Date temp = setToDayOne(date);
				int index = monthsBetweenTwoDate(temp, end);
				for (ChiTietHoaDon sp : hoaDon.getList()) {
					for (int i = 0; i < 3; i++) {
						if (sp.getLoaiSanPham().equals(loai[i])) {
							soLieu[index].getData()[0][i] += sp.getThanhTien();
							soLieu[index].getData()[1][i] += sp.getSoLuong();
							int tienLai = sp.getThanhTien() - sp.getSoLuong() * sp.getSanPham().getGiaMua();
							soLieu[index].getData()[2][i] += tienLai;
						}
					}
				}
			}
		}
	}

	/**
	 * thêm dữ liệu cho biểu đồ cột
	 */
	private CategoryDataset taoDuLieuCot() {
		int loaiBieuDo = cbNhomDoanhThu.getSelectedIndex();
		if (count > countMax) {
			JOptionPane.showMessageDialog(null, "Lưu ý biểu đồ có số cột tối đa là " + countMax);
			count = countMax;
		}
		dataset = new DefaultCategoryDataset();
		for (int i = count - 1; i >= 0; i--) {
			for (int j = 0; j < 3; j++) {
				dataset.addValue(soLieu[i].getData()[loaiBieuDo][j], loai[j], soLieu[i].getLabel());
			}
		}
		return dataset;
	}

	/**
	 * @return barChart
	 */
	private JFreeChart taoBieuDoCot() {
		JFreeChart barChart = ChartFactory.createBarChart3D("", "Nhóm sản phẩm", loaiBD[cbNhomDoanhThu.getSelectedIndex()], taoDuLieuCot());
		return barChart;
	}

	/**
	 * thống kê tổng doanh thu, lãi, so luong cua3 3 loại sản phẩm
	 */
	private void thongKeTatCa() {
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				tongCong[k][j] = 0;
			}
		}
		for (int i = 0; i < soLieu.length; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					tongCong[k][j] += soLieu[i].getData()[k][j];
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			int tong = 0;
			for (int j = 0; j < 3; j++) {
				tong += tongCong[i][j];
			}
			tongCong[i][3] = tong;
		}
		int loaiSP = cbNhomSanPham.getSelectedIndex();
		lbTongDoanhThu.setText(tongCong[0][loaiSP] + " VNĐ");
		lbTongSPbanRa.setText(tongCong[1][loaiSP] + " sản phẩm");
		lbTongLoiNhuan.setText(tongCong[2][loaiSP] + " VNĐ");
	}

	/**
	 * đưa thời gian về 0h00
	 */
	private Date setTo0hour(Date time) {
		Date date = new Date(time.getTime());
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	/**
	 * đưa ngày về 23h59'59
	 */
	private Date setTo23Hour(Date time) {
		Date date = new Date(time.getTime());
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	}

	/**
	 * đưa ngày về đầu tháng
	 */
	private Date setToDayOne(Date time) {
		Date temp = setTo0hour(time);
		temp.setDate(1);
		return calendar.getTime();
	}

	/**
	 * đưa ngày về ngày thứ hai
	 */
	private Date setToMonday(Date time) {
		calendar.setTime(setTo0hour(time));
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DATE, Calendar.MONDAY - weekday);
		Date temp = calendar.getTime();
		return temp;
	}

	/**
	 * đếm số tháng giữa 2 ngày
	 */
	private int monthsBetweenTwoDate(Date s1, Date s2) {
		final Calendar d1 = Calendar.getInstance();
		d1.setTime(s1);
		final Calendar d2 = Calendar.getInstance();
		d2.setTime(s2);
		int diff = (d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR)) * 12 + d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
		return diff;
	}
	
	/**
	 * tính tổng lương nhân viên
	 */
	private long tinhTongTienLuong(){
		long sum=0;
		for (NhanVien nhanVien : nv.getList()) {
			sum+=nhanVien.getLuong();
		}
		return sum;
	}
}

/**
 * @author truong
 *
 *         lưu giá trị của biểu đồ ( doanh thu, lãi, số lượng bán)
 */
class ColumChart {
	private String label; // tên của 1 cột

	private int[][] data = new int[3][3]; // lưu doanh thu, lãi, số lượng bán

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
	}

}
