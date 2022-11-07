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
    public static Text level, time, point;
    public static int timeNum = 120;
    public static boolean nextLevel;
    public static long timeToExchange;

    public static void createIndex(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.YELLOW);
        level.setX(384);
        level.setY(20);

        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.YELLOW);
        time.setX(800);
        time.setY(20);

        point = new Text("Point: 0");
        point.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        point.setFill(Color.YELLOW);
        point.setX(608);
        point.setY(20);

        Image pauseGame = new Image("/textures/newGame.png");
        statusGame = new ImageView(pauseGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);


        Pane pane = new Pane();
        pane.getChildren().addAll(level, time, statusGame, point);
        pane.setMinSize(960, 32);
        pane.setMaxSize(960, 480);
        pane.setStyle("-fx-background-color: #353535");
        root.getChildren().add(pane);

        statusGame.setOnMouseClicked(event -> {
            if (check) {
                running = !running;
            } else {
                new Level1();
                running = true;
            }
            updateIndex();
        });

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

    public static void nextLevel() {
        if (nextLevel) {
            long now = System.currentTimeMillis();
            if (now - timeToExchange > 3000) {
                switch (_level) {
                    case 1:
                        new Level2();
                        break;
                    case 2:
                        new Level1();
                }
                nextLevel = false;
            }
        }
    }
}
