package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.UISkin.NotebookPaperTable;
import com.iaj.fbla2017.map.actors.character.player.Player;
import com.iaj.fbla2017.profiles.ProfilesManager;
import com.iaj.fbla2017.test.TestRoomScreen;

/**
 *
 * @author Jan Fic
 */
public class ProfileSelectionScreen implements Screen {

    private final Game game;
    Stage stage;
    Table table;
    NotebookPaperTable menu;

    Skin skin;

    ProfilesManager profileManager;

    public ProfileSelectionScreen(Game g) {
        game = g;
        stage = new Stage(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2));

        skin = (Skin) Assets.GetInstance().get("skin/composed/skin.json");

        profileManager = ProfilesManager.getProfiles();

        table = new Table();
        table.setFillParent(true);

        menu = new NotebookPaperTable("Profile Selection", "title");
        menu.row();

        for (int i = 0; i < profileManager.profiles.size(); i++) {
            TextButton profileButton = new TextButton(profileManager.profiles.get(i).toString(), skin);
            final Player p = profileManager.profiles.get(i).getPlayer();
            profileButton.add(p);
            profileButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new TestRoomScreen(game,p));
                }
            });
            menu.add(profileButton).width(200).pad(12).height(50);
            menu.row();
        }

        TextButton makeNewProfile = new TextButton("Make\nNew Profile", skin);
        makeNewProfile.setColor(0.6f, 1f, 0.6f, 1f);
        makeNewProfile.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ProfileCreationScreen(game));
            }

        });

        TextButton backButton;
        backButton = new TextButton("Back", skin);
        backButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
            }

        });
        backButton.setColor(1f, 0.4f, 0.6f, 1f);

        menu.add(makeNewProfile).height(60).padBottom(10).width(150);
        menu.row();
        menu.add(backButton);
        menu.row().height(100 - menu.getHeight() - 40);
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
