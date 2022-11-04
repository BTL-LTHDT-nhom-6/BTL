package group6.entities.moveObjects;

import javafx.scene.image.Image;
import group6.entities.Entity;


public abstract class Character extends Entity {
    public boolean alive = true;
    protected String direction;
    protected int keepMove = 0;
    protected int time = 60;
    public boolean removed =  false;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveRight();

    public abstract void moveLeft();

    public abstract void putBomb();

    protected abstract void loadAnimation();

    @Override
    public void update() {

    }
}
