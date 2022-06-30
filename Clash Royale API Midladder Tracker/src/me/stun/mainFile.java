package me.stun;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class mainFile {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		new me.stun.startup.Console();
		new me.stun.startup.StartupImage();
		
		me.stun.data.DeckData.totalMatches = me.stun.io.ArrayBuilder.readPreviousMatchesArray();
		me.stun.startup.StartupImage.progressbar.setValue(35);
		me.stun.startup.Console.TextArea.append("opening window..\n");

		new me.stun.gui.Window(me.stun.data.DeckData.totalMatches, null);
		me.stun.startup.StartupImage.instance.dispose();

	}

}
