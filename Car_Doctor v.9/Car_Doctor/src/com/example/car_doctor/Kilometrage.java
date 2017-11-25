package com.example.car_doctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Kilometrage extends ActionBarActivity {
    
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String  old_kilometrage="Key_ancien_kilometrage"; 
	SharedPreferences sharedpreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kilometrage);
		final EditText EditText_kilometrage;
		EditText_kilometrage= (EditText) findViewById(R.id.editText1);
		
		//charger l'ancienne valeur du kilometrage a partir de sharedPreferences
		 sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		final int ancien_kilometrage=sharedpreferences.getInt(old_kilometrage,0);
		EditText_kilometrage.setText( String.valueOf(ancien_kilometrage));
		
		
		//gestion du bouton enregistrer
		final Button Bouton_Enregistrement_kilometrage = (Button)findViewById(R.id.enregistrement);
		Bouton_Enregistrement_kilometrage.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{

	                    public void onClick(View aView)
	                    {
	                    	//Recuperation du nouveau kilometrage et sauvegarde
	                    	int nouveau_kilometrage=0;
	                    	if(EditText_kilometrage.getText().toString().equals("")) nouveau_kilometrage=0;
	                    	else nouveau_kilometrage=Integer.parseInt(EditText_kilometrage.getText().toString());
	                		if ((nouveau_kilometrage < ancien_kilometrage))
	                		Toast.makeText(getApplicationContext(), "Vous avez saisie une valeure inférieure à l'ancien kilometrage" , 
	        						Toast.LENGTH_LONG).show(); 
	       	                else {
	                		      //mise à jour du kilometrage dans SharedPreferences
	                			  Editor editor = sharedpreferences.edit();
	               	              editor.putInt(old_kilometrage, nouveau_kilometrage);
	               	              editor.commit();
	               	              
	                			  Toast.makeText(getApplicationContext(), "Kilometrage mis à jours" , 
	        				      Toast.LENGTH_LONG).show();   
	                    	      Intent toAnotherActivity = new Intent(aView.getContext(),Demarrage.class);
	                              startActivityForResult(toAnotherActivity, 0);
	                		      }
	                		}
	    		}
	    		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kilometrage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
