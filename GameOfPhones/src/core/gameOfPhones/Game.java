package core.gameOfPhones;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONException;

import server.Communication;

import core.gameOfPhones.VenueList.Venue;

import game.GameData;
import game.GameSingleton;
import game.Lobbies;
import game.Player;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Game extends Activity {
	private GameData game;
	Player current = GameSingleton.getPlayer();
	Spinner playerSpinner;
	ListView listview;
	boolean isMyPlayer;
	private Button buyConquestPoints;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		listview = (ListView) findViewById(R.id.resource_list);
		game = GameSingleton.getGame();
		TextView gameTitle = (TextView) findViewById(R.id.GameName);
		gameTitle.setText(game.getGameName());
		setResourceList();
		setButtons();

	}

	private void setResourceList() {
		ArrayList<String> list = new ArrayList<String>();

		list.addAll(getResourceList());
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
		setPlayerSpinner();
	}

	private void setPlayerSpinner() {
		ArrayAdapter<Player> playerList = new ArrayAdapter<Player>(this,
				android.R.layout.simple_spinner_item);
		playerList
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		playerList = fillPlayersList(playerList);

		playerSpinner = (Spinner) findViewById(R.id.players_view);

		playerSpinner.setAdapter(playerList);

		playerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// System.out.println((Player) arg0.getSelectedItem());
				GameSingleton.setPlayer((Player) arg0.getSelectedItem());
				current = (Player) arg0.getSelectedItem();
				ArrayList<String> list = new ArrayList<String>();

				list.addAll(getResourceList());
				ArrayAdapter adapter = new ArrayAdapter(Game.this,
						android.R.layout.simple_list_item_1, list);
				listview.setAdapter(adapter);
				setButtons();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

	}

	private ArrayAdapter<Player> fillPlayersList(ArrayAdapter<Player> playerList) {

		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (game.getPlayers().get(i).getPlayer_id()
					.equals(user.TokenSingleton.getUserID())) {
				playerList.insert(game.getPlayers().get(i), 0);
			} else {
				playerList.add(game.getPlayers().get(i));
			}

		}
		return playerList;
	}

	public void setButtons() {
		Button buildings = (Button) findViewById(R.id.See_Buildings);
		// setFreeFont(toMenuButton);
		buildings.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				GameSingleton.setPlayer(current);
				Intent intent = new Intent(Game.this, Buildings.class);
				startActivity(intent);

			}
		});
		buyConquestPoints = (Button) findViewById(R.id.BuyConquestPoint);
		if (current.getPlayer_id().equals(user.TokenSingleton.getUserID())) {
			buyConquestPoints.setVisibility(View.VISIBLE);
			// setFreeFont(toMenuButton);
			buyConquestPoints.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					GameSingleton.setGame(game);
					Intent intent = new Intent(Game.this, Market.class);
					startActivityForResult(intent,1);

				}
			});
		} else {
			buyConquestPoints.setVisibility(View.GONE);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		GameSingleton.setPlayer(null);
	}

	// buildings
	private ArrayList<String> getResourceList() {
		System.out.println(current);
		if (current == null) {
			for (int i = 0; i < game.getPlayers().size(); i++) {
				if (game.getPlayers().get(i).getPlayer_id()
						.equals(user.TokenSingleton.getUserID())) {
					current = game.getPlayers().get(i);
				}

			}
		}

		return current.getResources();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
