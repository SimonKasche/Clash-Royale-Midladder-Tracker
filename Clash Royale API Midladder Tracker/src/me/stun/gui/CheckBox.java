package me.stun.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CheckBox extends JLabel{
	
	private boolean checked = false;
	private ImageIcon checkedIcon;
	private ImageIcon uncheckedIcon;
	
	public CheckBox(int width, int height) {
		
		try {
			
			BufferedImage img = ImageIO.read(new File("resources/checked.png"));
			Image scaledImage = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);//TODO make unchecked.png (desktop) and finish this crap
			checkedIcon = new ImageIcon(scaledImage);
			
			img = ImageIO.read(new File("resources/unchecked.png"));
			scaledImage = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			uncheckedIcon = new ImageIcon(scaledImage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
	}
	
	public boolean isChecked() {
		
		if(checked == true) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public void setChecked(boolean b) {
		
		if(b == true) {
			
			checked = true;
			this.setIcon(checkedIcon);
			
		}else {
			
			checked = false;
			this.setIcon(uncheckedIcon);
			
		}
		
	}
	
}
