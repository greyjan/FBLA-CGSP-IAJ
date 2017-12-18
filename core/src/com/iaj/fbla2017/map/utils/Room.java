package com.iaj.fbla2017.map.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.iaj.fbla2017.map.objects.Door;
import com.iaj.fbla2017.map.objects.Furniture;
import com.iaj.fbla2017.map.objects.StudentDesk;
import com.iaj.fbla2017.map.objects.Table;
import com.iaj.fbla2017.map.objects.Wall;
import com.iaj.fbla2017.map.actors.character.Character;
import com.iaj.fbla2017.map.tiles.Carpet;
import com.iaj.fbla2017.map.utils.IsometricActor.IsometricComparatorByPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jan Fic
 */
public class Room extends Stage {

    List<Group> layers;

    private Group wall, furniture, characters, floor;

    protected int width, height;

    public Room(TiledMap map, Viewport viewport) {
        super(viewport);
        makeWall(map);
        makeFurnature(map);
    }

    private void makeWall(TiledMap map) {
        Group walls = new Group();
        walls.setName("walls");
        for (int i = 1; i <= 5; i++) {
            ArrayList<IsometricActor> wall = new ArrayList<IsometricActor>();
            String layerName = "wall " + i;

            MapObjects objects = map.getLayers().get(layerName).getObjects();

            Iterator<MapObject> iterator = objects.iterator();
            while (iterator.hasNext()) {
                MapObject object = iterator.next();
                if (object.getProperties().get("type", String.class).equalsIgnoreCase("wall")) {
                    //System.out.println();
                    int x = object.getProperties().get("x", float.class).intValue();
                    int y = object.getProperties().get("y", float.class).intValue();

                    Wall w = new Wall(x, y);
                    setToPosition(w, map);
                    wall.add(w);
                }
            }

            wall.sort(new Comparator<IsometricActor>() {
                @Override
                public int compare(IsometricActor t, IsometricActor t1) {
                    if (t.getIsoY() < t1.getIsoY()) {
                        return 1;
                    } else if (t.getIsoY() > t1.getIsoY()) {
                        return -1;
                    } else if (t.getIsoX() < t1.getIsoX()) {
                        return -1;
                    } else if (t.getIsoX() > t1.getIsoX()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            Group wall_layer = new Group();
            for (int j = 0; j < wall.size(); j++) {
                wall_layer.addActor(wall.get(j));
            }
            int offX = (int) map.getLayers().get(layerName).getRenderOffsetX();
            int offY = -(int) map.getLayers().get(layerName).getRenderOffsetY();
            wall_layer.setPosition(offX, offY);
            walls.addActor(wall_layer);
        }
        this.addActor(walls);
    }

    private void makeFurnature(TiledMap map) {
        Group furnitureLayer = new Group();
        furnitureLayer.setName("furnitureLayer");

        ArrayList<IsometricActor> actors = new ArrayList<IsometricActor>();

        MapObjects objects = map.getLayers().get("objects").getObjects();
        Iterator<MapObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            MapObject object = iterator.next();
            if (object.getProperties().get("type", String.class).equalsIgnoreCase("furniture")) {
                if (object.getProperties().get("furnitureType", String.class).equalsIgnoreCase("studentDesk")) {
                    String direction = object.getProperties().get("direction", String.class);
                    int x = object.getProperties().get("x", float.class).intValue();
                    int y = object.getProperties().get("y", float.class).intValue();
                    StudentDesk studentDesk = new StudentDesk(x, y, direction);
                    setToPosition(studentDesk, map);
                    actors.add(studentDesk);
                } else if (object.getProperties().get("furnitureType", String.class).equalsIgnoreCase("door")) {
                    String direction = object.getProperties().get("direction", String.class);
                    int x = object.getProperties().get("x", float.class).intValue();
                    int y = object.getProperties().get("y", float.class).intValue();
                    Door door = new Door(x, y, direction);
                    setToPosition(door, map);
                    actors.add(door);
                } else if (object.getProperties().get("furnitureType", String.class).equalsIgnoreCase("table")) {
                    String direction = object.getProperties().get("direction", String.class);
                    int x = object.getProperties().get("x", float.class).intValue();
                    int y = object.getProperties().get("y", float.class).intValue();
                    String part = object.getProperties().get("part", String.class);
                    Table table = new Table(x, y, direction, part);
                    setToPosition(table, map);
                    actors.add(table);
                }
            }
        }

        actors.sort(new Comparator<IsometricActor>() {
            @Override
            public int compare(IsometricActor t, IsometricActor t1) {
                if (t.getIsoY() < t1.getIsoY()) {
                    return 1;
                } else if (t.getIsoY() > t1.getIsoY()) {
                    return -1;
                } else if (t.getIsoX() < t1.getIsoX()) {
                    return -1;
                } else if (t.getIsoX() > t1.getIsoX()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (int j = 0; j < actors.size(); j++) {
            furnitureLayer.addActor(actors.get(j));
        }
        int offX = (int) map.getLayers().get("objects").getRenderOffsetX();
        int offY = -(int) map.getLayers().get("objects").getRenderOffsetY();

        furnitureLayer.setPosition(offX, offY);
        this.addActor(furnitureLayer);
    }

    public void setToPosition(IsometricActor a, TiledMap map) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        float x = (a.getIsoX() + a.getIsoY()) / 2 * layer.getTileWidth();
        float y = -((a.getIsoX() - a.getIsoY()) * layer.getTileHeight() / 2);
        a.setPosition(x + a.getOffsetX(), y + a.getOffsetY());
    }

    public Room(int width, int height, Viewport viewport) {
        super(viewport);
        this.width = width;
        this.height = height;

        wall = new Group();
        wall.setName("wall");
        furniture = new Group();
        furniture.setName("furniture");
        furniture.setPosition(0, -10);
        characters = new Group();
        characters.setName("characters");
        characters.setPosition(0, 10);
        floor = new Group();
        floor.setName("floor");
        this.create();
        this.addActor(floor);
        this.addActor(wall);
        this.addActor(furniture);
        this.addActor(characters);
        this.getViewport().getCamera().position.x = 16;
        this.getViewport().getCamera().position.y = 0;
    }

    public void addFurnature(Furniture f) {
        furniture.addActor(f);
        furniture.getChildren().sort();
    }

    public void addCharacter(Character c) {
        characters.addActor(c);
        characters.getChildren().sort();
    }

    public void setFloor(String type) {

        if (type.equals("carpet")) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Carpet c = new Carpet(x, y);
                    c.setColor(x / 10f, y / 10f, 255 / 255f, 1);
                    floor.addActor(c);
                }
            }
        }

    }

    protected void create() {
    }

    @Override
    public void draw() {
        super.draw(); //To change body of generated methods, choose Tools | Templates.
    }

    protected List<Group> getLayers() {
        return layers;
    }

}
