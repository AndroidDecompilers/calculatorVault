package calculator.vault.com.p084i;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import calculator.vault.com.applock.C3136a;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p068a.C1625q;

public class C2929b extends SQLiteOpenHelper {
    private static String f9147b = "dbGiftAds";
    private static String f9148c = "AdsApplist";
    private static String f9149d = "AdsAlbums";
    private static String f9150e = "appName";
    private static String f9151f = "link";
    private static String f9152g = "shortDesc";
    private static String f9153h = "icon";
    private static String f9154i = "image_url";
    private static String f9155j = "isAlbumAd";
    Context f9156a;

    public C2929b(Context context) {
        super(context, f9147b, null, 21);
        this.f9156a = context;
    }

    public ArrayList<C3136a> m14123a(PackageManager packageManager) {
        ArrayList<C3136a> arrayList = new ArrayList();
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery("select * from " + f9148c, new String[0]);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                String string = rawQuery.getString(rawQuery.getColumnIndex(f9151f));
                String substring = string.substring(string.lastIndexOf("?id=") + 4, string.length());
                String string2 = rawQuery.getString(rawQuery.getColumnIndex(f9150e));
                String string3 = rawQuery.getString(rawQuery.getColumnIndex(f9152g));
                String string4 = rawQuery.getString(rawQuery.getColumnIndex(f9153h));
                if (!C1131f.m5808a(substring, packageManager)) {
                    arrayList.add(new C3136a(true, string2, string, string4, string3));
                }
                rawQuery.moveToNext();
            }
        } catch (SQLiteException e) {
            onCreate(getWritableDatabase());
        }
        return arrayList;
    }

    public ArrayList<C1625q> m14124a(PackageManager packageManager, boolean z) {
        ArrayList<C1625q> arrayList = new ArrayList();
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery("select * from " + f9149d + " where " + f9155j + "=?", new String[]{"" + z});
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                String string = rawQuery.getString(rawQuery.getColumnIndex(f9151f));
                if (!C1131f.m5808a(string.substring(string.lastIndexOf("?id=") + 4, string.length()), packageManager)) {
                    arrayList.add(new C1625q(rawQuery.getString(rawQuery.getColumnIndex(f9150e)), rawQuery.getString(rawQuery.getColumnIndex(f9152g)), rawQuery.getString(rawQuery.getColumnIndex(f9153h)), rawQuery.getString(rawQuery.getColumnIndex(f9154i)), string));
                }
                rawQuery.moveToNext();
            }
        } catch (SQLiteException e) {
            onCreate(getWritableDatabase());
        }
        return arrayList;
    }

    public void m14125a() {
        getWritableDatabase().execSQL("delete from " + f9148c);
    }

    public boolean m14126a(String str, String str2, String str3, String str4) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(f9150e, str);
            contentValues.put(f9151f, str2);
            contentValues.put(f9152g, str3);
            contentValues.put(f9153h, str4);
            writableDatabase.insertOrThrow(f9148c, null, contentValues);
        } catch (SQLException e) {
        }
        return true;
    }

    public boolean m14127a(String str, String str2, String str3, String str4, String str5, boolean z) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(f9150e, str);
            contentValues.put(f9151f, str2);
            contentValues.put(f9154i, str3);
            contentValues.put(f9152g, str4);
            contentValues.put(f9153h, str5);
            contentValues.put(f9155j, "" + z);
            writableDatabase.insertOrThrow(f9149d, null, contentValues);
        } catch (SQLiteException e) {
        }
        return true;
    }

    public void m14128b() {
        getWritableDatabase().execSQL("delete from " + f9149d);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table " + f9148c + " (id integer primary key,appName text not null,link text not null unique,shortDesc text not null,icon text not null)");
        sQLiteDatabase.execSQL("create table " + f9149d + " (id integer primary key,appName text not null,link text not null unique,image_url text,shortDesc text,icon text not null,isAlbumAd text not null)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + f9148c);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + f9149d);
        onCreate(sQLiteDatabase);
    }
}
