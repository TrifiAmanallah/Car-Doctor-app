package com.example.car_doctor;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	public class java {

	}
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "PiecesManager";
 
    // Contacts table name
    private static final String TABLE_PIECES = "pieces";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_jour = "jour";
    private static final String KEY_mois = "mois";
    private static final String KEY_annee = "annee";
    private static final String KEY_kilometrage = "kilometrage";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PIECES_TABLE = "CREATE TABLE " + TABLE_PIECES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_jour + " INTEGER,"
                + KEY_mois + " INTEGER," + KEY_annee + " INTEGER,"+ KEY_kilometrage + " INTEGER"+ ")";
        db.execSQL(CREATE_PIECES_TABLE);
    }
 // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIECES);
 
        // Create tables again
        onCreate(db);
    }
 // Ajouter nouvelle piece
    public void addPiece(PIECES piece) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(KEY_jour, piece.getJour());
        values.put(KEY_mois, piece.getMois());
        values.put(KEY_annee, piece.getAnnee());
        values.put(KEY_kilometrage, piece.getKilometrage());
 
        // Inserting Row
        db.insert(TABLE_PIECES, null, values);
        db.close(); // Closing database connection
    }
 // trouver une piece
    PIECES getPiece(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_PIECES, new String[] { KEY_ID,
                KEY_jour, KEY_mois,KEY_annee,KEY_kilometrage }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        PIECES piece = new PIECES(Integer.parseInt(cursor.getString(0)),
        		Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
        cursor.close();
    	db.close();
    	 // return contact
        return piece;
    }
 
 // Updating single piece
    public int updatePiece(PIECES piece) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues(); 
        values.put(KEY_jour, piece.getJour());
        values.put(KEY_mois, piece.getMois());
        values.put(KEY_annee, piece.getAnnee());
        values.put(KEY_kilometrage, piece.getKilometrage());
        // updating row
        return db.update(TABLE_PIECES, values, KEY_ID + " = ?", new String[] { String.valueOf(piece.getID()) });
    }
 // Deleting single piece
    public void deletePiece(PIECES piece) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PIECES, KEY_ID + " = ?",new String[] { String.valueOf(piece.getID()) });
        db.close();
    }
 // Getting pieces Count
    public int getPiecesNombre() {
        String countQuery = "SELECT  * FROM " + TABLE_PIECES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
}
