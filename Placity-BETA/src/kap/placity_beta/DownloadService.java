package kap.placity_beta;

import java.io.File;
import java.io.FileOutputStream;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

public class DownloadService extends IntentService{

	private String id;
	
	public DownloadService() {
		super("IntentService");
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
		String files = ServerInterface.getFileList(id);
		Log.i("file", files);
        for (String list : files.split(",")){
			save(id, list);
		}
        SystemClock.sleep(500);
        Intent i = new Intent();
        i.setAction("DownloadStep.ONE");
        sendBroadcast(i);
	}
	
	//Method for saving
	private void save(String id, String filename) {
		Log.v(id, "fetching " + filename);
		
		String basePath = getDir("games", Context.MODE_PRIVATE).getAbsolutePath()+File.separator+id; //Set base directory for game
		File dir = new File(basePath);
		/*if (!dir.exists()){
			dir.mkdirs(); //Create folder
		}*/
		dir.delete();
		dir.mkdirs();
		File file = new File(dir, filename); //Create a file in that directory
		
		try{
		FileOutputStream myFile = new FileOutputStream(file);
		myFile.write(ServerInterface.getGameFile(id, filename));
		myFile.close();}
		catch (Exception e){
			Log.v(id,e.getMessage());
		}
		
	}

}