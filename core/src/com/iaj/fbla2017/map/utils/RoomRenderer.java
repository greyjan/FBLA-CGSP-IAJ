/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import java.util.Iterator;

/**
 *
 * @author Jan Fic
 */
public class RoomRenderer extends IsometricTiledMapRenderer {

    public RoomRenderer(TiledMap map) {
        super(map);
    }

    public RoomRenderer(TiledMap map, Batch batch) {
        super(map, batch);
    }

    public RoomRenderer(TiledMap map, float unitScale) {
        super(map, unitScale);
    }

    public RoomRenderer(TiledMap map, float unitScale, Batch batch) {
        super(map, unitScale, batch);
    }

    @Override
    public void renderObjects(MapLayer layer) {
        Iterator<MapObject> objects = layer.getObjects().iterator();
        while (objects.hasNext()) {
            MapObject object = objects.next();
            if (object instanceof TextureMapObject) {
                TextureMapObject textureObject = (TextureMapObject) object;

                batch.draw(
                        textureObject.getTextureRegion(),
                        textureObject.getX(),
                        textureObject.getY()
                );
            }
        }

    }
}
