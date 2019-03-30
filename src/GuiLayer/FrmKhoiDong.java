package GuiLayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

/**
 * @author truong
 *
 *         frame khởi động chương trình
 */
public class FrmKhoiDong extends JFrame {

	private JPanel contentPane;
	private int countPercent = 0;
	private Image backgroundImage;
	private JProgressBar progressBar; // thanh tiến trình

	public FrmKhoiDong() {
		backgroundImage = new ImageIcon(getClass().getResource("/img/splashBackground.png")).getImage();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 269);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, null); // vẽ hình nền
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // đường viền
		setContentPane(contentPane);
		contentPane.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(0, 255, 369, 14);
		progressBar.setValue(countPercent);
		progressBar.setForeground(Color.BLUE);
		Timer time = new Timer();
		time.schedule(new TimerTask() { // tăng thanh tiến trình
			@Override
			public void run() {
				progressBar.setValue(++countPercent);
				repaint();
				if (countPercent > 100) {
					time.cancel();
				}
			}
		}, 0, 10);
		contentPane.add(progressBar);
		setLocationRelativeTo(null);
	}
}
