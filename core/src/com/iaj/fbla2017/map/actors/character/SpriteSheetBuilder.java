/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.actors.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.map.actors.character.Character.Gender;
import com.iaj.fbla2017.map.actors.character.Character.HeadShape;

/**
 *
 * @author Jan Fic
 */
public class SpriteSheetBuilder {

    public final static FileHandle saveDir = Gdx.files.local("FBLA2017SHS/");
    String path = "character/";

    private HeadShape headShape;
    private Gender gender;
    
    private Animation<Sprite> standingEastAnim;
    
    public SpriteSheetBuilder(Character c) {
        gender = c.getGender();
        headShape = c.getHeadShape();
        //standing anim
        ////east
        Sprite f1 = buildSprite(c);
        standingEastAnim = new Animation<Sprite>(0.25f,f1);
    }

    public Animation<Sprite> getStandingEastAnim() {
        return standingEastAnim;
    }
    
    private Sprite buildSprite(Character c) {
    
        Sprite sprite = null;
        
        Texture head = (Texture) Assets.GetInstance().get(path + "head/" + headShape.toString() + ".png");
        Texture torso = (Texture) Assets.GetInstance().get(path + "torso/" + gender.toString() + ".png");
        Texture legs = (Texture) Assets.GetInstance().get(path + "leg/" + gender.toString() + ".png");
        Texture larm = (Texture) Assets.GetInstance().get(path + "arm/left.png");
        Texture rarm = (Texture) Assets.GetInstance().get(path + "arm/right.png");

        Pixmap h = getPixmap(head);
        Pixmap t = getPixmap(torso);
        Pixmap l = getPixmap(legs);
        Pixmap la = getPixmap(larm);
        Pixmap ra = getPixmap(rarm);

        Pixmap p = new Pixmap(32, 96, Pixmap.Format.RGBA8888);
        //p.setBlending(Pixmap.Blending.None); 
        p.setColor(0f, 0, 0, 0);
        p.drawPixmap(l, 0, 16 + 32 - 9);
        p.drawPixmap(la, 0, 16);
        p.drawPixmap(t, 0, 16);
        p.drawPixmap(ra, 0, 16);
        p.drawPixmap(h, 0, 0);

        //p.drawPixmap(p, 0, 0);
        Texture texture = new Texture(p);
        sprite = new Sprite(texture);
        
        return sprite;
    }

    public Pixmap getPixmap(Texture texture) {
        TextureData data = texture.getTextureData();
        if (!data.isPrepared()) {
            data.prepare();
        }
        return data.consumePixmap();
    }
}
