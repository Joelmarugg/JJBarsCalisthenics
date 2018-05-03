package com.jjbars.jjbarscalisthenics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_Path = "";
    private static String DB_Name = "thenxex";
    private SQLiteDatabase Database;
    private final Context myContext;


    // Takes and keeps reference of the passsed context in order to access to the application assets and resources
    public DatabaseHelper(Context context) {
        super(context,DB_Name,null,1);
        this.myContext = context;
        DB_Path = myContext.getDatabasePath(DB_Name).toString();
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDatabase();

        if(dbExist){
            //do nothing database already exist
        }else{
            // create database to override with ThenX DB
            this.getWritableDatabase();
            try {

                copyDataBase();

            }catch (IOException e ){

                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase(){

        SQLiteDatabase DBCheck = null;

        try {
            String myPath = DB_Path;
            DBCheck = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            //database doesnt exist yet
        }
        if(DBCheck != null){
            DBCheck.close();
        }
        return false; // DBCheck != null ? true : false;
    }

    //copies the ThenX file into the just created database
    private void copyDataBase() throws IOException{

        //opens local db as input stream
        InputStream myInput = myContext.getAssets().open(DB_Name);

        //path to the just created db
        String outFileName = DB_Path;

        //open the empty db as output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer,0,length);
        }
        //close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLiteException{

        String myPath = DB_Path;
        Database = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if(Database != null)
            Database.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
   // public Cursor querx (String table, String[] columns)
}
