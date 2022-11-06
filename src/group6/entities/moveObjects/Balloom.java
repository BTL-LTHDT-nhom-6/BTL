package group6.entities.moveObjects;

import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;
import static group6.BombermanGame.*;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image img, String direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        this.setSpeed(1);
    }

    @Override
    protected void runningAnimation() {
        animation++;

        if (direction.equals("up") || direction.equals("right")) {
            _sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation, time);
            this.setImg(_sprite.getFxImage());
        }
        if (direction.equals("down") || direction.equals("left")) {
            _sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation, time);
            this.setImg(_sprite.getFxImage());
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.isAlive())
            gc.drawImage(this.getImg(), x, y);
        else {
            if (timeDis > 0) gc.drawImage(Sprite.balloom_dead.getFxImage(), x, y);
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
