package core.gameOfPhones;

import org.json.JSONException;
import org.json.JSONObject;

import server.Communication;
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
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class Market extends Activity {

	private Spinner artifactsSpinner;
	private Spinner blueprintsSpinner;
	private Spinner fuelSpinner;
	private Spinner materialSpinner;
	private Spinner luxuriesSpinner;
	private Spinner produceSpinner;
	private Button buyPoint;
	private Player current;
	private int total = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market);
		current = game.GameSingleton.getPlayer();

		setArtifactsSpinner();
		setBlueprintsSpinner();
		setFuelSpinner();
		setMaterialSpinner();
		setLuxuriesSpinner();
		setProduceSpinner();

	}

	private void setBuyButton() {
		buyPoint = (Button) findViewById(R.id.GetPoint);
		System.out.println(total);
		System.out.println((15 - current.getStudio_level()));
		if (total == (15 - current.getStudio_level())) {
			buyPoint.setVisibility(View.VISIBLE);
			final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						Communication upgrade = new Communication();
						// action=buy_vp&user_id=(.+)&game_id=(.+)&artifacts=(.+)&blueprints=(.+)&fuel=(.+)&materials=(.+)&luxuries=(.+)&food=(.+)
						JSONObject worked = upgrade
								.getResponse("action=buy_vp&user_id="
										+ user.TokenSingleton.getUserID()
										+ "&game_id="
										+ GameSingleton.getGame().getId()
										+ "&artifacts="
										+ artifactsSpinner.getSelectedItem()
										+ "&blueprints="
										+ blueprintsSpinner.getSelectedItem()
										+ "&fuel="
										+ fuelSpinner.getSelectedItem()
										+ "&materials="
										+ materialSpinner.getSelectedItem()
										+ "&luxuries="
										+ luxuriesSpinner.getSelectedItem()
										+ "&food="
										+ produceSpinner.getSelectedItem());
						Intent i = getIntent();
						i.putExtra("heightInfo", "finished");

						setResult(RESULT_OK, i);

						finish();

						
			
						// Stuff goes in there
						// upgrade.getResponse("")
						break;

					case DialogInterface.BUTTON_NEGATIVE:
						break;
					}
				}
			};
			buyPoint.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Communication join = new Communication();
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Market.this);
					builder.setMessage("Are you sure?")
							.setPositiveButton("Yes", dialogClickListener)
							.setNegativeButton("No", dialogClickListener)
							.show();

				}
			});

		} else
			buyPoint.setVisibility(View.GONE);
	}

	public void setArtifactsSpinner() {
		artifactsSpinner = (Spinner) findViewById(R.id.artifact_spinner);
		ArrayAdapter<Integer> resource = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		resource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resource.add(new Integer(0));
		for (int i = 1; i <= current.getArtifacts(); i++) {
			resource.add(new Integer(i));
		}

		artifactsSpinner.setAdapter(resource);
		artifactsSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						computeRunningTotal();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}

	public void setBlueprintsSpinner() {
		blueprintsSpinner = (Spinner) findViewById(R.id.Blueprint_Spinner);
		ArrayAdapter<Integer> resource = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		resource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resource.add(new Integer(0));

		for (int i = 1; i <= (int) current.getBlueprints(); i++) {
			resource.add(new Integer(i));
		}

		blueprintsSpinner.setAdapter(resource);

		blueprintsSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						computeRunningTotal();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}

	public void setFuelSpinner() {

		fuelSpinner = (Spinner) findViewById(R.id.Fuel_Spinner);
		ArrayAdapter<Integer> resource = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		resource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resource.add(new Integer(0));
		for (int i = 1; i <= current.getFuel(); i++) {
			resource.add(new Integer(i));
		}

		fuelSpinner.setAdapter(resource);
		fuelSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				computeRunningTotal();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	public void setMaterialSpinner() {

		materialSpinner = (Spinner) findViewById(R.id.Material_Spinner);
		ArrayAdapter<Integer> resource = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		resource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resource.add(new Integer(0));
		for (int i = 1; i <= current.getMaterial(); i++) {
			resource.add(new Integer(i));
		}

		materialSpinner.setAdapter(resource);
		materialSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				computeRunningTotal();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	public void setLuxuriesSpinner() {

		luxuriesSpinner = (Spinner) findViewById(R.id.Luxuries_Spinner);
		ArrayAdapter<Integer> resource = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		resource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		resource.add(new Integer(0));
		for (int i = 1; i <= current.getLuxuries(); i++) {
			resource.add(new Integer(i));
		}

		luxuriesSpinner.setAdapter(resource);
		luxuriesSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				computeRunningTotal();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	public void setProduceSpinner() {
		produceSpinner = (Spinner) findViewById(R.id.Produce_Spinner);
		ArrayAdapter<Integer> resource = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item);
		resource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resource.add(new Integer(0));
		for (int i = 1; i <= current.getProduce(); i++) {
			resource.add(new Integer(i));
		}

		produceSpinner.setAdapter(resource);
		produceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				computeRunningTotal();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.market, menu);
		return true;
	}

	private int computeRunningTotal() {
		int tempTotal = 0;
		tempTotal += (Integer) artifactsSpinner.getSelectedItem();
		tempTotal += (Integer) blueprintsSpinner.getSelectedItem();
		tempTotal += (Integer) fuelSpinner.getSelectedItem();
		tempTotal += (Integer) materialSpinner.getSelectedItem();
		tempTotal += (Integer) luxuriesSpinner.getSelectedItem();
		tempTotal += (Integer) produceSpinner.getSelectedItem();

		TextView total = (TextView) findViewById(R.id.totalResourceSpent);
		total.setText(new Integer(tempTotal).toString() + "/"
				+ (15 - current.getStudio_level()));
		this.total = tempTotal;
		setBuyButton();

		return tempTotal;
	}
}
