package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.UISkin.NotebookPaperTable;
import com.iaj.fbla2017.map.actors.character.player.Player;
import com.iaj.fbla2017.profiles.Profile;
import com.iaj.fbla2017.profiles.ProfilesManager;

public class ProfileCreationScreen implements Screen {

    private final Game game;
    Stage stage;
    Table table;
    NotebookPaperTable menu;

    Skin skin;

    ProfilesManager profileManager;

    Player newPlayer;
    TextField name;
    SelectBox<String> gender;
    SelectBox<String> headShape;
    TextField age;
    TextButton makeProfile;
    TextButton backButton;
    final Color lightColor;
    final Color darkColor;
    final float[] range = new float[3];
    Color skinColor;
    Slider skinColorSlider;

    public ProfileCreationScreen(Game g) {
        game = g;
        stage = new Stage(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2));

        {
            lightColor = new Color(227 / 255f, 216 / 255f, 197 / 255f, 255 / 255f);
            darkColor = new Color(92 / 255f + .1f, 75 / 255f + .1f, 44 / 255f + .1f, 255 / 255f);
            skinColor = new Color();
            float[] hsl = {38f, .52f, .5f};
            darkColor.fromHsv(hsl);
            range[0] = lightColor.r - darkColor.r;
            range[1] = lightColor.g - darkColor.g;
            range[2] = lightColor.b - darkColor.b;
            
        }

        skin = (Skin) Assets.GetInstance().get("skin/composed/skin.json");

        profileManager = ProfilesManager.getProfiles();

        newPlayer = new Player(1);

        makeStage();
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

    private void makeStage() {
        table = new Table();
        table.setFillParent(true);

        menu = new NotebookPaperTable("New Student", "title");
        menu.row().height(16).padBottom(10);

        Label nameLabel = new Label("Name: ", skin);
        name = new TextField("", skin);

        Label genderLabel = new Label("Gender: ", skin);
        gender = new SelectBox<String>(skin);
        gender.setItems("male", "female");
        gender.setSelected(newPlayer.getGender().toString());

        Label headShapeLabel = new Label("Head Shape: ", skin);
        headShape = new SelectBox<String>(skin);
        headShape.setItems("pointed", "round", "square", "wide");
        headShape.setSelected(newPlayer.getHeadShape().toString());

        Label ageLabel = new Label("Age: ", skin);
        age = new TextField("", skin);
        age.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());

        gender.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                newPlayer.setGender(gender.getSelected().toLowerCase());
            }
        });

        headShape.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                newPlayer.setHeadShape(headShape.getSelected());
            }
        });

        skinColorSlider = new Slider(0, 1, 0.01f, false, skin);
        skinColorSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                setColorFromSlider(skinColorSlider.getValue());
                newPlayer.setColor(skinColor);
            }
        });
        

        makeProfile = new TextButton("Make Profile", skin);
        makeProfile.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                profileManager.profiles.add(new Profile(name.getText(), gender.getSelected(), headShape.getSelected(), Integer.parseInt(age.getText()), newPlayer));
                profileManager.saveAll();
                game.setScreen(new ProfileSelectionScreen(game));
            }

        });
        makeProfile.setColor(0.6f, 1f, 0.6f, 1f);

        backButton = new TextButton("Back", skin);
        backButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ProfileSelectionScreen(game));
            }

        });
        backButton.setColor(1f, 0.4f, 0.6f, 1f);

        menu.add(nameLabel);
        menu.add(name);
        menu.row().bottom();
        menu.add(genderLabel);
        menu.add(gender);
        menu.row();
        menu.add(headShapeLabel);
        menu.add(headShape);
        menu.row().height(16);
        menu.add(ageLabel);
        menu.add(age);
        menu.row();
        menu.add(newPlayer).colspan(2);
        //options
        //skintone
        menu.row();
        menu.add(skinColorSlider).colspan(2).padBottom(10);
        menu.row();
        menu.add(backButton);
        menu.add(makeProfile);

        table.add(menu);
        stage.addActor(table);
    }

    private void setColorFromSlider(float percent) {
        skinColor = darkColor.cpy().add(range[0] * percent, range[0] * percent, range[0] * percent, range[0] * percent);
    }
}
