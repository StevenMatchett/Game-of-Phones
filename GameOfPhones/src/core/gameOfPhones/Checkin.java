package core.gameOfPhones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

;//.Array;

public class Checkin extends Activity {
	private static final URL URL = null;
	private JSONObject venues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
		getVenues();
		
	}

	private void getVenues() {
		LocationsNear loc = new LocationsNear();
		loc.execute(URL);
		try {
			loc.get(10000000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			JSONArray places = venues.getJSONObject("response").getJSONArray("groups")
					.getJSONObject(0).getJSONArray("items");
			
			for (int i=0; i< places.length(); i++){
				String id = (String) venues.getJSONObject("response").getJSONArray("groups")
						.getJSONObject(0).getJSONArray("items").getJSONObject(i).getJSONObject("venue").get("id");
				String name = (String) venues.getJSONObject("response").getJSONArray("groups")
						.getJSONObject(0).getJSONArray("items").getJSONObject(i).getJSONObject("venue").get("name");
				
				System.out.println("id:"+id+", name:"+name);
						
			}
			//System.out.println(venues.getJSONObject("response").getJSONArray("groups")
					//.getJSONObject(0).getJSONArray("items").getJSONObject(0).getJSONObject("venue").get("id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getGPS() {
		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = manager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double lat = location.getLatitude();
		double lon = location.getLongitude();
		return lat + "," + lon;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_checkin, menu);
		return true;
	}

	private class LocationsNear extends AsyncTask<URL, Integer, Long> {
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(Long result) {
			// showDialog("Downloaded " + result + " bytes");
		}

		protected JSONObject getVenues() {
			
			
			try {
				  URL url = new URL(
							"https://api.foursquare.com/v2/venues/explore?ll="
									+ getGPS() + "&oauth_token="
									+ user.TokenSingleton.getToken());
			        URLConnection yc = url.openConnection();
			        BufferedReader in = new BufferedReader(new InputStreamReader(
			                                    yc.getInputStream()));
			        String inputLine;
			        while ((inputLine = in.readLine()) != null) 
			        	venues = new JSONObject(inputLine);
			        in.close();

			} catch (Exception e) {
				Log.v("ERROR", e.toString());
			}

			return null;
		}

		@Override
		protected Long doInBackground(URL... params) {
			getVenues();
			return (long) 0.;
		}
	}
}
