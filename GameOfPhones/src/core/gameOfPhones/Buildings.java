package core.gameOfPhones;

import org.json.JSONException;
import org.json.JSONObject;

import server.Communication;
import game.GameData;
import game.Player;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Buildings extends Activity {

	Button upgrade;
	TextView about;
	TextView buildingLevel;
	int selectedUpgrade;
	Spinner buildings;
	Player current;
	TextView viewingPlayerBuilding;
	boolean yourBuildings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buildings);

		viewingPlayerBuilding = (TextView) findViewById(R.id.player_viewing);
		upgrade = (Button) findViewById(R.id.upgrade);
		about = (TextView) findViewById(R.id.about);
		buildingLevel = (TextView) findViewById(R.id.building_level);
		reset();

		current = game.GameSingleton.getPlayer();
		viewingPlayerBuilding.setText("Currenlt Viewing Buildings:"
				+ current.getPlayerName());
		yourBuildings = current.getPlayer_id().equals(
				user.TokenSingleton.getUserID());
		upgrade.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// action=update_building&user_id=xxxx&game_id=xxxx&building_id=xxxx
				Communication upgradeBuilding = new Communication();
				JSONObject upgradeResult;
			
				
				if (selectedUpgrade == 1) {
					upgradeResult = upgradeBuilding
							.getResponse("action=update_building&user_id="
									+ user.TokenSingleton.getUserID()
									+ "&game_id="
									+ game.GameSingleton.getGame().getId()
									+ "&building_id=Agency");
					
				} else if (selectedUpgrade == 2) {
					upgradeResult = upgradeBuilding
							.getResponse("action=update_building&user_id="
									+ user.TokenSingleton.getUserID()
									+ "&game_id="
									+ game.GameSingleton.getGame().getId()
									+ "&building_id=Factory");
				} else if (selectedUpgrade == 3) {
					upgradeResult = upgradeBuilding
							.getResponse("action=update_building&user_id="
									+ user.TokenSingleton.getUserID()
									+ "&game_id="
									+ game.GameSingleton.getGame().getId()
									+ "&building_id=Lab");

				} else if (selectedUpgrade == 4) {
					upgradeResult = upgradeBuilding
							.getResponse("action=update_building&user_id="
									+ user.TokenSingleton.getUserID()
									+ "&game_id="
									+ game.GameSingleton.getGame().getId()
									+ "&building_id=Studio");

				} else {
					upgradeResult = upgradeBuilding
							.getResponse("action=update_building&user_id="
									+ user.TokenSingleton.getUserID()
									+ "&game_id="
									+ game.GameSingleton.getGame().getId()
									+ "&building_id=Temple");

				}
				

				upgradeResource(upgradeResult, selectedUpgrade);
				buildings.setSelection(0);
			}

		});

		ArrayAdapter<CharSequence> mapList = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		mapList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mapList.add("-----------");
		mapList.add("Agency");
		mapList.add("Factory");
		mapList.add("Lab");
		mapList.add("Studio");
		mapList.add("Temple");

		buildings = (Spinner) findViewById(R.id.building_spinner);
		buildings.setSelection(0);
		buildings.setAdapter(mapList);

		buildings.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				if (arg2 == 0) {
					reset();
				} else if (arg2 == 1) {
					selectedUpgrade = 1;
					drawAgency();
				} else if (arg2 == 2) {
					selectedUpgrade = 2;
					drawFactory();
				} else if (arg2 == 3) {
					selectedUpgrade = 3;
					drawLab();
				} else if (arg2 == 4) {
					selectedUpgrade = 4;
					drawStudio();
				} else if (arg2 == 5) {
					selectedUpgrade = 5;
					drawTemple();
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				reset();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.buildings, menu);
		return true;
	}

	private void upgradeResource(JSONObject upgradeResult, int selectedUpgrade) {
		try {
			String upgrade = upgradeResult.getString("upgraded");
			if (upgrade.equals("fail")) {
				Toast.makeText(
						Buildings.this,
						"You are not able to upgrade this building at this time.  Please try again when you have more resources",
						Toast.LENGTH_LONG).show();
				return;
			}

			if (selectedUpgrade == 1) {
				// selectedUpgrade = 1;
				// drawAgency();
			} else if (selectedUpgrade == 2) {
				// selectedUpgrade = 2;
				// drawFactory();
			} else if (selectedUpgrade == 3) {
				// selectedUpgrade = 3;
				// drawLab();
			} else if (selectedUpgrade == 4) {
				// selectedUpgrade = 4;
				// drawStudio();
			} else if (selectedUpgrade == 5) {
				// selectedUpgrade = 5;
				// drawTemple();
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void reset() {
		upgrade.setVisibility(View.GONE);
		about.setVisibility(View.GONE);
		buildingLevel.setVisibility(View.GONE);
	}

	private void drawTemple() {
		if (yourBuildings)
			upgrade.setVisibility(View.VISIBLE);
		about.setVisibility(View.VISIBLE);
		buildingLevel.setVisibility(View.VISIBLE);
		about.setText("This is info about what a temple is and what it can do");
		buildingLevel.setText("Temple Level is "
				+ game.GameSingleton.getPlayer().getFactory_level());

	}

	private void drawStudio() {
		if (yourBuildings)
			upgrade.setVisibility(View.VISIBLE);
		about.setVisibility(View.VISIBLE);
		buildingLevel.setVisibility(View.VISIBLE);
		about.setText("This is info about what a Studio is and what it can do");
		buildingLevel.setText("Studio Level is "
				+ game.GameSingleton.getPlayer().getStudio_level());
	}

	private void drawLab() {
		if (yourBuildings)
			upgrade.setVisibility(View.VISIBLE);
		about.setVisibility(View.VISIBLE);
		buildingLevel.setVisibility(View.VISIBLE);
		about.setText("This is info about what a Lab is and what it can do");
		buildingLevel.setText("Lab Level is "
				+ game.GameSingleton.getPlayer().getLab_level());
	}

	private void drawFactory() {
		if (yourBuildings)
			upgrade.setVisibility(View.VISIBLE);
		about.setVisibility(View.VISIBLE);
		buildingLevel.setVisibility(View.VISIBLE);
		about.setText("This is info about what a Factory is and what it can do");
		buildingLevel.setText("Factory Level is "
				+ game.GameSingleton.getPlayer().getFactory_level());
	}

	private void drawAgency() {
		if (yourBuildings)
			upgrade.setVisibility(View.VISIBLE);
		about.setVisibility(View.VISIBLE);
		buildingLevel.setVisibility(View.VISIBLE);
		about.setText("This is info about what a Agency is and what it can do");
		buildingLevel.setText("Agency Level is "
				+ game.GameSingleton.getPlayer().getAgency_level());
	}

}
