/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.map.utils.IsometricActor;

/**
 *
 * @author n080873
 */
public class Carpet extends IsometricActor {

    Sprite sprite;

    public Carpet(int isoX, int isoY) {
        super(0, isoX, isoY);
        sprite = new Sprite((Texture) Assets.GetInstance().get("map/tileSet/carpet.png"));
        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    
    

}
