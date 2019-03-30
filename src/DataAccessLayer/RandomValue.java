package DataAccessLayer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import DataObject.ChiTietHoaDon;
import DataObject.DiaNhac;
import DataObject.DiaPhim;
import DataObject.HoaDon;
import DataObject.KhachHang;
import DataObject.NhanVien;
import DataObject.Sach;

/**
 * @truong truong
 * 
 *         tạo ra hàng loạt các câu lệnh thêm sản phẩm vào arraylist
 */
public class RandomValue {

	public static void main(String[] args) {
		// random ra 300 san pham bat ki
		System.out.println("Bạn hãy nhập lựa chọn từ 1 đến ..cho phù hợp");
		System.out.println("1: ghi 300 câu lệnh thêm sách ra file: ");
		System.out.println("2: ghi 300 câu lệnh thêm đĩa nhạc ra file: ");
		System.out.println("3: ghi 300 câu lệnh thêm đĩa phim ra file: ");
		System.out.println("4: ghi 100 câu lệnh thêm hóa đơn ra file: ");
		int choose = 0;
		Scanner scan = new Scanner(System.in);
		choose = scan.nextInt();
		if (choose == 1) {
			randomSach();
		} else if (choose == 2) {
			randomCDNhac();
		} else if (choose == 3) {
			randomCDPhim();
		} else if (choose == 4) {
			randomHoaDon();
		}
	}

	/**
	 * ghi hóa đơn
	 */
	public static void randomHoaDon() {
		Random rand = new Random();
		StringBuilder sb = null;
		try {
			FileOutputStream fout = new FileOutputStream("data/command.txt", true);
			OutputStreamWriter out = new OutputStreamWriter(fout, StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(out);
			bw.write("\n\n\n");
			for (int i = 1; i <= 200; i++) {
				sb = new StringBuilder();
				sb.append("listSP= new ArrayList<>(); \n ");
				// listSP.add(new ChiTietHoaDon(lsNhac.get(index),1, soLuong));
				// listSP.add(new ChiTietHoaDon(lsNhac.get(index), soLuong));
				// listSP.add(new ChiTietHoaDon(lsNhac.get(index), soLuong));
				// listSP.add(new ChiTietHoaDon(lsNhac.get(index), soLuong));
				// list.add(new HoaDon(maHDBan, lsNV.get(index),
				// lsKH.get(index), new Date(2017-1900, month, date,h,m,s), listSP));
				int soSP = rand.nextInt(4) + 1;
				for (int j = 1; j <= soSP; j++) {
					sb.append("listSP.add(new ChiTietHoaDon( ");
					int loaiSP = rand.nextInt(3);
					switch (loaiSP) {
					case 0:
						sb.append("lsSach");
						break;
					case 1:
						sb.append("lsNhac");
						break;
					case 2:
						sb.append("lsPhim");
						break;
					default:
						break;
					}
					sb.append(".get(" + rand.nextInt(280));
					sb.append("),");
					switch (loaiSP) {
					case 0:
						sb.append("\"Sách\",");
						break;
					case 1:
						sb.append("\"Đĩa nhạc\",");
						break;
					case 2:
						sb.append("\"Đĩa phim\",");
						break;
					default:
						break;
					}
					sb.append((1 + rand.nextInt(5)) + ")); \n");
				}
				sb.append("list.add(new HoaDon(\"hd" + i);
				sb.append("\",lsNV.get(" + rand.nextInt(20));
				sb.append("), lsKH.get(" + rand.nextInt(30));
				sb.append("), new Date(2017-1900, " + rand.nextInt(12));
				sb.append("," + (1 + rand.nextInt(27)));
				sb.append(","+rand.nextInt(23));
				sb.append(","+rand.nextInt(59));
				sb.append(","+rand.nextInt(59));
				sb.append("),listSP)); \n");
				bw.write(sb.toString());
			}
			bw.close();
			out.close();
			fout.close();
		} catch (Exception e) {
		}
	}

	/**
	 * tao ra 300 cau lenh list.add(new Sach(..)); ghi vao file command.txt
	 */
	public static void randomSach() {

		String[] tacGia = new String[] { "Nhiều tác giả", "Võ nguyên giáp", "Bruce fleet & alton gansky", "Lương duy thứ", "Nguyễn ngọc ký", "Nguyễn nhật ánh", "Thái bá tân", "Quang dũng",
				"Hoài thanh . hoài chân", "Thanh trúc", "Hạnh phi. kiến văn", "Nguyên ngọc", "Ma văn kháng", "Xuân sách", "Trang thế hy", "Vladimir levshin", "Alexander romanovich belyaev",
				"Various Articles", "Morris & goscinny", "Andy stanton ,david tazzyman", "Clive cussler", "Michele VII Ducas", "Maria d'Alania", "Niceforo Briennio" };

		String[] nxb = new String[] { "Nxb thời đại", "Nxb văn học", "Nxb phụ nữ", "Nxb trẻ", "Nxb hội nhà văn", "Nxb tổng hợp tp.hcm", "Nxb kim đồng", "Nxb hồng đức", "Nxb văn hóa thông tin",
				"Nxb nông nghiệp", "Nxb từ điển bách khoa", "Nxb lao động xã hội", "Văn hóa - thông tin" };

		String[] tensach = new String[] { "Húng nhại", "Con quỷ một giò", "Đồi thiên thu", "Mối tình truyền kiếp", "Hồi chuông gọi hồn", "Viên kim cương báo oán - chuyện không kể lúc nửa đêm",
				"Mộ chàng xác thiếp", "Truyện kinh dị chọn lọc - bóng người trong đêm", "Cuộc đời chim sẻ - tập 1: rơi xuống tóc anh", "Đất nước đứng lên", "365 ngày yêu", "Thế kỷ bị mất",
				"Bàn tay nhỏ dưới mưa", "Võ sĩ lên đài", "Chảy qua bóng tố", "Mỗi nhà văn một tác phẩm - đất lửa", "Lá nằm trong lá ( bìa cứng )", "Sbc là săn bắt chuột", "Thế giới c",
				"Beloved oxford", "Bởi những ngày chúng mình chưa có", "Đảo mộng mơ - (bìa cứng)", "Cơn giông - giải thưởng văn học asean 2006", "Thục nữ pk xã hội đen", "Bạch đằng dậy sóng (tập 1)",
				"Tiếng gọi tình yêu (tiểu thuyết)", "Tuyển tập vũ trọng phụng (trọn bộ 2 cuốn)", "Nhắm mắt thấy paris (tb)", "đệ nhất phu nhân trần lệ xuân",
				"Cho tôi xin một vé đi tuổi thơ (giải thưởng văn học asean))", "Trở lại tìm nhau", "Frankenstein", "Open the window, eyes closed", "Ôn thi ccna trong 24h",
				"Tự học adobe illustrator cs5", "Revit architecture 2011 từ a đến z - tập 1 (phiên bản mới)", "Sketchup & vray trong thiết kế kiến trúc",
				"Photoshop dành cho người bắt đầu - dùng cho cả 2 phiên bản photoshop cs5 & cs6 - tập 1", "Kỹ thuật vi xử lý & lập trình assembly cho hệ vi xử lý",
				"Mental ray giáo trình thiết kế nội - ngoại thất với v- ray 3ds max", "Vẽ 3d & gia công khuôn với inventor 10 & pro/engineer wildfire (tủ sách hala)",
				"Tiếng anh chuyên nghành công nghệ thông tin và kỹ thuật máy tính", "Trò chơi vương quyền - tập 3c: tử hôn", "Ba ngày ở nước tí hon", "Những vụ kỳ án của sherlock holmes",
				"John carter và thống soái hỏa tinh", "Marvel comic ep1", "Inheritance - di sản thừa kế - tập 1 (phần tiếp theo của hỏa kiếm)", "Hỏa ngục",
				"Thám tử lừng danh con nan - tập 1: lá thư thách đấu gửi kudo ", "Emily ở trang trại trăng non", "Mật mã tây tạng - cuộc truy tìm kho báu ngàn năm của phật giáo tây ",
				"Ma cà rồng ở dallas", "Sự thật về hòn đá phù thủy", "Demonata - tập 6: sự khải huyền của quỷ", "Chờ một ngày nắng", "Tóm gọn sói già phố wall", "Truyện ngắn jack london" };

		String[] loai = new String[] { "Sách văn học", "Cổ tích", "Truyện dài", "Tiểu thuyết", "Truyện ngắn", "Thơ", "Truyện cười", "Phóng sự", "Sách lịch sử", "Truyện viễn tưởng", "Manga" };

		String[] gianHang = new String[] { "a1", "a2", "a3", "b", "c", "d" };
		String sb = null;
		try {

			Random rand = new Random();
			FileOutputStream fout = new FileOutputStream("data/command.txt", true);
			OutputStreamWriter out = new OutputStreamWriter(fout, StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(out);
			bw.write("\n\n");
			for (int i = 1; i <= 300; i++) {
				//new Sach(maSP, tenSP, loai, gianHang, soLuong, giaMua, giaBan, chietKhau, nhaXuatBan, tacGia)
				int giamua = rand.nextInt(200) + 1;
				sb = "list.add(new Sach(\"s" + i + "\",\"" + tensach[rand.nextInt(tensach.length)] + "\",\"" + loai[rand.nextInt(loai.length)] + "\",\"" + gianHang[rand.nextInt(gianHang.length)]
						+ "\"," +(50+ rand.nextInt(200)) + "," + giamua + "000," + (giamua +50+ rand.nextInt(50)) + "000," + rand.nextInt(10) + ",\"" + nxb[rand.nextInt(nxb.length)] + "\",\""
						+ tacGia[rand.nextInt(tacGia.length)] + "\")); \n";

				bw.write(sb);
			}
			bw.close();
			out.close();
			fout.close();
		} catch (Exception e) {
		}

	}

	/**
	 * tao ra 300 cau lenh list.add(new DiaNhac(..)); ghi vao file command.txt
	 */
	public static void randomCDNhac() {
		// new DiaNhac(maSP, tenSP, loai, gianHang, soLuong, giaMua, giaBan,
		// chietKhau, caSi, nhaSanXuat)
		String[] ten = new String[] { "RELAX PIANO VOL.7 - TRÁI TIM MÙA THU", "CA NHẠC THIẾU NHI ĐẶC SẮC - QUÀ CỦA BA", "HÒA TẤU NHỮNG TÌNH KHÚC BẤT TỬ VOL.16 - MƯA NGÂU",
				"TUẤN HƯNG - LIVE SHOW RANH GIỚI & TÌNH YÊU", "HÒA TẤU GUITAR VOL.5", "PHÍA KHÔNG NGƯỜI", "ĐÔI MẮT NGƯỜI XƯA ", "FRAGILE ", "TOP HIT LÀN SÓNG XANH", "Tình quê",
				"LIVE SHOW: CUNG ĐÀN XƯA", "HÃY NÓI LỜI YÊU NHAU", "ANH BA NGỐ MIỀN TÂY", "Tuổi thần tiên", "Saigon Radio", "HÒa tấu ", "Giấc mơ tôi", "Bức thư tình thứ 5", "Quê hương",
				"Hòa tấu Mozart", "Ca nhạc thiếu nhi", "Nửa trái tim", "Hòa tấu acostic", "Love songs", "Nhạc sống", "The best ABBA", "The best Beatle", "MERRY CHRISTMAS NO. 2 - NON STOP",
				"Thánh ca buồn", "Những bài ca không quên" };

		String[] loai = new String[] { "Nhạc trẻ", "Nhạc trữ tình", "Nhạc hòa tấu/cổ điển", "Nhạc thiếu nhi", "Nhạc cách mạng", "Nhạc quê hương", "Nhạc dân tộc", "DJ Nonstop", "Nhạc quốc tế",
				"Nhạc Bolero", "Cải lương", "Nhạc Trịnh", "Nhạc xuân", "Nhạc rap", "Nhạc dance" };
		String[] gianHang = new String[] { "f1", "f2", "f3", "f4", "h", "g4", "g5", "g6", "g8", "k2", "k3", "m", "n" };
		String[] caSy = new String[] { "Nhiều Nghệ Sỹ", "Tuấn Hưng", "Vĩnh Tâm", "Hoàng Quyên", "Tùng Dương", "Ngọc Khuê", "Hà Trần", "Thanh Lam", "Mỹ Tâm", "Mr. Đàm", "Lê Hiếu", "Lưu Chí Vỹ",
				"Khánh Phương", "Thủy Tiên", "Hà Anh Tuấn", "Phạm Toàn Thắng", "Quang Lê", "Đông Nhi", "Đen", "Rum", "Uni5", "Sơn Tùng", "Khắc Việt", "Chi dân", "Phan Mạnh Quỳnh" };
		String[] nhaSanXuat = new String[] { "Trùng Dương Audio & Video", "Phương Nam Phim", "Công Ty TNHH Một Thành Viên Giải Trí Hát", "Nhà Sách Phương Nam", "Bách Việt", "Sóng vàng production",
				"Nguyễn Hải Phong studio", "AVATAR Entertainment", "Khắc Hưng", "Masew", "Dj tít", "Masmello", "Chainsmoker", "NCS", "YG entertainment", "Pew pew studio" };
		String sb = null;
		try {
			Random rand = new Random();
			FileOutputStream fout = new FileOutputStream("data/command.txt", true);
			OutputStreamWriter out = new OutputStreamWriter(fout, StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(out);
			bw.write("\n\n");
			for (int i = 1; i <= 300; i++) {
				int giamua = rand.nextInt(200) + 1;
				sb = "list.add(new DiaNhac(\"n" + i + "\",\"" + ten[rand.nextInt(ten.length)] + "\",\"" + loai[rand.nextInt(loai.length)] + "\",\"" + gianHang[rand.nextInt(gianHang.length)] + "\","
						+(50+ rand.nextInt(200)) + "," + giamua + "000," + (giamua +50+ rand.nextInt(50)) + "000," + rand.nextInt(10) + ",\"" + caSy[rand.nextInt(caSy.length)] + "\",\""
						+ nhaSanXuat[rand.nextInt(nhaSanXuat.length)] + "\")); \n";

				bw.write(sb);
			}
			bw.close();
			out.close();
			fout.close();
		} catch (Exception e) {
		}

	}

	/**
	 * tao ra 300 cau lenh list.add(new DiaPhim(..)); ghi vao file command.txt
	 */
	public static void randomCDPhim() {
		// new DiaPhim(maSP, tenSP, loai, gianHang, soLuong, giaMua, giaBan,
		// chietKhau, dienVien, daoDien)
		String[] ten = new String[] { "Mẹ chồng nàng dâu", "Dòng sông không trở lại", "Người phán xử", "Doraemon", "Cô giáo Thảo", "Tiếu ngạo giang hồ", "Thor 3", "The Avengers", "Spider man 2",
				"Khuynh thế hoàng phi", "Thiên hạ", "Cảnh sát hình sự", "Fast and Furious 8", "Nằm vùng trở về" };

		String[] loai = new String[] { "Hoạt hình", "Tâm lý xã hội", "Kiếm hiệp", "Hành động", "Khoa học viễn tưởng", "Cổ trang", "Lãng mạn", "Tình cảm 18+" };
		String[] gianHang = new String[] { "d1", "d2", "d3", "d4", "e1", "e2", "e3", "e4" };
		String[] dienVien = new String[] { "Hoàng Dũng", "Hoàng Thùy Linh", "Chris Evans", "Robert Downey", "Kim Dung", "Gal Gadot", "Đan Lê", "Scarlett Johansson", "Lâm Tâm Như", "Chris Hemsworth",
				"Xuka", "Trần Kiều Ân", "Tobey Maguire", "Chu Hùng", "Hoắc Kiến Hoa", "Hoàng Thùy Linh", "Vin Diesel", "Việt Anh", "Nobita" };
		String[] daoDien = new String[] { "Fujiko f Fujio", "Joss Whedon", "James Wan", "Taika Waitini", "Kim Dung", "Nhiều đạo diễn", "Lý Thuần", "Trần Lâm", "Sam Raimi" };
		String sb = null;
		try {
			Random rand = new Random();
			FileOutputStream fout = new FileOutputStream("data/command.txt", true);
			OutputStreamWriter out = new OutputStreamWriter(fout, StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(out);
			bw.write("\n\n");
			for (int i = 1; i <= 300; i++) {
				int giamua = rand.nextInt(200) + 1;
				sb = "list.add(new DiaPhim(\"p" + i + "\",\"" + ten[rand.nextInt(ten.length)] + "\",\"" + loai[rand.nextInt(loai.length)] + "\",\"" + gianHang[rand.nextInt(gianHang.length)] + "\","
						+(50+ rand.nextInt(200)) + "," + giamua + "000," + (giamua +50+ rand.nextInt(50)) + "000," + rand.nextInt(10) + ",\"" + daoDien[rand.nextInt(daoDien.length)] + "\",\""
						+ dienVien[rand.nextInt(dienVien.length)] + "\")); \n";

				bw.write(sb);
			}
			bw.close();
			out.close();
			fout.close();
		} catch (Exception e) {
		}

	}

}
