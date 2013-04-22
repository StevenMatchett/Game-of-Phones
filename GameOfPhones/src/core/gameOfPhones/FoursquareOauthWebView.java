package core.gameOfPhones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import server.Communication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FoursquareOauthWebView extends Activity {
	private JSONObject fourSquareUser;
	private static final String TAG = "FoursquareOauthWebVeiw";

	/**
	 * Get these values after registering your oauth app at:
	 * https://foursquare.com/oauth/
	 */
	public static final String CALLBACK_URL = "https://cgutshal.com";
	public static final String CLIENT_ID = "JQ40M5ZLDW5EW5TOWWL2OJT0DZV5GUFCGIEWKNHA13X4YEPR";

	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forusquare_oauth_web_veiw);

		String url = "https://foursquare.com/oauth2/authenticate"
				+ "?client_id=" + CLIENT_ID + "&response_type=token"
				+ "&redirect_uri=" + CALLBACK_URL;
		
		WebView webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebViewClient(new WebViewClient() {
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				String fragment = "#access_token=";
				int start = url.indexOf(fragment);
				if (start > -1) {
					// You can use the accessToken for api calls now.
					String accessToken = url.substring(
							start + fragment.length(), url.length());

					Log.v(TAG, "OAuth complete, token: [" + accessToken + "].");
					user.TokenSingleton.setToken(accessToken);
					setUserID();
					user.TokenSingleton.setUserInfo(fourSquareUser);
					Communication login = new Communication();
					login.getResponse("action=login&user_id="
							+ user.TokenSingleton.getUserID() + "&user_name="
							+ user.TokenSingleton.getUserName());

					Toast.makeText(FoursquareOauthWebView.this,
							"Token: " + accessToken, Toast.LENGTH_SHORT).show();

				}
			}

			

			private void setUserID() {
				GetUserID loc = new GetUserID();
				final URL URL = null;
				loc.execute(URL);

				try {
					loc.get(10000000, TimeUnit.MILLISECONDS);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		webview.loadUrl(url);

		// Bad Process?

		// Generate User Profile

		Intent menuScreen = new Intent(this, MenuScreen.class);
		startActivity(menuScreen);
	}

	private class GetUserID extends AsyncTask<URL, Integer, Long> {
		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(Long result) {
			// showDialog("Downloaded " + result + " bytes");
		}

		protected JSONObject getVenues() {

			try {
				URL url = new URL(
						"https://api.foursquare.com/v2/users/self?oauth_token="
								+ user.TokenSingleton.getToken());
				URLConnection yc = url.openConnection();

				BufferedReader in = new BufferedReader(new InputStreamReader(
						yc.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null)
					fourSquareUser = new JSONObject(inputLine);
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

	
}
