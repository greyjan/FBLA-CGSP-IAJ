/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Iterator;

/**
 *
 * @author Jan Fic
 */
public class BasicRoomScreen implements Screen {

    Stage stage;
    TiledMap map;
    private final IsometricTiledMapRenderer mapRenderer;
    private final SpriteBatch batch;
    private final Game game;

    public BasicRoomScreen(Game g) {
        this.game = g;
        batch = new SpriteBatch();
        stage = new Stage();

        map = new TmxMapLoader().load("map/school/schoolRooms/test.tmx");
        mapRenderer = new IsometricTiledMapRenderer(map);
        ((OrthographicCamera) (stage.getViewport().getCamera())).zoom = 1f;
        //TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        stage.getViewport().getCamera().position.set(0, 0, 0);
        stage.getViewport().getCamera().update();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

        mapRenderer.setView((OrthographicCamera) stage.getViewport().getCamera());
        mapRenderer.render();
        Iterator<MapLayer> iterator = map.getLayers().iterator();
        while(iterator.hasNext()) {
            MapLayer l = iterator.next();
            mapRenderer.renderObjects(l);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
