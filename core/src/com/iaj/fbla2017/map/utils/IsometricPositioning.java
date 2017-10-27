/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.utils;

/**
 *
 * @author Jan Fic
 */
interface IsometricPositioning {
    float getIsoX();
    float getIsoY();
    void setIsoX(float x);
    void setIsoY(float y);
    void setIsoPosition(float x, float y);
    void moveIsoBy(float vx, float vy);
}
