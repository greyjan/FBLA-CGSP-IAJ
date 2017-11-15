/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.iaj.fbla2017.map.objects.StudentDesk;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jan Fic
 */
public class Room extends Stage {

    List<Group> layers;
    

    public Room(TiledMap map,Viewport viewport) {
        super(viewport);
        makeWall(map);
        makeFurnature(map);
    }

    private void makeWall(TiledMap map) {
        Group furnatureLayer = new Group();
        furnatureLayer.setName("furnatureLayer");
        MapObjects objects = map.getLayers().get("wall").getObjects();
        Iterator<MapObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            MapObject object = iterator.next();
            if (object.getProperties().get("type", String.class).equalsIgnoreCase("furnature")) {
                if (object.getProperties().get("furnatureType", String.class).equalsIgnoreCase("studentDesk")) {
                    String direction = object.getProperties().get("direction", String.class);
                    int x = object.getProperties().get("x", float.class).intValue();
                    int y = object.getProperties().get("y", float.class).intValue();

                    StudentDesk studentDesk = new StudentDesk(x, y, direction);
                    setToPosition(studentDesk, map);
                    furnatureLayer.addActor(studentDesk);

                }
            }
        }
        int offX = (int) map.getLayers().get("objects").getRenderOffsetX();
        int offY = -(int) map.getLayers().get("objects").getRenderOffsetY();
        furnatureLayer.setPosition(offX, offY);
        this.addActor(furnatureLayer);
    }

    private void makeFurnature(TiledMap map) {
        Group furnatureLayer = new Group();
        furnatureLayer.setName("furnatureLayer");
        MapObjects objects = map.getLayers().get("objects").getObjects();
        Iterator<MapObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            MapObject object = iterator.next();
            if (object.getProperties().get("type", String.class).equalsIgnoreCase("furnature")) {
                if (object.getProperties().get("furnatureType", String.class).equalsIgnoreCase("studentDesk")) {
                    String direction = object.getProperties().get("direction", String.class);
                    int x = object.getProperties().get("x", float.class).intValue();
                    int y = object.getProperties().get("y", float.class).intValue();

                    StudentDesk studentDesk = new StudentDesk(x, y, direction);
                    setToPosition(studentDesk, map);
                    furnatureLayer.addActor(studentDesk);

                }
            }
        }
        int offX = (int) map.getLayers().get("objects").getRenderOffsetX();
        int offY = -(int) map.getLayers().get("objects").getRenderOffsetY();
        furnatureLayer.setPosition(offX, offY);
        this.addActor(furnatureLayer);
    }

    @Override
    public void draw() {
        super.draw(); //To change body of generated methods, choose Tools | Templates.
       
    }

    public void setToPosition(IsometricActor a, TiledMap map) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        float x = (a.getIsoX() + a.getIsoY()) / 2 * layer.getTileWidth();
        float y = -((a.getIsoX() - a.getIsoY()) * layer.getTileHeight() / 2) + 5;
        a.setPosition(x + a.getOffsetX(), y + a.getOffsetY());
    }
}
