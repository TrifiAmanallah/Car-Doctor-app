package com.example.car_doctor;

import android.support.v7.app.ActionBarActivity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Demarrage extends ActionBarActivity {

	//variables des SharedPreferences
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String old_kilometrage="Key_ancien_kilometrage";
	public static final String test_notification="key_notification";
	public static final String test_reset  ="test_reset";
	SharedPreferences sharedpreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demarrage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.demarrage, menu);		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		
		//Récuperation des boutons
		final ImageButton Bouton_kilometrage = (ImageButton)findViewById(R.id.bt2);
		final ImageButton Bouton_formulaire  =(ImageButton)findViewById(R.id.bt3);
		final ImageButton Bouton_diagnostique  =(ImageButton)findViewById(R.id.bouton1);
		final ImageButton Bouton_notification  =(ImageButton)findViewById(R.id.bt4);
		final ImageButton Bouton_reset =(ImageButton)findViewById(R.id.bt5);
		final ImageButton Bouton_exit =(ImageButton)findViewById(R.id.bt6);
		final ImageButton Bouton_achat =(ImageButton)findViewById(R.id.bt7);

        //Gestion des boutons
		Bouton_diagnostique.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{

	                    public void onClick(View aView)
	                    {
	                           if (!(sharedpreferences.getInt(old_kilometrage,0)==0))
	                    	   {Toast.makeText(getApplicationContext(), "Diagnostique" ,Toast.LENGTH_SHORT).show(); 
	                           Intent toAnotherActivity = new Intent(aView.getContext(),Diagnostique.class);
	                           startActivityForResult(toAnotherActivity, 0);}
	                           else    Toast.makeText(getApplicationContext(), "veuillez d'abord saisir vos données" ,Toast.LENGTH_SHORT).show();  
	                    }
	    		}
	    		);
		Bouton_kilometrage.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{
	                    public void onClick(View aView)
	                    {
	                          Toast.makeText(getApplicationContext(), "Kilometrage" ,Toast.LENGTH_SHORT).show(); 
	                           Intent toAnotherActivity = new Intent(aView.getContext(),Kilometrage.class);
	                           startActivityForResult(toAnotherActivity, 0);
	                    }
	    		}
	    		);
		Bouton_formulaire.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{
	                    public void onClick(View aView)
	                    {
	                          Toast.makeText(getApplicationContext(), "Formulaire" ,Toast.LENGTH_SHORT).show(); 
	                           Intent toAnotherActivity = new Intent(aView.getContext(),Formulaire.class);
	                           startActivityForResult(toAnotherActivity, 0);
	                    }
	    		}
	    		);
		Bouton_notification.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{

	                    public void onClick(View aView)
	                    {
	                           if (sharedpreferences.getBoolean(test_notification,true))
	                    	     {Toast.makeText(getApplicationContext(), "Notifications Désactivées" ,Toast.LENGTH_SHORT).show(); 
	                    	      Editor editor = sharedpreferences.edit();
	               	              editor.putBoolean(test_notification,false);
	               	              editor.commit();
	               	           // Récupération du Notification Manager
	               		        NotificationManager manager = (NotificationManager)
	               		        getSystemService(Context.NOTIFICATION_SERVICE);
	               		        manager.cancel(0);}
	                        else {Toast.makeText(getApplicationContext(), "Notifications Activées" ,Toast.LENGTH_SHORT).show();  
	                              Editor editor = sharedpreferences.edit();
	               	              editor.putBoolean(test_notification,true);
	               	              editor.commit();}
	                    }
	    		}
	    		);
		Bouton_reset.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{
	                    public void onClick(View aView)
	                    {
	                          Toast.makeText(getApplicationContext(), "Reset avec succès" ,Toast.LENGTH_SHORT).show(); 
	                          Editor editor = sharedpreferences.edit();
               	              editor.putBoolean(test_reset,true);
               	              editor.commit();
	                          Intent toAnotherActivity = new Intent(aView.getContext(),MainActivity.class);
	                          startActivityForResult(toAnotherActivity, 0);
	                    }
	    		}
	    		);
		Bouton_exit.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{
	                    public void onClick(View aView)
	                    {Intent intent = new Intent(Intent.ACTION_MAIN);
	                    intent.addCategory(Intent.CATEGORY_HOME);
	                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                    startActivity(intent);
	                    }
	    		}
	    		);
		Bouton_achat.setOnClickListener(			 
	    		new View.OnClickListener()

	    		{
	                    public void onClick(View aView)
	                    {
	                    	Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://tunisiecasse.com"));
	                    	startActivity(intent);
	                    }
	    		}
	    		);
		
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
