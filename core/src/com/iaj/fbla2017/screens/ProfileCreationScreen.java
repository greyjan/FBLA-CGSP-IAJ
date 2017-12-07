package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.UISkin.NotebookPaperTable;
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

    //profile components
    TextField name;
    TextField gender;
    TextField age;
    TextButton makeProfile;

    public ProfileCreationScreen(Game g) {
        game = g;
        stage = new Stage(new ExtendViewport(SandboxGame.WIDTH / 2, SandboxGame.HEIGHT / 2));

        skin = (Skin) Assets.GetInstance().get("skin/composed/skin.json");

        profileManager = ProfilesManager.getProfiles();

        table = new Table();
        table.setFillParent(true);

        menu = new NotebookPaperTable("New Student", "title");
        menu.row().height(16);

        Label nameLabel = new Label("Name: ", skin);
        name = new TextField("", skin);
        
        Label genderLabel = new Label("Gender: ", skin);
        gender = new TextField("", skin);

        Label ageLabel = new Label("Age: ", skin);
        age = new TextField("", skin);
        age.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());

        makeProfile = new TextButton("Make Profile",skin);
        makeProfile.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               
                profileManager.profiles.add(new Profile(name.getText(), gender.getText(), Integer.parseInt(age.getText())));
                profileManager.saveAll();
                game.setScreen(new ProfileSelectionScreen(game));
            }
            
            
        });
        
        menu.add(nameLabel);
        menu.add(name);
        menu.row().height(16).bottom();
        menu.add(genderLabel);
        menu.add(gender);
        menu.row().height(16);
        menu.add(ageLabel);
        menu.add(age);
       
        menu.row().height(16);
         menu.add(makeProfile);
//        TextField testTF = new TextField("testing test field", skin);
//        menu.add(testTF);

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
