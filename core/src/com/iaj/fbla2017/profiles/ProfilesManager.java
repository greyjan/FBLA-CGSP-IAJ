/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.profiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Fic
 */
public class ProfilesManager {
    
    private static ProfilesManager INSTANCE = null;
    public final static FileHandle saveDir = Gdx.files.local("FBLA2017SHS/saves/");
    private FileHandle saves;
    public ArrayList<Profile> profiles;
    public Profile currentProfile;
    private ProfilesManager() {
        saves = saveDir.child("saves.txt");
        System.out.println(saves.file().getAbsolutePath());
        profiles = new ArrayList<Profile>();
        if(!saves.exists()) {
            saves.writeString("", false);
        }
        Scanner scan = null;
        try {
            scan = new Scanner(saves.file());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfilesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(scan.hasNext()){
            profiles.add(Profile.load(scan.nextLine()));
        }
    }
    
    public static ProfilesManager getProfiles() {
        if(INSTANCE == null) {
            INSTANCE = new ProfilesManager();
        }
        return INSTANCE;
    }
    
    public void saveAll() {
        
        saves.writeString("", false);
        for(Profile p : profiles) {
            p.save();
            saves.writeString(p.name + "\n", true);
        }      
    }
    
    
}
