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

/**
 *
 * @author Jan Fic
 */
public class Table extends Furniture {
    
    public Table(int x, int y, String direction, String part) {
        super(x, y);
        sprite = new Sprite((Texture) Assets.GetInstance().get(Furniture.FURNITURE_PATH + "table" + part + direction + ".png"));
        this.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        this.setIsoX((x - 16 ) / (16));
        this.setIsoY((y) / (16));
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
