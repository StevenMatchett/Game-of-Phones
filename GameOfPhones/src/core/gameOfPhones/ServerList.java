package core.gameOfPhones;

import game.GameData;
import game.GameSingleton;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import server.Communication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ServerList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_list);

		final ListView listview = (ListView) findViewById(R.id.games);
		ArrayList<GameData> list = new ArrayList<GameData>();

		JSONObject games = null;
		try {
			Communication allGames = new Communication();
			games = allGames.getResponse("action=get_game_list&user_id="+user.TokenSingleton.getUserID());
			//games = new JSONObject(
					/* need to make call to the server */
				//	"{\"games\":[{\"game_id\":123,\"name\":\"this is the game name\",\"maxplayers\":2,\"map\":\"death rock\",\"conquest_points\":7,\"players\":[{\"player_id\":\"23324234\",\"player_name\":\"Steven\",\"conquest_points\":2,\"factory_level\":3,\"studio_level\":4,\"temple_level\":5,\"lab_level\":8,\"agency_level\":4,\"artifacts\":34,\"blueprints\":33,\"fuel\":800,\"material\":23,\"luxuries\":23,\"produce\":234},{\"player_id\":\"23324234\",\"player_name\":\"Adam\",\"conquest_points\":2,\"factory_level\":3,\"studio_level\":4,\"temple_level\":5,\"lab_level\":8,\"agency_level\":4,\"artifacts\":34,\"blueprints\":33,\"fuel\":800,\"material\":23,\"luxuries\":23,\"produce\":234}]}]}");
			System.out.println(games);
			game.Lobbies.addGames(games.getJSONArray("games"));
			list = game.Lobbies.getLobbies();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * for (int i = 0; i < gameData.size(); i++) { Game test = new
		 * Game(gameData
		 * .get(i).get(0),gameData.get(i).get(1),gameData.get(i).get(2));
		 * list.add(test); }
		 */
		final ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				final GameData item = (GameData) parent.getItemAtPosition(position);
				launch(item);
				finish();

			}

		});
	}

	private void launch(GameData game) {
		Intent lobby = new Intent(ServerList.this, Lobby.class);
		GameSingleton.setGame(game);
		startActivity(lobby);
	}

	private ArrayList<ArrayList<String>> getNotStartedGamesFromServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_server_list, menu);
		return true;
	}

}
