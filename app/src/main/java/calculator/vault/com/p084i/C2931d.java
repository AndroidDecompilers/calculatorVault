package calculator.vault.com.p084i;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import calculator.vault.com.p068a.C1633s;

public class C2931d extends SQLiteOpenHelper {
    private static String f9162b = "dbSelfie";
    private static String f9163c = "pictures";
    private static String f9164d = "filePath";
    private static String f9165e = "fileTime";
    private static String f9166f = "appName";
    Context f9167a;

    public C2931d(Context context) {
        super(context, f9162b, null, 1);
        this.f9167a = context;
    }

    public ArrayList<C1633s> m14132a() {
        ArrayList<C1633s> arrayList = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("select * from " + f9163c, new String[0]);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(new C1633s(rawQuery.getString(rawQuery.getColumnIndex(f9164d)), rawQuery.getString(rawQuery.getColumnIndex(f9165e)), rawQuery.getString(rawQuery.getColumnIndex(f9166f))));
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public boolean m14133a(String str) {
        getWritableDatabase().delete(f9163c, f9164d + "=?", new String[]{str});
        return true;
    }

    public boolean m14134a(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(f9165e, str2);
        contentValues.put(f9164d, str);
        contentValues.put(f9166f, str3);
        writableDatabase.insert(f9163c, null, contentValues);
        return true;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table " + f9163c + " (id integer primary key,filePath text not null unique,fileTime text not null,appName text not null)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + f9163c);
        onCreate(sQLiteDatabase);
    }
}
