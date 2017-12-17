/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.actors.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.map.utils.IsometricActor;
import com.iaj.fbla2017.profiles.Profile;
import com.iaj.fbla2017.profiles.ProfilesManager;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jan Fic
 */
public class Character extends IsometricActor {

    public static final String PATH = "newCharacterFolder/";

    SpriteSheetBuilder ssb;
    SpriteBatch batch;

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

    public Pixmap getPixmap(Texture texture) {
        TextureData data = texture.getTextureData();
        if (!data.isPrepared()) {
            data.prepare();
        }
        return data.consumePixmap();
    }

    public void saveSprites(FileHandle root) {
        //standing
        ////east
        for (int i = 0; i < this.standingEastAnim.getKeyFrames().length; i++) {
            FileHandle fl = root.child("standing/").child("east/").child("frame" + i + ".png");
            PixmapIO.writePNG(fl, this.standingEastAnim.getKeyFrames()[i].getTexture().getTextureData().consumePixmap());
        }
        ////south
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
