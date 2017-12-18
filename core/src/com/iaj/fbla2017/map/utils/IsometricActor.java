/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Comparator;

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
        isoX = 0;
        isoY = 0;
        setToPosition();
    }
    
    public IsometricActor(int l, float isoX, float isoY) {
        layer = l;
        this.isoX = isoX;
        this.isoY = isoY;
        setToPosition();
        setOffsetX(-16);
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
        setToPosition();
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

    public void setToPosition() {
        float x = (getIsoX() - getIsoY()) / 2 * 32;
        float y = -((getIsoX() + getIsoY()) * 16 / 2);
        setPosition(x + getOffsetX(), y + getOffsetY());
    }
    
    @Override
    public int compareTo(IsometricActor t) {
        if (t.getIsoY() < this.getIsoY()) {
            return -1;
        } else if (t.getIsoY() > this.getIsoY()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static class IsometricComparatorByPosition implements Comparator<IsometricActor> {

        @Override
        public int compare(IsometricActor t, IsometricActor t1) {
            if (t.getIsoY() < t1.getIsoY()) {
                return 1;
            } else if (t.getIsoY() > t1.getIsoY()) {
                return -1;
            } else if (t.getIsoX() < t1.getIsoX()) {
                return -1;
            } else if (t.getIsoX() > t1.getIsoX()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
