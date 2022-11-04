package group6.entities.moveObjects;

import group6.entities.block.Bomb;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import group6.graphics.Sprite;
import java.util.Iterator;
import static group6.BombermanGame.*;

public class Bomber extends Character {

    private int speed = 2;
    private int loadKilled = 0;
    private int timeToRevive = 10;
    public int action = 30;
    public int run = 0;

    public Bomber(int x, int y, String direction, Image img) {
        super(x, y, img);
        this.direction = direction;
    }

    public  void putBomb() {
        int _x = bomberman.getX() / 32;
        int _y = bomberman.getY() / 32;
        _x = Math.round(_x);
        _y = Math.round(_y);
        Bomb newBomb = new Bomb(_x, _y, Sprite.bomb.getFxImage(), 1);
        bombs.add(newBomb);
        posBomb[_x][_y] = POS_BOMB;
    }

    public void removeBomb() {
        Iterator<Bomb> itr = bombs.iterator();
        Bomb b;
        while (itr.hasNext()) {
            b = itr.next();
            if (b.isBomb == 0) {
                itr.remove();
            }
        }
    }

    public void collisionEnemy() {
        if (bomberman.alive = true) {
            int ax = bomberman.getX();
            int ay = bomberman.getY();
            for (Character enemy : entities) {
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

    public void killedAnimation(GraphicsContext gc) {
        loadKilled++;
        img = Sprite.movingSprite(Sprite.player_dead1,
                Sprite.player_dead2,
                Sprite.player_dead3,
                loadKilled, 60).getFxImage();
        gc.drawImage(img, this.x, this.y);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (bomberman.alive) {
            gc.drawImage(this.getImg(),x,y);
        } else {
            if (timeToRevive > 0) {
                killedAnimation(gc);
                timeToRevive--;
            }
        }
    }

    @Override
    public void update() {
        removeBomb();
        collisionEnemy();
        if (this.alive) {
            switch (direction) {
                case "up": moveUp();break;
                case "down":moveDown();break;
                case "right": moveRight();break;
                case "left": moveLeft();break;
            }
        }
    }

    public void canMoveRight() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32+1][this.y / 32] == 0) {
                direction = "right";
                run = 32/speed;
            }
        }
    }
    public void canMoveUp() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32][this.y / 32 - 1] == 0) {
                direction = "up";
                run = 32/speed;
            }
        }
    }
    public void canMoveDown() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32][this.y / 32 + 1] == 0) {
                direction = "down";
                run = 32/speed;
            }
        }

    }
    public void canMoveLeft() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32-1][this.y / 32] == 0) {
                direction = "left";
                run = 32/speed;
            }
        }
    }

    @Override
    public void moveRight() {
            if (run > 0) {
                this.x += speed;
                loadAnimation();
                run--;
            }


    }

    @Override
    public void moveLeft() {
            if (run > 0) {
                this.x -= speed;
                loadAnimation();
                run--;
            }

    }

    @Override
    public void moveDown() {
            if (run > 0) {
                this.y += speed;
                loadAnimation();
                run--;
            }
    }

    @Override
    public void moveUp() {
            if (run > 0) {
                this.y -= speed;
                loadAnimation();
                run--;
            }
    }

    public void loadAnimation() {
        keepMove++;
        switch (direction) {
            case "up":
                _sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                            keepMove, action);
                this.setImg(_sprite.getFxImage());
                break;
            case "down":
                _sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2,
                            keepMove, action);

                this.setImg(_sprite.getFxImage());
                break;
            case "left":
                _sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                            keepMove, action);
                this.setImg(_sprite.getFxImage());
                break;
            case "right":
                _sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                            keepMove, action);
                this.setImg(_sprite.getFxImage());
                break;
        }

    }
}
