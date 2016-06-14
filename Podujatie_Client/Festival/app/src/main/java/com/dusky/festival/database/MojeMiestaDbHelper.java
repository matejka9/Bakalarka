package com.dusky.festival.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dusky.festival.model.MyPoint;

import java.util.ArrayList;

/**
 * Created by dusky on 6/14/16.
 */
public class MojeMiestaDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "miesta";
    public static final String TABLE_NAME = "moje_miesta";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ID_PODUJATIE = "id_podujatie";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_TEXT = "text";

    public MojeMiestaDbHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table " + TABLE_NAME +
                        " (" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_ID_PODUJATIE + " integer, " + COLUMN_LONGITUDE + " real, " + COLUMN_LATITUDE + " real, " + COLUMN_TEXT + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Long insertMojeMiesto(MyPoint point, Long idPodujatie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (point.getId() != null) {
            contentValues.put(COLUMN_ID, point.getId());
        }
        contentValues.put(COLUMN_ID_PODUJATIE, idPodujatie);
        contentValues.put(COLUMN_LONGITUDE, point.getLongitude());
        contentValues.put(COLUMN_LATITUDE, point.getLatitude());
        contentValues.put(COLUMN_TEXT, point.getText());
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public Integer deleteMojeMiesto(MyPoint point)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[] { Long.toString(point.getId()) });
    }

    public ArrayList<MyPoint> getAllMojeMiesto(long podujatieId)
    {
        ArrayList<MyPoint> array_list = new ArrayList<MyPoint>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME + " where " + COLUMN_ID_PODUJATIE + "=" + podujatieId, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            MyPoint model = new MyPoint();
            model.setId((long) res.getInt(res.getColumnIndex(COLUMN_ID)));
            model.setLatitude(res.getLong(res.getColumnIndex(COLUMN_LATITUDE)));
            model.setLongitude(res.getLong(res.getColumnIndex(COLUMN_LONGITUDE)));
            model.setText(res.getString(res.getColumnIndex(COLUMN_TEXT)));
            array_list.add(model);
            res.moveToNext();
        }

        return array_list;
    }
}
