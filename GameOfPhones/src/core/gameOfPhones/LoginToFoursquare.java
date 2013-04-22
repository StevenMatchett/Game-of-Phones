package core.gameOfPhones;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginToFoursquare extends Activity {


	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_login_to_foursquare);
	        
	        Button btn = (Button)findViewById(R.id.button);
	        btn.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Intent intent = new Intent(LoginToFoursquare.this, FoursquareOauthWebView.class);
	                startActivity(intent);
	            }
	        });
	    }

}
