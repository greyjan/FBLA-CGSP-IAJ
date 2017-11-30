/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 *
 * @author Jan Fic
 */
public class UISkin extends Skin {

    private static Skin INSTANCE = null;

    private UISkin() {
        INSTANCE = Assets.GetInstance().get("skin/composed/skin.json");
    }

    public static Skin getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UISkin();
        }
        return INSTANCE;
    }

    public static class NotebookPaperTable extends Table {

        Texture paper;
        Texture header;
        
        Label headerLabel;

        public NotebookPaperTable(String title, String style) {
            paper = (Texture) Assets.GetInstance().get("skin/paper.png");
            paper.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.Repeat);

            header = (Texture) Assets.GetInstance().get("skin/paperHeader.png");
            header.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.Repeat);

            headerLabel = new Label(title,(Skin)Assets.GetInstance().get("skin/composed/skin.json"),style);
            headerLabel.setAlignment(Align.bottom);
            this.row().height(31).padTop(4).padBottom(1).fillX();
            this.add(headerLabel);

            this.padBottom(16);
            this.padRight(32);
        }

        public void setTitle(String title) {
            headerLabel.setText(title);
        }
        
        public void setHeader(Label l) {
            this.headerLabel = l;
            headerLabel.invalidate();
        }
        
        @Override
        public void addActor(Actor actor) {
            super.addActor(actor);
            this.padLeft(35);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            Color c = getColor();
            batch.setColor(c.r, c.g, c.b, c.a);
            //batch.draw(texture, getX(), getY());
            //batch.draw(header, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, (int) getWidth(), (int) getHeight(), false, false);
            batch.draw(paper, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, (int) getWidth(), (int) getHeight(), false, false);
            batch.draw(header, getX(), getY() + getHeight() - 31, getOriginX(), getOriginY(), getWidth(), 31, getScaleX(), getScaleY(), getRotation(), 0, 0, (int) getWidth(), (int) 31, false, false);

            super.draw(batch, parentAlpha);

        }

    }

}
