package group6.entities.moveObjects;

import group6.entities.Entity;
import group6.entities.block.Bomb;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import group6.graphics.Sprite;
import java.util.Iterator;
import static group6.BombermanGame.*;

public class Bomber extends Entity {
    private static final int SPEED = 2;

    private boolean alive = true;
    private String direction;
    protected int animation = 0; // iterator for loading Animation
    public int loadKilled = 0;
    private int timeToRevive = 10;
    private int totalTime = 30; // time to load an effect
    public int timeToMove = 0; // time to move and load animations
    private int bomberSpeed = SPEED;

    public Bomber(int x, int y, String direction, Image img) {
        super(x, y, img);
        this.direction = direction;
    }

    public int getSpeed() {
        return bomberSpeed;
    }

    public void increaseSpeed() {
        bomberSpeed += 2;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setTimeToRevive(int timeToRevive) {
        this.timeToRevive = timeToRevive;
    }

    @Override
    public void update() {
        removeBomb();
        enemyCollision();
        if (isAlive()) {
            switch (direction) {
                case "up": moveUp();break;
                case "down":moveDown();break;
                case "right": moveRight();break;
                case "left": moveLeft();break;
            }
        } else {
            running = false;
            Image gameOver = new Image("/gameOver.png");
            gameView.setImage(gameOver);
            exit.setImage(new Image("/exitButton1.png"));
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (bomberman.isAlive()) {
            gc.drawImage(this.getImg(),x,y);
        } else {
            if (timeToRevive > 0) {
                deadAnimation(gc);
                timeToRevive--;
            }
            check = false;
        }

    }

    public  void putBomb() {
        int _x = bomberman.getX() / 32;
        int _y = bomberman.getY() / 32;
        Bomb newBomb = new Bomb(_x, _y, Sprite.bomb.getFxImage(), 1);
        bombs.add(newBomb);
        posBomb[_x][_y] = POS_BOMB;
    }

    public void removeBomb() {
        Iterator<Bomb> itr = bombs.iterator();
        Bomb b;
        while (itr.hasNext()) {
            b = itr.next();
            if (b.statusBomb == 0) {
                itr.remove();
            }
        }
    }

    public void enemyCollision() {
        if (bomberman.isAlive()) {
            int ax = bomberman.getX();
            int ay = bomberman.getY();
            for (Enemy enemy : entities) {
                int bx = enemy.getX();
                int by = enemy.getY();
                if (ax == bx && by - 32 < ay && by + 32 > ay
                        || ay == by && bx - 32 < ax && bx + 32 > ax) {
                    bomberman.setAlive(false);
                    break;
                }
            }
        }
    }

    public void deadAnimation(GraphicsContext gc) {
        loadKilled++;
        img = Sprite.movingSprite(Sprite.player_dead1,
                Sprite.player_dead2,
                Sprite.player_dead3,
                loadKilled, 60).getFxImage();
        super.render(gc);
    }


    public void canMoveRight() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32+1][this.getY() / 32] == 0
                    && posBomb[this.x / 32+1][this.y / 32] != POS_BOMB) {
                direction = "right";
                timeToMove = 32/getSpeed();
            }
        }
    }
    public void canMoveUp() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32][this.getY() / 32 - 1] == 0
                    && posBomb[this.x / 32][this.y / 32-1] != POS_BOMB) {
                direction = "up";
                timeToMove = 32/getSpeed();
            }
        }
    }
    public void canMoveDown() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32][this.getY() / 32 + 1] == 0
                    && posBomb[this.x / 32][this.y / 32+1] != POS_BOMB) {
                direction = "down";
                timeToMove = 32/getSpeed();
            }
        }

    }
    public void canMoveLeft() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32 - 1][this.getY() / 32] == 0
                    && posBomb[this.x / 32-1][this.y / 32] != POS_BOMB) {
                direction = "left";
                timeToMove = 32/getSpeed();
            }
        }
    }

    public void moveRight() {
            if (timeToMove > 0) {
                this.setX(this.getX() + getSpeed());
                movingAnimation();
                timeToMove--;
            }
    }

    public void moveLeft() {
            if (timeToMove > 0) {
                this.setX(this.getX() - getSpeed());
                movingAnimation();
                timeToMove--;
            }
    }

    public void moveDown() {
            if (timeToMove > 0) {
                this.setY(this.getY() + getSpeed());
                movingAnimation();
                timeToMove--;
            }
    }


    public void moveUp() {
            if (timeToMove > 0) {
                this.setY(this.getY() - getSpeed());
                movingAnimation();
                timeToMove--;
            }
    }

    public void movingAnimation() {
        animation++;
        
        switch (direction) {
            case "up":
                _sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                            animation, totalTime);
                this.setImg(_sprite.getFxImage());
                break;
            case "down":
                _sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2,
                            animation, totalTime);

                this.setImg(_sprite.getFxImage());
                break;
            case "left":
                _sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                            animation, totalTime);
                this.setImg(_sprite.getFxImage());
                break;
            case "right":
                _sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                            animation, totalTime);
                this.setImg(_sprite.getFxImage());
                break;
        }

    }
}
