package me.stun.thread;

public class ProgressbarWorker extends Thread{
	
	public static int progress = 0;
	public static boolean running = false;
	
	public void run() {
		
		running = true;
		progress = 0;
		me.stun.gui.Window.progressbar.setValue(0);
		me.stun.gui.Window.progressbar.setVisible(true);
		
		while(running == true) {
			
			me.stun.gui.Window.progressbar.setValue(progress);
			
		}
		
		me.stun.gui.Window.progressbar.setVisible(false);
		
	}
	
}
