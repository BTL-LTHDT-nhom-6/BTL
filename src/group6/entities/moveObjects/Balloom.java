package group6.entities.moveObjects;

import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;
import static group6.BombermanGame.*;



public class Balloom extends Character {
    private int speed = 1;
    public int actionLockCounter = 0;
    public boolean isMoving = true;
    public int timeDis = 20;
    public boolean removed =  false;


    public Balloom(int xUnit, int yUnit, Image img, String direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
    }

    public boolean canMoveRight() {
        if (this.x % 32 == 0) {
            return pos[this.x / 32 + 1][this.y / 32] == 0;
        }
        if (this.y % 32 == 0) {
            return true;
        }
        // 8 used to fixed space between bomber and obstacles when cannot move right.
        return false;
    }
    public boolean canMoveUp() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            return pos[this.x / 32][this.y / 32 - 1] == 0;
        }
        if (this.x % 32 == 0) {
            return true;
        }
        return false;
    }
    public boolean canMoveDown() {
        return pos[this.x / 32][this.y / 32 + 1] == 0;

    }
    public boolean canMoveLeft() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            return pos[this.x / 32 - 1][this.y / 32] == 0;
        }
        if (this.y % 32 == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void moveUp() {
        direction = "up";
        for (int i = 0; i < speed; ++i) {
            if (canMoveUp() == false) {
                isMoving = false;
                break;
            }
            this.y --;
        }
        keepMove += speed;
        if (keepMove > 60) keepMove = 0;
        loadAnimation();
    }

    @Override
    public void moveDown() {

        direction = "down";
        for (int i = 0; i < speed; ++i) {
            if (canMoveDown() == false) {
                isMoving = false;
                break;
            }
            this.y ++;
        }
        keepMove+= speed;
        if (keepMove > 60) keepMove = 0;
        loadAnimation();
    }

    @Override
    public void moveLeft() {

        direction = "left";
        for (int i = 0; i < speed; ++i) {
            if (canMoveLeft() == false) {
                isMoving = false;
                break;
            }
            this.x--;
        }
        keepMove += speed;
        if (keepMove > 60) keepMove = 0;
        loadAnimation();
    }

    @Override
    public void moveRight() {

        direction = "right";
        for (int i = 0; i < speed; ++i) {
            if (canMoveRight() == false) {
                isMoving = false;
                break;
            }
            this.x++;
        }
        keepMove += speed;
        if (keepMove > 60) keepMove = 0;
        loadAnimation();
    }

    @Override
    protected void loadAnimation() {
        if (direction.equals("up") || direction.equals("right")) {
            _sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, keepMove, time);
            this.setImg(_sprite.getFxImage());
        }
        if (direction.equals("down") || direction.equals("left")) {
            _sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, keepMove, time);
            this.setImg(_sprite.getFxImage());
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.alive)
            gc.drawImage(this.getImg(), x, y);
        else {
            if (timeDis > 0) gc.drawImage(Sprite.balloom_dead.getFxImage(), x, y);
        }
    }

    @Override
    public void update() {
        if (!this.alive) {
            if (timeDis > 0) timeDis--;
            else this.removed = true;
            return;
        }
        if (this.alive) {
            if (!isMoving) {
                Random random = new Random();
                int randomDirection = random.nextInt(4);
                if (randomDirection == 0) {
                    direction = "up";
                }
                if (randomDirection == 1) {
                    direction = "down";
                }
                if (randomDirection == 2) {
                    direction = "left";
                }
                if (randomDirection == 3) {
                    direction = "right";
                }
                isMoving = true;
            }
            switch (direction) {
                case "up":
                    moveUp();
                    break;
                case "right":
                    moveRight();
                    break;
                case "left":
                    moveLeft();
                    break;
                case "down":
                    moveDown();
                    break;
            }
        }
    }

    @Override
    public void putBomb() {

    }
}
