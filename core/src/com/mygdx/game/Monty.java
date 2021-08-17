package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import managers.GameManager;
import managers.InputManager;

public class Monty implements ApplicationListener{
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private float w;
	private float h;
	
	@Override
	public void create () {
		this.w = (float) Gdx.graphics.getWidth();
		this.h = (float) Gdx.graphics.getHeight();
		(this.camera = new OrthographicCamera(this.w, this.h)).setToOrtho(false);
		this.batch = new SpriteBatch();
		GameManager.initialize(this.w,this.h);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(16384);
		this.batch.setProjectionMatrix(this.camera.combined);
		InputManager.handleInput(this.camera);
		this.batch.begin();
		GameManager.renderGame(this.batch);
		this.batch.end();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
		GameManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
