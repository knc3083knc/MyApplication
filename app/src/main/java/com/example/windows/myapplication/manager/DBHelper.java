package com.example.windows.myapplication.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.windows.myapplication.dao.FavMusic;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "fav_music.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MUSIC_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                FavMusic.TABLE,
                FavMusic.Column.ID,
                FavMusic.Column.MUSIC_NAME,
                FavMusic.Column.ARTIST,
                FavMusic.Column.ALBUM,
                FavMusic.Column.DESCRIPTION);

        Log.d("DBHelper", CREATE_MUSIC_TABLE);

        db.execSQL(CREATE_MUSIC_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + FavMusic.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i("DBHelper", "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);

    }

    public List<String> getFavMusicList() {
        List<String> favMusic = new ArrayList<String>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (FavMusic.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            favMusic.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2) + " " +
                    cursor.getString(3) + " " +
                    cursor.getString(4));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return favMusic;
    }

    public void addFavMusic(FavMusic favMusic) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(FavMusic.Column.MUSIC_NAME, favMusic.getMusicName());
        values.put(FavMusic.Column.ARTIST, favMusic.getArtist());
        values.put(FavMusic.Column.ALBUM, favMusic.getArtist());
        values.put(FavMusic.Column.DESCRIPTION, favMusic.getDescription());

        sqLiteDatabase.insert(FavMusic.TABLE, null, values);

        sqLiteDatabase.close();
    }
}
