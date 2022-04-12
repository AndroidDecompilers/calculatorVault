package calculator.vault.com.p084i;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class C2930c extends SQLiteOpenHelper {
    private static String f9157b = "dbPaths";
    private static String f9158c = "media";
    private static String f9159d = "fileName";
    private static String f9160e = "realPath";
    Context f9161a;

    public C2930c(Context context) {
        super(context, f9157b, null, 1);
        this.f9161a = context;
    }

    public boolean m14129a(String str) {
        getWritableDatabase().delete(f9158c, f9159d + "=?", new String[]{str});
        return true;
    }

    public boolean m14130a(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(f9159d, str);
        contentValues.put(f9160e, str2);
        writableDatabase.insert(f9158c, null, contentValues);
        return true;
    }

    public String m14131b(String str) {
        Cursor rawQuery = getReadableDatabase().rawQuery("select " + f9160e + " from " + f9158c + " where " + f9159d + " = ?", new String[]{str});
        rawQuery.moveToFirst();
        try {
            return rawQuery.getString(0);
        } catch (CursorIndexOutOfBoundsException e) {
            return "null";
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table " + f9158c + " (id integer primary key,fileName text not null unique,realPath text not null)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + f9158c);
        onCreate(sQLiteDatabase);
    }
}
