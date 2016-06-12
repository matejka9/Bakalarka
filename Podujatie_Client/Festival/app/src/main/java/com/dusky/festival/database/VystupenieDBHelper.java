package com.dusky.festival.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dusky.myapplication.backend.podujatie.model.VystupenieResponseEntityModel;
import com.google.api.client.util.DateTime;

import java.util.ArrayList;

/**
 * Created by dusky on 5/30/16.
 */
public class VystupenieDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "vystupenie";
    public static final String TABLE_NAME = "vystupenie";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PODIUM_ID = "podiumId";
    public static final String COLUMN_CAS_OD = "cas_od";
    public static final String COLUMN_CAS_DO = "cas_do";
    public static final String COLUMN_NAZOV = "nazov";
    public static final String COLUMN_DETAIL = "detail";

    public VystupenieDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table " + TABLE_NAME +
                        " (" + COLUMN_ID + " integer primary key, " + COLUMN_PODIUM_ID + " integer, " + COLUMN_CAS_OD + " text, " + COLUMN_CAS_DO + " text, " + COLUMN_NAZOV + " text, " + COLUMN_DETAIL + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertVystupenie(VystupenieResponseEntityModel vystupenie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, vystupenie.getId());
        contentValues.put(COLUMN_PODIUM_ID, vystupenie.getPodiumId());
        contentValues.put(COLUMN_CAS_OD, vystupenie.getCasOd().toString());
        contentValues.put(COLUMN_CAS_DO, vystupenie.getCasDo().toString());
        contentValues.put(COLUMN_NAZOV, vystupenie.getNazov());
        contentValues.put(COLUMN_DETAIL, vystupenie.getDetail());
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Integer deleteVystupenie(VystupenieResponseEntityModel vystupenie)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[] { Long.toString(vystupenie.getId()) });
    }

    public ArrayList<VystupenieResponseEntityModel> vystupenieForPodium(Long idPodium)
    {
        ArrayList<VystupenieResponseEntityModel> array_list = new ArrayList<VystupenieResponseEntityModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME + " where " + COLUMN_PODIUM_ID + " = " + idPodium, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            VystupenieResponseEntityModel model = new VystupenieResponseEntityModel();
            model.setId(res.getLong(res.getColumnIndex(COLUMN_ID)));
            model.setPodiumId(res.getLong(res.getColumnIndex(COLUMN_PODIUM_ID)));
            model.setCasOd(DateTime.parseRfc3339(res.getString(res.getColumnIndex(COLUMN_CAS_OD))));
            model.setCasDo(DateTime.parseRfc3339(res.getString(res.getColumnIndex(COLUMN_CAS_DO))));
            model.setNazov(res.getString(res.getColumnIndex(COLUMN_NAZOV)));
            model.setDetail(res.getString(res.getColumnIndex(COLUMN_DETAIL)));
            array_list.add(model);
            res.moveToNext();
        }

        return array_list;
    }
}
