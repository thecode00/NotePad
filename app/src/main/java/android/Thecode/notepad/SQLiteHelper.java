package android.Thecode.notepad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class SQLiteHelper{

    private static final String dbName = "memoDB";
    private static final String table = "memoTable";
    private static final int version = 1;

    private OpenHelper opener;
    private SQLiteDatabase db;
    private Context context;

    public SQLiteHelper(Context context){
        this.context = context;
        this.opener = new OpenHelper(context, dbName, null, version);
        db = opener.getWritableDatabase();
    }

    private class OpenHelper extends SQLiteOpenHelper{

        public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create = "CREATE TABLE " + table + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "maintext TEXT)";
            db.execSQL(create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + table);
            onCreate(db);
        }
    }

    public void insertMemo(Memo memo){
        String sql = "INSERT INTO " + table + " VALUES(NULL, '" +memo.mainText + "');";
        db.execSQL(sql);
    }

    public void deleteMemo(int position){
        String sql = "DELETE FROM " + table + " WHERE ID = " + position + ";";
        db.execSQL(sql);
    }

    public ArrayList<Memo> selectAll(){
        String sql = "SELECT * FROM " + table;

        ArrayList<Memo> list = new ArrayList<>();

        Cursor result = db.rawQuery(sql, null);
        result.moveToFirst();

        while(!result.isAfterLast()){

            Memo memo = new Memo(result.getInt(0), result.getString(1));
            list.add(memo);
            result.moveToNext();
        }
        result.close();
        return list;
    }
}