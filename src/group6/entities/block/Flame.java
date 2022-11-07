package group6.entities.block;

import group6.entities.Entity;
import group6.entities.moveObjects.Enemy;
import javafx.scene.canvas.GraphicsContext;
import static group6.BombermanGame.*;

public class Flame extends Entity {
    protected String _direction;
    private int _radius;
    protected FlameSegment[] flameSegments = new FlameSegment[0];

    public Flame(int x, int y, String direction, int radius) {
        this.x = x;
        this.y = y;
        _direction = direction;
        _radius = radius;
        drawFlameSegments();
    }

    @Override
    public void update() {
    }


    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < flameSegments.length; ++i) {
            flameSegments[i].render(gc);
        }
    }

    /**
     * Create new flame segments with max radius.
     */
    private void drawFlameSegments() {
        flameSegments = new FlameSegment[defineFlameLength()];
        boolean last;
        int xa = x;
        int ya = y;
        for (int i = 0; i < flameSegments.length; i++) {
            last = i == flameSegments.length -1 ? true : false;
            switch (_direction) {
                case "up": ya--; break;
                case "right": xa++; break;
                case "down": ya++; break;
                case "left": xa--; break;
            }
            flameSegments[i] = new FlameSegment(xa, ya, _direction, last);

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
        define maximum length of flames.
     */
    private int defineFlameLength() {
        int maxRadius = 0;
        int xa = x;
        int ya = y;
        while (maxRadius < _radius) {
            if (_direction.equals("up")) ya--;
            if (_direction.equals("right")) xa++;
            if (_direction.equals("down")) ya++;
            if (_direction.equals("left")) xa--;

            //check Wall Collision
            if (pos[xa][ya] == 2) break;
            //check Brick Collision and Portal Position
            if (pos[xa][ya] == 3 || pos[xa][ya] == 1) {
                maxRadius++;
                break;
            }
            maxRadius++;
        }
        return maxRadius;
    }


}
