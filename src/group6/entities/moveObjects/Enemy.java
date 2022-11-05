package group6.entities.moveObjects;

import group6.entities.Entity;
import javafx.scene.image.Image;

import static group6.BombermanGame.*;

public abstract class Enemy extends Entity {
    protected boolean alive = true;
    protected String direction;
    protected int animation = 0;
    protected int time = 30;
    protected boolean removed =  false;
    protected int speed;
    protected boolean isMoving = true;
    public int timetoMove = 0;
    protected int timeDis = 20;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void canMoveRight() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32+1][this.y / 32] == 0
                    && posBomb[this.x / 32+1][this.y / 32] != POS_BOMB) {
                direction = "right";
                timetoMove = 32/getSpeed();
            } else isMoving = false;
        }
    }
    public void canMoveUp() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32][this.y / 32 - 1] == 0
                    && posBomb[this.x / 32][this.y / 32 - 1] != POS_BOMB) {
                direction = "up";
                timetoMove = 32/getSpeed();
            } else isMoving = false;
        }
    }
    public void canMoveDown() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32][this.y / 32 + 1] == 0
                    && posBomb[this.x / 32][this.y / 32 + 1] != POS_BOMB) {
                direction = "down";
                timetoMove = 32/getSpeed();
            } else isMoving = false;
        }

    }
    public void canMoveLeft() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32-1][this.y / 32] == 0
                    && posBomb[this.x / 32 - 1][this.y / 32] != POS_BOMB) {
                direction = "left";
                timetoMove = 32/getSpeed();
            } else isMoving = false;
        }
    }


    public void moveRight() {
        if (timetoMove > 0) {
            this.x += speed;
            runningAnimation();
            timetoMove--;
        }
    }


    public void moveLeft() {
        if (timetoMove > 0) {
            this.x -= speed;
            runningAnimation();
            timetoMove--;
        }
    }


    public void moveDown() {
        if (timetoMove > 0) {
            this.y += speed;
            runningAnimation();
            timetoMove--;
        }
    }


    public void moveUp() {
        if (timetoMove > 0) {
            this.y -= speed;
            runningAnimation();
            timetoMove--;
        }
    }

    protected abstract void runningAnimation();

    @Override
    public void update() {

    }
}
