package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy implements IObject {

    public abstract Rectangle getBound();

    public abstract boolean isOutOfScreen();

    @Override
    public abstract void draw(Graphics g);

    @Override
    public abstract void update();

}
