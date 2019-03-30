package GuiLayer;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class PnHelp extends JPanel {
	private CardLayout card = new CardLayout();
	private JButton btnNewButton_4, btnTrGipV, btnTrGipV_1, btnTrGipV_2, btnTrGipV_3, btnTrGipV_4, btnTrGipV_5, btnTrc, btnTipTheo, btnNewButton;
	private JPanel pnNhacHelp2, pnPhimHelp1, pnPhimHelp2, pnNhacHelp1, pnMenuHelp, pnSachHelp, pnCardLayout, pnSachHelp2,panelKhach1,panelKhach2;

	/**
	 * Create the panel.
	 */
	public PnHelp() {
		setPreferredSize(new Dimension(950, 650));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(PnHelp.class.getResource("/img/home.png")));
		panel_3.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_2);

		btnTrc = new JButton("Trước");
		panel_2.add(btnTrc);

		btnTipTheo = new JButton("Tiếp theo");
		panel_2.add(btnTipTheo);

		pnCardLayout = new JPanel();
		add(pnCardLayout, BorderLayout.CENTER);
		pnCardLayout.setLayout(card);

		pnMenuHelp = new JPanel();
		pnCardLayout.add(pnMenuHelp, "help");
		pnMenuHelp.setLayout(null);

		JLabel lblTrGip = new JLabel("Trợ giúp");
		lblTrGip.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblTrGip.setBounds(12, 13, 301, 63);
		pnMenuHelp.add(lblTrGip);

		btnNewButton_4 = new JButton("kho sách");
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBounds(12, 69, 205, 25);
		pnMenuHelp.add(btnNewButton_4);

		btnTrGipV = new JButton("kho đĩa nhạc");
		btnTrGipV.setBorder(null);
		btnTrGipV.setBounds(12, 107, 205, 25);
		pnMenuHelp.add(btnTrGipV);

		btnTrGipV_1 = new JButton("kho đĩa phim");
		btnTrGipV_1.setBorder(null);
		btnTrGipV_1.setBounds(12, 145, 205, 25);
		pnMenuHelp.add(btnTrGipV_1);

		btnTrGipV_2 = new JButton("thanh toán");
		btnTrGipV_2.setBorder(null);
		btnTrGipV_2.setBounds(12, 183, 205, 25);
		pnMenuHelp.add(btnTrGipV_2);

		btnTrGipV_3 = new JButton("hóa đơn");
		btnTrGipV_3.setBorder(null);
		btnTrGipV_3.setBounds(12, 221, 205, 25);
		pnMenuHelp.add(btnTrGipV_3);

		btnTrGipV_4 = new JButton("thống kê");
		btnTrGipV_4.setBorder(null);
		btnTrGipV_4.setBounds(12, 259, 205, 25);
		pnMenuHelp.add(btnTrGipV_4);

		btnTrGipV_5 = new JButton("khách hàng");
		btnTrGipV_5.setBorder(null);
		btnTrGipV_5.setBounds(12, 297, 205, 25);
		pnMenuHelp.add(btnTrGipV_5);

		pnSachHelp = new JPanel();
		pnCardLayout.add(pnSachHelp, "sach1");
		pnSachHelp.setLayout(null);

		JLabel label_4 = new JLabel("Trợ giúp kho sách");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4.setBounds(42, 0, 366, 34);
		pnSachHelp.add(label_4);

		JLabel lblTmSch = new JLabel("Tìm sách: ");
		lblTmSch.setForeground(Color.BLUE);
		lblTmSch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTmSch.setBounds(24, 47, 78, 16);
		pnSachHelp.add(lblTmSch);

		JLabel lblTmSch_1 = new JLabel("để tìm sách ta gõ từ khóa cần tìm vào ô: ");
		lblTmSch_1.setBounds(107, 49, 253, 16);
		pnSachHelp.add(lblTmSch_1);

		JLabel label_5 = new JLabel("");
		label_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_5.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/searchBar.jpg")));
		label_5.setBounds(136, 66, 529, 34);
		pnSachHelp.add(label_5);

		JLabel lblDanhSchCc = new JLabel("danh sách các kết quả sẽ hiện ra: ");
		lblDanhSchCc.setBounds(107, 107, 212, 16);
		pnSachHelp.add(lblDanhSchCc);

		JLabel label_6 = new JLabel("");
		label_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_6.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/searchResultSach.png")));
		label_6.setBounds(136, 126, 438, 208);
		pnSachHelp.add(label_6);

		JLabel lblClickVo = new JLabel("click vào 1 kết quả ta sẽ xem được cuốn sách của kết quả đó");
		lblClickVo.setBounds(107, 347, 407, 16);
		pnSachHelp.add(lblClickVo);

		JLabel lblXem = new JLabel("Để xem 1 sách:");
		lblXem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXem.setForeground(Color.BLUE);
		lblXem.setBounds(24, 386, 133, 27);
		pnSachHelp.add(lblXem);

		JLabel lblClickVoSch = new JLabel(" click vào sách đó ở trên bảng");
		lblClickVoSch.setBounds(153, 393, 180, 16);
		pnSachHelp.add(lblClickVoSch);

		JLabel lblSpXp = new JLabel("Để sắp xếp sách:");
		lblSpXp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSpXp.setForeground(Color.BLUE);
		lblSpXp.setBounds(24, 415, 133, 27);
		pnSachHelp.add(lblSpXp);

		JLabel label_7 = new JLabel("");
		label_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_7.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/columSach.jpg")));
		label_7.setBounds(66, 444, 671, 34);
		pnSachHelp.add(label_7);

		JLabel lblCcSchTheo = new JLabel("kích đúp vào tên cột cần sắp xếp");
		lblCcSchTheo.setBounds(163, 422, 199, 16);
		pnSachHelp.add(lblCcSchTheo);

		pnSachHelp2 = new JPanel();
		pnCardLayout.add(pnSachHelp2, "sach2");
		pnSachHelp2.setLayout(null);

		JLabel label = new JLabel("");
		label.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/addSach.jpg")));
		label.setBounds(12, 87, 919, 158);
		pnSachHelp2.add(label);

		JLabel lblTrGipKho = new JLabel("Trợ giúp kho sách");
		lblTrGipKho.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrGipKho.setBounds(25, 0, 366, 34);
		pnSachHelp2.add(lblTrGipKho);

		JLabel lblThmSch = new JLabel("Để thêm sách:");
		lblThmSch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThmSch.setForeground(Color.BLUE);
		lblThmSch.setBounds(25, 47, 123, 24);
		pnSachHelp2.add(lblThmSch);

		JLabel lblSauClick = new JLabel("Sau đó click vào nút: ");
		lblSauClick.setForeground(Color.BLACK);
		lblSauClick.setBounds(65, 261, 123, 24);
		pnSachHelp2.add(lblSauClick);

		JButton btnThmSch = new JButton("Thêm sách");
		btnThmSch.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnAddIconSmall.png")));
		btnThmSch.setBounds(186, 261, 140, 25);
		pnSachHelp2.add(btnThmSch);

		JLabel lblLuM = new JLabel("Lưu ý: ");
		lblLuM.setForeground(Color.RED);
		lblLuM.setBounds(65, 284, 53, 24);
		pnSachHelp2.add(lblLuM);

		JLabel lblSa = new JLabel("Để sửa 1 sách:  ");
		lblSa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSa.setForeground(Color.BLUE);
		lblSa.setBounds(25, 350, 165, 16);
		pnSachHelp2.add(lblSa);

		JLabel lblSauSa = new JLabel("Sau đó, sửa các thuộc tính của sách và ấn nút : ");
		lblSauSa.setForeground(Color.BLACK);
		lblSauSa.setBounds(67, 379, 284, 24);
		pnSachHelp2.add(lblSauSa);

		JButton btnSaSch = new JButton("Sửa sách");
		btnSaSch.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnEditIcon.png")));
		btnSaSch.setBounds(344, 379, 133, 25);
		pnSachHelp2.add(btnSaSch);

		JLabel lblXa = new JLabel("Để xóa 1 sách:");
		lblXa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXa.setForeground(Color.BLUE);
		lblXa.setBounds(25, 431, 165, 16);
		pnSachHelp2.add(lblXa);

		JButton btnXaSch = new JButton("Xóa sách");
		btnXaSch.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnDeletetIcon.png")));
		btnXaSch.setBounds(317, 429, 134, 25);
		pnSachHelp2.add(btnXaSch);

		JLabel lblMua = new JLabel("Để mua 1 cuốn sách: ");
		lblMua.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMua.setForeground(Color.BLUE);
		lblMua.setBounds(25, 481, 165, 23);
		pnSachHelp2.add(lblMua);

		JButton btnMua = new JButton("Mua");
		btnMua.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnBuyIcon.png")));
		btnMua.setBounds(328, 482, 97, 25);
		pnSachHelp2.add(btnMua);

		JLabel lblCunSchBn = new JLabel("cuốn sách bạn chọn sẽ có trong giỏ hàng");
		lblCunSchBn.setBounds(428, 486, 243, 16);
		pnSachHelp2.add(lblCunSchBn);

		JLabel lblCniny = new JLabel("cần điền đầy đủ thông tin của sách vào phần thông tin:");
		lblCniny.setBounds(142, 53, 488, 16);
		pnSachHelp2.add(lblCniny);

		JLabel lblMSchThm = new JLabel("mã sách thêm vào cần khác mã các sách trong kho, nếu trùng sẽ có thông báo lỗi");
		lblMSchThm.setBounds(109, 288, 479, 16);
		pnSachHelp2.add(lblMSchThm);

		JLabel lblChnSch = new JLabel("chọn sách đó, và ấn nút: ");
		lblChnSch.setBounds(188, 486, 160, 16);
		pnSachHelp2.add(lblChnSch);

		JLabel lblChnSch_1 = new JLabel("chọn sách đó trước, và ấn nút: ");
		lblChnSch_1.setBounds(143, 433, 182, 16);
		pnSachHelp2.add(lblChnSch_1);

		JLabel lblCnChnSch = new JLabel("cần chọn sách đó trước. Các thông tin của sách sẽ hiện lên ở phần thông tin.");
		lblCnChnSch.setBounds(142, 352, 488, 16);
		pnSachHelp2.add(lblCnChnSch);
		//

		//
		pnNhacHelp1 = new JPanel();
		pnCardLayout.add(pnNhacHelp1, "nhac1");
		pnNhacHelp1.setLayout(null);

		JLabel label_4a = new JLabel("Trợ giúp đĩa nhạc");
		label_4a.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4a.setBounds(42, 0, 366, 34);
		pnNhacHelp1.add(label_4a);

		JLabel lblTmScha = new JLabel("Tìm đĩa nhạc: ");
		lblTmScha.setForeground(Color.BLUE);
		lblTmScha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTmScha.setBounds(24, 42, 121, 24);
		pnNhacHelp1.add(lblTmScha);

		JLabel lblTmSch_1a = new JLabel("để tìm đĩa nhạc ta gõ từ khóa cần tìm vào ô: ");
		lblTmSch_1a.setBounds(134, 49, 328, 16);
		pnNhacHelp1.add(lblTmSch_1a);

		JLabel label_5a = new JLabel("");
		label_5a.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_5a.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/searchBar.jpg")));
		label_5a.setBounds(144, 67, 529, 34);
		pnNhacHelp1.add(label_5a);

		JLabel lblDanhSchCca = new JLabel("danh sách các kết quả sẽ hiện ra: ");
		lblDanhSchCca.setBounds(107, 114, 212, 16);
		pnNhacHelp1.add(lblDanhSchCca);

		JLabel label_6a = new JLabel("");
		label_6a.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_6a.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/searchResultNhac.png")));
		label_6a.setBounds(134, 134, 438, 208);
		pnNhacHelp1.add(label_6a);

		JLabel lblClickVoa = new JLabel("click vào 1 kết quả ta sẽ xem được đĩa nhạc của kết quả đó");
		lblClickVoa.setBounds(107, 355, 407, 16);
		pnNhacHelp1.add(lblClickVoa);

		JLabel lblXema = new JLabel("Để xem 1 đĩa nhạc:");
		lblXema.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXema.setForeground(Color.BLUE);
		lblXema.setBounds(24, 393, 155, 27);
		pnNhacHelp1.add(lblXema);

		JLabel lblClickVoScha = new JLabel(" click vào đĩa nhạc đó ở trên bảng");
		lblClickVoScha.setBounds(177, 400, 248, 16);
		pnNhacHelp1.add(lblClickVoScha);

		JLabel lblSpXpa = new JLabel("Để sắp xếp đĩa nhạc:");
		lblSpXpa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSpXpa.setForeground(Color.BLUE);
		lblSpXpa.setBounds(24, 422, 165, 27);
		pnNhacHelp1.add(lblSpXpa);

		JLabel label_7a = new JLabel("");
		label_7a.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_7a.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/columNhac.jpg")));
		label_7a.setBounds(66, 451, 671, 34);
		pnNhacHelp1.add(label_7a);

		JLabel lblCcSchTheoa = new JLabel("kích đúp vào tên cột cần sắp xếp");
		lblCcSchTheoa.setBounds(192, 430, 199, 16);
		pnNhacHelp1.add(lblCcSchTheoa);

		pnNhacHelp2 = new JPanel();
		pnCardLayout.add(pnNhacHelp2, "nhac2");
		pnNhacHelp2.setLayout(null);

		JLabel labela = new JLabel("");
		labela.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labela.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/addNhac.jpg")));
		labela.setBounds(12, 87, 919, 158);
		pnNhacHelp2.add(labela);

		JLabel lblTrGipKhoa = new JLabel("Trợ giúp đĩa nhạc");
		lblTrGipKhoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrGipKhoa.setBounds(25, 0, 366, 34);
		pnNhacHelp2.add(lblTrGipKhoa);

		JLabel lblThmScha = new JLabel("Để thêm đĩa nhạc:");
		lblThmScha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThmScha.setForeground(Color.BLUE);
		lblThmScha.setBounds(25, 47, 155, 24);
		pnNhacHelp2.add(lblThmScha);

		JLabel lblSauClicka = new JLabel("Sau đó click vào nút: ");
		lblSauClicka.setForeground(Color.BLACK);
		lblSauClicka.setBounds(65, 261, 123, 24);
		pnNhacHelp2.add(lblSauClicka);

		JButton btnThmScha = new JButton("Thêm đĩa nhạc");
		btnThmScha.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnAddIconSmall.png")));
		btnThmScha.setBounds(186, 261, 166, 25);
		pnNhacHelp2.add(btnThmScha);

		JLabel lblLuMa = new JLabel("Lưu ý: ");
		lblLuMa.setForeground(Color.RED);
		lblLuMa.setBounds(65, 284, 53, 24);
		pnNhacHelp2.add(lblLuMa);

		JLabel lblSaa = new JLabel("Để sửa 1 đĩa nhạc:  ");
		lblSaa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSaa.setForeground(Color.BLUE);
		lblSaa.setBounds(25, 350, 165, 24);
		pnNhacHelp2.add(lblSaa);

		JLabel lblSauSaa = new JLabel("Sau đó, sửa các thuộc tính của đĩa nhạc và ấn nút : ");
		lblSauSaa.setForeground(Color.BLACK);
		lblSauSaa.setBounds(67, 379, 326, 24);
		pnNhacHelp2.add(lblSauSaa);

		JButton btnSaScha = new JButton("Sửa đĩa nhạc");
		btnSaScha.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnEditIcon.png")));
		btnSaScha.setBounds(377, 378, 178, 25);
		pnNhacHelp2.add(btnSaScha);

		JLabel lblXaa = new JLabel("Để xóa 1 đĩa nhạc:");
		lblXaa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXaa.setForeground(Color.BLUE);
		lblXaa.setBounds(25, 416, 165, 23);
		pnNhacHelp2.add(lblXaa);

		JButton btnXaScha = new JButton("Xóa đĩa nhạc");
		btnXaScha.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnDeletetIcon.png")));
		btnXaScha.setBounds(388, 416, 134, 25);
		pnNhacHelp2.add(btnXaScha);

		JLabel lblMuaa = new JLabel("Để mua 1 đĩa nhạc: ");
		lblMuaa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMuaa.setForeground(Color.BLUE);
		lblMuaa.setBounds(25, 470, 165, 23);
		pnNhacHelp2.add(lblMuaa);

		JButton btnMuaa = new JButton("Mua");
		btnMuaa.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnBuyIcon.png")));
		btnMuaa.setBounds(328, 471, 97, 25);
		pnNhacHelp2.add(btnMuaa);

		JLabel lblCunSchBna = new JLabel("đĩa nhạc bạn chọn sẽ có trong giỏ hàng");
		lblCunSchBna.setBounds(428, 475, 243, 16);
		pnNhacHelp2.add(lblCunSchBna);

		JLabel lblCninya = new JLabel("cần điền đầy đủ thông tin của đĩa nhạc vào phần thông tin:");
		lblCninya.setBounds(168, 53, 488, 16);
		pnNhacHelp2.add(lblCninya);

		JLabel lblMSchThma = new JLabel("mã đĩa nhạc thêm vào cần khác mã các đĩa nhạc trong kho, nếu trùng sẽ có thông báo lỗi");
		lblMSchThma.setBounds(109, 288, 545, 16);
		pnNhacHelp2.add(lblMSchThma);

		JLabel lblChnScha = new JLabel("chọn đĩa nhạc đó, và ấn nút: ");
		lblChnScha.setBounds(188, 475, 160, 16);
		pnNhacHelp2.add(lblChnScha);

		JLabel lblChnSch_1a = new JLabel("chọn đĩa nhạc đó trước, và ấn nút: ");
		lblChnSch_1a.setBounds(177, 421, 212, 16);
		pnNhacHelp2.add(lblChnSch_1a);

		JLabel lblCnChnScha = new JLabel("cần chọn đĩa nhạc đó trước. Các thông tin của sách sẽ hiện lên ở phần thông tin.");
		lblCnChnScha.setBounds(172, 352, 488, 16);
		pnNhacHelp2.add(lblCnChnScha);
		//

		pnPhimHelp1 = new JPanel();
		pnCardLayout.add(pnPhimHelp1, "phim1");
		pnPhimHelp1.setLayout(null);

		JLabel label_4ab = new JLabel("Trợ giúp đĩa phim");
		label_4ab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4ab.setBounds(42, 0, 366, 34);
		pnPhimHelp1.add(label_4ab);

		JLabel lblTmSchab = new JLabel("Tìm đĩa phim: ");
		lblTmSchab.setForeground(Color.BLUE);
		lblTmSchab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTmSchab.setBounds(24, 42, 121, 24);
		pnPhimHelp1.add(lblTmSchab);

		JLabel lblTmSch_1ab = new JLabel("để tìm đĩa phim ta gõ từ khóa cần tìm vào ô: ");
		lblTmSch_1ab.setBounds(134, 49, 328, 16);
		pnPhimHelp1.add(lblTmSch_1ab);

		JLabel label_5ab = new JLabel("");
		label_5ab.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_5ab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/searchBar.jpg")));
		label_5ab.setBounds(144, 67, 529, 34);
		pnPhimHelp1.add(label_5ab);

		JLabel lblDanhSchCcab = new JLabel("danh sách các kết quả sẽ hiện ra: ");
		lblDanhSchCcab.setBounds(107, 110, 212, 16);
		pnPhimHelp1.add(lblDanhSchCcab);

		JLabel label_6ab = new JLabel("");
		label_6ab.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_6ab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/searchResultPhim.png")));
		label_6ab.setBounds(138, 129, 438, 208);
		pnPhimHelp1.add(label_6ab);

		JLabel lblClickVoab = new JLabel("click vào 1 kết quả ta sẽ xem được đĩa phim của kết quả đó");
		lblClickVoab.setBounds(107, 350, 407, 16);
		pnPhimHelp1.add(lblClickVoab);

		JLabel lblXemab = new JLabel("Để xem 1 đĩa phim:");
		lblXemab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXemab.setForeground(Color.BLUE);
		lblXemab.setBounds(24, 379, 155, 27);
		pnPhimHelp1.add(lblXemab);

		JLabel lblClickVoSchab = new JLabel(" click vào đĩa phim đó ở trên bảng");
		lblClickVoSchab.setBounds(177, 386, 248, 16);
		pnPhimHelp1.add(lblClickVoSchab);

		JLabel lblSpXpab = new JLabel("Để sắp xếp đĩa phim:");
		lblSpXpab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSpXpab.setForeground(Color.BLUE);
		lblSpXpab.setBounds(24, 408, 165, 27);
		pnPhimHelp1.add(lblSpXpab);

		JLabel label_7ab = new JLabel("");
		label_7ab.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_7ab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/columPhim.jpg")));
		label_7ab.setBounds(66, 437, 671, 34);
		pnPhimHelp1.add(label_7ab);

		JLabel lblCcSchTheoab = new JLabel("kích đúp vào tên cột cần sắp xếp");
		lblCcSchTheoab.setBounds(192, 416, 199, 16);
		pnPhimHelp1.add(lblCcSchTheoab);

		pnPhimHelp2 = new JPanel();
		pnCardLayout.add(pnPhimHelp2, "phim2");
		pnPhimHelp2.setLayout(null);

		JLabel labelab = new JLabel("");
		labelab.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/addPhim.jpg")));
		labelab.setBounds(12, 87, 919, 158);
		pnPhimHelp2.add(labelab);

		JLabel lblTrGipKhoab = new JLabel("Trợ giúp đĩa phim");
		lblTrGipKhoab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrGipKhoab.setBounds(25, 0, 366, 34);
		pnPhimHelp2.add(lblTrGipKhoab);

		JLabel lblThmSchab = new JLabel("Để thêm đĩa phim:");
		lblThmSchab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThmSchab.setForeground(Color.BLUE);
		lblThmSchab.setBounds(25, 47, 155, 24);
		pnPhimHelp2.add(lblThmSchab);

		JLabel lblSauClickab = new JLabel("Sau đó click vào nút: ");
		lblSauClickab.setForeground(Color.BLACK);
		lblSauClickab.setBounds(65, 261, 123, 24);
		pnPhimHelp2.add(lblSauClickab);

		JButton btnThmSchab = new JButton("Thêm đĩa phim");
		btnThmSchab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnAddIconSmall.png")));
		btnThmSchab.setBounds(186, 261, 166, 25);
		pnPhimHelp2.add(btnThmSchab);

		JLabel lblLuMab = new JLabel("Lưu ý: ");
		lblLuMab.setForeground(Color.RED);
		lblLuMab.setBounds(65, 284, 53, 24);
		pnPhimHelp2.add(lblLuMab);

		JLabel lblSaab = new JLabel("Để sửa 1 đĩa phim:  ");
		lblSaab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSaab.setForeground(Color.BLUE);
		lblSaab.setBounds(25, 350, 165, 24);
		pnPhimHelp2.add(lblSaab);

		JLabel lblSauSaab = new JLabel("Sau đó, sửa các thuộc tính của đĩa phim và ấn nút : ");
		lblSauSaab.setForeground(Color.BLACK);
		lblSauSaab.setBounds(67, 379, 326, 24);
		pnPhimHelp2.add(lblSauSaab);

		JButton btnSaSchab = new JButton("Sửa đĩa phim");
		btnSaSchab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnEditIcon.png")));
		btnSaSchab.setBounds(377, 378, 178, 25);
		pnPhimHelp2.add(btnSaSchab);

		JLabel lblXaab = new JLabel("Để xóa 1 đĩa phim:");
		lblXaab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXaab.setForeground(Color.BLUE);
		lblXaab.setBounds(25, 425, 165, 23);
		pnPhimHelp2.add(lblXaab);

		JButton btnXaSchab = new JButton("Xóa đĩa phim");
		btnXaSchab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnDeletetIcon.png")));
		btnXaSchab.setBounds(388, 425, 155, 25);
		pnPhimHelp2.add(btnXaSchab);

		JLabel lblMuaab = new JLabel("Để mua 1 đĩa phim: ");
		lblMuaab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMuaab.setForeground(Color.BLUE);
		lblMuaab.setBounds(25, 479, 165, 23);
		pnPhimHelp2.add(lblMuaab);

		JButton btnMuaab = new JButton("Mua");
		btnMuaab.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnBuyIcon.png")));
		btnMuaab.setBounds(328, 480, 97, 25);
		pnPhimHelp2.add(btnMuaab);

		JLabel lblCunSchBnab = new JLabel("đĩa phim bạn chọn sẽ có trong giỏ hàng");
		lblCunSchBnab.setBounds(428, 484, 243, 16);
		pnPhimHelp2.add(lblCunSchBnab);

		JLabel lblCninyab = new JLabel("cần điền đầy đủ thông tin của đĩa phim vào phần thông tin:");
		lblCninyab.setBounds(168, 53, 488, 16);
		pnPhimHelp2.add(lblCninyab);

		JLabel lblMSchThmab = new JLabel("mã đĩa phim thêm vào cần khác mã các đĩa phim trong kho, nếu trùng sẽ có thông báo lỗi");
		lblMSchThmab.setBounds(109, 288, 545, 16);
		pnPhimHelp2.add(lblMSchThmab);

		JLabel lblChnSchab = new JLabel("chọn đĩa phim đó, và ấn nút: ");
		lblChnSchab.setBounds(188, 484, 160, 16);
		pnPhimHelp2.add(lblChnSchab);

		JLabel lblChnSch_1ab = new JLabel("chọn đĩa phim đó trước, và ấn nút: ");
		lblChnSch_1ab.setBounds(177, 430, 212, 16);
		pnPhimHelp2.add(lblChnSch_1ab);

		JLabel lblCnChnSchab = new JLabel("cần chọn đĩa phim đó trước. Các thông tin của sách sẽ hiện lên ở phần thông tin.");
		lblCnChnSchab.setBounds(172, 352, 488, 16);
		pnPhimHelp2.add(lblCnChnSchab);

		//
		JPanel panelThanhToan = new JPanel();
		pnCardLayout.add(panelThanhToan, "mua");
		panelThanhToan.setLayout(null);

		JLabel lblTrGipV = new JLabel("Trợ giúp về thanh toán");
		lblTrGipV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrGipV.setBounds(28, 13, 327, 38);
		panelThanhToan.add(lblTrGipV);

		JLabel lblGiaoDinThanh = new JLabel("Giao diện thanh toán bao gồm thông tin hóa đơn và giỏ hàng");
		lblGiaoDinThanh.setBounds(24, 52, 406, 16);
		panelThanhToan.add(lblGiaoDinThanh);

		JLabel lblThm = new JLabel("Thêm 1 sản phẩm vào giỏ:");
		lblThm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThm.setForeground(Color.BLUE);
		lblThm.setBounds(12, 75, 208, 28);
		panelThanhToan.add(lblThm);

		JLabel label_2 = new JLabel("");
		label_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_2.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/tfMaSanPhamMua.jpg")));
		label_2.setBounds(392, 81, 295, 31);
		panelThanhToan.add(label_2);

		JLabel lblSauChn = new JLabel("sau đó chọn số lượng sản phẩm: ");
		lblSauChn.setBounds(84, 239, 248, 16);
		panelThanhToan.add(lblSauChn);

		JButton btnThmSnPhm = new JButton("Thêm sản phẩm");
		btnThmSnPhm.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnAddIconSmall.png")));
		btnThmSnPhm.setBounds(152, 291, 159, 25);
		panelThanhToan.add(btnThmSnPhm);

		JLabel lblMunThayi = new JLabel("Muốn thay đổi số lượng sản phẩm: ");
		lblMunThayi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMunThayi.setForeground(Color.BLUE);
		lblMunThayi.setBounds(12, 364, 266, 29);
		panelThanhToan.add(lblMunThayi);

		JButton btnNewButton_5 = new JButton("Thêm sản phẩm");
		btnNewButton_5.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnAddIconSmall.png")));
		btnNewButton_5.setBounds(731, 368, 159, 25);
		panelThanhToan.add(btnNewButton_5);

		JLabel lblMunBSn = new JLabel("Muốn bỏ sản phẩm ra khỏi giỏ:");
		lblMunBSn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMunBSn.setForeground(Color.BLUE);
		lblMunBSn.setBounds(12, 435, 266, 38);
		panelThanhToan.add(lblMunBSn);

		JButton btnXaSnPhm = new JButton("Xóa sản phẩm");
		btnXaSnPhm.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnDeletetIcon.png")));
		btnXaSnPhm.setBounds(500, 444, 154, 25);
		panelThanhToan.add(btnXaSnPhm);

		JLabel lblNhpMSn = new JLabel("nhập mã sản phẩm tại ô: ");
		lblNhpMSn.setBounds(215, 83, 167, 16);
		panelThanhToan.add(lblNhpMSn);

		JLabel lblChnSnPhm = new JLabel(" chọn sản phẩm đó trong bảng, và ấn nút: ");
		lblChnSnPhm.setBounds(250, 448, 261, 16);
		panelThanhToan.add(lblChnSnPhm);

		JLabel lblChnSnPhm_1 = new JLabel("chọn sản phẩm đó ở trong bảng, thay đổi số lượng của sản phẩm, và ấn nút : ");
		lblChnSnPhm_1.setBounds(275, 372, 470, 16);
		panelThanhToan.add(lblChnSnPhm_1);

		JLabel lblNuNhpM = new JLabel("nếu nhập mã sản phẩm thành công sẽ có thông báo:");
		lblNuNhpM.setBounds(84, 138, 406, 16);
		panelThanhToan.add(lblNuNhpM);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/tim thay 1 sp.png")));
		lblNewLabel.setBounds(409, 125, 296, 38);
		panelThanhToan.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nếu tìm thấy 2 sản phẩm cùng mã, bạn hãy chọn cái mình muốn trong ô:");
		lblNewLabel_1.setBounds(84, 188, 430, 25);
		panelThanhToan.add(lblNewLabel_1);

		JLabel label_10 = new JLabel("");
		label_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_10.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/loai san pham.png")));
		label_10.setBounds(526, 175, 327, 38);
		panelThanhToan.add(label_10);

		JLabel label_11 = new JLabel("");
		label_11.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_11.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/chon so luong.png")));
		label_11.setBounds(287, 227, 311, 45);
		panelThanhToan.add(label_11);

		JLabel lblRinNt = new JLabel("rồi ấn nút");
		lblRinNt.setBounds(84, 295, 56, 16);
		panelThanhToan.add(lblRinNt);

		JPanel panelThanhToan2 = new JPanel();
		pnCardLayout.add(panelThanhToan2, "name_115451144245698");
		panelThanhToan2.setLayout(null);

		JLabel label_1 = new JLabel("Cuối cùng, ấn nút: ");
		label_1.setBounds(34, 424, 109, 16);
		panelThanhToan2.add(label_1);

		JButton button = new JButton("Mua");
		button.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnBuyIcon.png")));
		button.setBounds(139, 420, 148, 25);
		panelThanhToan2.add(button);

		JLabel label_8 = new JLabel("Sau đó, bạn cần chọn khách hàng bằng cách nhập tên hay mã khách hàng vào ô: ");
		label_8.setBounds(62, 301, 483, 16);
		panelThanhToan2.add(label_8);

		JLabel label_9 = new JLabel("");
		label_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_9.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/ma khach hang.png")));
		label_9.setBounds(220, 330, 353, 46);
		panelThanhToan2.add(label_9);

		JLabel label_12 = new JLabel("Trợ giúp về thanh toán");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_12.setBounds(12, 13, 327, 38);
		panelThanhToan2.add(label_12);

		JLabel lblMuaCc = new JLabel("Để mua các sản phẩm");
		lblMuaCc.setForeground(Color.BLUE);
		lblMuaCc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMuaCc.setBounds(22, 65, 200, 16);
		panelThanhToan2.add(lblMuaCc);

		JLabel lblBnCnNhp = new JLabel("bạn cần nhập mã hóa đơn vào ô:");
		lblBnCnNhp.setBounds(62, 119, 362, 16);
		panelThanhToan2.add(lblBnCnNhp);

		JLabel lblLu = new JLabel("Lưu ý:");
		lblLu.setForeground(Color.RED);
		lblLu.setBounds(62, 248, 56, 16);
		panelThanhToan2.add(lblLu);

		JLabel lblMHan = new JLabel("mã hóa đơn không được trùng với các mã hóa đơn đã có");
		lblMHan.setBounds(102, 248, 421, 16);
		panelThanhToan2.add(lblMHan);

		JLabel label_3 = new JLabel("");
		label_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_3.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/ma hoa don.png")));
		label_3.setBounds(199, 148, 384, 56);
		panelThanhToan2.add(label_3);

		JPanel panelHoaDon = new JPanel();
		pnCardLayout.add(panelHoaDon, "hd");
		panelHoaDon.setLayout(null);

		JLabel lblTrGipV_1 = new JLabel("Trợ giúp về hóa đơn");
		lblTrGipV_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrGipV_1.setBounds(22, 0, 394, 50);
		panelHoaDon.add(lblTrGipV_1);

		JLabel lblXem_1 = new JLabel("Để xem 1 hóa đơn:");
		lblXem_1.setForeground(Color.BLUE);
		lblXem_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXem_1.setBounds(32, 63, 173, 16);
		panelHoaDon.add(lblXem_1);

		JLabel lblChnHan = new JLabel("chọn hóa đơn đó trong bảng:");
		lblChnHan.setBounds(71, 110, 231, 16);
		panelHoaDon.add(lblChnHan);

		JLabel label_13 = new JLabel("");
		label_13.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_13.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/chon hoa don.png")));
		label_13.setBounds(22, 139, 867, 228);
		panelHoaDon.add(label_13);

		JLabel lblCcThngTin = new JLabel("các thông tin của hóa đơn sẽ hiện ra");
		lblCcThngTin.setBounds(71, 391, 377, 16);
		panelHoaDon.add(lblCcThngTin);

		JLabel lblXa_1 = new JLabel("Để xóa 1 hóa đơn:");
		lblXa_1.setForeground(Color.BLUE);
		lblXa_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXa_1.setBounds(22, 460, 183, 16);
		panelHoaDon.add(lblXa_1);

		JLabel lblChnHan_1 = new JLabel("chọn hóa đơn cần xóa, rồi ấn vào nút: ");
		lblChnHan_1.setBounds(175, 462, 241, 16);
		panelHoaDon.add(lblChnHan_1);

		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnDeletetIcon.png")));
		btnXa.setBounds(395, 458, 97, 25);
		panelHoaDon.add(btnXa);

		JPanel panelThongKe = new JPanel();
		pnCardLayout.add(panelThongKe, "thongKe");
		panelThongKe.setLayout(null);

		JLabel lblTrGipV_2 = new JLabel("Trợ giúp về thống kê doanh thu");
		lblTrGipV_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrGipV_2.setBounds(36, 0, 352, 50);
		panelThongKe.add(lblTrGipV_2);

		JLabel lblVBiu = new JLabel("Để vẽ biểu đồ thống kê: ");
		lblVBiu.setForeground(Color.BLUE);
		lblVBiu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVBiu.setBounds(12, 58, 198, 26);
		panelThongKe.add(lblVBiu);

		JLabel lblChnNgyBt = new JLabel("chọn ngày bắt đầu ở:");
		lblChnNgyBt.setBounds(216, 65, 135, 19);
		panelThongKe.add(lblChnNgyBt);

		JLabel lblChnNgyKt = new JLabel("chọn ngày kết thúc ở:");
		lblChnNgyKt.setBounds(216, 123, 135, 19);
		panelThongKe.add(lblChnNgyKt);

		JLabel label_14 = new JLabel("");
		label_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_14.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/ngay bd.png")));
		label_14.setBounds(363, 58, 286, 42);
		panelThongKe.add(label_14);

		JLabel label_15 = new JLabel("");
		label_15.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_15.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/ngay kt.png")));
		label_15.setBounds(363, 110, 292, 42);
		panelThongKe.add(label_15);

		JLabel lblBngCchClick = new JLabel("bằng cách click vào ô: ");
		lblBngCchClick.setBounds(216, 172, 158, 16);
		panelThongKe.add(lblBngCchClick);

		JLabel label_16 = new JLabel("");
		label_16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_16.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/date chooser.png")));
		label_16.setBounds(363, 165, 56, 34);
		panelThongKe.add(label_16);

		JLabel lblChnChuK = new JLabel("chọn  chu kì thời gian để thống kê (theo ngày, tuần, tháng)");
		lblChnChuK.setBounds(216, 217, 352, 19);
		panelThongKe.add(lblChnChuK);

		JLabel label_17 = new JLabel("");
		label_17.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_17.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/chia theo ngay.png")));
		label_17.setBounds(591, 205, 212, 42);
		panelThongKe.add(label_17);

		JLabel lblChnNiDung = new JLabel("chọn nội dung cần thống kê (doanh thu, lãi, số lượng sản phẩm)");
		lblChnNiDung.setBounds(216, 277, 379, 26);
		panelThongKe.add(lblChnNiDung);

		JLabel label_18 = new JLabel("");
		label_18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_18.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/noi dung hien thi.png")));
		label_18.setBounds(601, 271, 272, 42);
		panelThongKe.add(label_18);

		JLabel lblRinNt_1 = new JLabel("rồi ấn nút: ");
		lblRinNt_1.setBounds(216, 334, 70, 26);
		panelThongKe.add(lblRinNt_1);

		JButton btnVBiu = new JButton("Vẽ biểu đồ");
		btnVBiu.setBounds(283, 335, 97, 25);
		panelThongKe.add(btnVBiu);

		JLabel lblThngK = new JLabel("Để thống kê tổng số tiền trong thời gian bạn đã chọn: ");
		lblThngK.setForeground(Color.BLUE);
		lblThngK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThngK.setBounds(12, 388, 486, 42);
		panelThongKe.add(lblThngK);

		JLabel lblNewLabel_2 = new JLabel("chọn loại sản phẩm cần hiển thị doanh thu trong");
		lblNewLabel_2.setBounds(91, 443, 341, 16);
		panelThongKe.add(lblNewLabel_2);

		JLabel label_19 = new JLabel("");
		label_19.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_19.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/tong hop theo.png")));
		label_19.setBounds(395, 428, 370, 50);
		panelThongKe.add(label_19);

		JLabel lblTngDoanhThu = new JLabel("tổng doanh thu, tiền lãi, số sản phẩm bán ra sẽ hiện lên");
		lblTngDoanhThu.setBounds(91, 503, 428, 16);
		panelThongKe.add(lblTngDoanhThu);
		///
		panelKhach1 = new JPanel();
		pnCardLayout.add(panelKhach1, "khach1");
		panelKhach1.setLayout(null);

		JLabel label_4abd = new JLabel("Trợ giúp khách hàng");
		label_4abd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4abd.setBounds(42, 0, 366, 34);
		panelKhach1.add(label_4abd);

		JLabel lblTmSchabd = new JLabel("Tìm khách hàng: ");
		lblTmSchabd.setForeground(Color.BLUE);
		lblTmSchabd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTmSchabd.setBounds(24, 42, 155, 24);
		panelKhach1.add(lblTmSchabd);

		JLabel lblTmSch_1abd = new JLabel("để tìm khách hàng ta gõ từ khóa cần tìm vào ô: ");
		lblTmSch_1abd.setBounds(161, 47, 328, 16);
		panelKhach1.add(lblTmSch_1abd);

		JLabel label_5abd = new JLabel("");
		label_5abd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_5abd.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/searchBar.jpg")));
		label_5abd.setBounds(144, 67, 529, 34);
		panelKhach1.add(label_5abd);

		JLabel lblDanhSchCcabd = new JLabel("danh sách các kết quả sẽ hiện ra: ");
		lblDanhSchCcabd.setBounds(107, 110, 212, 16);
		panelKhach1.add(lblDanhSchCcabd);

		JLabel label_6abd = new JLabel("");
		label_6abd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_6abd.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/tim khach.png")));
		label_6abd.setBounds(138, 129, 438, 208);
		panelKhach1.add(label_6abd);

		JLabel lblClickVoabd = new JLabel("click vào 1 kết quả ta sẽ xem được khách hàng của kết quả đó");
		lblClickVoabd.setBounds(107, 350, 407, 16);
		panelKhach1.add(lblClickVoabd);

		JLabel lblXemabd = new JLabel("Để xem 1 khách hàng:");
		lblXemabd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXemabd.setForeground(Color.BLUE);
		lblXemabd.setBounds(24, 379, 188, 27);
		panelKhach1.add(lblXemabd);

		JLabel lblClickVoSchabd = new JLabel(" click vào khách hàng đó ở trên bảng");
		lblClickVoSchabd.setBounds(194, 386, 248, 16);
		panelKhach1.add(lblClickVoSchabd);

		JLabel lblSpXpabd = new JLabel("Để sắp xếp khách hàng:");
		lblSpXpabd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSpXpabd.setForeground(Color.BLUE);
		lblSpXpabd.setBounds(24, 408, 188, 27);
		panelKhach1.add(lblSpXpabd);

		JLabel label_7abd = new JLabel("");
		label_7abd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_7abd.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/colum khach.png")));
		label_7abd.setBounds(66, 437, 671, 47);
		panelKhach1.add(label_7abd);

		JLabel lblCcSchTheoabd = new JLabel("kích đúp vào tên cột cần sắp xếp");
		lblCcSchTheoabd.setBounds(204, 415, 199, 16);
		panelKhach1.add(lblCcSchTheoabd);

		panelKhach2 = new JPanel();
		pnCardLayout.add(panelKhach2, "phim2");
		panelKhach2.setLayout(null);

		JLabel labelabd = new JLabel("");
		labelabd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelabd.setIcon(new ImageIcon(PnHelp.class.getResource("/img/help/them khach.png")));
		labelabd.setBounds(12, 87, 919, 106);
		panelKhach2.add(labelabd);

		JLabel lblTrGipKhoabd = new JLabel("Trợ giúp khách hàng");
		lblTrGipKhoabd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrGipKhoabd.setBounds(25, 0, 366, 34);
		panelKhach2.add(lblTrGipKhoabd);

		JLabel lblThmSchabd = new JLabel("Để thêm khách hàng:");
		lblThmSchabd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThmSchabd.setForeground(Color.BLUE);
		lblThmSchabd.setBounds(25, 47, 182, 24);
		panelKhach2.add(lblThmSchabd);

		JLabel lblSauClickabd = new JLabel("Sau đó click vào nút: ");
		lblSauClickabd.setForeground(Color.BLACK);
		lblSauClickabd.setBounds(65, 206, 123, 24);
		panelKhach2.add(lblSauClickabd);

		JButton btnThmSchabd = new JButton("Thêm khách hàng");
		btnThmSchabd.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnAddIconSmall.png")));
		btnThmSchabd.setBounds(186, 206, 166, 25);
		panelKhach2.add(btnThmSchabd);

		JLabel lblLuMabd = new JLabel("Lưu ý: ");
		lblLuMabd.setForeground(Color.RED);
		lblLuMabd.setBounds(65, 229, 53, 24);
		panelKhach2.add(lblLuMabd);

		JLabel lblSaabd = new JLabel("Để sửa 1 khách hàng:  ");
		lblSaabd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSaabd.setForeground(Color.BLUE);
		lblSaabd.setBounds(25, 350, 196, 24);
		panelKhach2.add(lblSaabd);

		JLabel lblSauSaabd = new JLabel("Sau đó, sửa các thuộc tính của khách hàng và ấn nút : ");
		lblSauSaabd.setForeground(Color.BLACK);
		lblSauSaabd.setBounds(89, 381, 326, 24);
		panelKhach2.add(lblSauSaabd);

		JButton btnSaSchabd = new JButton("Sửa khách hàng");
		btnSaSchabd.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnEditIcon.png")));
		btnSaSchabd.setBounds(413, 381, 178, 25);
		panelKhach2.add(btnSaSchabd);

		JLabel lblXaabd = new JLabel("Để xóa 1 khách hàng:");
		lblXaabd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXaabd.setForeground(Color.BLUE);
		lblXaabd.setBounds(25, 425, 196, 23);
		panelKhach2.add(lblXaabd);

		JButton btnXaSchabd = new JButton("Xóa khách hàng");
		btnXaSchabd.setIcon(new ImageIcon(PnHelp.class.getResource("/img/btnDeletetIcon.png")));
		btnXaSchabd.setBounds(424, 426, 155, 25);
		panelKhach2.add(btnXaSchabd);

		JLabel lblCninyabd = new JLabel("cần điền đầy đủ thông tin của khách hàng vào phần thông tin:");
		lblCninyabd.setBounds(199, 53, 488, 16);
		panelKhach2.add(lblCninyabd);

		JLabel lblMSchThmabd = new JLabel("mã khách hàng thêm vào cần khác mã các khách hàng trong kho, nếu trùng sẽ có thông báo lỗi");
		lblMSchThmabd.setBounds(109, 233, 545, 16);
		panelKhach2.add(lblMSchThmabd);

		JLabel lblChnSch_1abd = new JLabel("chọn khách hàng đó trước, và ấn nút: ");
		lblChnSch_1abd.setBounds(199, 432, 226, 16);
		panelKhach2.add(lblChnSch_1abd);

		JLabel lblCnChnSchabd = new JLabel("cần chọn khách hàng đó trước. Các thông tin của sách sẽ hiện lên ở phần thông tin.");
		lblCnChnSchabd.setBounds(194, 354, 488, 16);
		panelKhach2.add(lblCnChnSchabd);

		addListener();

	}

	private void addListener() {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(pnCardLayout, "help");
			}
		});
		btnTrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.previous(pnCardLayout);
			}
		});
		btnTipTheo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.next(pnCardLayout);
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pnCardLayout, "sach1");
			}
		});
		btnTrGipV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pnCardLayout, "nhac1");
			}
		});
		btnTrGipV_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pnCardLayout, "phim1");
			}
		});
		btnTrGipV_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(pnCardLayout, "mua");
			}
		});

		btnTrGipV_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pnCardLayout, "hd");
			}
		});
		btnTrGipV_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pnCardLayout, "thongKe");
			}
		});
		btnTrGipV_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pnCardLayout, "khach1");
			}
		});
	}

	public void showPanelHelpFor(JPanel pn) {
		if (pn instanceof PnSach) {
			card.show(pnCardLayout, "sach1");
		} else if (pn instanceof PnDiaNhac) {
			card.show(pnCardLayout, "nhac1");
		} else if (pn instanceof PnDiaPhim) {
			card.show(pnCardLayout, "phim1");
		} else if (pn instanceof PnThanhToan) {
			card.show(pnCardLayout, "mua");
		} else if (pn instanceof PnHoaDon) {
			card.show(pnCardLayout, "hd");
		} else if (pn instanceof PnReport) {
			card.show(pnCardLayout, "thongKe");
		} else if (pn instanceof PnKhachHang) {
			card.show(pnCardLayout, "khach1");
		}
		
	}
}
