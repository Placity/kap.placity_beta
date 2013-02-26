package kap.placity_beta;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;

public class ServerInterface {

	//public static final String SERVER_URL = "http://www.blendboom.lima-city.de/APP2/";
	public static final String SERVER_URL = "http://www.questioneditor-3.moontec.de/";
	
	
	//Download filelist
	public static String getFileList(String id) throws Exception{
		String data = "?id=" + URLEncoder.encode(id);
		data += "&command=" + URLEncoder.encode("getFileList");
		String result = "";
		try {
			result = executeHttpRequest(data);
		}
		
		catch (Exception e) {
			Log.v("error",e.getMessage());
			Log.v("error", "Stream not read");
			result = "";
		}
		return result;
		
//		BufferedReader in =  null;
//		String data = null;
//		try{
//			HttpClient client = new DefaultHttpClient();
//			URI website = new URI(SERVER_URL + "server.php/?id=" + id + "&command=getFileList");
//			HttpGet request = new HttpGet();
//			request.setURI(website);
//			HttpResponse response = client.execute(request);
//			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//			StringBuffer sb = new StringBuffer("");
//			String l = "";
//			while ((l = in.readLine()) != null) {
//				sb.append(l);
//			}
//			in.close();
//			data = sb.toString();
//			return data;
//		} finally {
//			if(in != null){
//				try{
//					in.close();
//					return data;
//				}catch (Exception e){
//					e.printStackTrace();
//				}
//			}
//		}
		
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
	public static String executeHttpRequest(String data) {
		String result = "";
		try {
			URL url = new URL(SERVER_URL + "server.php");
			URLConnection con = url.openConnection();
			
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			//connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStreamWriter dataOut = new OutputStreamWriter(con.getOutputStream());
			dataOut.write(data);
			dataOut.flush();
			dataOut.close();
			
			InputStreamReader dataIn = new InputStreamReader(con.getInputStream());
			int in;
			while ((in = dataIn.read()) != -1) {
				result += (char) in;
			}
			dataIn.close();
			
//			BufferedReader reader = new BufferedReader (new InputStreamReader(connection.getInputStream()));
//			StringBuilder string = new StringBuilder();
//			String inputLine = "";
//			while (reader.ready()){
//				inputLine = reader.readLine();
//				string.append(inputLine);
//			};
//			result = string.toString();
			
		} catch (IOException e) {
			Log.v("error",e.getMessage());
			Log.v("error", "Server not found");
			result = null;
		}
		return result;
	}
	
//	public static String executeHttpRequest(String data) throws IOException {
//		String result = "";
//		InputStream  is = null;
//		
//		try {		
//			URL url = new URL(SERVER_URL + "server.php" + data);
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET");
//	        con.setDoInput(true);
//	        
//	        con.connect();
//	        
//			is = con.getInputStream();
//			
//			result = readInputToString(is);
//			
//		} 
//		
//		finally {
//				is.close();
//			}
//		
//		return result;
//	}
//
//	public static String readInputToString (InputStream stream) throws IOException{
//		Reader reader = null;
//		reader = new InputStreamReader(stream, "UTF-8");
//		char[] buffer = new char[500];
//		reader.read(buffer);
//		
//		return new String(buffer);
//	}
}
