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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.iaj.fbla2017.map.objects.Furniture;
import com.iaj.fbla2017.map.actors.character.Character;

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
        addAsset("skin/paperHeader.png", Texture.class);
        String bodyPart;
        String animationType;
        String direction;
        
        //character
        bodyPart = "head/";
        animationType = "standing/";
        direction = "east/";
        addAsset(Character.PATH + bodyPart + animationType + direction + "pointed/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "round/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "wide/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "square/frame0.png",Texture.class);
        
        bodyPart = "legs/";
        animationType = "standing/";
        direction = "east/";
        addAsset(Character.PATH + bodyPart + animationType + direction + "female/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "male/frame0.png",Texture.class);
        
        bodyPart = "arms/";
        animationType = "standing/";
        direction = "east/";
        addAsset(Character.PATH + bodyPart + animationType + direction + "left/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "right/frame0.png",Texture.class);
        
        bodyPart = "torso/";
        animationType = "standing/";
        direction = "east/";
        addAsset(Character.PATH + bodyPart + animationType + direction + "female/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "male/frame0.png",Texture.class);
        
        bodyPart = "head/";
        animationType = "standing/";
        direction = "south/";
        addAsset(Character.PATH + bodyPart + animationType + direction + "pointed/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "round/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "wide/frame0.png",Texture.class);
        addAsset(Character.PATH + bodyPart + animationType + direction + "square/frame0.png",Texture.class);
        
        //UISkin
        addAsset("skin/composed/skin.json",Skin.class);
        //addAsset("skin/",Texture.class);
        
        //maps
        //addAsset("map/school/schoolRooms/test.tmx",TiledMap.class);
        
        //furnature
        addAsset("map/school/schoolFurniture/smallDeskWest.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "wall.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "studentDeskWest.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "studentDeskNorth.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "doorEast.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "doorSouth.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "tableRightWest.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "tableRightNorth.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "tableLeftWest.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "tableLeftNorth.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "tableCenterNorth.png", Texture.class);
        addAsset(Furniture.FURNITURE_PATH + "tableCenterWest.png", Texture.class);
        
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
