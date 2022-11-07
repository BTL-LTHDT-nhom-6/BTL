package group6.entities.block;

import group6.entities.Entity;
import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static group6.BombermanGame.*;
import static group6.BombermanGame.pos;

public class Portal extends Entity {
    public int timeToDis = 30; // time for clearing brick.
    public boolean destroyed = false;  // check if brick destroyed
    public int transition = 0; // iterator used to load brick explosion

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    private void clearBrick() {
        for (Entity entity : stillObjects) {
            if (entity instanceof Portal)
                if (posBomb[entity.getX() / 32][entity.getY() / 32] == POS_FLAME) {
                    ((Portal) entity).destroyed = true;
                }
        }
    }

    /**
     * Detect if bomberman went into portal.
     */
    public static boolean intoPortal() {
        for (Entity entity : stillObjects) {
            if (entity instanceof Portal) {
                if (bomberman.getX() / 32 == entity.getX() / 32
                        && bomberman.getY() / 32 == entity.getY() / 32) {
                    return true;
                }
            }
        }
        return false;
    }

    public void explodedBrick() {
        transition ++;
        this.setImg(Sprite.movingSprite(Sprite.brick_exploded,
                Sprite.brick_exploded1,
                Sprite.brick_exploded2, transition, 60).getFxImage());
    }

    @Override
    public void update() {
        clearBrick();
        if (this.destroyed) {
            if (timeToDis > 0) {
                timeToDis--;
            }
            pos[this.getX()/32][this.getY()/32] = 0;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.destroyed) {
            if (timeToDis > 0) explodedBrick();
            else {
                this.setImg(Sprite.portal.getFxImage());

            }
        }
        super.render(gc);
    }
}
