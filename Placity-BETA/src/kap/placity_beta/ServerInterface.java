package kap.placity_beta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ServerInterface {

	public static final String SERVER_URL = "http://www.blendboom.lima-city.de/APP2/server.php";
	//public static final String SERVER_URL = "http://www.kabt.moontec.de";
	
	
	//Download filelist
	public static String getFileList(String id) {
		String data = "command=" + URLEncoder.encode("getFileList");
		data += "&id=" + URLEncoder.encode(id);
		return executeHttpRequest(data);
	}
	
	//Download requested file
	public static String getGameFile(String id, String filename) {
		
		String data = "command=" + URLEncoder.encode("getGameFile");
		data += "&id=" + URLEncoder.encode(id);
		data += "&filename=" + URLEncoder.encode(filename);
		return executeHttpRequest(data);
	}
	
	//Http-Request
	public static String executeHttpRequest(String data) {
		String result = "";
		try {
			URL url = new URL(SERVER_URL);
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
			result = null;
		}
		return result;
	}

}
