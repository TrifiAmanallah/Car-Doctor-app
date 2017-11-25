package com.example.car_doctor;


import java.util.ArrayList;
import java.util.Calendar;

import android.app.ExpandableListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Diagnostique extends  ExpandableListActivity
{
	public int ID_NOTIFICATION = 0;
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String  old_kilometrage="Key_ancien_kilometrage";
	public static final String test_notification="key_notification";

	SharedPreferences sharedpreferences;
	
	private ArrayList<Liste_parent> parents;
	DatabaseHandler gestionnaire_BD = new DatabaseHandler(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Resources res = this.getResources();
	    Drawable devider = res.getDrawable(R.drawable.line);
        //variables pour la notification
		boolean test_notification_yellow=false;
		boolean test_notification_red=false;

	    
//Recuperation du dernier kilometrage sauvegardé dans sharedPreferences	
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	    final int kilometrage=sharedpreferences.getInt(old_kilometrage,0);	   


//Recuperation des anciennes valeurs a partir de la BD
		  final PIECES vidange =gestionnaire_BD.getPiece(1);
		  final PIECES bougies =gestionnaire_BD.getPiece(2);
		  final PIECES filtre_huil =gestionnaire_BD.getPiece(3);
		  final PIECES filtre_air =gestionnaire_BD.getPiece(4);
		  final PIECES filtre_habitacle =gestionnaire_BD.getPiece(5);
		  final PIECES huil_boite_vitesse =gestionnaire_BD.getPiece(6);
		  final PIECES visite_technique =gestionnaire_BD.getPiece(7);
		  final PIECES assurance =gestionnaire_BD.getPiece(8);
		  final PIECES courroie =gestionnaire_BD.getPiece(9);
		  final PIECES disque_frein =gestionnaire_BD.getPiece(10);
		  final PIECES suspension =gestionnaire_BD.getPiece(11);
	      //les constantes de comparaison
		  final int km_max_vidange=10000;
		  final int km_max_bougies= 30000;
		  final int km_max_filtre_huil= 10000;
		  final int km_max_filtre_air= 20000;
		  final int km_max_huil_boite_vitesse= 30000;
		  final int km_max_disque_frein= 30000; 
		  final int km_max_suspension= 20000; 
		  //date actuelle
		  Time aujourdhui = new Time(Time.getCurrentTimezone());
		  aujourdhui.setToNow();
		  Calendar date_aujourdhui=Calendar.getInstance();
		  date_aujourdhui.set(aujourdhui.year,aujourdhui.month,aujourdhui.monthDay); 
		  
//calcul des kilometrages restants
		  final int km_restant_vidange= kilometrage-vidange.getKilometrage();
		  final int km_restant_bougies= kilometrage-bougies.getKilometrage();
		  final int km_restant_filtre_huil = kilometrage-filtre_huil .getKilometrage();
		  final int km_restant_filtre_air= kilometrage-filtre_air.getKilometrage();
		  final int km_restant_huil_boite_vitesse= kilometrage-huil_boite_vitesse.getKilometrage();
		  final int km_restant_disque_frein = kilometrage-disque_frein.getKilometrage();
		  final int km_restant_suspension= kilometrage-suspension.getKilometrage();
//calcul de dates restantes 
		 
		  //convertion des dates en type Calendar pour faciliter le calcul
		  Calendar date_vidange=Calendar.getInstance();
		  date_vidange.set(vidange.getAnnee(),vidange.getMois(),vidange.getJour());
		  
		  Calendar date_bougies=Calendar.getInstance();
		  date_bougies.set(bougies.getAnnee(),bougies.getMois(),bougies.getJour());
		 
		  Calendar date_filtre_huil=Calendar.getInstance();
		  date_filtre_huil.set(filtre_huil.getAnnee(),filtre_huil.getMois(),filtre_huil.getJour());
		  
		  Calendar date_filtre_air=Calendar.getInstance();
		  date_filtre_air.set(filtre_air.getAnnee(),filtre_air.getMois(),filtre_air.getJour());
		  
		  Calendar date_filtre_habitacle=Calendar.getInstance();
		  date_filtre_habitacle.set(filtre_habitacle.getAnnee(),filtre_habitacle.getMois(),filtre_habitacle.getJour());
		  
		  Calendar date_filtre_huil_boite_vitesse=Calendar.getInstance();
		  date_filtre_huil_boite_vitesse.set(huil_boite_vitesse.getAnnee(),huil_boite_vitesse.getMois(),huil_boite_vitesse.getJour());
		  
		  Calendar date_filtre_visite_technique=Calendar.getInstance();
		  date_filtre_visite_technique.set(visite_technique.getAnnee(),visite_technique.getMois(),visite_technique.getJour());
		  
		  Calendar date_assurance=Calendar.getInstance();
		  date_assurance.set(assurance.getAnnee(),assurance.getMois(),assurance.getJour());
		  
		  Calendar date_courroie=Calendar.getInstance();
		  date_courroie.set(courroie.getAnnee(),courroie.getMois(),courroie.getJour());
		  
		  Calendar date_disque_frein=Calendar.getInstance();
		  date_disque_frein.set(disque_frein.getAnnee(),disque_frein.getMois(),disque_frein.getJour());
		  
		  Calendar date_suspension=Calendar.getInstance();
		  date_suspension.set(suspension.getAnnee(),suspension.getMois(),suspension.getJour());
		  
		 
		  //calcule du nombre de jours restants	  
	      long jours_restant_vidange=(date_aujourdhui.getTimeInMillis()-date_vidange.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_bougies=(date_aujourdhui.getTimeInMillis()-date_bougies.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_filtre_huil=(date_aujourdhui.getTimeInMillis()-date_filtre_huil.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_filtre_air=(date_aujourdhui.getTimeInMillis()-date_filtre_air.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_filtre_habitacle=(date_aujourdhui.getTimeInMillis()-date_filtre_huil.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_huil_boite_vitesse=(date_aujourdhui.getTimeInMillis()-date_filtre_huil.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_visite_technique=(date_aujourdhui.getTimeInMillis()-date_filtre_huil.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_assurance=(date_aujourdhui.getTimeInMillis()-date_assurance.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_courroie=(date_aujourdhui.getTimeInMillis()-date_courroie.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_disque_frein=(date_aujourdhui.getTimeInMillis()-date_disque_frein.getTimeInMillis())/(24 * 60 * 60 * 1000);
	      long jours_restant_suspension=(date_aujourdhui.getTimeInMillis()-date_suspension.getTimeInMillis())/(24 * 60 * 60 * 1000);

		 	  
// parametres de la liste extensible
		getExpandableListView().setGroupIndicator(null);
		getExpandableListView().setDivider(devider);
		getExpandableListView().setChildDivider(devider);
		getExpandableListView().setDividerHeight(1);
		registerForContextMenu(getExpandableListView());
		
	   
//creation et affichage d'une liste extensible
				// Creation de la liste des parent
				final ArrayList<Liste_parent> list = new ArrayList<Liste_parent>();
				for (int i = 1; i < 14; i++)
				{
					//Creation des objets de classe parent
					final Liste_parent parent = new Liste_parent();
					
					  // saisie des valeurs des parents
					      if(i==1){
					    	  parent.setName("" + i);
					    	  parent.setText1("Vidange");
					    	  parent.setText2("");
					    	  parent.setChildren(new ArrayList<Liste_enfant>());
					    	  
					    	// creation des objets de classe enfant 
					    	  final Liste_enfant enfant = new Liste_enfant();
					    	  enfant.setName("" + i);
					    	  if((km_restant_vidange <= km_max_vidange)&&(jours_restant_vidange <= 365))
					    	  {enfant.setText1("Il reste "+(365-jours_restant_vidange)+" jours ou "+ (km_max_vidange-km_restant_vidange) +" KM avant la prochaine maintenance");
					    	         if(((365-jours_restant_vidange)<31)||(km_max_vidange-km_restant_vidange)<50) parent.setEtat(1);
					    	         else parent.setEtat(0);}
					    	  else if (km_restant_vidange>km_max_vidange)
					    	  {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (km_restant_vidange-km_max_vidange) +" KM");
					    	  parent.setEtat(2); 
					    	  }
					    	  else if (jours_restant_vidange>365) 
					    	  {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_vidange-365) +" Jours");
					    	  parent.setEtat(2);
					    	  }
					    	  //associer l'enfant au parent
							  parent.getChildren().add(enfant);
					        }
				    	 else if(i==2){
				    		   parent.setName("" + i);
						       parent.setText1("Bougies d'allumage");
						       parent.setText2("");
						       parent.setChildren(new ArrayList<Liste_enfant>());
						       
						       final Liste_enfant enfant = new Liste_enfant();
						       enfant.setName("" + i);
						       if((km_restant_bougies <= km_max_bougies)&&(jours_restant_bougies<=2*365))
							   {enfant.setText1("Il reste "+(2*365-jours_restant_bougies)+" jours ou "+ (km_max_bougies-km_restant_bougies) +" KM avant avant la prochaine maintenance");
						                if(((2*365-jours_restant_bougies)<31)||(km_max_bougies-km_restant_bougies)<50) parent.setEtat(1);
				    	                else parent.setEtat(0);}
						       else if (km_restant_bougies>km_max_bougies) 
							   {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (km_restant_bougies-km_max_bougies) +" KM");
							   parent.setEtat(2);
							   }
							   else if (jours_restant_vidange>2*365)
							   {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_bougies-2*365) +" Jours");
							   parent.setEtat(2);
							   } 
							   //associer l'enfant au parent
			     			   parent.getChildren().add(enfant);
				        	 }
				    	 else if(i==3){
				    		   parent.setName("" + i);
						       parent.setText1("Filtre à huile ");
						       parent.setText2("");
						       parent.setChildren(new ArrayList<Liste_enfant>());
						       
						       final Liste_enfant enfant = new Liste_enfant();
						       enfant.setName("" + i);
						       if((km_restant_filtre_huil <= km_max_filtre_huil)&&(jours_restant_filtre_huil <= 365))
							   {enfant.setText1("Il reste "+(365-jours_restant_filtre_huil)+" jours ou "+ (km_max_filtre_huil-km_restant_filtre_huil) +" KM avant la prochaine maintenance");
							              if(((365-jours_restant_filtre_huil)<31)||(km_max_filtre_huil-km_restant_filtre_huil)<50) parent.setEtat(1);
		    	                          else parent.setEtat(0);} 	  
							   else if (km_restant_filtre_huil>km_max_filtre_huil)
							   {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (km_restant_filtre_huil-km_max_filtre_huil) +" KM");
							   parent.setEtat(2);
							   }
							   else if (jours_restant_filtre_huil>365)
							   {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_filtre_huil-365) +" Jours");
							   parent.setEtat(2);
							   }
						   	   //associer l'enfant au parent
			    			   parent.getChildren().add(enfant);
				    	   }
						  else if(i==4){
						    	parent.setName("" + i);
								parent.setText1("Filtre à air ");
							    parent.setText2("");
								parent.setChildren(new ArrayList<Liste_enfant>());
								       
						        final Liste_enfant enfant = new Liste_enfant();
						        enfant.setName("" + i);
							    if((km_restant_filtre_air <= km_max_filtre_air)&&(jours_restant_filtre_air <= 365))
							    {enfant.setText1("Il reste "+(365-jours_restant_filtre_air)+" jours ou "+ (km_max_filtre_air-km_restant_filtre_air) +" KM avant avant la prochaine maintenance");
							            if(((365-jours_restant_filtre_air)<31)||(km_max_filtre_air-km_restant_filtre_air)<50) parent.setEtat(1);
		    	                        else parent.setEtat(0);}
							    else if (km_restant_filtre_air>km_max_filtre_air)
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (km_restant_filtre_air-km_max_filtre_air) +" KM");
								parent.setEtat(2);
								 }
							    else if (jours_restant_filtre_air>365)
							    {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_filtre_air-365) +" Jours");
							    parent.setEtat(2);
							    }
								//associer l'enfant au parent
								parent.getChildren().add(enfant);
								 }
						  else if(i==5){
						    	parent.setName("" + i);
							    parent.setText1("Filtre d'habitacle");
							    parent.setText2("");
		                        parent.setChildren(new ArrayList<Liste_enfant>());
								       
								final Liste_enfant enfant = new Liste_enfant();
								enfant.setName("" + i);
								if(jours_restant_filtre_habitacle <= 365)
							    {enfant.setText1("Il reste "+(365-jours_restant_filtre_habitacle)+" jours avant la prochaine maintenance");
							           if((365-jours_restant_filtre_habitacle)<31) parent.setEtat(1);
    	                               else parent.setEtat(0);}
							    else
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_filtre_habitacle-365) +" Jours");
								parent.setEtat(2);
								}
							    //associer l'enfant au parent
								parent.getChildren().add(enfant);   
				        	      }
						  else if(i==6){
						    	parent.setName("" + i);
								parent.setText1("Essui-glaces");
								parent.setText2("");
		                        parent.setChildren(new ArrayList<Liste_enfant>());
								       
								final Liste_enfant enfant = new Liste_enfant();
							    enfant.setName("" + i);
								enfant.setText1("Il faut controler l'epaisseur des balais "); 
								parent.getChildren().add(enfant);     
							      }
						  else if(i==7){
			    				parent.setName("" + i);
								parent.setText1("Huile de boite vitesse");
					    		parent.setText2("");
								parent.setChildren(new ArrayList<Liste_enfant>());
										       
								final Liste_enfant enfant = new Liste_enfant();
								enfant.setName("" + i);
								if((km_restant_huil_boite_vitesse <= km_max_huil_boite_vitesse)&&(jours_restant_huil_boite_vitesse <= 365))
								{enfant.setText1("Il reste "+(365-jours_restant_huil_boite_vitesse)+" jours ou "+ (km_max_huil_boite_vitesse-km_restant_huil_boite_vitesse) +" KM avant la prochaine maintenance");
								        if(((365-jours_restant_huil_boite_vitesse)<31)||(km_max_huil_boite_vitesse-km_restant_huil_boite_vitesse)<50) parent.setEtat(1);
    	                                else parent.setEtat(0);}	 
						        else if (km_restant_huil_boite_vitesse>km_max_huil_boite_vitesse)
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (km_restant_huil_boite_vitesse-km_max_huil_boite_vitesse) +" KM");
								parent.setEtat(2);
								}
								else if (jours_restant_huil_boite_vitesse>365)
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_huil_boite_vitesse-365) +" Jours");
								parent.setEtat(2);
								}		
								//associer l'enfant au parent
								parent.getChildren().add(enfant); 
										}
						  else if(i==8){
								parent.setName("" + i);
								parent.setText1("Visite technique");
					     		parent.setText2("");
								parent.setChildren(new ArrayList<Liste_enfant>());
										       
								final Liste_enfant enfant= new Liste_enfant();
								enfant.setName("" + i);
								if(jours_restant_visite_technique <= 365)
								{enfant.setText1("Il reste "+(365-jours_restant_visite_technique)+" jours avant la prochaine visite technique");
								     if((365-jours_restant_visite_technique)<31) parent.setEtat(1);
	                                 else parent.setEtat(0);}   
								else
							    {enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_visite_technique-365) +" Jours");
							    parent.setEtat(2);
							    }
								//associer l'enfant au parent
								parent.getChildren().add(enfant);    
										}
						  else if(i==9){
							    parent.setName("" + i);
								parent.setText1("Assurance ");
								parent.setText2("");
								parent.setChildren(new ArrayList<Liste_enfant>());
										       
								final Liste_enfant enfant = new Liste_enfant();
								enfant.setName("" + i);
								if(jours_restant_assurance <= 365)
								{enfant.setText1("Il reste "+(365-jours_restant_assurance)+" jours avant la prochaine date d'assurance");
								      if((365-jours_restant_assurance)<31) parent.setEtat(1);
                                      else parent.setEtat(0);}     
								 else if (jours_restant_assurance>365)   
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_assurance-365) +" Jours");
								parent.setEtat(2);
								 }
								//associer l'enfant au parent
								parent.getChildren().add(enfant);
										}
						 else if(i==10){
						    	parent.setName("" + i);
								parent.setText1("Courroie crantée, ");
								parent.setText2("galets, pompe à eau ");
								parent.setChildren(new ArrayList<Liste_enfant>());
										       
								final Liste_enfant enfant = new Liste_enfant();
								enfant.setName("" + i);
								if(jours_restant_courroie <= 5*365)
								{enfant.setText1("Il reste "+(5*365-jours_restant_courroie)+" jours avant la prochaine maintenance de la courroie crantée");
								      if((5*365-jours_restant_courroie)<31) parent.setEtat(1);
                                      else parent.setEtat(0);}    	  
								else if (jours_restant_courroie>5*365)   
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_courroie-5*365) +" Jours");
								parent.setEtat(2);
								}
								//associer l'enfant au parent
								parent.getChildren().add(enfant);									}
						 else if(i==11){
								parent.setName("" + i);
								parent.setText1("Disque de frein ");
								parent.setText2("");
								parent.setChildren(new ArrayList<Liste_enfant>());
										       
								final Liste_enfant enfant = new Liste_enfant();
								enfant.setName("" + i);
								if((km_restant_disque_frein <= km_max_disque_frein)&&(jours_restant_disque_frein <= 2*365))
								{enfant.setText1("Il reste "+(2*365-jours_restant_disque_frein)+" jours ou "+ (km_max_disque_frein-km_restant_disque_frein) +" KM avant la prochaine maintenance");
								      if(((2*365-jours_restant_disque_frein)<31)||(30000-km_restant_disque_frein)<50) parent.setEtat(1);
				    	              else parent.setEtat(0);}   	  
								else if (km_restant_disque_frein>km_max_disque_frein)
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (km_restant_disque_frein-km_max_disque_frein) +" KM");
								parent.setEtat(2);
								}
								else if (jours_restant_disque_frein>2*365) 
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_disque_frein-2*365) +" Jours");
								parent.setEtat(2);
								}
								//associer l'enfant au parent
								parent.getChildren().add(enfant);
										}
						  else if(i==12){
								parent.setName("" + i);
								parent.setText1("Les pneus ");
								parent.setText2("");
								parent.setChildren(new ArrayList<Liste_enfant>());
										       
								final Liste_enfant enfant = new Liste_enfant();
								enfant.setName("" + i);
								enfant.setText1("Verifier si la profondeur du trait est superieur a 1.5 mm");
								parent.getChildren().add(enfant);   
										}
						  else if(i==13){
								parent.setName("" + i);
								parent.setText1("La suspension ");
							    parent.setText2("");
								parent.setChildren(new ArrayList<Liste_enfant>());
										       
								final Liste_enfant enfant = new Liste_enfant();
								enfant.setName("" + i);
								if((km_restant_suspension <= km_max_suspension)&&(jours_restant_suspension <= 2*365))
								{enfant.setText1("Il reste "+(2*365-jours_restant_suspension)+" jours ou "+ (20000-km_restant_suspension) +" KM avant la prochaine maintenance");
								     if(((2*365-jours_restant_suspension)<31)||(km_max_suspension-km_restant_suspension)<50) parent.setEtat(1);
			    	                 else parent.setEtat(0);}    	 
								else if (km_restant_suspension>km_max_suspension)
								{enfant.setText1("vous avez depassé la prochaine maintenance de "+ (km_restant_suspension-km_max_suspension) +" KM");
								parent.setEtat(2);
								}
								else if (jours_restant_suspension>2*365)   
								{ enfant.setText1("vous avez depassé la prochaine maintenance de "+ (jours_restant_suspension-2*365) +" Jours");
								parent.setEtat(2);
								}
								//associer l'enfant au parent
								parent.getChildren().add(enfant); 
								}
					
					if(parent.getEtat()==2) test_notification_red=true;
					if(parent.getEtat()==1) test_notification_yellow=true;
				    //ajouter l'objet de classe parent dans la liste      
					list.add(parent);
				}
		//mettre à jour des valeurs de la liste extensible
		loadHosts(list);

//gestion de la notification	        
		    if((test_notification_red||test_notification_yellow)&&sharedpreferences.getBoolean(test_notification,true)) {

		    //creation et affichage de la notification			
	        int icon = R.drawable.ic_launcher;
	        // Le premier titre affiché
	        CharSequence tickerText = "Une(ou plusieures) maintenance est requise";
	        // Daté de maintenant
	        long when = System.currentTimeMillis();
	       
	        // La notification est créée
	        Notification notification = new Notification(icon,tickerText,when);     
	        Intent notificationIntent = new Intent(Diagnostique.this,Diagnostique.class);
	        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
	        PendingIntent contentIntent =PendingIntent.getActivity(Diagnostique.this, 0, notificationIntent,0);
	        
	        //choix du contenu de la notification
	        if(test_notification_red)
	        notification.setLatestEventInfo(Diagnostique.this, "Rappel","Une date de maintenance est dépassée", contentIntent);
	        else if(test_notification_yellow)
		    notification.setLatestEventInfo(Diagnostique.this, "Avertissement","Une date de maintenance est proche", contentIntent);
	       

	        // Récupération du Notification Manager
	        NotificationManager manager = (NotificationManager)
	        getSystemService(Context.NOTIFICATION_SERVICE);
	        manager.notify(ID_NOTIFICATION, notification);
	        }
	
	}
	
	
	
	private void loadHosts(final ArrayList<Liste_parent> newParents)
	{
		if (newParents == null)
			return;
		
		parents = newParents;
		
		// Check for ExpandableListAdapter object
		if (this.getExpandableListAdapter() == null)
		{
			 //Create ExpandableListAdapter Object
			final MyExpandableListAdapter mAdapter = new MyExpandableListAdapter();
			
			// Set Adapter to ExpandableList Adapter
			this.setListAdapter(mAdapter);
		}
		else
		{
			 // Refresh ExpandableListView data 
			((MyExpandableListAdapter)getExpandableListAdapter()).notifyDataSetChanged();
		}	
	}

	/**
	 * A Custom adapter to create Parent view (Used grouprow.xml) and Child View((Used childrow.xml).
	 */
	private class MyExpandableListAdapter extends BaseExpandableListAdapter
	{
		

		private LayoutInflater inflater;

		public MyExpandableListAdapter()
		{
			// Create Layout Inflator
			inflater = LayoutInflater.from(Diagnostique.this);
		}
    
		
		// This Function used to inflate parent rows view
		
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, 
				View convertView, ViewGroup parentView)
		{
			final Liste_parent parent = parents.get(groupPosition);
			
			// Inflate grouprow.xml file for parent rows
			convertView = inflater.inflate(R.layout.activity_liste_parent, parentView, false); 
			
			// Get grouprow.xml file elements and set values
			((TextView) convertView.findViewById(R.id.text1)).setText(parent.getText1());
			((TextView) convertView.findViewById(R.id.text)).setText(parent.getText2());
			ImageView image=(ImageView)convertView.findViewById(R.id.image);
			image.setImageResource(getResources().getIdentifier("com.example.car_doctor:drawable/setting"+parent.getName(),null,null));
			ImageView rightcheck=(ImageView)convertView.findViewById(R.id.rightcheck);
			
			if      (parent.isGreen())   rightcheck.setImageResource(R.drawable.green_check);
			else if (parent.isWarning()) rightcheck.setImageResource(R.drawable.yellow_check);
			else if (parent.isRed())	 rightcheck.setImageResource(R.drawable.red_check);
			
					
			// Change right check image on parent at runtime
			
			return convertView;
		}

		
		// This Function used to inflate child rows view
		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, 
				View convertView, ViewGroup parentView)
		{
			final Liste_parent parent = parents.get(groupPosition);
			final Liste_enfant child = parent.getChildren().get(childPosition);
			
			// Inflate childrow.xml file for child rows
			convertView = inflater.inflate(R.layout.activity_liste_enfant, parentView, false);
			
			// Get childrow.xml file elements and set values
			((TextView) convertView.findViewById(R.id.text1)).setText(child.getText1());
			ImageView image=(ImageView)convertView.findViewById(R.id.image);
			image.setImageResource(getResources().getIdentifier("com.example.car_doctor:drawable/setting"+parent.getName(),null,null));
			
			return convertView;
		}

		
		@Override
		public Object getChild(int groupPosition, int childPosition)
		{
			//Log.i("Childs", groupPosition+"=  getChild =="+childPosition);
			return parents.get(groupPosition).getChildren().get(childPosition);
		}

		//Call when child row clicked
		@Override
		public long getChildId(int groupPosition, int childPosition)
		{
			/****** When Child row clicked then this function call *******/
			
			return childPosition;
		}

		@Override
		public int getChildrenCount(int groupPosition)
		{
			int size=0;
			if(parents.get(groupPosition).getChildren()!=null)
				size = parents.get(groupPosition).getChildren().size();
			return size;
		}
     
		
		@Override
		public Object getGroup(int groupPosition)
		{
			Log.i("Parent", groupPosition+"=  getGroup ");
			
			return parents.get(groupPosition);
		}

		@Override
		public int getGroupCount()
		{
			return parents.size();
		}

		//Call when parent row clicked
		@Override
		public long getGroupId(int groupPosition)
		{
			
			return groupPosition;
		}

		@Override
		public void notifyDataSetChanged()
		{
			// Refresh List rows
			super.notifyDataSetChanged();
		}

		@Override
		public boolean isEmpty()
		{
			return ((parents == null) || parents.isEmpty());
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition)
		{
			return true;
		}

		@Override
		public boolean hasStableIds()
		{
			return true;
		}

		@Override
		public boolean areAllItemsEnabled()
		{
			return true;
		}
		
		
		
		
	}
	
	
}

