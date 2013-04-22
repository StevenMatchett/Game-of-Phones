package core.gameOfPhones;

import server.Communication;
import android.app.Activity;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewGame extends Activity {
	Spinner s2;
	Spinner s;
	Spinner s1;
	EditText t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);

		t = (EditText) findViewById(R.id.lobby_name);
		ArrayAdapter<CharSequence> mapList = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		mapList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mapList.add("Please Select a Map");
		mapList.add("Hydraphoria");
		mapList.add("Warlock");
		mapList.add("Adrenaline");
		mapList.add("Nazareth");
		s = (Spinner) findViewById(R.id.map_type);
		s.setAdapter(mapList);

		ArrayAdapter<CharSequence> numberOfPlayers = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		numberOfPlayers
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		numberOfPlayers.add("Players");
		numberOfPlayers.add("2");
		numberOfPlayers.add("3");
		numberOfPlayers.add("4");
		numberOfPlayers.add("5");
		numberOfPlayers.add("6");
		s1 = (Spinner) findViewById(R.id.numberOfPlayers);
		s1.setAdapter(numberOfPlayers);

		ArrayAdapter<CharSequence> numberOfPoints = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		numberOfPoints
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		numberOfPoints.add("Points");
		numberOfPoints.add("5");
		numberOfPoints.add("6");
		numberOfPoints.add("7");
		numberOfPoints.add("8");
		numberOfPoints.add("9");
		numberOfPoints.add("10");
		s2 = (Spinner) findViewById(R.id.numberOfVictoryPoints);
		s2.setAdapter(numberOfPoints);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		return true;
	}

	public void create_game(View view) {
		String players = s1.getSelectedItem().toString();
		String map = s.getSelectedItem().toString();
		String points = s2.getSelectedItem().toString();
		Communication new_game_talk = new Communication();
		new_game_talk.getResponse("action=new_game&user_id="+user.TokenSingleton.getUserID()+"&lobby_name="
				+ t.getText() + "&map=" + map + "&points=" + points
				+ "&players=" + players);
		System.out.println("action=new_game&user_id="+user.TokenSingleton.getUserID()+"&lobby_name="
				+ t.getText() + "&map=" + map + "&points=" + points
				+ "&players=" + players);
		finish();
	}

}
