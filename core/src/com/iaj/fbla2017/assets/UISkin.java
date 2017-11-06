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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

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

    public static class NotebookPaperTable extends Table {

        Texture paper;

        public NotebookPaperTable() {
            paper = (Texture) Assets.GetInstance().get("skin/paper.png");
            paper.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.Repeat);
            //this.setBounds(0, 0, paper.getWidth(), paper.getWidth());
            this.padBottom(16);
        }

        @Override
        public void addActor(Actor actor) {
            super.addActor(actor); //To change body of generated methods, choose Tools | Templates.
            this.padLeft(32);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            Color c = getColor();
            batch.setColor(c.r, c.g, c.b, c.a);
            //batch.draw(texture, getX(), getY());
            batch.draw(paper, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, (int) getWidth(), (int) getHeight(), false, false);
            super.draw(batch, parentAlpha);

        }

    }

}
