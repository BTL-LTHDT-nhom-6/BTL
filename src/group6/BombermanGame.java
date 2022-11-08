package group6;

import group6.entities.block.Bomb;
import group6.entities.block.Portal;
import group6.entities.moveObjects.Bomber;
import group6.entities.moveObjects.Enemy;
import group6.level.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import group6.entities.Entity;
import group6.graphics.Sprite;
import static group6.level.LevelUp.*;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 15;
    public static final int POS_BOMB = 4;
    public static final int POS_FLAME = 5;
    public static int _level = 1;
    public static int _point = 0;
    public static int _width = 0;
    public static int _height = 0;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Enemy> entities = new ArrayList<>();
    public static Bomber bomberman;
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static int[][] pos;
    public static int[][] posBomb;
    public static boolean running = true;
    private long previousTime;
    public static ImageView gameView;
    public static boolean check = false;
    public static ImageView start;
    public static ImageView exit;
    public static boolean tfSound = true;
    public static Sound soundAll;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas, background and start/exit button
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        Image author = new Image("/menu.png");
        gameView = new ImageView(author);
        gameView.setX(0);
        gameView.setY(0);
        start = new ImageView(new Image("/play-02.png"));
        start.setX(224);
        start.setY(250);
        exit = new ImageView(new Image("/exit-02.png"));
        exit.setX(576);
        exit.setY(250);


        // Tao root container
        Group root = new Group();
        LevelUp.createIndex(root);
        root.getChildren().addAll(canvas, gameView, start, exit);
        // Tao scene
        Scene scene = new Scene(root);

        // Add scene vao stage
        stage.setScene(scene);
        stage.show();

        exit.setOnMouseClicked(e -> System.exit(0));

        scene.setOnKeyPressed(event -> {
            if (bomberman.isAlive()) {
                switch (event.getCode()) {
                    case UP:
                        bomberman.canMoveUp();
                        break;
                    case DOWN:
                        bomberman.canMoveDown();
                        break;
                    case LEFT:
                        bomberman.canMoveLeft();
                        break;
                    case RIGHT:
                        bomberman.canMoveRight();
                        break;
                    case SPACE:
                        bomberman.putBomb();
                        break;
                    case A:
                        entities.clear();
                        break;
                }
            }
        });

        previousTime = System.currentTimeMillis();



        start.setOnMouseClicked(event -> {
            _point = 0;
            new Level1();
            running = true;
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                    countDown();
                    updateIndex();
                }
            }
        };
        timer.start();
        bomberman = new Bomber(1,1,"right", Sprite.player_right.getFxImage());
    }


    
    public void update() {
        stillObjects.forEach(Entity::update);
        bomberman.update();
        bombs.forEach(Bomb::update);
        entities.forEach(Entity::update);

        for (int i = 0; i < entities.size(); ++i) {
            if (entities.get(i).isRemoved()) entities.remove(i);
        }

        if (entities.isEmpty() && Portal.intoPortal() && !nextLevel) {
            nextLevel = true;
            timeToExchange = System.currentTimeMillis();
        }
        nextLevel();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        bomberman.render(gc);
        if (!bombs.isEmpty()) bombs.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));

    }

    /**
     * Set up maximum time for a level.
     */
    public void countDown() {
        long now = System.currentTimeMillis();
        if (now - previousTime > 1000) {
            previousTime = System.currentTimeMillis();
            time.setText("Time: " + timeNum);
            timeNum--;
            if (timeNum < 0)
                bomberman.setAlive(false);
        }
    }
}
