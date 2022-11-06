package group6.level;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import group6.level.*;

import static group6.BombermanGame.*;

public class LevelUp {
    private static ImageView statusGame;
    public static Text level, time;
    public static int timeNumber = 120;
    public static boolean wait;
    public static long waitingTime;

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);

        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(608);
        time.setY(20);

        Image pauseGame = new Image("/textures/pauseGame.png");
        statusGame = new ImageView(pauseGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);


        Pane pane = new Pane();
        pane.getChildren().addAll(level, time, statusGame);
        pane.setMinSize(960, 32);
        pane.setMaxSize(960, 480);
        pane.setStyle("-fx-background-color: #353535");
        root.getChildren().add(pane);

        statusGame.setOnMouseClicked(event -> {
            if (bomberman.isAlive()) {
                running = !running;
            }
            updateMenu();
        });

    }

    public static void updateMenu() {
        level.setText("Level: " + _level);
        if (bomberman.isAlive()) {
            if (running) {
                Image pauseGame = new Image("/textures/pauseGame.png");
                statusGame.setImage(pauseGame);
            } else {
                Image playGame = new Image("/textures/playGame.png");
                statusGame.setImage(playGame);
            }
        }
    }

    public static void waitToLevelUp() {
        if (wait) {
            long now = System.currentTimeMillis();
            if (now - waitingTime > 3000) {
                switch (_level) {
                    case 1:
                        new Level2();
                        break;
                    case 2:
                        new Level1();
                }
                wait = false;
            }
        }
    }
}
