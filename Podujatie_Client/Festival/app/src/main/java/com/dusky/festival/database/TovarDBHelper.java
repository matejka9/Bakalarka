package com.dusky.festival.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dusky.myapplication.backend.podujatie.model.TovarResponseEntityModel;

import java.util.ArrayList;

/**
 * Created by dusky on 5/30/16.
 */
public class TovarDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tovar";
    public static final String TABLE_NAME = "tovar";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STANOK_ID = "stanokId";
    public static final String COLUMN_CENA = "cena";
    public static final String COLUMN_NAZOV = "nazov";
    public static final String COLUMN_DETAIL = "detail";

    public TovarDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table " + TABLE_NAME +
                        " (" + COLUMN_ID + " integer primary key, " + COLUMN_STANOK_ID + " integer, " + COLUMN_CENA + " real, " + COLUMN_NAZOV + " text, " + COLUMN_DETAIL + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertTovar(TovarResponseEntityModel tovar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, tovar.getId());
        contentValues.put(COLUMN_STANOK_ID, tovar.getStanokId());
        contentValues.put(COLUMN_CENA, tovar.getCena());
        contentValues.put(COLUMN_NAZOV, tovar.getNazov());
        contentValues.put(COLUMN_DETAIL, tovar.getDetail());
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Integer deletePodujatie(TovarResponseEntityModel tovar)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[] { Long.toString(tovar.getId()) });
    }

    public ArrayList<TovarResponseEntityModel> tovarForStanok(Long idStanok)
    {
        ArrayList<TovarResponseEntityModel> array_list = new ArrayList<TovarResponseEntityModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME + " where " + COLUMN_STANOK_ID + " = " + idStanok, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            TovarResponseEntityModel model = new TovarResponseEntityModel();
            model.setId((long) res.getInt(res.getColumnIndex(COLUMN_ID)));
            model.setStanokId(res.getLong(res.getColumnIndex(COLUMN_STANOK_ID)));
            model.setCena(res.getDouble(res.getColumnIndex(COLUMN_CENA)));
            model.setNazov(res.getString(res.getColumnIndex(COLUMN_NAZOV)));
            model.setDetail(res.getString(res.getColumnIndex(COLUMN_DETAIL)));
            array_list.add(model);
            res.moveToNext();
        }

        return array_list;
    }
}
