/*******************************************************
 * Copyright (C) 2015 Mirco Timmermann - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Mirco Timmermann <mtimmermann@gmx.de>, December 2016
 * 
 *******************************************************/
package com.iaj.fbla2017.assets;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;


/*
 * Assets loader.
 */
public class LevelAssets {
	protected AssetManager _assetManager;
	
	private boolean _isLoading = false;
	private boolean _finished = false;
	
	public interface ILoadListener {
		void OnBegin();
		void OnLoading(float value);
		void OnFinished();
	};
	
	private List<ILoadListener> _listener = null;
	
	
	public LevelAssets() {
		_listener = new ArrayList<ILoadListener>();
		
		_assetManager = new AssetManager();
	}
	
	public <T> void addAsset(String url, Class<T> type) {
		_assetManager.load(url, type);
	}
	
	public <T> void addAsset(String url, Class<T> type, AssetLoaderParameters<T> parameter) {
		_assetManager.load(url, type, parameter);
	}
	
	public void unload(String url) {
		_assetManager.unload(url);
	}
	
	public void unload() {
		_assetManager.clear();
	}
	
	public void dispose() {
		unload();
		_assetManager.dispose();
	}

	public float getProgress() {
		return _assetManager.getProgress();
	}
	
	public boolean update() {
		if(!_finished) {
			if(!_isLoading) {
				_isLoading = true;
				
				if(_listener != null) {
					_onBegin();
				}
			}
		}
		
		_finished = _assetManager.update();
		
		if(!_finished) {
			float progress = _assetManager.getProgress();
			_onLoading(progress);
			
		} else {
			if(_isLoading) {
				_isLoading = false;
				
				if(_listener != null) {
					float progress = _assetManager.getProgress();
					
					_onLoading(progress);
					_onFinished();
					
				}
			}
		}
		
		return _finished;
	}

	private void _onBegin() {
		onBegin();
		
		for(int i = _listener.size()-1; i >= 0; i--) {
			ILoadListener listener = _listener.get(i);
			
			listener.OnBegin();
		}
	}
	
	private void _onLoading(float progress) {
		onLoading(progress);
		
		for(int i = _listener.size()-1; i >= 0; i--) {
			ILoadListener listener = _listener.get(i);
			
			listener.OnLoading(progress);
		}
	}

	private void _onFinished() {
		onFinished();
		
		for(int i = _listener.size()-1; i >= 0; i--) {
			ILoadListener listener = _listener.get(i);
			
			listener.OnFinished();
		}
	}
	
	protected void onBegin() { };
	protected void onLoading(float progress) { };
	protected void onFinished() { };
	
	public boolean IsFinished() {
		return _finished;
	}

	public void addLoadListener(ILoadListener listener) {
		_listener.add(listener);
	}
	
	public void removeLoadListener(ILoadListener listener) {
		_listener.remove(listener);
	}

	public boolean has(String url) {
		return _assetManager.isLoaded(url);
	}
	
	public <T> T get(String url) {
		return _assetManager.get(url);
	}
	
	public AssetManager getAssetManager() {
		return _assetManager;
	}
}
