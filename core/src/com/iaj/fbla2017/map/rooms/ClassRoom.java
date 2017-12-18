/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.rooms;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.iaj.fbla2017.map.actors.character.player.Player;
import com.iaj.fbla2017.map.utils.Room;

/**
 *
 * @author n080873
 */
public class ClassRoom extends Room{

    Player player;
    
    public ClassRoom(Viewport v, Player p) {
        super(10,10,v);
        player = p;
        player.setIsoPosition(0, 0);
        player.setToPosition();
        this.addActor(player);
    }

    @Override
    protected void create() {
        this.setFloor("carpet");
    }
    
    
    
}
