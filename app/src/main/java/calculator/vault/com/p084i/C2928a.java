package calculator.vault.com.p084i;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashSet;

public class C2928a extends SQLiteOpenHelper {
    private static String f9142a = "myDb";
    private static String f9143b = "apps";
    private static String f9144c = "name";
    private static String f9145d = "state";
    private static C2928a f9146e = null;

    private C2928a(Context context) {
        super(context, f9142a, null, 1);
    }

    public static synchronized C2928a m14118a(Context context) {
        C2928a c2928a;
        synchronized (C2928a.class) {
            if (f9146e == null) {
                f9146e = new C2928a(context.getApplicationContext());
            }
            c2928a = f9146e;
        }
        return c2928a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashSet<String> m14119a() {
        HashSet<String> strings = new HashSet<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from apps where state='1'", null);
        if (cursor.moveToFirst()) {
            do {
                strings.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
        return strings;
    }

    public boolean m14120a(String str) {
        try {
            getWritableDatabase().delete(f9143b, "name=?", new String[]{str});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean m14121a(String str, int i) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", str);
            contentValues.put("state", Integer.valueOf(i));
            writableDatabase.insertOrThrow(f9143b, null, contentValues);
        } catch (SQLException e) {
        }
        return true;
    }

    public boolean m14122b(String str, int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(f9144c, str);
        contentValues.put(f9145d, Integer.valueOf(i));
        writableDatabase.update(f9143b, contentValues, "name = ? ", new String[]{str});
        return true;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table apps (id integer primary key,name text not null unique,state integer)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS apps");
        onCreate(sQLiteDatabase);
    }
}
