package GuiLayer;

import java.awt.Dimension;

import javax.swing.JPanel;

import DataObject.NhanVien;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import DataAccessLayer.DataAccess;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author thịnh
 *
 *         đổi thông tin cá nhân của nhân viên hiện tại
 */
public class PnDoiThongTin extends JPanel {
	private JTextField tfTen, tfDiaChi, tfSDT;
	private JPasswordField pwOLD, pwNew, pwNew2;
	private JLabel lbMaNV, lbVitri, lbTienLuong, lbAdmin, lbThongBao;
	private JComboBox comboBox;
	private JDateChooser dateChoose;
	private JButton btnSaveChanges;

	private String messenger;
	private DataAccess<NhanVien> data = new DataAccess<>(NhanVien.class);
	private boolean dataIsValid;
	private long soDienThoai;
	private NhanVien user;

	/**
	 * Create the panel.
	 */
	public PnDoiThongTin(NhanVien user) {
		this.user = user;
		data.readFile();
		//setBorder(new EmptyBorder(3, 3, 3, 3));
		setPreferredSize(new Dimension(950, 650));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lbHeader = new JLabel("Đổi thông tin cá nhân");
		lbHeader.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lbHeader);

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMa.setPreferredSize(new Dimension(200, 35));
		panel.add(lblMa);

		lbMaNV = new JLabel("New label");
		lbMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lbMaNV);

		JPanel panel1 = new JPanel();
		FlowLayout flpanel1 = (FlowLayout) panel1.getLayout();
		flpanel1.setAlignment(FlowLayout.LEFT);
		add(panel1);

		JLabel lblTen = new JLabel("Tên nhân viên");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTen.setPreferredSize(new Dimension(200, 35));
		panel1.add(lblTen);

		tfTen = new JTextField();
		tfTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel1.add(tfTen);
		tfTen.setColumns(25);

		JPanel panel2 = new JPanel();
		FlowLayout flpanel2 = (FlowLayout) panel2.getLayout();
		flpanel2.setAlignment(FlowLayout.LEFT);
		add(panel2);

		JLabel lbVT = new JLabel("Vị trí làm việc");
		lbVT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbVT.setPreferredSize(new Dimension(200, 35));
		panel2.add(lbVT);

		lbVitri = new JLabel("New label");
		lbVitri.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel2.add(lbVitri);

		JPanel panel3 = new JPanel();
		FlowLayout flpanel3 = (FlowLayout) panel3.getLayout();
		flpanel3.setAlignment(FlowLayout.LEFT);
		add(panel3);

		JLabel lblDate = new JLabel("Ngày sinh");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setPreferredSize(new Dimension(200, 35));
		panel3.add(lblDate);

		dateChoose = new JDateChooser();
		panel3.add(dateChoose);

		JPanel panel4 = new JPanel();
		FlowLayout flpanel4 = (FlowLayout) panel4.getLayout();
		flpanel4.setAlignment(FlowLayout.LEFT);
		add(panel4);

		JLabel lbSex = new JLabel("Giới tính");
		lbSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSex.setPreferredSize(new Dimension(200, 35));
		panel4.add(lbSex);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		panel4.add(comboBox);

		JPanel panel5 = new JPanel();
		FlowLayout flpanel5 = (FlowLayout) panel5.getLayout();
		flpanel5.setAlignment(FlowLayout.LEFT);
		add(panel5);

		JLabel lbDC = new JLabel("Địa chỉ");
		lbDC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbDC.setPreferredSize(new Dimension(200, 35));
		panel5.add(lbDC);

		tfDiaChi = new JTextField();
		tfDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel5.add(tfDiaChi);
		tfDiaChi.setColumns(25);

		JPanel panel6 = new JPanel();
		FlowLayout flpanel6 = (FlowLayout) panel6.getLayout();
		flpanel6.setAlignment(FlowLayout.LEFT);
		add(panel6);

		JLabel lbSo = new JLabel("Số điện thoại");
		lbSo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSo.setPreferredSize(new Dimension(200, 35));
		panel6.add(lbSo);

		tfSDT = new JTextField();
		tfSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel6.add(tfSDT);
		tfSDT.setColumns(25);

		JPanel panel7 = new JPanel();
		FlowLayout flpanel7 = (FlowLayout) panel7.getLayout();
		flpanel7.setAlignment(FlowLayout.LEFT);
		add(panel7);

		JLabel lbTien = new JLabel("Tiền lương");
		lbTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTien.setPreferredSize(new Dimension(200, 35));
		panel7.add(lbTien);

		lbTienLuong = new JLabel("New label");
		lbTienLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel7.add(lbTienLuong);

		JPanel panel8 = new JPanel();
		FlowLayout flpanel8 = (FlowLayout) panel8.getLayout();
		flpanel8.setAlignment(FlowLayout.LEFT);
		add(panel8);

		JLabel lbQUyen = new JLabel("Quyền admin");
		lbQUyen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbQUyen.setPreferredSize(new Dimension(200, 35));
		panel8.add(lbQUyen);

		lbAdmin = new JLabel("New label");
		lbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel8.add(lbAdmin);

		JPanel panel9 = new JPanel();
		FlowLayout flpanel9 = (FlowLayout) panel9.getLayout();
		flpanel9.setAlignment(FlowLayout.LEFT);
		add(panel9);

		JLabel lbMK = new JLabel("Mật khẩu");
		lbMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbMK.setPreferredSize(new Dimension(200, 35));
		panel9.add(lbMK);

		pwOLD = new JPasswordField();
		pwOLD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwOLD.setColumns(25);
		panel9.add(pwOLD);

		JPanel panel10 = new JPanel();
		FlowLayout flpanel10 = (FlowLayout) panel10.getLayout();
		flpanel10.setAlignment(FlowLayout.LEFT);
		add(panel10);

		JLabel lnNhapMK = new JLabel("Nhập mật khẩu mới");
		lnNhapMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lnNhapMK.setPreferredSize(new Dimension(200, 35));
		panel10.add(lnNhapMK);

		pwNew = new JPasswordField();
		pwNew.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwNew.setColumns(25);
		panel10.add(pwNew);

		JPanel panel11 = new JPanel();
		FlowLayout flpanel11 = (FlowLayout) panel11.getLayout();
		flpanel11.setAlignment(FlowLayout.LEFT);
		add(panel11);

		JLabel lblNhapLaiMK = new JLabel("Nhập lại mật khẩu mới");
		lblNhapLaiMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhapLaiMK.setPreferredSize(new Dimension(200, 35));
		panel11.add(lblNhapLaiMK);

		pwNew2 = new JPasswordField();
		pwNew2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwNew2.setColumns(25);
		panel11.add(pwNew2);

		lbThongBao = new JLabel(" ");
		lbThongBao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbThongBao.setForeground(Color.RED);
		panel11.add(lbThongBao);

		JPanel panel12 = new JPanel();
		add(panel12);
		btnSaveChanges = new JButton("Lưu thay đổi");
		btnSaveChanges.setFont(new Font("Tahoma", Font.PLAIN, 16));

		dateChoose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateChoose.setPreferredSize(new Dimension(150, 30));
		panel12.add(btnSaveChanges);
		show(user);
		addListener();
	}

	/**
	 * bắt sự kiện
	 */
	private void addListener() {
		// khi gõ vào ô nhập lại mật khẩu
		pwNew2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!pwNew.getText().equals(pwNew2.getText())) {
					lbThongBao.setText("Mời bạn nhập lại mật khẩu mới");
				} else {
					lbThongBao.setText(" ");
				}
			}
		});
		// khi click nút lưu thay đổi
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kiemTraHopLe();
				if (dataIsValid) {
					int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa thông tin cá nhân ?\n   ", "Sửa", JOptionPane.YES_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						boolean gioiTinhNam;
						if (comboBox.getSelectedIndex() == 0) {
							gioiTinhNam = true;
						} else {
							gioiTinhNam = false;
						}
						String password;
						if (pwNew.getText().equals("")) {
							password = user.getMatKhau();
						} else {
							password = pwNew.getText();
						}
						NhanVien temp = new NhanVien(user.getID(), tfTen.getText(), user.getViTri(), tfDiaChi.getText(), password, user.isAdmin(), gioiTinhNam, dateChoose.getDate(), soDienThoai,
								user.getLuong());

						data.update(temp, user.getID());
						data.writeFile();
						show(temp);
					}
				} else {
					JOptionPane.showMessageDialog(null, messenger, "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * trình bày các thông tin lên
	 */
	public void show(NhanVien user) {
		lbMaNV.setText(user.getID());
		tfTen.setText(user.getHoTen());
		lbVitri.setText(user.getViTri());
		if (user.isGioiTinhNam()) {
			comboBox.setSelectedIndex(0);
		} else {
			comboBox.setSelectedIndex(1);
		}
		tfDiaChi.setText(user.getDiaChi());
		tfSDT.setText(user.getSoDienThoai() + "");
		lbTienLuong.setText(user.getLuong() + " VNĐ");
		if (user.isAdmin()) {
			lbAdmin.setText("Có");
		} else {
			lbAdmin.setText("Không");
		}
		dateChoose.setDateFormatString("dd/MM/yyyy");
		dateChoose.setDate(user.getNgaySinh());
	}

	/**
	 * kiểm tra sự hợp lệ của các thông tin
	 */
	private void kiemTraHopLe() {
		dataIsValid = true;
		messenger = "";
		if (tfTen.getText().equals("")) {
			messenger = "Tên bị trống!";
			dataIsValid = false;
			return;
		}
		// kiểm tra sđt
		try {
			this.soDienThoai = Integer.parseInt(tfSDT.getText());
			if (this.soDienThoai < 10000000) {
				throw new Exception();
			}
		} catch (Exception e) {
			messenger = "Mức chiết khấu sai\nVui lòng sửa lại!\n  ";
			dataIsValid = false;
			return;
		}
		if (!pwOLD.getText().equals(user.getMatKhau()) || !pwNew.getText().equals(pwNew2.getText())) {
			messenger = "Mật khẩu sai!";
			dataIsValid = false;
			return;
		}
	}
}
