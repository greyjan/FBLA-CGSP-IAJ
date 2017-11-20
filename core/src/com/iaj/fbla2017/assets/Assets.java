/** *****************************************************
 * Copyright (C) 2015 Mirco Timmermann - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Mirco Timmermann <mtimmermann@gmx.de>, December 2016
 *
 * game file
 ******************************************************
 */
package com.iaj.fbla2017.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.iaj.fbla2017.map.objects.Furniture;

/*
 * singleton for assets on loading. 
 * (Can be used too instead of using the build in level assets loader of the 
 * levels itslelf. Often you dont want to use the level asset loader because its nicer 
 * to have one loading screen on the start on smaller games)
 */
public class Assets extends LevelAssets {

    private static Assets INSTANCE = null;

    private Assets() {
        
        addAsset("player/person.png", Texture.class);
        
        //paper
        addAsset("skin/paper.png", Texture.class);
        addAsset("skin/paperMargin.png", Texture.class);
        
        //character 
            //body
        addAsset("character/head/round.png", Texture.class);
        addAsset("character/head/pointed.png", Texture.class);
        addAsset("character/head/square.png", Texture.class);
        addAsset("character/head/wide.png", Texture.class);
        addAsset("character/leg/female.png", Texture.class);
        addAsset("character/leg/male.png", Texture.class);
        addAsset("character/torso/female.png", Texture.class);
        addAsset("character/torso/male.png", Texture.class);
        addAsset("character/arm/left.png", Texture.class);
        addAsset("character/arm/right.png", Texture.class);
        
        //UISkin
        addAsset("skin/composed/skin.json",Skin.class);
        //addAsset("skin/",Texture.class);
        
        //maps
        //addAsset("map/school/schoolRooms/test.tmx",TiledMap.class);
        
        //furnature
        addAsset("map/school/schoolFurniture/smallDeskWest.png", Texture.class);
        addAsset("map/tileSet/wall.png", Texture.class);
        
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
        if (INSTANCE == null) {
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
