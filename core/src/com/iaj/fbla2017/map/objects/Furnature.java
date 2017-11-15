/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.iaj.fbla2017.map.utils.IsometricActor;

/**
 *
 * @author Jan Fic
 */
public class Furnature extends IsometricActor{

    public final static String FURNATURE_PATH = "map/school/schoolFurnature/";
    protected Sprite sprite;
    
    public Furnature(int x, int y) {
        super(0);
        sprite = new Sprite();
        this.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        this.setIsoX(x/(32/2));
        this.setIsoY(y/(32/2));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    
    
    
}
