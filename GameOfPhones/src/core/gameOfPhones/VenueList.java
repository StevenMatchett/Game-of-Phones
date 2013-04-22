package core.gameOfPhones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import server.Communication;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VenueList extends Activity {

	private static final URL URL = null;
	private JSONObject venues;
	private JSONObject checkin_data;
	private String theCheckinVenueId;

	private ArrayList<ArrayList<String>> getVenues() {
		LocationsNear loc = new LocationsNear();

		loc.execute(URL);
		try {
			loc.get(10000000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<ArrayList<String>> locat = new ArrayList<ArrayList<String>>();
		try {
			
			JSONArray places = venues.getJSONObject("response")
					.getJSONArray("groups").getJSONObject(0)
					.getJSONArray("items");

			for (int i = 0; i < places.length(); i++) {
				ArrayList<String> temp = new ArrayList<String>();
				System.out.println(venues);
				String id = (String) venues.getJSONObject("response")
						.getJSONArray("groups").getJSONObject(0)
						.getJSONArray("items").getJSONObject(i)
						.getJSONObject("venue").get("id");
				String name = (String) venues.getJSONObject("response")
						.getJSONArray("groups").getJSONObject(0)
						.getJSONArray("items").getJSONObject(i)
						.getJSONObject("venue").get("name");

				temp.add(id);
				temp.add(name);
				locat.add(temp);

			}

			return locat;

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue_list);
		
		final ListView listview = (ListView) findViewById(R.id.resource_list);

		ArrayList<ArrayList<String>> theVenues = getVenues();
		
		// final ArrayList<String> list = new ArrayList<String>();
		final ArrayList<Venue> list = new ArrayList<Venue>();

		for (int i = 0; i < theVenues.size(); i++) {
			Venue test = new Venue();
			test.setVenueId(theVenues.get(i).get(0));
			test.setVenueName(theVenues.get(i).get(1));
			list.add(test);
		}

		final ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				final Venue item = (Venue) parent.getItemAtPosition(position);
				item.checkin();

			}

		});

	}

	public class Venue {
		private String venueId;
		private String venueName;

		public String getVenueId() {
			return venueId;
		}

		public void checkin() {

			theCheckinVenueId = venueId;

			CheckIntoVenue loc = new CheckIntoVenue();
			loc.execute(URL);

			try {
				loc.get(10000000, TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// checkin_data is the var that contains all checkin info
			String send;
			try {
				send = (String) ((JSONObject) checkin_data
						.getJSONObject("response").getJSONObject("checkin")
						.getJSONObject("venue").getJSONArray("categories")
						.get(0)).getJSONArray("parents").get(0);
				Communication com = new Communication();
				
				com.getResponse("action=checkin&user_id="+user.TokenSingleton.getUserID()+"&location_category="+send);
				//action=checkin&user_id=(.+)&location_category=(.+)
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			finish();
		}

		public void setVenueId(String venueId) {
			this.venueId = venueId;
		}

		public String getVenueName() {
			return venueName;
		}

		public void setVenueName(String venueName) {
			this.venueName = venueName;
		}

		public String toString() {
			return venueName;
		}
	}

	public void logMyListItem(Object value) {
		Log.i(getLocalClassName(), value.toString());
	}

	private class StableArrayAdapter extends ArrayAdapter<Venue> {

		HashMap<Venue, Integer> mIdMap = new HashMap<Venue, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				ArrayList<Venue> list) {
			super(context, textViewResourceId, list);
			for (int i = 0; i < list.size(); ++i) {
				mIdMap.put(list.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			Venue item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
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

				StringWriter sw = new StringWriter();
				new Throwable("").printStackTrace(new PrintWriter(sw));
				String stackTrace = sw.toString();
				System.out.println(stackTrace);
			}

			return null;
		}

		@Override
		protected Long doInBackground(URL... params) {
			getVenues();
			return (long) 0.;
		}
	}

	private class CheckIntoVenue extends AsyncTask<URL, Integer, Long> {
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(Long result) {
			// showDialog("Downloaded " + result + " bytes");
		}

		protected JSONObject getVenues() {

			try {
				// https://api.foursquare.com/v2/checkins/add

				HttpURLConnection connection = null;
				PrintWriter outWriter = null;
				BufferedReader serverResponse = null;
				StringBuffer buff = new StringBuffer();
				try {
					// OPEN CONNECTION
					connection = (HttpURLConnection) new URL(
							"https://api.foursquare.com/v2/checkins/add?")
							.openConnection();

					// SET REQUEST INFO
					connection.setRequestMethod("POST");
					connection.setDoOutput(true);

					// CREATE A WRITER FOR OUTPUT
					outWriter = new PrintWriter(connection.getOutputStream());

					// PARAMETERS
					buff.append("venueId=");
					buff.append(URLEncoder.encode(theCheckinVenueId, "UTF-8"));
					buff.append("&");
					buff.append("&oauth_token=");
					buff.append(URLEncoder.encode(
							user.TokenSingleton.getToken(), "UTF-8"));

					// SEND PARAMETERS
					outWriter.println(buff.toString());

					outWriter.flush();
					outWriter.close();

					// RESPONSE STREAM
					serverResponse = new BufferedReader(new InputStreamReader(
							connection.getInputStream()));

					// READ THE RESPOSNE
					String line;
					while ((line = serverResponse.readLine()) != null) {
						checkin_data = new JSONObject(line);
					}
				} catch (MalformedURLException mue) {
					mue.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
					if (connection != null)
						connection.disconnect();

					if (serverResponse != null) {
						try {
							serverResponse.close();
						} catch (Exception ex) {
						}
					}
				}

			} catch (Exception e) {
				Log.v("ERROR", e.toString());
			}

			return checkin_data;
		}

		@Override
		protected Long doInBackground(URL... params) {
			getVenues();
			return (long) 0.;
		}
	}
}
