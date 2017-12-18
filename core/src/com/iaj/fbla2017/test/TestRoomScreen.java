/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.map.actors.character.Character.Direction;
import com.iaj.fbla2017.map.actors.character.player.Player;
import com.iaj.fbla2017.map.rooms.ClassRoom;
import com.iaj.fbla2017.map.utils.Room;

/**
 *
 * @author Jan Fic
 */
public class TestRoomScreen implements Screen {

    private final Game game;
    Stage stage;
    Player player;
    Room room;

    public TestRoomScreen(Game g, Player p) {
        game = g;
        player = p;

//        stage = new Stage(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2));
        room = new ClassRoom(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2), player);
        //room.addCharacter(player);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(room);
    }

    @Override
    public void render(float delta) {
        input();

//        stage.act();
//        stage.draw();
        room.act();
        room.draw();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.walk(Direction.SOUTH);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.walk(Direction.EAST);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.walk(Direction.NORTH);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.walk(Direction.WEST);
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
