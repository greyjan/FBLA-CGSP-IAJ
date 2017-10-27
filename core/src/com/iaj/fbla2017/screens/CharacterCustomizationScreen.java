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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.iaj.fbla2017.map.utils.IsometricTiledMapRendererWithSprites;

/**
 *
 * @author Jan Fic
 */
public class CharacterCustomizationScreen implements Screen {

    public Stage stage;
    private final TiledMap map;
    private IsometricTiledMapRendererWithSprites mapRenderer;
    private SpriteBatch batch;
    private final Game game;

    public CharacterCustomizationScreen(Game game) {
        //inits
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        map = new TiledMap();

        //map creation
        //
        //createMap()
        //
        //renderer specifications
        mapRenderer = new IsometricTiledMapRendererWithSprites(stage, map, 1);

        //camera
        //setCamera
        ((OrthographicCamera) (stage.getViewport().getCamera())).zoom = .25f / .75f;
        //TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        //stage.getViewport().getCamera().position.set(layer.getWidth() * layer.getTileWidth() / 2, 0, 0);
        stage.getViewport().getCamera().update();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
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
