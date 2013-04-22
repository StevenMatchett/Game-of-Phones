package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import android.os.AsyncTask;

public class Communication {
	private JSONObject response;
	private String params;

	public JSONObject getResponse(String params) {
		this.params = params;
		Communicate loc = new Communicate();
		final URL URL = null;
		loc.execute(URL);
	
		try {
			loc.get(10000000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	private class Communicate extends AsyncTask<URL, Integer, Long> {
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(Long result) {
			// showDialog("Downloaded " + result + " bytes");
		}

		protected JSONObject getjson() {
			try {
				
				Socket socket = new Socket("54.225.205.16", 6789);
				PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer.println(params + "\n");
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					response = new JSONObject(line);
				}
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected Long doInBackground(URL... params) {
			getjson();
			return (long) 0.;
		}
	}
}
