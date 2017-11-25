package com.example.car_doctor;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
 	  
	   public static final String MyPREFERENCES = "MyPrefs" ;
	   public static final String test_premiere_execution  ="test"; 
	   public static final String test_reset ="test_reset";
	   public static final String test_notification="key_notification";
	   public static final String  old_kilometrage="Key_ancien_kilometrage"; 


	   SharedPreferences sharedpreferences;
	   DatabaseHandler gestionnaire_BD = new DatabaseHandler(this);
	 
	   @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 //creation et chargement des valeurs par default de la BD lors de la premiere execution
		 
		//les valeurs par default
	      PIECES vidange =new PIECES(1,1,1,2009,0);
	      PIECES bougies =new PIECES(2,1,1,2009,0);
	      PIECES filtre_huil =new PIECES(3,1,1,2009,0);
	      PIECES filtre_air =new PIECES(4,1,1,2009,0);
	      PIECES filtre_habitacle =new PIECES(5,1,1,2009,0);
	      PIECES huil_boite_vitesse =new PIECES(6,1,1,2009,0);
	      PIECES visite_technique =new PIECES(7,1,1,2009,0);
	      PIECES assurance =new PIECES(8,1,1,2009,0);
	      PIECES courroie =new PIECES(9,1,1,2009,0);
	      PIECES disque_frein =new PIECES(10,1,1,2009,0);
	      PIECES suspention =new PIECES(11,1,1,2009,0); 
		  sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		 
		 if (sharedpreferences.getBoolean(test_premiere_execution,true)){
	      
	      //chargement des valeurs par default dans la BD
	      gestionnaire_BD.addPiece(vidange);              //ID=1
	      gestionnaire_BD.addPiece(bougies);              //ID=2
	      gestionnaire_BD.addPiece(filtre_huil);          //ID=3
	      gestionnaire_BD.addPiece(filtre_air);           //ID=4
	      gestionnaire_BD.addPiece(filtre_habitacle);     //ID=5
	      gestionnaire_BD.addPiece(huil_boite_vitesse);   //ID=6
	      gestionnaire_BD.addPiece(visite_technique);     //ID=7
	      gestionnaire_BD.addPiece(assurance);            //ID=8
	      gestionnaire_BD.addPiece(courroie );            //ID=9
	      gestionnaire_BD.addPiece(disque_frein );        //ID=10
	      gestionnaire_BD.addPiece(suspention);           //ID=11
	      //mise a jour des valeurs par default dans la BD
	      gestionnaire_BD.updatePiece(vidange);              //ID=1
	      gestionnaire_BD.updatePiece(bougies);              //ID=2
	      gestionnaire_BD.updatePiece(filtre_huil);          //ID=3
	      gestionnaire_BD.updatePiece(filtre_air);           //ID=4
	      gestionnaire_BD.updatePiece(filtre_habitacle);     //ID=5
	      gestionnaire_BD.updatePiece(huil_boite_vitesse);   //ID=6
	      gestionnaire_BD.updatePiece(visite_technique);     //ID=7
	      gestionnaire_BD.updatePiece(assurance);            //ID=8
	      gestionnaire_BD.updatePiece(courroie );            //ID=9
	      gestionnaire_BD.updatePiece(disque_frein );        //ID=10
	      gestionnaire_BD.updatePiece(suspention);           //ID=11
          //changement de la valeur du test_premiere_excution à partir de SharedPreferences
		  Editor editor = sharedpreferences.edit();
	      editor.putBoolean(test_premiere_execution, false);
	      editor.putBoolean(test_notification,true);
	      editor.putInt(old_kilometrage,0);
		  editor.commit();}
		  else if (sharedpreferences.getBoolean(test_reset,true)){
			//mise a jour des valeurs par default dans la BD
		      gestionnaire_BD.updatePiece(vidange);              //ID=1
		      gestionnaire_BD.updatePiece(bougies);              //ID=2
		      gestionnaire_BD.updatePiece(filtre_huil);          //ID=3
		      gestionnaire_BD.updatePiece(filtre_air);           //ID=4
		      gestionnaire_BD.updatePiece(filtre_habitacle);     //ID=5
		      gestionnaire_BD.updatePiece(huil_boite_vitesse);   //ID=6
		      gestionnaire_BD.updatePiece(visite_technique);     //ID=7
		      gestionnaire_BD.updatePiece(assurance);            //ID=8
		      gestionnaire_BD.updatePiece(courroie );            //ID=9
		      gestionnaire_BD.updatePiece(disque_frein );        //ID=10
		      gestionnaire_BD.updatePiece(suspention);           //ID=11
	          //changement de la valeur du test_premiere_excution à partir de SharedPreferences
			  Editor editor = sharedpreferences.edit();
		      editor.putBoolean(test_notification,true);
		      editor.putBoolean(test_reset, false);
		      editor.putInt(old_kilometrage,0);
			  editor.commit();}
			  
		  }
	  
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		 //passer à l'activité suivante aprés une attente
		int secondsDelayed = 5;
        new Handler().postDelayed(new Runnable() {
            public void run() {
            startActivity(new Intent(MainActivity.this, Demarrage.class));
            finish();
            }
    }, secondsDelayed * 1000);
	   
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
