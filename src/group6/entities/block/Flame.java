package group6.entities.block;

import group6.entities.Entity;
import group6.entities.moveObjects.Enemy;
import javafx.scene.canvas.GraphicsContext;
import static group6.BombermanGame.*;

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
        _flameSegments = new FlameSegment[defineFlameLength()];
        // TODO: tạo các segment dưới đây
        boolean last;
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

            if (bomberman.getX()/32 == xa && bomberman.getY()/32 == ya) bomberman.setAlive(false);

            for (Enemy enemy : entities) {
                if (enemy.getX() / 32 == xa && enemy.getY() / 32 == ya) enemy.setAlive(false);
            }
            if (last) {
                posBomb[xa][ya] = POS_FLAME;
            }
        }
    }

    /**
     * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
     * @return
     */
    private int defineFlameLength() {
        // TODO: thực hiện tính toán độ dài của Flame
        int radius = 0;
        int xa = x;
        int ya = y;
        while (radius < _radius) {
            if (_direction == "up") ya--;
            if (_direction == "right") xa++;
            if (_direction == "down") ya++;
            if (_direction == "left") xa--;

            if (pos[xa][ya] == 2) break; //check Wall Collision
            if (pos[xa][ya] == 3 || pos[xa][ya] == 1) { //check Brick Collision and Portal Position
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
