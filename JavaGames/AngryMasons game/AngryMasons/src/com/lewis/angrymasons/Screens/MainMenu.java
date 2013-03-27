package com.lewis.angrymasons.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.lewis.angrymasons.AngryMasons;

public class MainMenu implements Screen{
	
	AngryMasons game;
	
	Stage stage;
	BitmapFont black;
	BitmapFont white;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton button;
	TextButton exitButton;
	Label label;
	Label versionLabel;
	
	public MainMenu(AngryMasons game)
	{
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		batch.begin();
		
		// Draw everything in stage
		stage.draw();
		
		batch.end();
	}

	@SuppressWarnings("static-access")
	@Override
	public void resize(int width, int height) {
		if(stage == null)
		{
			stage = new Stage(width, height, true);
		}
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = black;
		
		button = new TextButton("PLAY", style);
		button.setWidth(400);
		button.setHeight(100);
		button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);
		
		button.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				return true;
			}
			
			// Must handle both touch up and touch down otherwise it doesn't work
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				System.out.println("Up");
				game.setScreen(new GameScreen(game));

			}
		});
		
		exitButton = new TextButton("EXIT", style);
		exitButton.setWidth(400);
		exitButton.setHeight(100);
		exitButton.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		exitButton.setY(Gdx.graphics.getHeight() / 4);	// A quarter from the bottom of the screen
		
		exitButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				return true;
			}
			
			// Must handle both touch up and touch down otherwise it doesn't work
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				System.out.println("Exit");
				game.dispose();
				System.exit(0);
			}
		});
		
		//Label stuff (Text above button)
		LabelStyle ls = new LabelStyle(white, Color.WHITE);
		label = new Label("Angry Masons", ls);
		label.setX(0);
		label.setY(Gdx.graphics.getHeight() / 2 + 100);
		label.setWidth(width);	// Stretches label across entire screen
		label.setAlignment(Align.center);	//Centres stretched label
		
		// Draw the version to the top of the screen
		versionLabel = new Label("Version " + game.VERSION, ls);
		versionLabel.setX(2);
		versionLabel.setY(Gdx.graphics.getHeight() - 30);
		
		
		
		stage.addActor(button);
		stage.addActor(exitButton);
		stage.addActor(label);
		stage.addActor(versionLabel);
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/button.pack");
		
		skin = new Skin();
		skin.addRegions(atlas);
		
		white = new BitmapFont(Gdx.files.internal("data/whitefont.fnt"), false);
		black = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		black.dispose();
		white.dispose();
		stage.dispose();
	}

}
