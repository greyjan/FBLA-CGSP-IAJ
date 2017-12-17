/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.profiles;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.iaj.fbla2017.map.actors.character.player.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Fic
 */
public class Profile implements Serializable {

    //general
    String name;
    int age;

    //character
    String gender;
    String headShape;
    String skinColor;
    transient Player player;
    

    public Profile(String name, String gender, String headShape, int age, Player c) {
        this.name = name;
        this.gender = gender;
        this.headShape = headShape;
        this.age = age;
        player = c;
        skinColor = c.getColor().toString();
    }

    public String getHeadShape() {
        return headShape;
    }

    public String getGender() {
        return gender;
    }

    public void save() {
        //profile save
        ObjectOutputStream out = null;
        try {
            FileHandle fl = ProfilesManager.saveDir.child(name + "/").child(name + ".ser");
            out = new ObjectOutputStream(fl.write(true));
            out.writeObject(this);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }

        //save player stuff
        FileHandle root = ProfilesManager.saveDir.child(name + "/").child("animations/");
        player.saveSprites(root);
    }

    public static Profile load(String name) {
        Profile p = null;
        ObjectInputStream in = null;
        try {

            FileHandle fl = ProfilesManager.saveDir.child(name + "/").child(name + ".ser");
            in = new ObjectInputStream(fl.read());

            p = (Profile) in.readObject();
            in.close();
        } catch (IOException ex) {
            System.err.println("Couldnt find save profile: " + name);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found error");
        }
        p.player = new Player(0, p.getGender(), p.getHeadShape());
        p.player.setColor(Color.valueOf(p.getSkinColorValue()));
        return p;
    }

    @Override
    public String toString() {
        return "Load Profile \n" + name;
    }

    

    public String getSkinColorValue() {
        return skinColor;
    }
    
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Player getPlayer() {
        return player;
    }

}
