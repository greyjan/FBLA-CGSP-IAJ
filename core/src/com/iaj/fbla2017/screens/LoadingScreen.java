package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.LevelAssets;
import com.iaj.fbla2017.assets.LevelAssets.ILoadListener;

public class LoadingScreen implements Screen {

    final SandboxGame game;

    protected BitmapFont font;
    protected SpriteBatch batch;

    protected final Stage stage;
    ProgressBar loadingBar;
    Texture background;

    public interface ILoadingListener {

        public void OnFinished();
    }

    private ILoadingListener loadingListener;
    private ILoadListener loadListener;

    private LevelAssets assets = Assets.GetInstance();

    public LoadingScreen(SandboxGame game) {
        System.out.println("Making Loading Screen...");
        Skin skin = new Skin(Gdx.files.internal("skin/composed/skin.json"));
        loadingBar = new ProgressBar(0, 1, 0.1f, false, skin);
        Label label = new Label("Loading...", skin);
        Table table = new Table();
        table.setFillParent(true);
        table.add(label);
        table.row();
        table.add(loadingBar);

        this.game = game;

        stage = new Stage(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2));
        stage.addActor(table);

        loadListener = new ILoadListener() {

            @Override
            public void OnBegin() {

            }

            @Override
            public void OnLoading(float value) {
                loadingBar.setValue(value);
            }

            @Override
            public void OnFinished() {
                assets.removeLoadListener(loadListener);
                loadListener = null;

                if (loadingListener != null) {
                    loadingListener.OnFinished();
                }
            }

        };

        assets.addLoadListener(loadListener);
        
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        assets.update();

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

    public void setILoadingListener(ILoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
