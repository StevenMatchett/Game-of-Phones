package core.gameOfPhones;

import java.util.ArrayList;
import java.util.Collection;

import game.GameData;
import game.GameSingleton;
import game.Player;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Game extends Activity {
	private GameData game;
	Player current;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		final ListView listview = (ListView) findViewById(R.id.resource_list);
		game = GameSingleton.getGame();
		TextView gameTitle = (TextView) findViewById(R.id.GameName);
		gameTitle.setText(game.getGameName());
		ArrayList<String> list = new ArrayList<String>();

		list.addAll(getResourceList());
		final ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		setButtons();

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

	}

	// buildings
	private ArrayList<String> getResourceList() {
		current = null;
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (game.getPlayers().get(i).getPlayer_id()
					.equals(user.TokenSingleton.getUserID())) {
				current = game.getPlayers().get(i);
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
