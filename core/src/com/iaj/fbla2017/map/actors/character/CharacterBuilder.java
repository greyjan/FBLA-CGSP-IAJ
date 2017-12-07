/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.actors.character;

import com.badlogic.gdx.graphics.Pixmap;

/**
 *
 * @author Jan Fic
 */
public class CharacterBuilder {
    private Character character;
    Pixmap pixmap;
    
    public CharacterBuilder() {
    }
    
    public void begin() {
        character = new Character(0);
        pixmap = new Pixmap(32,96,Pixmap.Format.RGB888);
    }
    
    public void body() {
        
    }
    
    
}
