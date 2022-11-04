package group6.entities.block;

import group6.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import static group6.BombermanGame.*;
import group6.entities.block.Brick;
import group6.entities.moveObjects.Bomber;
import group6.entities.moveObjects.Character;

public class Flame extends Entity {
    protected String _direction;
    private int _radius;
    protected int xOrigin, yOrigin;
    protected FlameSegment[] _flameSegments = new FlameSegment[0];

    /**
     *
     * @param x hoành độ bắt đầu của Flame
     * @param y tung độ bắt đầu của Flame
     * @param direction là hướng của Flame
     * @param radius độ dài cực đại của Flame
     */
    public Flame(int x, int y, String direction, int radius) {
        xOrigin = x;
        yOrigin = y;
        this.x = x;
        this.y = y;
        _direction = direction;
        _radius = radius;
        createFlameSegments();
    }

    private void createFlameSegments() {
        _flameSegments = new FlameSegment[calculatePermitedDistance()];

        /**
         * biến last dùng để đánh dấu cho segment cuối cùng
         */

        // TODO: tạo các segment dưới đây
        boolean last = false;
        int xa = x;
        int ya = y;
        for (int i = 0; i < _flameSegments.length; i++) {
            last = i == _flameSegments.length -1 ? true : false;

            switch (_direction) {
                case "up": ya--; break;
                case "right": xa++; break;
                case "down": ya++; break;
                case "left": xa--; break;
            }
            _flameSegments[i] = new FlameSegment(xa, ya, _direction, last);
            if (bomberman.getX()/32 == xa && bomberman.getY()/32 == ya) bomberman.alive = false;
            for (Character enemy : entities) {
                if (enemy.getX() / 32 == xa && enemy.getY() / 32 == ya) enemy.alive = false;
            }
        }
    }

    /**
     * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
     * @return
     */
    private int calculatePermitedDistance() {
        // TODO: thực hiện tính toán độ dài của Flame
        int radius = 0;
        int xa = x;
        int ya = y;
        while (radius < _radius) {
            if (_direction == "up") ya--;
            if (_direction == "right") xa++;
            if (_direction == "down") ya++;
            if (_direction == "left") xa--;

            if (pos[xa][ya] == 2) break;
            if (pos[xa][ya] == 3) {
                posBomb[xa][ya] = POS_BOMB;
                radius++;
                break;
            }
            radius++;
        }
        return radius;
    }



    @Override
    public void update() {
    }



    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < _flameSegments.length; ++i) {
            _flameSegments[i].render(gc);
        }
    }
}
