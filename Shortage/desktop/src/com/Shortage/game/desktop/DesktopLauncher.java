package com.Shortage.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Shortage.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		  LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	      cfg.title = "ShorAge";
	     // cfg.useGL20 = false;
	      cfg.width =800;
	      cfg.height =600;
	      
		new LwjglApplication(new Main(), cfg);
	}
}
