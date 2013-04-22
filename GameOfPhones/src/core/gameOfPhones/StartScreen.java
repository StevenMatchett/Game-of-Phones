package core.gameOfPhones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartScreen extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		Button toMenuButton = (Button)findViewById(R.id.toMenu);
	     //setFreeFont(toMenuButton);
	     toMenuButton.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	System.out.println("asdfasdfasdf");
	            	 Intent intent = new Intent(StartScreen.this, FoursquareOauthWebView.class);
		                startActivity(intent);

	            }
	     });
		
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_start_screen, menu);
		return true;
	}

	public void switchToMenuScreen(View veiw){
	//Code to switch to other screen
        Intent intent = new Intent(StartScreen.this, FoursquareOauthWebView.class);
        startActivity(intent);
	}
	
}
