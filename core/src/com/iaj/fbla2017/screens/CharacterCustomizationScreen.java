/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.map.utils.IsometricTiledMapRendererWithSprites;

/**
 *
 * @author Jan Fic
 */
public class CharacterCustomizationScreen implements Screen {

    public Stage stage;
    private final TiledMap map;
    private final IsometricTiledMapRendererWithSprites mapRenderer;
    private final SpriteBatch batch;
    private final Game game;
    PlayerTest test;
    Texture paperTexture;

    class Paper extends Actor {

        Texture texture;

        public Paper() {
            texture = (Texture) Assets.GetInstance().get("skin/paper.png");
            texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.Repeat);
            this.setBounds(0, 0, texture.getWidth(), texture.getWidth() );
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            Color c = getColor();
            batch.setColor(c.r, c.g, c.b, c.a);

            batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), false, false);
        }

    }

    public CharacterCustomizationScreen(Game game) {
        //inits
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        map = new TiledMap();

        paperTexture = (Texture) Assets.GetInstance().get("skin/paper.png");
        paperTexture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.Repeat);

        Paper paper = new Paper();
        paper.setPosition(0, 0);
        paper.setWidth(stage.getWidth());
        paper.setHeight(stage.getHeight());
        //paper.setPosition(10, 10);
        stage.addActor(paper);
        //debug
        test = new PlayerTest();
        test.setPosition(stage.getWidth()/2, stage.getHeight()/2);
        stage.addActor(test);

        //map creation
        //
        //createMap()
        //
        //renderer specifications
        mapRenderer = new IsometricTiledMapRendererWithSprites(stage, map, 1);

        //camera
        //setCamera
        ((OrthographicCamera) (stage.getViewport().getCamera())).zoom = 1f;
        //TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        //stage.getViewport().getCamera().position.set(0, 0, 0);
        stage.getViewport().getCamera().update();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        stage.act(delta);
        stage.draw();
        mapRenderer.setView((OrthographicCamera) stage.getViewport().getCamera());
        mapRenderer.render();

        batch.begin();
        //batch.draw(paperTexture, 10, 10, 0, 0, 128, 128);
        batch.end();

        input();
    }

    private void input() {
        Color c = new Color(Gdx.input.getX() / stage.getWidth(), Gdx.input.getY() / stage.getHeight(), 0.5f, 1);

        //test.setColor(new Color().fromHsv((float) (Math.random() * 360), 0.5f, 0.5f));
        test.setColor(c);
        //System.out.println(test.+getColor());

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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

    class PlayerTest extends Actor {

        Sprite sprite;

        public PlayerTest() {
            sprite = new Sprite((Texture) Assets.GetInstance().get("player/person.png"));
            this.setBounds(0, 0, sprite.getWidth(), sprite.getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            Color c = getColor();
            batch.setColor(c.r, c.g, c.b, c.a);
            // batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHieght(), parentAlpha, parentAlpha, parentAlpha);
            batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }

    }
}
