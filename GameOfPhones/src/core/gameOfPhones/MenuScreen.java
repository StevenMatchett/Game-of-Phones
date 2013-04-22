package core.gameOfPhones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuScreen extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_screen);

		Button serverList = (Button) findViewById(R.id.serverList);
		serverList.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent server = new Intent(MenuScreen.this, ServerList.class);
				startActivity(server);

			}
		});

		Button newGame = (Button) findViewById(R.id.newGame);
		newGame.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent server = new Intent(MenuScreen.this, NewGame.class);
				startActivity(server);

			}
		});

		Button venue = (Button) findViewById(R.id.venues);
		venue.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent venueList = new Intent(MenuScreen.this, VenueList.class);
				startActivity(venueList);

			}
		});
		Button currentGames = (Button) findViewById(R.id.current_games);
		currentGames.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		
				Intent venueList = new Intent(MenuScreen.this, CurrentGames.class);
				startActivity(venueList);

			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu_screen, menu);
		return true;
	}

}
