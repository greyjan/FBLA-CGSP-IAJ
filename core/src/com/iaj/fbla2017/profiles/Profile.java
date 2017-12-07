/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.profiles;

import com.badlogic.gdx.files.FileHandle;
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

    String name;
    String gender;
    int age;
    //Player player;

    public Profile(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        
    }

    public void save() {
        ObjectOutputStream out = null;
        try {
            FileHandle fl = ProfilesManager.saveDir.child(name + ".ser");
            out = new ObjectOutputStream(fl.write(true));
            out.writeObject(this);
            out.close();
            System.out.println(fl.file().getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Profile load(String name) {
        Profile p = null;
        ObjectInputStream in = null;
        try {

            FileHandle fl = ProfilesManager.saveDir.child(name + ".ser");
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
        return "Load Profile \n" + name ;
    }

}
