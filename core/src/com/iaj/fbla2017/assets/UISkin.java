/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 *
 * @author Jan Fic
 */
public class UISkin extends Skin {

    private static UISkin INSTANCE = null;

    private UISkin() {
        INSTANCE = Assets.GetInstance().get("skin/composed/skin.json");
    }

    public static UISkin getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UISkin();
        }
        return INSTANCE;
    }

    private void addResources() {
        add("stickynote", new NinePatch((Texture) Assets.GetInstance().get("skin/stickynote.9.png")));
        add("stickynoteOver", new NinePatch((Texture) Assets.GetInstance().get("skin/stickynoteOver.9.png")));
    }

    private void addStyles() {
        TextButtonStyle textbuttonStyle = new TextButtonStyle();
        textbuttonStyle.font = new BitmapFont();
        textbuttonStyle.up = this.getDrawable("stickynote");
        add("stickynoteTextButton", textbuttonStyle, TextButtonStyle.class);
    }

    public static class NotebookLabel extends Label {

        Texture texture;

        public NotebookLabel(CharSequence text, Skin skin, String style) {
            super(text, skin,style);
            texture = (Texture) Assets.GetInstance().get("skin/paper.png");
            texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.Repeat);
            this.setBounds(0, 0, texture.getWidth(), texture.getWidth());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            Color c = getColor();
            batch.setColor(c.r, c.g, c.b, c.a);
            batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), false, false);
            super.draw(batch, parentAlpha);

        }

    }

}
