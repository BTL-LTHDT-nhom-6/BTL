package group6.entities.moveObjects;

import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, Image img, String direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        this.setSpeed(2);
    }

    @Override
    protected void runningAnimation() {
        animation++
        ;
        if (direction.equals("up") || direction.equals("right")) {
            _sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animation, time);
            this.setImg(_sprite.getFxImage());
        }
        if (direction.equals("down") || direction.equals("left")) {
            _sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animation, time);
            this.setImg(_sprite.getFxImage());
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.isAlive())
            gc.drawImage(this.getImg(), x, y);
        else {
            if (timeDis > 0) gc.drawImage(Sprite.doll_dead.getFxImage(), x, y);
        }
    }

    @Override
    public void update() {
        if (!this.isAlive()) {
            if (timeDis > 0) timeDis--;
            else this.setRemoved(true);
            return;
        }

        if (this.isAlive()) {
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
}
