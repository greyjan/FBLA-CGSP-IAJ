package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.iaj.fbla2017.SandboxGame;
import com.iaj.fbla2017.assets.Assets;
import com.iaj.fbla2017.assets.LevelAssets;
import com.iaj.fbla2017.assets.LevelAssets.ILoadListener;


public class LoadingScreen implements Screen{

	final SandboxGame game;
	
	protected OrthographicCamera camera;
	protected final Stage stage;
	
	Texture background;
	
	public interface ILoadingListener {
		public void OnFinished();
	}
	
	private ILoadingListener loadingListener;
	private ILoadListener loadListener;
	
	private LevelAssets assets = Assets.GetInstance();
	
	
	public LoadingScreen(SandboxGame game) {
		
		//
		this.game = game;
		
		//
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//
		stage = new Stage(new ScreenViewport());
		
		//
		
		//
		
		loadListener = new ILoadListener() {

			@Override
			public void OnBegin() {
				
			}

			@Override
			public void OnLoading(float value) {
				
			}

			@Override
			public void OnFinished() {
				assets.removeLoadListener(loadListener);
				loadListener = null;
				
				if(loadingListener != null) {
					loadingListener.OnFinished();
				}
			}
			
		};
		
		
		assets.addLoadListener(loadListener);
		
		//
		
		
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		assets.update();
		
		stage.act(delta);
		stage.draw();
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

	@Override
	public void hide() {
		
	}

	public void setILoadingListener(ILoadingListener loadingListener) {
		this.loadingListener = loadingListener;
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

}
