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
    public static Sound soundMenu;


    public static void createIndex(Group root) {
        //TODO : set up level index
        soundMenu = new Sound(tfSound);
        soundMenu.sound(("/Sound/menuPlay.wav"));
        level = new Text("Level: 1");
        level.setX(384);
        level.setY(20);
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.YELLOW);

        //TODO : set up countdown index
        time = new Text("Times: 120");
        time.setX(800);
        time.setY(20);
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.YELLOW);

        //TODO : set up player's points index
        point = new Text("Point: 0");
        point.setX(608);
        point.setY(20);
        point.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        point.setFill(Color.YELLOW);


        //TODO : set up play/pause button
        Image pauseGame = new Image("/textures/newGame.png");
        statusGame = new ImageView(pauseGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        statusGame.setOnMouseClicked(event -> {
            if (check) {
                if (soundAll.isBoolSound()) {
                    soundAll.setBoolSound(false);
                    soundAll.stopM();
                } else {
                    soundAll.setBoolSound(true);
                    soundAll.startM();
                }
                running = !running;
            }
            updateIndex();
        });

        //TODO : set up sound on/off button.
        sound = new ImageView(new Image("/sprites/soundon-02.png"));
        sound.setX(250);
        sound.setY(4);

        sound.setOnMouseClicked(event -> {
            if (soundAll.isBoolSound()) {
                soundAll.setBoolSound(false);
                soundAll.stopM();
                sound.setImage(new Image("/sprites/soundoff-02.png"));
                return;
            }
            soundAll.setBoolSound(true);
            soundAll.startM();
            sound.setImage(new Image("/sprites/soundon-02.png"));
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
                soundAll.stopM();
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
                Sound sound = new Sound(tfSound);
                switch (_level) {
                    case 1:
                        sound.sound("/Sound/level_complete.wav");
                        new Level2();
                        break;
                    case 2:
                        sound.sound("/Sound/level_complete.wav");
                        new Level1();
                }
                nextLevel = false;
            }
        }
    }
}
