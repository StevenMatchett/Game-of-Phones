package core.gameOfPhones;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayFriends extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout lView = new LinearLayout(this);

		TextView myText = new TextView(this);
		String friends = "";
		try {
			friends = new GetFriends().printFriends();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myText.setText(friends);

		lView.addView(myText);

		setContentView(lView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_friends, menu);
		return true;
	}

}
