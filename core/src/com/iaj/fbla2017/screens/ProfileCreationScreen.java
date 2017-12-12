package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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

/**
 *
 * @author Jan Fic
 */
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

    public ProfileCreationScreen(Game g) {
        game = g;
        stage = new Stage(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2));

        skin = (Skin) Assets.GetInstance().get("skin/composed/skin.json");

        profileManager = ProfilesManager.getProfiles();

        newPlayer = new Player(1);

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

        makeProfile = new TextButton("Make Profile", skin);
        makeProfile.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                profileManager.profiles.add(new Profile(name.getText(), gender.getSelected(), Integer.parseInt(age.getText()),newPlayer));
                profileManager.profiles.get(profileManager.profiles.size()-1).saveCharacterSprites(newPlayer);
                profileManager.saveAll();
                game.setScreen(new ProfileSelectionScreen(game));
            }

        });

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
        
        menu.row().height(16);
        menu.add(makeProfile);

        table.add(menu);
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
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
