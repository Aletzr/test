package fi.tuni.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {
    Texture texture;
    Rectangle rectangle;

    public float getX() {
        return rectangle.x;
    }
    public float getY() {
        return rectangle.y;
    }
    public void setX(float x) {
        rectangle.x = x;
    }
    public void setY(float y) {
        rectangle.y = y;
    }
    public Texture getTexture() {
        return texture;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public float getHeight() {
        return rectangle.height;
    }
    public float getWidth() {
        return rectangle.width;
    }
    public void dispose() {
        Gdx.app.log("TAG", "Object Dispose");
        texture.dispose();
    }
}
