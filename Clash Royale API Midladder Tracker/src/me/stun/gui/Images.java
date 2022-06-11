package me.stun.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Images {

	public static boolean arrowsVisible = true;
	public static boolean megaknightVisible = true;
	public static boolean balloonVisible = true;
	public static boolean eqVisible = true;
	public static boolean ebarbsVisible = true;
	public static boolean egolemVisible = true;
	public static boolean freezeVisible = true;
	public static boolean golemVisible = true;
	public static boolean hogVisible = true;
	public static boolean lightningVisible = true;
	public static boolean rgVisible = true;
	public static boolean valkVisible = true;
	public static boolean witchVisible = true;
	public static boolean wizardVisible = true;
	public static boolean gbarrelVisible = true;
	public static boolean egiantVisible = true;

	public static void addImages(JLabel cp) throws IOException {

		// arrows

		JLabel arrows = new JLabel();
		arrows.setBounds(15, 15, 150, 180);
		arrows.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (arrowsVisible == true) {
					percentage("arrows", arrows);
					arrowsVisible = false;
				} else {
					arrows.setVisible(true);
					arrowsVisible = true;
				}
			}
		});

		BufferedImage img = null;
		img = ImageIO.read(new File("resources/Arrows.png"));
		Image scaledImage = img.getScaledInstance(arrows.getWidth(), arrows.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(scaledImage);
		arrows.setIcon(imageIcon);
		cp.add(arrows);

		// megaknight

		JLabel megaknight = new JLabel();
		megaknight.setBounds(180, 15, 150, 180);
		megaknight.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (megaknightVisible == true) {
					percentage("mega knight", megaknight);
					megaknightVisible = false;
				} else {
					megaknight.setVisible(true);
					megaknightVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/MegaKnight.png"));
		scaledImage = img.getScaledInstance(megaknight.getWidth(), megaknight.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		megaknight.setIcon(imageIcon);
		cp.add(megaknight);

		// balloon

		JLabel balloon = new JLabel();
		balloon.setBounds(345, 15, 150, 180);
		balloon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (balloonVisible == true) {
					percentage("balloon", balloon);
					balloonVisible = false;
				} else {
					balloon.setVisible(true);
					balloonVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Balloon.png"));
		scaledImage = img.getScaledInstance(balloon.getWidth(), balloon.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		balloon.setIcon(imageIcon);
		cp.add(balloon);

		// eq

		JLabel eq = new JLabel();
		eq.setBounds(510, 15, 150, 180);
		eq.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (eqVisible == true) {
					percentage("earthquake", eq);
					eqVisible = false;
				} else {
					eq.setVisible(true);
					eqVisible = true;
				}

			}
		});

		img = ImageIO.read(new File("resources/Earthquake.png"));
		scaledImage = img.getScaledInstance(eq.getWidth(), eq.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		eq.setIcon(imageIcon);
		cp.add(eq);

		// ebarbs

		JLabel abarbs = new JLabel();
		abarbs.setBounds(675, 15, 150, 180);
		abarbs.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent me) {
				if (ebarbsVisible == true) {
					percentage("Elite Barbarians", abarbs);
					ebarbsVisible = false;
				} else {
					abarbs.setVisible(true);
					ebarbsVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/EliteBarbarians.png"));
		scaledImage = img.getScaledInstance(abarbs.getWidth(), abarbs.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		abarbs.setIcon(imageIcon);
		cp.add(abarbs);

		// egolem

		JLabel egolem = new JLabel();
		egolem.setBounds(840, 15, 150, 180);
		egolem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (egolemVisible == true) {
					percentage("Elixir Golem", egolem);
					egolemVisible = false;
				} else {
					egolem.setVisible(true);
					egolemVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Elixiergolem.png"));
		scaledImage = img.getScaledInstance(egolem.getWidth(), egolem.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		egolem.setIcon(imageIcon);
		cp.add(egolem);

		// freeze

		JLabel freeze = new JLabel();
		freeze.setBounds(1005, 15, 150, 180);
		freeze.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (freezeVisible == true) {
					percentage("Freeze", freeze);
					freezeVisible = false;
				} else {
					freeze.setVisible(true);
					freezeVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Freeze.png"));
		scaledImage = img.getScaledInstance(freeze.getWidth(), freeze.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		freeze.setIcon(imageIcon);
		cp.add(freeze);

		// gbarrel

		JLabel gbarrel = new JLabel();
		gbarrel.setBounds(1170, 15, 150, 180);
		gbarrel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (gbarrelVisible == true) {
					percentage("Goblin Barrel", gbarrel);
					gbarrelVisible = false;
				} else {
					gbarrel.setVisible(true);
					gbarrelVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/GoblinBarrel.png"));
		scaledImage = img.getScaledInstance(gbarrel.getWidth(), gbarrel.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		gbarrel.setIcon(imageIcon);
		cp.add(gbarrel);

		// golem

		JLabel golem = new JLabel();
		golem.setBounds(15, 200, 150, 180);
		golem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (golemVisible == true) {
					percentage("Golem", golem);
					golemVisible = false;
				} else {
					golem.setVisible(true);
					golemVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Golem.png"));
		scaledImage = img.getScaledInstance(golem.getWidth(), golem.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		golem.setIcon(imageIcon);
		cp.add(golem);

		// hog

		JLabel hog = new JLabel();
		hog.setBounds(840, 200, 150, 180);
		hog.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (hogVisible == true) {
					percentage("Hog Rider", hog);
					hogVisible = false;
				} else {
					hog.setVisible(true);
					hogVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/HogRider.png"));
		scaledImage = img.getScaledInstance(hog.getWidth(), hog.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		hog.setIcon(imageIcon);
		cp.add(hog);

		// lightning

		JLabel lightning = new JLabel();
		lightning.setBounds(180, 200, 150, 180);
		lightning.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (lightningVisible == true) {
					percentage("Lightning", lightning);
					lightningVisible = false;
				} else {
					lightning.setVisible(true);
					lightningVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Lightning.png"));
		scaledImage = img.getScaledInstance(lightning.getWidth(), lightning.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		lightning.setIcon(imageIcon);
		cp.add(lightning);

		// rg

		JLabel rg = new JLabel();
		rg.setBounds(345, 200, 150, 180);
		rg.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (rgVisible == true) {
					percentage("Royal Giant", rg);
					rgVisible = false;
				} else {
					rg.setVisible(true);
					rgVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/RoyalGiant.png"));
		scaledImage = img.getScaledInstance(rg.getWidth(), rg.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		rg.setIcon(imageIcon);
		cp.add(rg);

		// valk

		JLabel valk = new JLabel();
		valk.setBounds(510, 200, 150, 180);
		valk.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (valkVisible == true) {
					percentage("Valkyrie", valk);
					valkVisible = false;
				} else {
					valk.setVisible(true);
					valkVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Valkyrie.png"));
		scaledImage = img.getScaledInstance(valk.getWidth(), valk.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		valk.setIcon(imageIcon);
		cp.add(valk);

		// witch

		JLabel witch = new JLabel();
		witch.setBounds(675, 200, 150, 180);
		witch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (witchVisible == true) {
					percentage("Witch", witch);
					witchVisible = false;
				} else {
					witch.setVisible(true);
					witchVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Witch.png"));
		scaledImage = img.getScaledInstance(witch.getWidth(), witch.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		witch.setIcon(imageIcon);
		cp.add(witch);

		// wizard

		JLabel wizard = new JLabel();
		wizard.setBounds(1005, 200, 150, 180);
		wizard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (wizardVisible == true) {
					percentage("Wizard", wizard);
					wizardVisible = false;
				} else {
					wizard.setVisible(true);
					wizardVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/Wizard.png"));
		scaledImage = img.getScaledInstance(wizard.getWidth(), wizard.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		wizard.setIcon(imageIcon);
		cp.add(wizard);

		// egiant

		JLabel egiant = new JLabel();
		egiant.setBounds(1170, 200, 150, 180);
		egiant.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (egiantVisible == true) {
					percentage("Electro Giant", egiant);
					egiantVisible = false;
				} else {
					egiant.setVisible(true);
					egiantVisible = true;
				}
			}
		});

		img = ImageIO.read(new File("resources/ElectroGiant.png"));
		scaledImage = img.getScaledInstance(egiant.getWidth(), egiant.getHeight(), Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		egiant.setIcon(imageIcon);
		cp.add(egiant);

	}

	public static void percentage(String key, JLabel label) {

		JLabel percentage = new JLabel();
		percentage.setBounds(label.getBounds());
		percentage.setHorizontalAlignment(SwingConstants.CENTER);
		percentage.setVerticalAlignment(SwingConstants.CENTER);
		percentage.setForeground(Color.RED);

		int scale = (int) Math.pow(10, 1);
		String result = (float) Math
				.round(me.stun.data.DataProcessor.getPercentage(me.stun.data.DeckData.totalMatches, key) * scale)
				/ scale + "%";

		percentage.setText(result);
		percentage.setFont(new Font("Lucida Console", Font.PLAIN, 40));
		OldWindow.background.add(percentage);

		percentage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				label.setVisible(true);
			}
		});

		label.setVisible(false);

	}

}
