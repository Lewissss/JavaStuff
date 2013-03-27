package com.lewis.SpaceGame.View;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.lewis.SpaceGame.Models.Ship;

public class InputHandler implements InputProcessor {
	
	World world;
	Ship ship;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
