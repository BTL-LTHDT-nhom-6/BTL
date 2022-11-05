package group6.entities.block;

import group6.entities.Entity;
import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static group6.BombermanGame.*;
import static group6.BombermanGame.pos;

public class Portal extends Entity {
    public static boolean isPortal;
    public int timeToDis = 30;
    public boolean destroyed = false;
    public static int trans = 0;

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    private void checkHidden() {
        for (Entity entity : stillObjects) {
            if (entity instanceof Portal)
                if (posBomb[entity.getX() / 32][entity.getY() / 32] == POS_FLAME) {
                    ((Portal) entity).destroyed = true;
                }
        }
    }

    public void explodedBrick(GraphicsContext gc) {
        trans ++;
        this.setImg(Sprite.movingSprite(Sprite.brick_exploded,
                Sprite.brick_exploded1,
                Sprite.brick_exploded2, trans, 60).getFxImage());
        super.render(gc);
    }

    @Override
    public void update() {
        checkHidden();
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
            if (timeToDis > 0) explodedBrick(gc);
            else {
                this.setImg(Sprite.portal.getFxImage());

            }
        }
        super.render(gc);
    }
}
