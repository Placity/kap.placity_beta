package kap.placity_beta;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;

public class ServerInterface {

	public static final String SERVER_URL = "http://www.blendboom.lima-city.de/APP2/";
	
	
	//Download filelist
	public static String getFileList(String id) {
		String data = "command=" + URLEncoder.encode("getFileList");
		data += "&id=" + URLEncoder.encode(id);
		return executeHttpRequest(data);
		
	}
	
	 
	//Download requested file
	public static byte[] getGameFile(String id, String filename) {
		
		byte[] result;
		try {
			URL url = new URL(SERVER_URL + id + "/" + filename);
			URLConnection ucon = url.openConnection();
			
			InputStream in = ucon.getInputStream();
			BufferedInputStream bin = new BufferedInputStream(in);
			
			ByteArrayBuffer baf = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bin.read()) != -1) {
				baf.append((byte) current);
			}
			
			result = baf.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
			Log.v("Error ", "gameFile not found");
			result = null;
		}
		return result;
	} 
	
	//Http-Request
	public static String executeHttpRequest(String data) {
		String result = "";
		try {
			URL url = new URL(SERVER_URL + "server.php");
			URLConnection connection = url.openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
			dataOut.writeBytes(data);
			dataOut.flush();
			dataOut.close();
			
			DataInputStream dataIn = new DataInputStream(connection.getInputStream());
			String inputLine;
			while ((inputLine = dataIn.readLine()) != null) {
				result += inputLine;
			}
			dataIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			Log.v("error", "Server not found");
			result = null;
		}
		return result;
	}

}
