package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;


public class GameData implements Serializable{
	private Integer id;
	private String gameName;
	private int maxPlayers;
	private String mapName;
	private int conquestPoints;
	private ArrayList<Player> players = new ArrayList<Player>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public int getConquestPoints() {
		return conquestPoints;
	}

	public void setConquestPoints(int conquestPoints) {
		this.conquestPoints = conquestPoints;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public GameData(Integer id, String gameName, int maxPlayers, String mapName,
			int conquestPoints, JSONArray players) {
		this.id = id;
		this.gameName = gameName;
		this.maxPlayers = maxPlayers;
		this.mapName = mapName;
		this.conquestPoints = conquestPoints;

		setPlayers(players);

	}
	
	public String toString(){
		return gameName;
	}
	
	

	private void setPlayers(JSONArray players) {
		for (int x = 0; x < players.length(); x++) {
			try {
				
				String player_id        = players.getJSONObject(x).getString("player_id");
				String player_name      = players.getJSONObject(x).getString("player_name");
				Integer conquest_points = players.getJSONObject(x).getInt("conquest_points");
				Integer factory_level   = players.getJSONObject(x).getInt("factory_level");
				Integer studio_level    = players.getJSONObject(x).getInt("studio_level");
				Integer temple_level    = players.getJSONObject(x).getInt("temple_level");
				Integer lab_level       = players.getJSONObject(x).getInt("lab_level");
				Integer agency_level    = players.getJSONObject(x).getInt("agency_level");
				Integer artifacts       = players.getJSONObject(x).getInt("artifacts");
				Integer blueprints      = players.getJSONObject(x).getInt("blueprints");
				Integer fuel            = players.getJSONObject(x).getInt("fuel");
				Integer material        = players.getJSONObject(x).getInt("material");
				Integer luxuries        = players.getJSONObject(x).getInt("luxuries");
				Integer produce         = players.getJSONObject(x).getInt("produce");

				this.players.add(new Player(player_id, player_name,
						conquest_points, factory_level, studio_level,
						temple_level, lab_level, agency_level, artifacts,
						blueprints,fuel,material,luxuries,produce));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}



}
