package user;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenSingleton {
	private static String tokenValue;
	private static String userID;
	private static String userName;
	private static TokenSingleton tokenInstance;

	private TokenSingleton() {

	}

	public static void setToken(String str) {
		tokenValue = str;
	}

	public static String getToken() {
		return tokenValue;
	}

	public static String getUserName() {
		return userName;
	}

	public static String getUserID() {
		return userID;
	}

	public static TokenSingleton getTokenInstance() {
		if (tokenInstance == null)
			tokenInstance = new TokenSingleton();
		return tokenInstance;
	}

	public static void setUserInfo(JSONObject fourSquareUser) {
		try {

			userID = (String) fourSquareUser.getJSONObject("response")
					.getJSONObject("user").get("id");

			String firstName = (String) fourSquareUser
					.getJSONObject("response").getJSONObject("user")
					.get("firstName");
			String lastName = (String) fourSquareUser.getJSONObject("response")
					.getJSONObject("user").get("lastName");

			userName = firstName + "_" + lastName;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
