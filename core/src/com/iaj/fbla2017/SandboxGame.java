package com.iaj.fbla2017;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.iaj.fbla2017.screens.BasicRoomScreen;
import com.iaj.fbla2017.screens.CharacterCustomizationScreen;
import com.iaj.fbla2017.screens.LoadingScreen;
import com.iaj.fbla2017.screens.LoadingScreen.ILoadingListener;
import com.iaj.fbla2017.screens.MainMenuScreen;

public class SandboxGame extends Game {

    LoadingScreen loadingScreen;
    public CharacterCustomizationScreen cCScreen;
    public MainMenuScreen mmScreen;
    public BasicRoomScreen brScreen;

    @Override
    public void create() {
        LoadingScreen loading = new LoadingScreen(this);
        loading.setILoadingListener(new ILoadingListener() {
            @Override
            public void OnFinished() {
                cCScreen = new CharacterCustomizationScreen(SandboxGame.this);
                mmScreen = new MainMenuScreen(SandboxGame.this);
                brScreen = new BasicRoomScreen(SandboxGame.this);
                SandboxGame.this.setScreen(mmScreen);
            }
        });
        this.setScreen(loading);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        super.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {;
    }
}
