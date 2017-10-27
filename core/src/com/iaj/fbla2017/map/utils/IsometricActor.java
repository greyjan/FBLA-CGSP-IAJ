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
public class IsometricActor extends Actor implements IsometricPositioning {

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

    private void calcScreenPosition() {
        //float x = (a.getIsoX() - a.getIsoY()) / 2 * layer.getTileWidth() + layer.getWidth() * layer.getTileWidth() / 2;
        //float y = -((a.getIsoX() + a.getIsoY()) * layer.getTileHeight() / 2) + layer.getHeight() * layer.getTileHeight() / 2 - 3 + a.getLayer() * layer.getTileHeight();

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

}
