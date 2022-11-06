package group6;

import group6.entities.block.Bomb;
import group6.entities.block.Portal;
import group6.entities.moveObjects.Bomber;
import group6.entities.moveObjects.Enemy;
import group6.level.Level1;
import group6.level.LevelUp;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import group6.entities.Entity;
import group6.graphics.Sprite;
import group6.graphics.Map;
import static group6.level.LevelUp.*;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 15;
    public static final int POS_BOMB = 4;
    public static final int POS_FLAME = 5;
    public static int _level = 1;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Enemy> entities = new ArrayList<>();
    public static Bomber bomberman;
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static int[][] pos;
    public static int[][] posBomb;
    public static boolean running = true;

    private long lastTime;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        LevelUp.createMenu(root);
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Add scene vao stage
        stage.setScene(scene);
        stage.show();

        bomberman = new Bomber(1,1,"right", Sprite.player_right.getFxImage());
        new Level1();


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
                }
            }
        });

        lastTime = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                    time();
                    updateMenu();
                }
            }
        };
        timer.start();



    }



    public void update() {
        stillObjects.forEach(Entity::update);
        bomberman.update();
        bombs.forEach(Bomb::update);
        entities.forEach(Entity::update);

        for (int i = 0; i < entities.size(); ++i) {
            if (entities.get(i).isRemoved()) entities.remove(i);
        }

        if (entities.isEmpty() && Portal.intoPortal() && !wait) {
            wait = true;
            waitingTime = System.currentTimeMillis();
        }
        waitToLevelUp();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        bomberman.render(gc);
        if (!bombs.isEmpty()) bombs.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));

    }

    public void time() {
        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = System.currentTimeMillis();

            time.setText("Time: " + timeNumber);
            timeNumber--;
            if (timeNumber < 0)
                bomberman.setAlive(false);
        }
    }
}
