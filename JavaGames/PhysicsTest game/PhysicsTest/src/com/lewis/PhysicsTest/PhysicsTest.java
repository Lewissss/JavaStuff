package com.lewis.PhysicsTest;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsTest implements ApplicationListener {
	OrthographicCamera camera;
	
	float width, height;
	
	FPSLogger logger;
	
	World world;	// Handles physics and holds objects
	Box2DDebugRenderer renderer;
	
	Body circleBody;
	
	RayHandler handler;
	
	@Override
	public void create() {		
		width = Gdx.graphics.getWidth() / 5;
		height = Gdx.graphics.getHeight() / 5;
		
		camera = new OrthographicCamera(width, height);
		camera.position.set(width * 0.5f, height * 0.5f, 0);
		camera.update();
		
		world = new World(new Vector2(0, -9.8f), false);	// Parameters include gravity (9.8 METERS per second)
		
		renderer = new Box2DDebugRenderer();
		
		logger = new FPSLogger();
		
		BodyDef circleDef = new BodyDef();
		circleDef.type = BodyType.DynamicBody;
		circleDef.position.set(width / 2f, height / 2f);	// Put in middle of screen
		
		circleBody = world.createBody(circleDef);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(3f);
		
		FixtureDef circleFixture = new FixtureDef();
		circleFixture.shape = circleShape;
		circleFixture.density = 0.4f;
		circleFixture.friction = 0.2f;
		circleFixture.restitution = 0.8f; // Bouncyness
		
		circleBody.createFixture(circleFixture);
		
		// Create ground
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0,  3);
		
		Body groundBody = world.createBody(groundBodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox((camera.viewportWidth * 2), 3.0f);
		
		groundBody.createFixture(groundBox, 0.0f);
		
		handler = new RayHandler(world);
		handler.setCombinedMatrix(camera.combined);	// Camera not moving, so doesn't need to go in render method
		
		// Create a point light
		new PointLight(handler, 5000, Color.CYAN, 150, (width / 2) - 50, (height / 2) + 15);
		new PointLight(handler, 5000, Color.YELLOW, 200, (width / 2) + 50, (height / 2) + 15);
		
		new ConeLight(handler, 5000, Color.WHITE, 200, (width / 2), (height / 2), 270, 20);
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		renderer.render(world,  camera.combined);
		
		handler.updateAndRender();
		
		world.step(1/60f, 6, 2);	// second and third deal with accuracy
		
		logger.log();
	}
	
	public Body getCircle(){
		return circleBody;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
