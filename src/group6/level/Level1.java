package group6.level;

import group6.Sound;
import group6.entities.moveObjects.*;
import group6.graphics.Map;
import group6.graphics.Sprite;
import javafx.scene.image.Image;

import static group6.BombermanGame.*;
import static group6.level.LevelUp.*;

public class Level1 {
    public Level1() {
        entities.clear();
        check = true;
        timeNum = 60;

        if (soundMenu != null) soundMenu.closeM();
        if (soundAll != null) soundAll.closeM();
        soundAll = new Sound(tfSound);
        soundAll.sound("/Sound/title_screen.wav");

        Image transparent = new Image("/transparent.png");
        gameView.setImage(transparent);
        start.setImage(transparent);
        exit.setImage(transparent);

        Map.createMap("res/levels/Level1.txt");

        bomberman.setImg(Sprite.player_right.getFxImage());
        bomberman.setTotalTime(30);
        bomberman.setTimeToRevive(10);
        bomberman.setX(32);
        bomberman.setY(32);
        bomberman.setAlive(true);

        //entities.add(new Balloom(8, 11, Sprite.balloom_left1.getFxImage(), "down"));
        //entities.add(new Balloom(20, 11, Sprite.balloom_left1.getFxImage(), "down"));
        entities.add(new OneAl(5, 5, Sprite.oneal_left1.getFxImage(), "down"));
    }
}
