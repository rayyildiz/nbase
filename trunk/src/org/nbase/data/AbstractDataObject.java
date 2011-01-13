/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://kenai.com/projects/nbase
 * 
 * 
 * nbase is deliver with MIT licence
 */

package org.nbase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 *
 * @author rayyildiz
 */
public abstract class AbstractDataObject {

    protected static final String DATABASE_NAME = "NBase.db";
    protected static final int DATABASE_VERSION = 1;

    public abstract String getTableName();
    public abstract String createScript();
    public abstract String insertScript();

    private SQLiteStatement insertSQLiteStatement;

    public SQLiteStatement getInsertSQLiteStatement() {
        if ( insertSQLiteStatement == null){
            insertSQLiteStatement = _database.compileStatement(insertScript());
        }
        return insertSQLiteStatement;
    }
    

    private Context _context;
    public Context getContext() {
        return _context;
    }

    private SQLiteDatabase _database;
    public SQLiteDatabase getDatabase() {
        return _database;
    }

    public AbstractDataObject(Context _context) {
        this._context = _context;
        CustomSqliteOpener customSqliteOpener = new CustomSqliteOpener(_context);
        _database = customSqliteOpener.getWritableDatabase();
        insertSQLiteStatement = null;
    }

    private class CustomSqliteOpener extends SQLiteOpenHelper {
        public CustomSqliteOpener(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            getDatabase().execSQL(createScript());
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            getDatabase().execSQL("DROP TABLE IF EXISTS " + getTableName());
            onCreate(getDatabase());
        }
    }
}
