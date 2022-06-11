package me.stun.startup;

import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class StartupImage extends JFrame {
	public static StartupImage instance;
	public static Container cp;
	
	public static JProgressBar progressbar;
	public static JProgressBar plotProgressbar;
	
	public int frameWidth;
	public int frameHeight;
	public int x;
	public int y;
	
	public StartupImage() throws IOException {
		
		super();
		instance = this;

		frameWidth = 480;
		frameHeight = 270;

		setSize(frameWidth, frameHeight);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = (d.width - getSize().width) / 2;
		y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("StunShell");
		setResizable(false);
		cp = getContentPane();
		cp.setLayout(null);
		
		progressbar = new JProgressBar();
		progressbar.setBounds(0,frameHeight-10, frameWidth, 10);
		progressbar.setValue(0);
		progressbar.setForeground(Color.GREEN);
		cp.add(progressbar);
		
		plotProgressbar = new JProgressBar();
		plotProgressbar.setBounds(0,frameHeight-20, frameWidth, 10);
		plotProgressbar.setValue(0);
		plotProgressbar.setForeground(Color.GREEN);
		plotProgressbar.setVisible(false);
		cp.add(plotProgressbar);
		
		JLabel image = new JLabel();
		image.setBounds(0, 0, frameWidth, frameHeight);
		BufferedImage img = null;
		img = ImageIO.read(new File("resources/startup.jpg"));
		Image scaledImage = img.getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(scaledImage);
		image.setIcon(imageIcon);
		cp.add(image);
		
		setContentPane(cp);
		
		setUndecorated(true);
		setVisible(true);

	}

}
