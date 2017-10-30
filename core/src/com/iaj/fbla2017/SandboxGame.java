package com.iaj.fbla2017;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.iaj.fbla2017.screens.CharacterCustomizationScreen;
import com.iaj.fbla2017.screens.LoadingScreen;
import com.iaj.fbla2017.screens.LoadingScreen.ILoadingListener;

public class SandboxGame extends Game {

    LoadingScreen loadingScreen;
    public CharacterCustomizationScreen cCScreen;

    @Override
    public void create() {
        LoadingScreen loading = new LoadingScreen(this);
        loading.setILoadingListener(new ILoadingListener() {
            @Override
            public void OnFinished() {
                cCScreen = new CharacterCustomizationScreen(SandboxGame.this);
            }
        });
        this.setScreen(loading);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {;
    }
}
