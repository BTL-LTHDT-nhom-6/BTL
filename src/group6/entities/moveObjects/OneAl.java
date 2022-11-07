package group6.entities.moveObjects;

import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import group6.entities.moveObjects.Bomber;
import static group6.BombermanGame.*;
import java.util.Random;

public class OneAl extends Enemy {
    public OneAl(int xUnit, int yUnit, Image img, String direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        this.setSpeed(1);
    }

    @Override
    protected void runningAnimation() {
        animation++;

        if (direction.equals("up") || direction.equals("right")) {
            _sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, time);
            this.setImg(_sprite.getFxImage());
        }
        if (direction.equals("down") || direction.equals("left")) {
            _sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, time);
            this.setImg(_sprite.getFxImage());
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.isAlive())
            gc.drawImage(this.getImg(), x, y);
        else {
            if (timeDis > 0) gc.drawImage(Sprite.oneal_dead.getFxImage(), x, y);
        }
    }

    @Override
    public void update() {
        if (!this.isAlive()) {
            if (timeDis > 0) timeDis--;
            else {
                this.setRemoved(true);
                _point += 300;
            }
            return;
        }

        if (this.isAlive()) {
            if (!isMoving) {
                randomDirection();
                isMoving = true;
            }

            switch (direction) {
                case "up":
                    canMoveUp();
                    moveUp();
                    break;
                case "right":
                    canMoveRight();
                    moveRight();
                    break;
                case "left":
                    canMoveLeft();
                    moveLeft();
                    break;
                case "down":
                    canMoveDown();
                    moveDown();
                    break;
            }
        }
    }

    Random random = new Random();

    /**
     * Simple AI bomber moving.
     */
    public void randomDirection() {
        if (!bomberman.isAlive()) direction = normalMoving();
        else {
            int dimension = random.nextInt(2);
            if (dimension == 0) {
                String v = verticalMoving();
                if (v != null) direction = v;
                else direction = horizontalMoving();
            } else {
                String h = horizontalMoving();
                if (h != null) direction = h;
                else direction = verticalMoving();
            }
        }
    }

    /**
     * Detect bomber moving up or down.
     * @return vertical direction.
     */
    public String verticalMoving() {
        if (bomberman.getY() / 32 < this.getY() / 32) {
            return "up";
        } else if (bomberman.getY() / 32 > this.getY() / 32) {
            return "down";
        }
        return null;
    }

    /**
     * Detect bomber moving left or right.
     * @return horizontal direction.
     */
    public String horizontalMoving() {
        if (bomberman.getX() / 32 < this.getX() / 32) {
            return "left";
        } else if (bomberman.getX() / 32 > this.getX() / 32) {
            return "right";
        }
        return null;
    }

    public String normalMoving() {
        int randomDirection = random.nextInt(4);
        if (randomDirection == 0) {
            return "up";
        }
        if (randomDirection == 1) {
            return "down";
        }
        if (randomDirection == 2) {
            return "left";
        }
        if (randomDirection == 3) {
            return "right";
        }
        return null;
    }
}
