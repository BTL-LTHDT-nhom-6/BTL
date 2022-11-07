package group6.entities.moveObjects;

import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import static group6.BombermanGame.*;
import java.util.Random;

public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, Image img, String direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        this.setSpeed(1);
    }

    @Override
    protected void runningAnimation() {
        animation++;
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
        if (!isAlive()) {

            if (timeDis > 0) timeDis--;
            else {
                this.setRemoved(true);
                _point += 800;
            }
            return;
        }

        if (isAlive()) {
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
                    if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
                        if (this.getY() / 32 > 1) {
                            timeToMove = 32 / getSpeed();
                        } else {
                            isMoving = false;
                            break;
                        }
                    }
                    moveUp();
                    break;
                case "right":
                    if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
                        if (this.getX() / 32 < _width - 2) {
                            timeToMove = 32 / getSpeed();
                        } else {
                            isMoving = false;
                            break;
                        }
                    }
                    moveRight();
                    break;
                case "left":
                    if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
                        if (this.getX() / 32 > 1) {
                            timeToMove = 32 / getSpeed();
                        } else {
                            isMoving = false;
                            break;
                        }
                    }

                    moveLeft();
                    break;
                case "down":
                    if (this.getX() % 32  == 0 && this.getY() % 32 == 0) {
                        if (this.getY() / 32 < _height - 2) {
                            timeToMove = 32 / getSpeed();
                        } else {
                            isMoving = false;
                            break;
                        }
                    }

                    moveDown();
                    break;
            }
        }
    }
}
