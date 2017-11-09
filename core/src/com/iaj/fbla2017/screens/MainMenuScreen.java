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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.UISkin;
import com.iaj.fbla2017.assets.UISkin.NotebookPaperTable;

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
    NotebookPaperTable menu;
    Skin skin;

    public MainMenuScreen(Game g) {
        this.game = g;
        stage = new Stage(new ScreenViewport());
//        stage = new Stage(new FitViewport(500, 400));
        skin = Assets.GetInstance().get("skin/composed/skin.json");

        table = new Table();
        table.setFillParent(true);

        //menu
        menu = new NotebookPaperTable();
        //title
        title = new Label("Main Menu", skin, "title");
        
        //button test
        test = new TextButton("Play",skin);
        test.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (test.isOver()) {
                    game.setScreen(((SandboxGame) game).brScreen);

                }
            }
        });
        
        
        //some text
        Label text = new Label("Thistftftftftftftft is some text cause like \n ... why not. \n i hope the alignment is good. \n probs not tho",skin);
        //menu.add(title).colspan(3);
        menu.row();
        menu.add(text);
        menu.row();
        menu.add(test);
        //menu.setDebug(true);
        
        table.add(menu);

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
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
