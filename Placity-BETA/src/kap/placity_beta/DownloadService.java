package kap.placity_beta;

import java.io.File;
import java.io.FileOutputStream;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DownloadService extends IntentService{

	private String id;

	public DownloadService() {
		super("DownloadService");
	}
	
	//Get given game-ID from starting intent
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle b = intent.getExtras();
        id = b.getString("ID");
	    return super.onStartCommand(intent,flags,startId);
	}

	//Download of filelist and saving of individual files
	@Override
	protected void onHandleIntent(Intent intent) {        
        for (String list : ServerInterface.getFileList(id).split(",")){
			save(id, ServerInterface.getGameFile(id, list), list);
		}
	}
	
	//Method for saving
	private void save(String id, String gameFile, String filename) {
		Log.v(id, filename + " " + gameFile);
		
		String basePath = getDir("games", Context.MODE_PRIVATE).getAbsolutePath()+File.separator+id;
		File dir = new File(basePath);
		if (!dir.exists()){
			dir.mkdirs();
		}
		File file = new File(dir, filename);
		
		try{
		FileOutputStream myFile = new FileOutputStream(file);
		myFile.write(gameFile.getBytes());
		myFile.close();}
		catch (Exception e){
			Log.v(id,e.getMessage());
		}
		
	}

}