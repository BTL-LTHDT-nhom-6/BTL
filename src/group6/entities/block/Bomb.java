package group6.entities.block;

import group6.entities.Entity;
import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Iterator;

import static group6.BombermanGame.*;
import group6.entities.block.Brick;

public class Bomb extends Entity {
    protected static final int RADIUS = 1;
    public static int animation = 0;
    public int isBomb;
    protected double _timeToExplode = 120; //2 seconds - thoi gian phat no
    public int _timeAfter = 20;// thoi gian de no
    protected Flame[] _flames;


    public Bomb(int xUnit, int yUnit, Image img, int isBomb) {
        super(xUnit, yUnit, img);
        this.isBomb = isBomb;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isBomb == 2) {
            renderFlames(gc);
            explodedBomb(gc);

        } else if (isBomb == 1) {
            renderActiveBomb(gc);
        }
    }

    @Override
    public void update() {
        if (_timeToExplode > 0)
            _timeToExplode--;
        else {
            if (isBomb == 1) {
                explode();
            }
            if (_timeAfter > 0)
                _timeAfter--;
            else {
                isBomb = 0;
            }
        }
    }



    public void renderActiveBomb(GraphicsContext gc) {
        animation++;
        if (animation > 60) animation = 0;
        this.setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animation, 60).getFxImage());
        super.render(gc);
    }

    public void explodedBomb(GraphicsContext gc) {
        animation++;
        this.setImg(Sprite.movingSprite(Sprite.bomb_exploded,
                Sprite.bomb_exploded1,
                Sprite.bomb_exploded2,
                animation, 60).getFxImage());
        super.render(gc);
    }

    public void explode() {
         isBomb = 2;

         // TODO: create flames
        _flames = new Flame[4];

        _flames[0] = new Flame(x/32, y/32,"up",RADIUS);
        _flames[1] = new Flame(x/32, y/32,"right",RADIUS);
        _flames[2] = new Flame(x/32, y/32,"down",RADIUS);
        _flames[3] = new Flame(x/32, y/32,"left",RADIUS);
    }

    public void renderFlames(GraphicsContext gc) {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].render(gc);
        }
    }


}

