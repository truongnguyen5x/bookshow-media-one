package GuiLayer;

import java.awt.EventQueue;

import DataObject.NhanVien;

/**
 * @author truong
 *
 *         hàm main
 */
public class MainClass {
	private static FrmMain frame = null;
	private static FrmLogin login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// màn hình khởi động 1 giây
		FrmKhoiDong splash = new FrmKhoiDong();
		splash.setVisible(true);
		try {
			Thread.sleep(1600);
		} catch (InterruptedException e) {
		}
		splash.dispose();

		// màn hình login
		login = new FrmLogin();
		login.setVisible(true);
		login.loginEvent = new LoginEvent() {
			public void onLoginSuccess(NhanVien user) {
				showMainFrame(user);
			}
		};
	}

	// giao diện chương trình chính
	private static void showMainFrame(NhanVien currentUser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FrmMain(currentUser);
					frame.setVisible(true);
					frame.logoutEvent = new LogoutEvent() {
						// đăng xuất
						public void logout() {
							login.setVisible(true);
							frame.dispose();
						}
					};
				} catch (Exception e) {
				}
			}
		});
	}
}
