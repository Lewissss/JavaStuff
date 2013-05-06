package com.lewis.boxgame.Views;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lewis.boxgame.Models.Player;
import com.lewis.boxgame.Models.Recharger;

public class LightRenderer {

	RayHandler handler;
	GameWorld gWorld;
	World world;
	WorldRenderer wr;
	OrthographicCamera camera;

	Player player;

	Array<ConeLight> lights = new Array<ConeLight>();
	Array<Recharger> rechargers = new Array<Recharger>();
	Array<PointLight> rechargerLights = new Array<PointLight>();
	PointLight light;

	public LightRenderer(GameWorld gWorld, World world, WorldRenderer wr){

		this.gWorld = gWorld;
		this.world = gWorld.getWorld();
		this.wr = wr;
		this.camera = wr.getCamera();
		handler = new RayHandler(world);

		player = gWorld.getPlayer();
		rechargers = gWorld.getLevel().getRechargers();
		lights = wr.getConeLights();

		light = new PointLight(handler, 750, Color.GREEN, 100, 0, 0);

		for(Recharger r : rechargers){
			rechargerLights.add(new PointLight(handler, 500, Color.BLUE, r.getPower(), r.getPosition().x, r.getPosition().y));
		}
	}

	public void render(){

		handler.setCombinedMatrix(wr.getCamera().combined);

		light.attachToBody(player.getBody(), 0, 0);
		light.setXray(false);
		light.setSoft(true);
		light.setColor(Color.BLUE);

		if(Gdx.input.isKeyPressed(Keys.Q)){

			lights.add(new ConeLight(handler, 500, Color.YELLOW, 200, player.getPosition().x, player.getPosition().y, -90, 15));
		}

		light.setDistance(gWorld.getDistance());

		for(Recharger r : rechargers){
			r.update();
		}

		//Set recharge light power
		for(int i = 0; i < rechargers.size; i++){
			
			//Get power out of 100%
			float power = rechargers.get(i).getPower();
			float powerPercent = (power / rechargers.get(i).getMaxPower()) * 100;
			

			//Set the power of the light
			rechargerLights.get(i).setDistance(powerPercent);
			rechargerLights.get(i).setColor(Color.RED);
			
		}

		handler.updateAndRender();
	}

	public void dispose(){
		handler.dispose();
	}
}
