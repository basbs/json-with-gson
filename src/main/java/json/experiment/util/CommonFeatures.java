package json.experiment.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CommonFeatures {
    
    public static String getData(String urlString, int timeout) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-length", "0");
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);
			connection.connect();
			int status = connection.getResponseCode();
			switch (status) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				return sb.toString();
			}
		} catch (MalformedURLException ex) {
		} catch (IOException ex) {
		} finally {
			if (connection != null) {
				try {
					connection.disconnect();
				} catch (Exception ex) {
				}
			}
		}
		return "";
	}
}
