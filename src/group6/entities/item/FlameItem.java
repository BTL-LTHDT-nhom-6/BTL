package group6.entities.item;

import group6.entities.Entity;
import group6.entities.block.Bomb;
import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static group6.BombermanGame.*;

public class FlameItem extends Entity {
    public boolean getItem;
    public int timeToDis = 30;
    public boolean destroyed = false;
    public static int trans = 0;
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    private void clearBrick() {
        for (Entity entity : stillObjects) {
            if (entity instanceof FlameItem)
                if (posBomb[entity.getX() / 32][entity.getY() / 32] == POS_FLAME) {
                    ((FlameItem) entity).destroyed = true;
                }
        }
    }

    public void explodedBrick() {
        trans ++;
        this.setImg(Sprite.movingSprite(Sprite.brick_exploded,
                Sprite.brick_exploded1,
                Sprite.brick_exploded2, trans, 60).getFxImage());
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
        if (!this.getItem) {
            if (bomberman.getX() == this.getX() && bomberman.getY() == this.getY()) {
                this.getItem = true;
                Bomb.addBombRadius();
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.destroyed) {
            if (timeToDis > 0) explodedBrick();
            else {
                this.setImg(Sprite.powerup_flames.getFxImage());
            }
        }
        if (this.getItem) this.setImg(Sprite.grass.getFxImage());
        super.render(gc);
    }

}
