package com.tp.batman.francis.blockgame.framework.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.tp.batman.francis.blockgame.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AndroidFileIO implements FileIO {
	Context context;
	AssetManager assets;
	String storagePath;
	
	public AndroidFileIO(Context context){
		this.context = context;
		this.assets = context.getAssets();
		
		Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		
		// if there is an sd card, use it to save, if not, use internal storage
		if(isSDPresent)
			this.storagePath = Environment.getExternalStorageDirectory()
			 .getAbsolutePath()+ File.separator;
		else
			this.storagePath = context.getFilesDir().getAbsolutePath()+ File.separator;
	}
	
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}
	
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(storagePath+fileName);
	}
	
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(storagePath+fileName);
	}
	
	public SharedPreferences getPreferences(){
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
