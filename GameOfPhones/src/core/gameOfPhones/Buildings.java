package core.gameOfPhones;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Buildings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buildings);
		
		Spinner buildings;
		ArrayAdapter<CharSequence> mapList = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		mapList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mapList.add("-----------");
		mapList.add("Agency");
		mapList.add("Factory");
		mapList.add("Lab");
		mapList.add("Studio");
		mapList.add("Temple");

		buildings = (Spinner) findViewById(R.id.map_type);
		buildings.setAdapter(mapList);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buildings, menu);
		return true;
	}

}
