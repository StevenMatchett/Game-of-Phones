package core.gameOfPhones;

import game.*;

import java.util.ArrayList;
import java.util.Collections;

import server.Communication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Lobby extends Activity {

	private game.GameData game;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lobby);
		game = GameSingleton.getGame();

		TextView playersText = (TextView) findViewById(R.id.players);
		playersText.setText("Players (" + game.getPlayers().size() + "/"
				+ game.getMaxPlayers() + ")");

		final ListView listview = (ListView) findViewById(R.id.resource_list);

		final ArrayList<Player> list = game.getPlayers();

		

		final ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);

		listview.setAdapter(adapter);
		
		
		Button joinButton = (Button) findViewById(R.id.joinGame);
		joinButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Communication join = new Communication();
				join.getResponse("action=join_game&user_id="+user.TokenSingleton.getUserID()+"&game_id="+game.getId());
	
				finish();
				

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lobby, menu);
		return true;
	}

}
