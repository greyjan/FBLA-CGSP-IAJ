/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Fic
 */
public class IsometricTiledMapRendererWithSprites extends IsometricTiledMapRenderer {

    public final List<IsometricActor> actors;
    private final int drawSpritesAfterLayer = 1;
    Stage stage;

    public IsometricTiledMapRendererWithSprites(Stage s, TiledMap map, float zoom) {
        super(map, zoom);
        actors = new ArrayList<IsometricActor>();
        stage = s;
    }

    public void addActor(IsometricActor a) {
        actors.add(a);
        stage.addActor(a);
        setToPosition(a);
    }

    @Override
    public void render() {
        beginRender();
        int currentLayer = 0;
        for (int i = 0; i < map.getLayers().getCount(); i++) {
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(i);
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer) layer);
                    currentLayer++;
                    if (currentLayer == drawSpritesAfterLayer) {
                        for (IsometricActor a : actors) {
                            a.draw(batch, 0f);
                            batch.setColor(Color.WHITE);
                        }
                    }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
        }
        endRender();
    }

    public void addActors(ArrayList<IsometricActor> actors) {
        for (IsometricActor a : actors) {
            addActor(a);
        }
    }

    public void setToPosition(IsometricActor a) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        float x = (a.getIsoX() + a.getIsoY()) / 2 * layer.getTileWidth();
        float y = -((a.getIsoX() - a.getIsoY()) * layer.getTileHeight() / 2) + 5 ;
        a.setPosition(x + a.getOffsetX(), y + a.getOffsetY());
    }

}
