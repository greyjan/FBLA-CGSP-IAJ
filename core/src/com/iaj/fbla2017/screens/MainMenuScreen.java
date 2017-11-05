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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.UISkin;
import com.iaj.fbla2017.assets.UISkin.NotebookLabel;

/**
 *
 * @author Jan Fic
 */
public class MainMenuScreen implements Screen {

    //esentials
    private final Game game;

    //stage
    public Stage stage;
    Table table;
    TextButton test;
    Label title;
    Skin skin;

    public MainMenuScreen(Game g) {
        this.game = g;
        stage = new Stage(new ScreenViewport());
//        stage = new Stage(new FitViewport(500, 400));
        skin = Assets.GetInstance().get("skin/composed/skin.json");

        table = new Table();
        table.setFillParent(true);

        //title
        title = new Label("Main Menu", skin, "title");
        table.add(title);

        table.row().height(100);
        table.row();
        table.row();

        //test button
        test = new TextButton("this is a stickynoteTextButton \n and this is another line \n words", skin);
        test.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (test.isOver()) {
                    game.setScreen(((SandboxGame) game).cCScreen);

                }
            }
        });
        table.add(test);

//        UISkin.NotebookLabel label = new NotebookLabel("hello my name is jan \n and i am programming", skin, "default");
//        label.setColor(Color.WHITE);
//        table.add(label);

        stage.addActor(table);
        //stage.setDebugAll(true);

        ((OrthographicCamera) (stage.getViewport().getCamera())).zoom = 4f / 8f;

        //stage.getViewport().getCamera().position.set(layer.getWidth() * layer.getTileWidth() / 2, 0, 0);
        stage.getViewport().getCamera().update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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

}
