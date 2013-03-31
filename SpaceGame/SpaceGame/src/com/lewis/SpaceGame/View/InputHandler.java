package com.lewis.SpaceGame.View;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.Models.Laser;
import com.lewis.SpaceGame.Models.Ship;

public class InputHandler implements InputProcessor {
	
	World world;
	Ship ship;
	
	Vector3 touch = new Vector3();
	Vector2 vec2Touch = new Vector2();
	
	public InputHandler(World world){
		this.world = world;
	}
	

	@Override
	public boolean keyDown(int keycode) {
		
		ship = world.getShip();
		
		switch(keycode){
		case Keys.W:
			ship.getVelocity().y = 1;
			break;
		case Keys.S:
			ship.getVelocity().y = -1;
			break;
		case Keys.A:
			ship.getVelocity().x = -1;
			break;
		case Keys.D:
			ship.getVelocity().x = 1;
			break;
			//DEBUG	
		case Keys.M:
			SpaceGame.DEBUG = !SpaceGame.DEBUG;
			break;
		case Keys.ESCAPE:
			System.exit(0);
			default:
				break;
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {

		ship = world.getShip();
		
		switch(keycode){
		case Keys.W:
			if(ship.getVelocity().y == 1)
				ship.getVelocity().y = 0;
			break;
		case Keys.S:
			if(ship.getVelocity().y == -1)
				ship.getVelocity().y = 0;
			break;
		case Keys.A:
			if(ship.getVelocity().x == -1)
				ship.getVelocity().x = 0;
			break;
		case Keys.D:
			if(ship.getVelocity().x == 1)
				ship.getVelocity().x = 0;
			break;
			default:
				break;
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touch.set(screenX, screenY, 0);
		world.getRenderer().getCamera().unproject(touch);
		vec2Touch.set(touch.x, touch.y);
		
		ship = world.getShip();
		
		// Add laser
		world.addLaser(new Laser(Laser.SPEED, 0f, new Vector2(ship.getPosition().x + ship.getWidth() / 2, 
				ship.getPosition().y + ship.getHeight() / 2), .1f, 8/20f, new Vector2(vec2Touch.sub(ship.getOrigin()))));
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
