package me.stun;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class mainFile {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		new me.stun.startup.Console();
		new me.stun.startup.StartupImage();
		
		me.stun.startup.Console.TextArea.append("opening window..\n");

		new me.stun.gui.Window(null);
		me.stun.startup.StartupImage.instance.dispose();

	}

}
