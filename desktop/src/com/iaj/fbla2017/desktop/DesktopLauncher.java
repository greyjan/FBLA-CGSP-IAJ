package com.iaj.fbla2017.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.iaj.fbla2017.SandboxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                //config.fullscreen = true;
                config.width = 1200;
                config.height = 800;
		new LwjglApplication(new SandboxGame(), config);
	}
}
