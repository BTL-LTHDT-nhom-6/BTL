package group6.entities.block;

import group6.entities.Entity;
import group6.entities.moveObjects.Bomber;
import group6.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;


public class FlameSegment extends Entity {
    protected boolean max; // define last segment
    protected int ani = 0;
    protected String direction;

    public FlameSegment(int x, int y, String direction, boolean max) {
        this.x = x;
        this.y = y;
        this.max = max;
        this.direction = direction;
    }

    @Override
    public void render(GraphicsContext gc) {
        ani++;
        int xa = x << 5;
        int ya = y << 5;
        loadSegment(direction, max);
        this.setImg(_sprite.getFxImage());
        gc.drawImage(this.getImg(), xa, ya);
    }

    @Override
    public void update() {


    }

    public void loadSegment(String direction, boolean max) {
        switch (direction) {
            case "up":
                if(!max) {
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
                if(!max) {
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
                if(!max) {
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
                if(!max) {
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



}
