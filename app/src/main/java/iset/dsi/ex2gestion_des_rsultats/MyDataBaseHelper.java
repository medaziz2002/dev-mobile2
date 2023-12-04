package iset.dsi.ex2gestion_des_rsultats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "FinalResult.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_result";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_MOYENNE = "moyenne";




    public MyDataBaseHelper(Context context) {
//Constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_RESULT_TABLE=
                "CREATE TABLE "+TABLE_NAME+"("
                        +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                        +COLUMN_NOM+" TEXT, "
                        +COLUMN_PRENOM+" TEXT, "
                        +COLUMN_MOYENNE+" Text "+")";
        db.execSQL(CREATE_RESULT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);



    }

}
