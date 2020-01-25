package fi.tuni.tiko;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Spaceship extends GameObject {
    public Spaceship() {
        texture = new Texture("spaceship.png");
        float textureWidth = texture.getWidth() / 800.0f;
        float textureHeight = texture.getHeight() / 800.0f;
        rectangle = new Rectangle(0, 0, textureWidth, textureHeight);
    }
}
