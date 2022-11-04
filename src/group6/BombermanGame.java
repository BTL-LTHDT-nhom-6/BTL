package group6;

import group6.entities.block.Bomb;
import group6.entities.moveObjects.Bomber;
import group6.entities.moveObjects.Character;
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
import group6.entities.moveObjects.Balloom;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static final int POS_BOMB = 4;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Character> entities = new ArrayList<>();
    public static Bomber bomberman;
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static int[][] pos;
    public static int[][] posBomb;
    public static boolean running = true;
    public static boolean goUp, goDown, goLeft, goRight;



    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Add scene vao stage
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
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
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                }
            }
        };
        timer.start();
        Map.createMap("res/levels/Level1.txt");
        bomberman = new Bomber(1,1,"right", Sprite.player_right.getFxImage());
        entities.add(new Balloom(3, 13, Sprite.balloom_left1.getFxImage(), "down"));
        entities.add(new Balloom(9, 9, Sprite.balloom_left1.getFxImage(), "down"));
    }



    public void update() {
        stillObjects.forEach(Entity::update);
        bomberman.update();
        entities.forEach(Entity::update);
        bombs.forEach(Bomb::update);

        for (int i = 0; i < entities.size(); ++i) {
            if (entities.get(i).removed) entities.remove(i);
        }

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        bomberman.render(gc);
        entities.forEach(g -> g.render(gc));
        if (!bombs.isEmpty()) bombs.forEach(g -> g.render(gc));
    }
}
