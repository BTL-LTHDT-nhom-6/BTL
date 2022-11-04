package group6.entities.block;

import group6.entities.Entity;
import group6.entities.moveObjects.Bomber;
import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;


public class FlameSegment extends Entity {
    protected boolean _last;
    protected int ani = 0;
    protected String direction;

    public FlameSegment(int x, int y, String direction, boolean last) {
        this.x = x;
        this.y = y;
        _last = last;
        this.direction = direction;
    }

    public void loadSegment(String direction, boolean last) {
        switch (direction) {
            case "up":
                if(!last) {
                    _sprite = Sprite.movingSprite(Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, ani, 60);
                } else {
                    _sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last,
                            Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last2, ani, 60);
                }
                break;
            case "right":
                if(!last) {
                    _sprite = Sprite.movingSprite(Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, ani, 60);
                } else {
                    _sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                            Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last2, ani, 60);
                }
                break;
            case "down":
                if(!last) {
                    _sprite = Sprite.movingSprite(Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, ani, 60);
                } else {
                    _sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                            Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last2, ani, 60);
                }
                break;
            case "left":
                if(!last) {
                    _sprite = Sprite.movingSprite(Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, ani, 60);
                } else {
                    _sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                            Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last2, ani, 60);
                }
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        ani++;
        int xa = x << 5;
        int ya = y << 5;
        loadSegment(direction, _last);
        this.setImg(_sprite.getFxImage());
        gc.drawImage(this.getImg(), xa, ya);
    }

    @Override
    public void update() {
    }

}
