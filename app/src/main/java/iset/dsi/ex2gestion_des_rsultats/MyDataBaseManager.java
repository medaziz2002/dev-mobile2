package iset.dsi.ex2gestion_des_rsultats;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SymbolTable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseManager {
    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    public MyDataBaseManager(Context context) {
        dbHelper=new MyDataBaseHelper(context);
        this.context=context;
    }


    public SQLiteDatabase openWrite() {

        db= dbHelper.getWritableDatabase();
        return db;
    }

    public SQLiteDatabase openRead() {
        return dbHelper.getReadableDatabase();
    }

    public void close() {

        db.close();

    }

    void addResult(Resultat r){
  openWrite();
        ContentValues values = new ContentValues();

        values.put(dbHelper.COLUMN_NOM,r.getNom());
        values.put(dbHelper.COLUMN_PRENOM,r.getPrenom());
        values.put(dbHelper.COLUMN_MOYENNE,r.getMoyenne());

        try {
            long retour = db.insert(dbHelper.TABLE_NAME, null, values);
            Log.i("coderetour", retour + "");
        }catch (SQLException e )
        {
            e.printStackTrace();
        }
        close();

    }

    List<Resultat> readAllResult(){
        List<Resultat> lesResultats=new ArrayList<>();



        String querySelect="SELECT * FROM "+dbHelper.TABLE_NAME;
        db=openRead();
        Cursor cursor=db.rawQuery(querySelect,null);
//Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do{
                Resultat resultat = new Resultat(cursor.getInt(0),cursor.getString(1) ,cursor.getString(2), cursor.getFloat(3));
//Adding a contact to list


                lesResultats.add(resultat);
            }while (cursor.moveToNext());
        }


        return lesResultats;
    }

    void updateResult(Resultat res){


        System.out.println("je suis dans update pour loger "+res.toString());
        db=openWrite();
        ContentValues values=new ContentValues();

        values.put(dbHelper.COLUMN_MOYENNE, res.getMoyenne());
        values.put(dbHelper.COLUMN_PRENOM,res.getPrenom());
        values.put(dbHelper.COLUMN_NOM,res.getNom());

         db.update(dbHelper.TABLE_NAME,values,dbHelper.COLUMN_ID+"=?",new String[]{String.valueOf(res.getId())});
    }

    void deleteResult(String id){

        db=openWrite();
        db.delete(dbHelper.TABLE_NAME,dbHelper.COLUMN_ID+"=?"
                ,new String[]{String.valueOf(id)});


    }

    void deleteAllResult(){


            db = openWrite();

            try {

                db.delete(dbHelper.TABLE_NAME, null, null);
                Log.i("deleteAllResult", "Tous les résultats ont été supprimés.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }




}



