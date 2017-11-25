package com.example.car_doctor;

public class PIECES {
	public int _id,_jour,_mois,_annee,_kilometrage;       
	public PIECES (){
	}
	// constructor
    public PIECES(int id,int jour,int mois,int annee,int kilometrage){
        this._id = id;
        this._jour = jour;
        this._mois = mois;
        this._annee= annee;
        this._kilometrage= kilometrage;
    }
    // constructor
        public PIECES(int jour,int mois,int annee,int kilometrage){
        this._jour = jour;
        this._mois = mois;
        this._annee= annee;
        this._kilometrage= kilometrage;
    }
 // getting ID
    public int getID(){
        return this._id;
    }
 // setting id
    public void setID(int id){
        this._id = id;
    }
 // getting jour
    public int getJour(){
        return this._jour;
    }
 // setting jour
    public void setJour(int jour){
        this._jour = jour;
    }
 // getting mois
    public int getMois(){
        return this._mois;
    }
 // setting mois
    public void setMois(int mois){
        this._mois = mois;
    }
 // getting annee
    public int getAnnee(){
        return this._annee;
    }
 // setting annee
    public void setAnnee(int annee){
        this._annee = annee;
    }
 // getting kilometrage
    public int getKilometrage(){
        return this._kilometrage;
    }
 // setting kilometrage
    public void setKilometrage(int kilometrage){
        this._kilometrage = kilometrage;
    }
 
}
