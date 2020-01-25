package fi.tuni.tiko;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class SpaceShooter implements ApplicationListener {
	/* ---------------------------- */
	/* --- Main Game Class -------- */
	/* ---------------------------- */

	private Texture backGroundTexture;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	// Sounds
	private Sound collisionEffect;
	private Music backgroundMusic;

	// Player
	private Spaceship player;
	private final int playerSpeed = 3;

	// Enemies Array
	private ArrayList<Alien> aliens;
	private final int enemySpeed = 1;
	private boolean enemyMoveDirectionY = true;
	private boolean enemyMoveDirectionX = true;

	@Override
	public void create () {
		// Objects
		player = new Spaceship();

		batch = new SpriteBatch();
		// Enemies Array
		aliens = new ArrayList<Alien>();
		for(int i=0; i<5; i++) {
			Alien alien = new Alien();
			aliens.add(alien);
		}

		// Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 5);

		// Sounds
		collisionEffect = Gdx.audio.newSound(Gdx.files.internal("collision.mp3"));

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
		backgroundMusic.setVolume(0.1f);

		// Background Texture
		backGroundTexture = new Texture("backgroundIMG.png");

		// Debug
		Gdx.app.log("TAG", "Create");
	}

	@Override
	public void resize(int width, int height) {
		// Debug
		Gdx.app.log("TAG", "Resize");
	}

	@Override
	public void render () {
		update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(backGroundTexture, 0, 0, 10.0f, 5.0f);
		// Draw Player
		player.draw(batch);
		// Draw Enemies
		for(int i=0; i<aliens.size(); i++) {
			Alien x = aliens.get(i);
			x.draw(batch);
		}
		batch.end();
	}

	public void update() {
		// We use DeltaTime to make sure fps doesn't effect our speed.
		float deltaTime = Gdx.graphics.getDeltaTime();

		// Player Movement
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.setY(player.getY() + playerSpeed * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.setY(player.getY() - playerSpeed * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.setX(player.getX() - playerSpeed * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.setX(player.getX() + playerSpeed * deltaTime);
		}

		// Enemy Movement
		for(int i=0; i<aliens.size(); i++) {
			if(enemyMoveDirectionY) {
				if(aliens.get(i).getY() < 5.0f - 0.64f) {
					aliens.get(i).setY(aliens.get(i).getY() + enemySpeed * deltaTime);
				} else {
					enemyMoveDirectionY = false;
				}
			} else {
				if(aliens.get(i).getY() > 0.0f) {
					aliens.get(i).setY(aliens.get(i).getY() - enemySpeed * deltaTime);
				} else {
					enemyMoveDirectionY = true;
				}
			}
			if(enemyMoveDirectionX) {
				if(aliens.get(i).getX() < 10.0f - 0.64f) {
					aliens.get(i).setX(aliens.get(i).getX() + enemySpeed * deltaTime);
				} else {
					enemyMoveDirectionX = false;
				}
			} else {
				if(aliens.get(i).getX() > 0.0f) {
					aliens.get(i).setX(aliens.get(i).getX() - enemySpeed * deltaTime);
				} else {
					enemyMoveDirectionX = true;
				}
			}
			// Collision Detection
			if(aliens.get(i).getRectangle().overlaps(player.getRectangle())) {
				collisionEffect.play(0.1f);
				Gdx.app.log("TAG", "GAME OVER");
			}
		}
	}

	@Override
	public void pause() {
		// Debug
		Gdx.app.log("TAG", "Pause");
	}

	@Override
	public void resume() {
		// Debug
		Gdx.app.log("TAG", "Resume");
	}

	@Override
	public void dispose () {
		// Debug
		Gdx.app.log("TAG", "Dispose");

		// Dispose. We clear everything from ram memory.
		batch.dispose();
		collisionEffect.dispose();
		backgroundMusic.dispose();
		player.dispose();
		//enemy.dispose();
		backGroundTexture.dispose();
	}
}