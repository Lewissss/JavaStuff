package com.lewis.boxgame.Views;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lewis.boxgame.Models.Player;
import com.lewis.boxgame.Models.Recharger;

public class LightRenderer {

	private RayHandler handler;
	private GameWorld gWorld;
	private WorldRenderer wr;
	private Player player;

	private Array<Recharger> rechargers = new Array<Recharger>();
	private Array<PointLight> rechargerLights = new Array<PointLight>();
	private PointLight light;

	public LightRenderer(GameWorld gWorld, World world, WorldRenderer wr){

		this.gWorld = gWorld;
		gWorld.getWorld();
		this.wr = wr;
		wr.getCamera();
		
		setupRayHandler(world); 

		getObjects(gWorld, wr);

		light = new PointLight(handler, 750, Color.GREEN, 100, 0, 0);

		createChargerLights();
	}

	private void createChargerLights() {
		for(Recharger r : rechargers){
			rechargerLights.add(new PointLight(handler, 500, Color.BLUE, r.getPower(), r.getPosition().x, r.getPosition().y));
		}
	}

	private void getObjects(GameWorld gWorld, WorldRenderer wr) {
		player = gWorld.getPlayer();
		rechargers = gWorld.getLevel().getRechargers();
		wr.getConeLights();
	}

	private void setupRayHandler(World world) {
		RayHandler.setGammaCorrection(true);
		handler = new RayHandler(world);
		
		handler.setAmbientLight(0.05f);
		handler.setBlur(true);
		handler.setShadows(true);
	}

	public void render(){

		handler.setCombinedMatrix(wr.getCamera().combined);

		setPlayerLightAttributes();

		for(Recharger r : rechargers){
			r.update();
		}

		//Set recharge light power
		setRechargerLightPower();
		
		handler.updateAndRender();
	}

	private void setRechargerLightPower() {
		for(int i = 0; i < rechargers.size; i++){
			
			//Get power out of 100%
			float power = rechargers.get(i).getPower();
			float powerPercent = (power / rechargers.get(i).getMaxPower()) * 100;
			

			//Set the power of the light
			rechargerLights.get(i).setDistance(powerPercent);
			rechargerLights.get(i).setColor(Color.BLUE);
			
		}
	}

	private void setPlayerLightAttributes() {
		light.attachToBody(player.getBody(), 0, 0);
		// Consider changing
		light.setXray(true);
		light.setSoft(true);
		light.setColor(Color.RED);

		light.setDistance(gWorld.getDistance());
	}

	public void dispose(){
		handler.dispose();
	}
}
