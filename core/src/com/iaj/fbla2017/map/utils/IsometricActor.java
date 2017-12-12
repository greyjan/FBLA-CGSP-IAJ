/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Jan Fic
 */
public abstract class IsometricActor extends Actor implements IsometricPositioning, Comparable<IsometricActor> {

    private int offsetX, offsetY;
    private float isoX, isoY;
    private final int layer;

    public IsometricActor(int l) {
        layer = l;
    }

    public int getLayer() {
        return layer;
    }

    @Override
    public float getIsoX() {
        return isoX;
    }

    @Override
    public float getIsoY() {
        return isoY;
    }

    @Override
    public void setIsoX(float x) {
        isoX = x;
    }

    @Override
    public void setIsoY(float y) {
        isoY = y;
    }

    @Override
    public void setIsoPosition(float x, float y) {
        setIsoX(x);
        setIsoY(y);
    }

    @Override
    public void moveIsoBy(float vx, float vy) {
        isoX += vx;
        isoY += vy;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    @Override
    public int compareTo(IsometricActor t) {
        if(t.getIsoY() < this.getIsoY()) 
            return 1;
        else if(t.getIsoY() > this.getIsoY()) {
            return -1;
        }
        else 
            return 0;
    }

    
}
