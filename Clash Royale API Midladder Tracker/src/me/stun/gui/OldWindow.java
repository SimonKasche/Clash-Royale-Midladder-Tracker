package me.stun.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.json.simple.parser.ParseException;

@SuppressWarnings("serial")
public class OldWindow extends JFrame {

	public static Container cp;
	public static JLabel background;
	public static JButton button;
	public static JButton Averagebutton;
	public static JButton update;
	public static String playerTag;
	
	public String[][] matches;

	@SuppressWarnings("static-access")
	public OldWindow(String[][] matches1, String playertag) throws IOException {

		super();
		
		this.playerTag = playertag;
		this.matches = matches1;
		
		String[][] accessableMatches = this.matches;

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 1350;
		int frameHeight = 600;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Midladder Tracker");
		setResizable(false);
		setLayout(new GridLayout());

		Container cp = getContentPane();
		cp.setLayout(null);

		BufferedImage img = null;
		img = ImageIO.read(new File("resources/white.png"));
		Image scaledImage = img.getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(scaledImage);
		background = new JLabel(imageIcon);
		JPanel panel = new JPanel(new GridLayout());
		panel.add(background);

		button = new JButton("Card Usage Bar Chart");
		button.setBackground(Color.WHITE);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalAlignment(SwingConstants.CENTER);
		button.setForeground(Color.BLACK);
		button.setBounds(400, 420, 500, 50);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				me.stun.chart.BarChart.launch(accessableMatches, playerTag);
			}
		});
		background.add(button);

		Averagebutton = new JButton("Average Card Type usage");
		Averagebutton.setBackground(Color.WHITE);
		Averagebutton.setHorizontalAlignment(SwingConstants.CENTER);
		Averagebutton.setVerticalAlignment(SwingConstants.CENTER);
		Averagebutton.setForeground(Color.BLACK);
		Averagebutton.setBounds(400, 490, 500, 50);
		Averagebutton.setBorder(BorderFactory.createEmptyBorder());
		Averagebutton.setFocusPainted(false);
		Averagebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				me.stun.chart.SpellsDial.launch(accessableMatches);
				me.stun.chart.WinConditionDial.launch(accessableMatches);
			}
		});
		background.add(Averagebutton);

		update = new JButton("Update");
		update.setBackground(Color.WHITE);
		update.setHorizontalAlignment(SwingConstants.CENTER);
		update.setVerticalAlignment(SwingConstants.CENTER);
		update.setForeground(Color.BLACK);
		update.setBounds(40, 490, 200, 50);
		update.setBorder(BorderFactory.createEmptyBorder());
		update.setFocusPainted(false);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				Thread test = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        try {
							me.stun.mainFile.main(null);
						} catch (IOException | ParseException | InterruptedException e) {
							e.printStackTrace();
						};

                    }
                });         
                test.start();
			}
		});
		background.add(update);

		Images.addImages(background, this.matches);
		setContentPane(panel);
		setVisible(true);

	}

}
