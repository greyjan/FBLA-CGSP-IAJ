/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.profiles;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.iaj.fbla2017.map.actors.character.player.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.iaj.fbla2017.map.actors.character.Character;

/**
 *
 * @author Jan Fic
 */
public class Profile implements Serializable {

    String name;
    String gender;
    int age;
    //Player player;
    //Player player;

    public Profile(String name, String gender, int age, Player c) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        //player = c;
    }

    public void save() {
        //profile save
        ObjectOutputStream out = null;
        try {
            FileHandle fl = ProfilesManager.saveDir.child(name + "/").child(name + ".ser");
            out = new ObjectOutputStream(fl.write(true));
            out.writeObject(this);
            out.close();
            System.out.println(fl.file().getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean saveCharacterSprites(Character c) {
        FileHandle root = ProfilesManager.saveDir.child(name + "/").child("animations/");
        boolean isSuccess = true;
        for (int i = 0; i < c.standingEastAnim.getKeyFrames().length; i++) {
            FileHandle fl = root.child("standing/").child("east/").child("frame" + i + ".png");
            PixmapIO.writePNG(fl, c.standingEastAnim.getKeyFrames()[i].getTexture().getTextureData().consumePixmap());
        }
        return isSuccess;
    }

    public static Profile load(String name) {
        Profile p = null;
        ObjectInputStream in = null;
        try {

            FileHandle fl = ProfilesManager.saveDir.child(name + "/").child(name + ".ser");
            in = new ObjectInputStream(fl.read());

            p = (Profile) in.readObject();
            in.close();
            //System.out.println(fl.file().getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found error");
        }

        return p;
    }

    @Override
    public String toString() {
        return "Load Profile \n" + name;
    }

}
