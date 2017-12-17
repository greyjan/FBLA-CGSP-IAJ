/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.UISkin.NotebookPaperTable;
import com.iaj.fbla2017.profiles.Profile;
import com.iaj.fbla2017.map.actors.character.player.Player;

/**
 *
 * @author Jan Fic
 */
public class UserProfileScreen implements Screen {

    private final Game game;

    Stage stage;

    Skin skin;

    Table table;
    NotebookPaperTable menu;

    public UserProfileScreen(Game game,Profile p, Player c) {
        this.game = game;

        skin = (Skin) Assets.GetInstance().get("skin/composed/skin.json");
        
        table = new Table();
        table.setFillParent(true);

        menu = new NotebookPaperTable("", "");
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
