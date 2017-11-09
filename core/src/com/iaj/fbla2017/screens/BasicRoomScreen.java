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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

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

        map = new TmxMapLoader().load("map/school/schoolRooms/test.tmx");
        mapRenderer = new IsometricTiledMapRenderer(map);
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
