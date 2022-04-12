package calculator.vault.com.safebrowser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;

import calculator.vault.com.p068a.C1577d;
import calculator.vault.com.p068a.C1610n;

public class SaveBrowserDatabaseHelper extends SQLiteOpenHelper {
    Context f9492a;
    private String f9493b = "tblBookmarks";
    private String f9494c = "tblHistory";
    private String f9495d = "name";
    private String f9496e = "url";
    private String f9497f = "iconPath";
    private String f9498g = "colorCode";

    public SaveBrowserDatabaseHelper(Context context) {
        super(context, "DBBrowser", null, 11);
        this.f9492a = context;
    }

    public String m14453a(String str) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            Cursor rawQuery = writableDatabase.rawQuery("SELECT " + this.f9497f + " FROM " + this.f9493b + " WHERE " + this.f9496e + "=?", new String[]{str});
            if (rawQuery.getCount() <= 0) {
                return null;
            }
            rawQuery.moveToFirst();
            String string = rawQuery.getString(rawQuery.getColumnIndex(this.f9497f));
            writableDatabase.delete(this.f9493b, this.f9496e + "=?", new String[]{str});
            return string;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<C1577d> m14454a() {
        ArrayList<C1577d> arrayList = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("select * from " + this.f9493b, null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(new C1577d(rawQuery.getString(rawQuery.getColumnIndex(this.f9495d)), rawQuery.getString(rawQuery.getColumnIndex(this.f9496e)), rawQuery.getString(rawQuery.getColumnIndex(this.f9497f)), rawQuery.getInt(rawQuery.getColumnIndex(this.f9498g))));
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    void m14455a(File file) {
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                m14455a(a);
            }
        }
        file.delete();
    }

    public boolean m14456a(String str, int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.f9498g, Integer.valueOf(i));
        writableDatabase.update(this.f9493b, contentValues, this.f9496e + " = ? ", new String[]{str});
        return true;
    }

    public boolean m14457a(String str, String str2) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM " + this.f9494c + " WHERE " + this.f9496e + "=?", new String[]{str2});
            if (rawQuery.getCount() > 0) {
                rawQuery.moveToFirst();
                writableDatabase.delete(this.f9494c, this.f9496e + "=?", new String[]{str2});
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.f9495d, str);
            contentValues.put(this.f9496e, str2);
            writableDatabase.insertOrThrow(this.f9494c, null, contentValues);
        } catch (SQLException e) {
        }
        return true;
    }

    public boolean m14458a(String str, String str2, String str3, int i) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.f9495d, str);
            contentValues.put(this.f9496e, str2);
            contentValues.put(this.f9497f, str3);
            contentValues.put(this.f9498g, Integer.valueOf(i));
            writableDatabase.insertOrThrow(this.f9493b, null, contentValues);
        } catch (SQLException e) {
        }
        return true;
    }

    public ArrayList<C1610n> m14459b() {
        ArrayList<C1610n> arrayList = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("select * from " + this.f9494c, null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(new C1610n(rawQuery.getString(rawQuery.getColumnIndex(this.f9495d)), rawQuery.getString(rawQuery.getColumnIndex(this.f9496e))));
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public boolean m14460b(String str) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM " + this.f9494c + " WHERE " + this.f9496e + "=?", new String[]{str});
            if (rawQuery.getCount() <= 0) {
                return false;
            }
            rawQuery.moveToFirst();
            writableDatabase.delete(this.f9494c, this.f9496e + "=?", new String[]{str});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean m14461c() {
        try {
            getWritableDatabase().execSQL("delete from " + this.f9494c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean m14462c(String str) {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * from " + this.f9493b + " where " + this.f9496e + "=?", new String[]{str});
        if (rawQuery.getCount() <= 0) {
            rawQuery.close();
            return false;
        }
        rawQuery.close();
        return true;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table " + this.f9493b + " (id integer primary key," + this.f9495d + " text not null," + this.f9496e + " text not null unique," + this.f9497f + " text," + this.f9498g + " integer)");
        sQLiteDatabase.execSQL("create table " + this.f9494c + " (id integer primary key," + this.f9495d + " text not null," + this.f9496e + " text not null unique)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + this.f9493b);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + this.f9494c);
        onCreate(sQLiteDatabase);
        m14455a(new File(this.f9492a.getFilesDir() + "/bookmarks"));
    }
}
