package group6.level;

import group6.entities.moveObjects.Doll;
import group6.entities.moveObjects.OneAl;
import group6.entities.moveObjects.Balloom;
import group6.graphics.Map;
import group6.graphics.Sprite;

import static group6.BombermanGame.bomberman;
import static group6.BombermanGame.entities;

public class Level2 {
    public Level2() {
        Map.createMap("res/levels/Level2.txt");
        bomberman.setTotalTime(30);
        bomberman.setTimeToRevive(10);
        bomberman.setX(32);
        bomberman.setY(32);
        bomberman.setAlive(true);
        entities.add(new Balloom(26, 3, Sprite.balloom_left1.getFxImage(), "down"));
        entities.add(new Balloom(9, 9, Sprite.balloom_left1.getFxImage(), "up"));
        entities.add(new Doll(5, 5, Sprite.doll_left1.getFxImage(), "down"));
        entities.add(new Doll(26, 3, Sprite.doll_left1.getFxImage(), "left"));
        entities.add(new OneAl(5, 5, Sprite.oneal_left1.getFxImage(), "down"));
        entities.add(new OneAl(25, 11, Sprite.oneal_left1.getFxImage(), "right"));
    }

}
