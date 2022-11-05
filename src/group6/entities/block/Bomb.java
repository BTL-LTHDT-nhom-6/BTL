package group6.entities.block;

import group6.entities.Entity;
import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Iterator;
import static group6.BombermanGame.*;
import group6.entities.block.Brick;

public class Bomb extends Entity {
    private static final int RADIUS = 1; // maximum bomb radius
    public static int animation = 0; // iterator for loading effect
    public int statusBomb; // 0: empty, 1: putBomb, 2: exploding
    protected double timeToExplode = 120; // ready to explode
    public int timeAfter = 20;// time for exploding effect
    protected Flame[] _flames;

    public Bomb(int xUnit, int yUnit, Image img, int statusBomb) {
        super(xUnit, yUnit, img);
        this.statusBomb = statusBomb;
    }
    public int getRadius() {
        return RADIUS;
    }


    @Override
    public void render(GraphicsContext gc) {
        if (statusBomb == 2) {
            renderFlames(gc);
            renderExplodedBomb(gc);

        } else if (statusBomb == 1) {
            renderActiveBomb(gc);
        }
    }

    @Override
    public void update() {
        if (timeToExplode > 0)
            timeToExplode--;
        else {
            if (statusBomb == 1) {
                setFlames();
            }
            if (timeAfter > 0)
                timeAfter--;
            else {
                statusBomb = 0;
                posBomb[this.x/32][this.y/32] = 0;
            }
        }
    }

    public void renderActiveBomb(GraphicsContext gc) {
        animation++;
        if (animation > 60) animation = 0;
        this.setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animation, 60).getFxImage());
        super.render(gc);
    }

    public void renderExplodedBomb(GraphicsContext gc) {
        animation++;
        this.setImg(Sprite.movingSprite(Sprite.bomb_exploded,
                Sprite.bomb_exploded1,
                Sprite.bomb_exploded2,
                animation, 60).getFxImage());
        super.render(gc);
    }

    public void setFlames() {
         statusBomb = 2;
         // TODO: create flames
        _flames = new Flame[4];
        _flames[0] = new Flame(x/32, y/32,"up", getRadius());
        _flames[1] = new Flame(x/32, y/32,"right", getRadius());
        _flames[2] = new Flame(x/32, y/32,"down", getRadius());
        _flames[3] = new Flame(x/32, y/32,"left", getRadius());
    }

    public void renderFlames(GraphicsContext gc) {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].render(gc);
        }
    }

}

