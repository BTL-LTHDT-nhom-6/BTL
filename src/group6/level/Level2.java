package group6.level;

import group6.Sound;
import group6.entities.moveObjects.Doll;
import group6.entities.moveObjects.OneAl;
import group6.entities.moveObjects.Balloom;
import group6.graphics.Map;
import group6.graphics.Sprite;
import javafx.scene.image.Image;

import static group6.level.LevelUp.*;
import static group6.BombermanGame.*;

public class Level2 {
    public Level2() {
        entities.clear();
        check = true;
        timeNum = 90;

        if (soundAll != null) soundAll.closeM();
        soundAll = new Sound(tfSound);
        soundAll.sound("/Sound/soundGame.wav");

        Image transparent = new Image("/transparent.png");
        gameView.setImage(transparent);

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
