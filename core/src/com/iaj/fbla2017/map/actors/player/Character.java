/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.actors.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.iaj.fbla2017.map.utils.IsometricActor;
import java.util.Random;

/**
 *
 * @author Jan Fic
 */
public class Character extends IsometricActor {

    Sprite sprite;

    enum Gender {
        male, female
    }

    enum JawType {
        pointed, round, square, wide
    }
    
    enum HairStyle {
        
    }

    Color eyeColor;
    Color skinColor;

    Gender gender;
    JawType jawType;
    HairStyle hair;

    public Character(int l) {
        super(l);
        sprite = new Sprite();
        Random rand = new Random();
        gender = Gender.values()[rand.nextInt(Gender.values().length)];
        jawType = JawType.values()[rand.nextInt(JawType.values().length)];
        buildSprite();
    }

    private void buildSprite() {
        
    }

}
