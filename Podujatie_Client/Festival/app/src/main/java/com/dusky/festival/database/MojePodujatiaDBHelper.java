package com.dusky.festival.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dusky.myapplication.backend.podujatie.model.PodujatieResponseEntityModel;
import com.google.api.client.util.DateTime;

import java.util.ArrayList;

/**
 * Created by dusky on 5/29/16.
 */
public class MojePodujatiaDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "podujatie";
    public static final String TABLE_NAME = "moje_podujatia";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATUM_OD = "datum_od";
    public static final String COLUMN_DATUM_DO = "datum_do";
    public static final String COLUMN_NAZOV = "nazov";
    public static final String COLUMN_TYP = "typ";

    public MojePodujatiaDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table " + TABLE_NAME +
                        " (" + COLUMN_ID + " integer primary key, " + COLUMN_DATUM_OD + " text, " + COLUMN_DATUM_DO + " text, " + COLUMN_NAZOV + " text, " + COLUMN_TYP + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertPodujatie(PodujatieResponseEntityModel podujatie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, podujatie.getId());
        contentValues.put(COLUMN_DATUM_OD, podujatie.getDatumOd().toString());
        contentValues.put(COLUMN_DATUM_DO, podujatie.getDatumDo().toString());
        contentValues.put(COLUMN_NAZOV, podujatie.getNazov());
        contentValues.put(COLUMN_TYP, podujatie.getTyp());
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Integer deletePodujatie(PodujatieResponseEntityModel podujatie)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[] { Long.toString(podujatie.getId()) });
    }

    public ArrayList<PodujatieResponseEntityModel> getAllPodujatie()
    {
        ArrayList<PodujatieResponseEntityModel> array_list = new ArrayList<PodujatieResponseEntityModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            PodujatieResponseEntityModel model = new PodujatieResponseEntityModel();
            model.setId((long) res.getInt(res.getColumnIndex(COLUMN_ID)));
            model.setDatumOd(DateTime.parseRfc3339(res.getString(res.getColumnIndex(COLUMN_DATUM_OD))));
            model.setDatumDo(DateTime.parseRfc3339(res.getString(res.getColumnIndex(COLUMN_DATUM_DO))));
            model.setNazov(res.getString(res.getColumnIndex(COLUMN_NAZOV)));
            model.setTyp(res.getString(res.getColumnIndex(COLUMN_TYP)));
            array_list.add(model);
            res.moveToNext();
        }

        return array_list;
    }
}
