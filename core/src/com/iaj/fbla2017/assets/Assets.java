/*******************************************************
 * Copyright (C) 2015 Mirco Timmermann - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Mirco Timmermann <mtimmermann@gmx.de>, December 2016
 * 
 * game file
 *******************************************************/
package com.iaj.fbla2017.assets;

import com.badlogic.gdx.graphics.Texture;

/*
 * singleton for assets on loading. 
 * (Can be used too instead of using the build in level assets loader of the 
 * levels itslelf. Often you dont want to use the level asset loader because its nicer 
 * to have one loading screen on the start on smaller games)
 */
public class Assets extends LevelAssets {
	private static Assets INSTANCE = null;
	
	
	private Assets() {
		addAsset("badlogic.jpg", Texture.class);
                 //test
		/*
		Texture.class
		Sound.class
		Music.class
		TextureAtlas.class
		BitmapFont.class
		FreeTypeFontGenerator.class
		Skin.class
		TiledMap.class
		ShaderProgram.class
		*/
	}
	
	public static Assets GetInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Assets();
		}
		return INSTANCE;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		INSTANCE = null;
	}
}
