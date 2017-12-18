/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.map.actors.character.player;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.iaj.fbla2017.map.actors.character.Character;
import static com.iaj.fbla2017.map.actors.character.Character.Direction.EAST;
import static com.iaj.fbla2017.map.actors.character.Character.Direction.NORTH;
import static com.iaj.fbla2017.map.actors.character.Character.Direction.WEST;

/**
 *
 * @author Jan Fic
 */
public class Player extends Character {

    Runnable walkForward = new Runnable() {

        @Override
        public void run() {

        }

    };

    public Player(int value) {
        super(value);
    }

    public Player(int l, String _gender, String _headShape) {
        super(l, _gender, _headShape);
        this.setIsoPosition(0, 0);
    }

    public void walk(final Direction d) {
        if (this.getActions().size == 0) {
            SequenceAction walkAction = sequence(
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            Player.this.setDirection(d);
                        }
                    }),
                    Actions.delay(0.05f),
                    Actions.moveBy(d == NORTH || d == EAST ? 16 : -16, d == NORTH || d == WEST ? 8 : -8, 0.25f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            if (null != Player.this.getDirection()) {
                                switch (Player.this.getDirection()) {
                                    case NORTH:
                                        Player.this.moveIsoBy(0, -1);
                                        break;
                                    case SOUTH:
                                        Player.this.moveIsoBy(0, 1);
                                        break;
                                    case WEST:
                                        Player.this.moveIsoBy(-1, 0);
                                        break;
                                    case EAST:
                                        Player.this.moveIsoBy(1, 0);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }));
            this.addAction(walkAction);
        }
    }

}
