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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.UISkin.NotebookPaperTable;

/**
 *
 * @author Jan Fic
 */
public class MainMenuScreen implements Screen {

    private final Game game;

    public Stage stage;
    Table table;
    TextButton playButton, exitButton;
    Label title;
    NotebookPaperTable menu;
    Skin skin;

    public MainMenuScreen(Game g) {
        this.game = g;
        stage = new Stage(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2));
        skin = Assets.GetInstance().get("skin/composed/skin.json");

        table = new Table();
        table.setFillParent(true);

        //menu
        menu = new NotebookPaperTable("FBLA: The First Year", "title");
        //title
        title = new Label("Main Menu", skin, "title");

        //button test
        playButton = new TextButton("Play", skin);
        playButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (playButton.isOver()) {
                    game.setScreen(((SandboxGame) game).psScreen);

                }
            }
        });

        exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (exitButton.isOver()) {
                    Gdx.app.exit();
                }
            }
        });
        
        

        Label text = new Label("The Game", skin);
        menu.row();
        menu.add(text);
        menu.row();
        menu.add(playButton).width(60).height(60).padBottom(10);
        menu.row();
        menu.add(exitButton).width(60).height(60).padBottom(10);
        menu.row().height(150 - menu.getHeight());
        menu.add();

        table.add(menu);

        stage.addActor(table);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Color c = Color.TAN;
        Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
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
