package group6.level;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import group6.Sound;

import static group6.BombermanGame.*;

public class LevelUp {
    private static ImageView statusGame;
    public static ImageView sound;
    public static Text level, time, point;
    public static int timeNum = 120;
    public static boolean nextLevel;
    public static long timeToExchange;


    public static void createIndex(Group root) {
        //TODO : set up level index
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.YELLOW);
        level.setX(384);
        level.setY(20);

        //TODO : set up countdown index
        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.YELLOW);
        time.setX(800);
        time.setY(20);

        //TODO : set up player's points index
        point = new Text("Point: 0");
        point.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        point.setFill(Color.YELLOW);
        point.setX(608);
        point.setY(20);

        //TODO : set up play/pause button
        Image pauseGame = new Image("/textures/newGame.png");
        statusGame = new ImageView(pauseGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        statusGame.setOnMouseClicked(event -> {
            if (check) {
                running = !running;
            } else {
                new Level1();
                running = true;
            }
            updateIndex();
        });

        //TODO : set up sound on/off button.
        sound = new ImageView(new Image("/sprites/soundon-02.png"));
        sound.setX(250);
        sound.setY(4);

        sound.setOnMouseClicked(event -> {
            soundOn = !soundOn;
            if (soundOn) sound.setImage(new Image("/sprites/soundon-02.png"));
            else sound.setImage(new Image("/sprites/soundoff-02.png"));
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(level, time, statusGame, point, sound);
        pane.setMinSize(960, 32);
        pane.setMaxSize(960, 480);
        pane.setStyle("-fx-background-color: #353535");
        root.getChildren().add(pane);
    }

    public static void updateIndex() {
        level.setText("Level: " + _level);
        point.setText("Point: " + _point);
        if (check) {
            if (running) {
                Image pauseGame = new Image("/textures/pauseGame.png");
                statusGame.setImage(pauseGame);

            } else {
                Image playGame = new Image("/textures/playGame.png");
                statusGame.setImage(playGame);
            }
        } else {
            Image newGame = new Image("/textures/newGame.png");
            statusGame.setImage(newGame);
        }
    }

    /**
     * Change level.
     */
    public static void nextLevel() {
        if (nextLevel) {
            gameView.setImage(new Image("/levelup.png"));
            long currentTime = System.currentTimeMillis();
            if (currentTime - timeToExchange > 2000) {
                Sound sound = new Sound(soundOn);
                switch (_level) {
                    case 1:
                        new Level2();
                        sound.soundNextLevel();
                        break;
                    case 2:
                        new Level1();
                        sound.soundNextLevel();
                }
                nextLevel = false;
            }
        }
    }
}
