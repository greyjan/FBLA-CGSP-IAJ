/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.iaj.fbla2017.assets.Assets;

/**
 *
 * @author Jan Fic
 */
public class Table extends Furniture {
    
    public Table(int x, int y, String direction) {
        super(x, y);
        this.sprite = new Sprite((Texture) Assets.GetInstance().get(FURNATURE_PATH));
    }
    
}
