/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.actors.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.map.utils.IsometricActor;
import com.iaj.fbla2017.profiles.Profile;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jan Fic
 */
public class Character extends IsometricActor {

    transient String path = "character/";
    SpriteSheetBuilder ssb;
    transient SpriteBatch batch;

    ArrayList<String> animations;
    
    //animation stuff;
    float statetime;
    Animation<Sprite> currentAnim;

    public Animation<Sprite> standingEastAnim;

    public enum Gender {
        male, female
    }

    public enum HeadShape {
        pointed, round, square, wide
    }

    public enum HairStyle {

    }

    transient Color eyeColor;
    transient Color skinColor;

    Gender gender;
    HeadShape headShape;
    HairStyle hair;

    public Character(int l) {
        super(l);
        
        randomize();
        ssb = new SpriteSheetBuilder(this);
        setAllAnims();
        currentAnim = standingEastAnim;
        setBounds(0, 0, 32, 96);
    }

    public Character(int l, String _gender, String _headShape) {
        super(l);
        gender = Gender.valueOf(_gender);
        headShape = HeadShape.valueOf(_headShape);
        ssb = new SpriteSheetBuilder(this);
        setAllAnims();
        currentAnim = standingEastAnim;
        this.setBounds(0, 0, 32, 96);
    }
    
    public Character(Profile p) {
        super(0);
        
    }

    private void setAllAnims() {
        standingEastAnim = ssb.getStandingEastAnim();
    }

    private void randomize() {
        Random rand = new Random();

        gender = Gender.values()[rand.nextInt(Gender.values().length)];
        headShape = HeadShape.values()[rand.nextInt(HeadShape.values().length)];
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        statetime += Gdx.graphics.getDeltaTime();
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        batch.draw(currentAnim.getKeyFrame(statetime), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    private void buildSprite() {
        //this part is my favourite / favorite 
        /*
            using enums to randomize the character, then using the names of the values 
            to form the path of the image used. 
         */
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

        Pixmap p = new Pixmap(32, 96, Format.RGBA8888);
        //p.setBlending(Pixmap.Blending.None); 
        p.setColor(0f, 0, 0, 0);
        p.drawPixmap(l, 0, 16 + 32 - 9);
        p.drawPixmap(la, 0, 16);
        p.drawPixmap(t, 0, 16);
        p.drawPixmap(ra, 0, 16);
        p.drawPixmap(h, 0, 0);

        //p.drawPixmap(p, 0, 0);
        Texture texture = new Texture(p);
        //sprite = new Sprite(texture);
    }

    public Pixmap getPixmap(Texture texture) {
        TextureData data = texture.getTextureData();
        if (!data.isPrepared()) {
            data.prepare();
        }
        return data.consumePixmap();
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);;
        ssb = new SpriteSheetBuilder(this);
        setAllAnims();
        currentAnim = standingEastAnim;
    }

    public void setHeadShape(String headShape) {
        this.headShape = HeadShape.valueOf(headShape);
        ssb = new SpriteSheetBuilder(this);
        setAllAnims();
        currentAnim = standingEastAnim;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public HairStyle getHair() {
        return hair;
    }

    public Gender getGender() {
        return gender;
    }

    public HeadShape getHeadShape() {
        return headShape;
    }

}
