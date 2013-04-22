package game;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

public class Lobbies {

	private static ArrayList<GameData> lobbies = new ArrayList<GameData>();
	private static Lobbies lobbyInstance;

	private Lobbies() {
	}

	public static ArrayList<GameData> getLobbies() {
		return lobbies;
	}

	public static void addGame(GameData game) {
		lobbies.add(game);
	}

	public static void addGames(JSONArray games) {
		lobbies = new ArrayList<GameData>();
		for (int i = 0; i < games.length(); i++) {
			try {
				int game_id = games.getJSONObject(i).getInt("game_id");
				String name = games.getJSONObject(i).getString("name");
				int maxplayers = games.getJSONObject(i).getInt("maxplayers");
				String map = games.getJSONObject(i).getString("map");
				int conquest_points = games.getJSONObject(i).getInt("conquest_points");
				JSONArray players = games.getJSONObject(i).getJSONArray("players");
				lobbies.add(new GameData(game_id, name, maxplayers, map,
						conquest_points, players));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	public static Lobbies getLobby() {
		if (lobbyInstance == null)
			lobbyInstance = new Lobbies();
		return lobbyInstance;
	}

}
