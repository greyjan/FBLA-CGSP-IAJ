/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.map.utils.IsometricActor;

/**
 *
 * @author n080873
 */
public class Wall extends IsometricActor {

    protected Sprite sprite;

    public Wall(int x, int y) {
        super(0);
        sprite = new Sprite((Texture) Assets.GetInstance().get("map/tileSet/wall.png"));
        
        this.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        this.setIsoX((x - 16) / (32 / 2));
        this.setIsoY((y  - 16) / (32 / 2));
        System.out.println(x  + " , " + y + " , " + getIsoX() + " , " + getIsoY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
