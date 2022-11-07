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
    public int timeToMove = 0;
    protected int timeDis = 20; // time for disappear effects

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

    /**
     * Detect collision in every dimension and set time to move.
     */
    public void canMoveRight() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32+1][this.y / 32] == 0
                    && posBomb[this.x / 32+1][this.y / 32] != POS_BOMB) {
                direction = "right";
                timeToMove = 32/getSpeed();
            } else isMoving = false;
        }
    }
    public void canMoveUp() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32][this.y / 32 - 1] == 0
                    && posBomb[this.x / 32][this.y / 32 - 1] != POS_BOMB) {
                direction = "up";
                timeToMove = 32/getSpeed();
            } else isMoving = false;
        }
    }
    public void canMoveDown() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32][this.y / 32 + 1] == 0
                    && posBomb[this.x / 32][this.y / 32 + 1] != POS_BOMB) {
                direction = "down";
                timeToMove = 32/getSpeed();
            } else isMoving = false;
        }

    }
    public void canMoveLeft() {
        if (this.x % 32  == 0 && this.y % 32 == 0) {
            if (pos[this.x / 32-1][this.y / 32] == 0
                    && posBomb[this.x / 32 - 1][this.y / 32] != POS_BOMB) {
                direction = "left";
                timeToMove = 32/getSpeed();
            } else isMoving = false;
        }
    }

    /**
     * Implement enemies' moving.
     */
    public void moveRight() {
        if (timeToMove > 0) {
            this.x += speed;
            runningAnimation();
            timeToMove--;
        }
    }


    public void moveLeft() {
        if (timeToMove > 0) {
            this.x -= speed;
            runningAnimation();
            timeToMove--;
        }
    }


    public void moveDown() {
        if (timeToMove > 0) {
            this.y += speed;
            runningAnimation();
            timeToMove--;
        }
    }


    public void moveUp() {
        if (timeToMove > 0) {
            this.y -= speed;
            runningAnimation();
            timeToMove--;
        }
    }

    protected abstract void runningAnimation();

    @Override
    public void update() {

    }
}
