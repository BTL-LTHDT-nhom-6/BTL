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
    private int loadKilled = 0;
    public int timeToRevive = 10;
    public int totalTime = 30; // time to load an effect
    public int timetoMove = 0; // time to move and load animation

    public Bomber(int x, int y, String direction, Image img) {
        super(x, y, img);
        this.direction = direction;
    }

    public int getSpeed() {
        return SPEED;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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
                if (ax == bx && by - 32 <= ay && by + 32 >= ay
                        || ay == by && bx - 32 <= ax && bx + 32 >= ax) {
                    bomberman.alive = false;
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
        gc.drawImage(img, this.x, this.y);
    }


    public void canMoveRight() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32+1][this.getY() / 32] == 0
                    && posBomb[this.x / 32+1][this.y / 32] != POS_BOMB) {
                direction = "right";
                timetoMove = 32/getSpeed();
            }
        }
    }
    public void canMoveUp() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32][this.getY() / 32 - 1] == 0
                    && posBomb[this.x / 32][this.y / 32-1] != POS_BOMB) {
                direction = "up";
                timetoMove = 32/getSpeed();
            }
        }
    }
    public void canMoveDown() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32][this.getY() / 32 + 1] == 0
                    && posBomb[this.x / 32][this.y / 32+1] != POS_BOMB) {
                direction = "down";
                timetoMove = 32/getSpeed();
            }
        }

    }
    public void canMoveLeft() {
        if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
            if (pos[this.getX() / 32 - 1][this.getY() / 32] == 0
                    && posBomb[this.x / 32-1][this.y / 32] != POS_BOMB) {
                direction = "left";
                timetoMove = 32/getSpeed();
            }
        }
    }

    public void moveRight() {
            if (timetoMove > 0) {
                this.setX(this.getX() + getSpeed());
                movingAnimation();
                timetoMove--;
            }
    }

    public void moveLeft() {
            if (timetoMove > 0) {
                this.setX(this.getX() - getSpeed());
                movingAnimation();
                timetoMove--;
            }
    }

    public void moveDown() {
            if (timetoMove > 0) {
                this.setY(this.getY() + getSpeed());
                movingAnimation();
                timetoMove--;
            }
    }


    public void moveUp() {
            if (timetoMove > 0) {
                this.setY(this.getY() - getSpeed());
                movingAnimation();
                timetoMove--;
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
