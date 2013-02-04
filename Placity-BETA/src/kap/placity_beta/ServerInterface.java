package kap.placity_beta;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;

public class ServerInterface {

	public static final String SERVER_URL = "http://www.blendboom.lima-city.de/APP2/";
	//public static final String SERVER_URL = "http://www.questioneditor-3.moontec.de/";
	
	
	//Download filelist
	public static String getFileList(String id) {
		String data = "?id=" + URLEncoder.encode(id);
		data += "&command=" + URLEncoder.encode("getFileList");
		String result = "";
		try {
			result = executeHttpRequest(data);
		}
		
		catch (IOException e) {
			Log.v("error",e.getMessage());
			Log.v("error", "Stream not read");
			result = "";
		}
		return result;
		
	}
	
	 
	//Download requested file
	public static byte[] getGameFile(String id, String filename) {
		
		byte[] result;
		try {
			Log.v("filename | GetGameFile", filename);

			URL url = new URL(filename);
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
//	public static String executeHttpRequest(String data) {
//		String result = "";
//		try {
//			URL url = new URL(SERVER_URL + "server.php");
//			URLConnection connection = url.openConnection();
//			
//			connection.setDoInput(true);
//			connection.setDoOutput(true);
//			//connection.setUseCaches(true);
//			//connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			
//			DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
//			dataOut.writeBytes(data);
//			dataOut.flush();
//			dataOut.close();
//			
//			//DataInputStream dataIn = new DataInputStream(connection.getInputStream());
//			//String inputLine;
//			//while ((inputLine = dataIn.readLine()) != null) {
//				//result += inputLine;
//			//};
//			//dataIn.close();
//			
//			BufferedReader reader = new BufferedReader (new InputStreamReader(connection.getInputStream()));
//			StringBuilder string = new StringBuilder();
//			String inputLine = "";
//			while (reader.ready()){
//				inputLine = reader.readLine();
//				string.append(inputLine);
//			};
//			result = string.toString();
//			
//		} catch (IOException e) {
//			Log.v("error",e.getMessage());
//			Log.v("error", "Server not found");
//			result = null;
//		}
//		return result;
//	}
	
	public static String executeHttpRequest(String data) throws IOException {
		String result = "";
		InputStream  is = null;
		
		try {		
			URL url = new URL(SERVER_URL + "server.php" + data);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
	        con.setDoInput(true);
	        
	        con.connect();
	        
			is = con.getInputStream();
			
			result = readInputToString(is);
			
		} 
		
		finally {
				is.close();
			}
		
		return result;
	}

	public static String readInputToString (InputStream stream) throws IOException{
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[500];
		reader.read(buffer);
		
		return new String(buffer);
	}
}
