import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HOS extends JFrame {

	public HOS() throws FileNotFoundException, IOException, InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File file = new File(System.getProperty("user.dir") + "//hos.jpg");
		JLabel lbl = new JLabel("");
		add(lbl);
		pack();
		setBounds(200, 200, 500, 500);
		setVisible(true);
		while (true) {
			Thread.sleep(10);
			BufferedImage oldImage = ImageIO.read(new FileInputStream(file));
			BufferedImage newImage = new BufferedImage(oldImage.getHeight(), oldImage.getWidth(), oldImage.getType());
			lbl.setIcon(new ImageIcon(newImage));
			Graphics2D graphics = (Graphics2D) newImage.getGraphics();
			graphics.rotate(Math.toRadians(90), newImage.getWidth() / 2, newImage.getHeight() / 2);
			graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2,
					(newImage.getHeight() - oldImage.getHeight()) / 2);
			graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
			ImageIO.write(newImage, "JPEG", new FileOutputStream(file));
		}
	}

	public static void main(String[] args) {
		try {
			try {
				new HOS();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
