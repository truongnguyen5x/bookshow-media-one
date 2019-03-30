package GuiLayer;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author thịnh
 *
 */
public class PnAbout extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnAbout() {
		setLayout(null);
		
		JLabel lblPhnMmQun = new JLabel("Phần mềm quản lý cửa hàng sách, đĩa nhạc, đĩa phim");
		lblPhnMmQun.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblPhnMmQun.setBounds(178, 13, 551, 68);
		add(lblPhnMmQun);
		
		JLabel lblMediaOne = new JLabel("MEDIA ONE");
		lblMediaOne.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblMediaOne.setBounds(346, 68, 181, 68);
		add(lblMediaOne);
		
		JLabel lblPhinBn = new JLabel("Phiên bản 1.0 (Dec 14, 2017) full");
		lblPhinBn.setBounds(330, 153, 190, 33);
		add(lblPhinBn);
		
		JLabel lblNhmTcGi = new JLabel("Nhóm tác giả: group PT04 | KSCLC - HTTT&TT - K60 | Trường Đại học Bách khoa Hà Nội");
		lblNhmTcGi.setBounds(198, 218, 620, 26);
		add(lblNhmTcGi);
		
		JLabel lblNguynVnTrng = new JLabel("Nguyễn Văn Trường");
		lblNguynVnTrng.setBounds(369, 251, 119, 16);
		add(lblNguynVnTrng);
		
		JLabel lblNguynVnV = new JLabel("Nguyễn Văn Vũ");
		lblNguynVnV.setBounds(381, 313, 94, 16);
		add(lblNguynVnV);
		
		JLabel lblNgVnTi = new JLabel("Ngô Văn Tài");
		lblNgVnTi.setBounds(390, 294, 76, 16);
		add(lblNgVnTi);
		
		JLabel lbloTrngThnh = new JLabel("Đào Trọng Thịnh");
		lbloTrngThnh.setBounds(376, 271, 104, 16);
		add(lbloTrngThnh);
		
		JLabel lblMNgungChng = new JLabel("Mã nguồn chương trình:");
		lblMNgungChng.setBounds(278, 373, 148, 16);
		add(lblMNgungChng);
		
		JTextField txtGithubcomkaironswmediaone = new JTextField();
		txtGithubcomkaironswmediaone.setBorder(null);
		txtGithubcomkaironswmediaone.setEditable(false);
		txtGithubcomkaironswmediaone.setText("github.com/kaironsw/MediaOne");
		txtGithubcomkaironswmediaone.setBounds(437, 368, 190, 26);
		add(txtGithubcomkaironswmediaone);
		txtGithubcomkaironswmediaone.setColumns(10);
		
		JLabel lblLinHTc = new JLabel("Liên hệ tác giả:");
		lblLinHTc.setBounds(313, 405, 94, 16);
		add(lblLinHTc);
		
		JTextField txtKaironswgmailcom = new JTextField();
		txtKaironswgmailcom.setBorder(null);
		txtKaironswgmailcom.setEditable(false);
		txtKaironswgmailcom.setText("kaironsw@gmail.com");
		txtKaironswgmailcom.setBounds(425, 402, 143, 22);
		add(txtKaironswgmailcom);
		txtKaironswgmailcom.setColumns(10);
	}

}
