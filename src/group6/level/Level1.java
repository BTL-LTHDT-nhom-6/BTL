package group6.level;

import group6.entities.moveObjects.*;
import group6.graphics.Map;
import group6.graphics.Sprite;

import static group6.BombermanGame.*;

public class Level1 {
    public Level1() {
        Map.createMap("res/levels/Level1.txt");
        bomberman.setTotalTime(30);
        bomberman.setTimeToRevive(10);
        bomberman.setX(32);
        bomberman.setY(32);
        bomberman.setAlive(true);
        //entities.add(new Balloom(3, 13, Sprite.balloom_left1.getFxImage(), "down"));
        //entities.add(new Balloom(9, 9, Sprite.balloom_left1.getFxImage(), "down"));
        //entities.add(new Doll(5, 5, Sprite.doll_left1.getFxImage(), "down"));
        entities.add(new OneAl(5, 5, Sprite.oneal_left1.getFxImage(), "down"));
    }
}
