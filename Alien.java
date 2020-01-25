package fi.tuni.tiko;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Alien extends GameObject {
    public Alien() {
        texture = new Texture("enemy.png");
        float textureWidth = texture.getWidth() / 800.0f;
        float textureHeight = texture.getHeight() / 800.0f;
        rectangle = new Rectangle(MathUtils.random(0.0f, 10.0f - 0.64f), MathUtils.random(0.0f, 5.0f - 0.64f), textureWidth, textureHeight);
    }
}
