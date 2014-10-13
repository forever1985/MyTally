package com.tally.provider;

import java.util.ArrayList;

import com.tally.log.TallyLog;
import com.tally.provider.TalliesContentProvider.CostTypesColumns;
import com.tally.provider.TalliesContentProvider.InComeTypesColumns;
import com.tally.provider.TalliesContentProvider.TalliesColumns;
import com.tally.provider.TalliesContentProvider.TallyTypesColumns;
import com.tally.provider.TalliesContentProvider.TallyUserColumns;
import com.tally.tally.R;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.SystemClock;

public class TalliesOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    
    private static final String TALLY_DB = "tally.db";
    
    private static TalliesOpenHelper mTalliesOpenHelper;
    
    public static final String TABLE_TALLIES_NAME = "tallies";
    public static final String TABLE_COST_NAME  = "costtypes";
    public static final String TABLE_INCOME_NAME = "incometypes";
    public static final String TABLE_USER_NAME = "users";
    public static final String TABLE_TYPES_NAME = "types";
    public static final String VIEW_TALLY_NAME = "view_tallies";
    
    private static Context mContext;
    
    private TalliesOpenHelper (Context context) {
        super(context,TALLY_DB,null,VERSION);
    }
    
    public static TalliesOpenHelper getInstance (Context context) {
        if (null == mTalliesOpenHelper) {
            mTalliesOpenHelper = new TalliesOpenHelper(context);
        }
        if (null == mContext) {
            mContext = context;
        }
        return mTalliesOpenHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createCostTypesTable(db);
        createInComeTypesTable(db);
        createTalliesUsersTable(db);
        createTalliesTable(db);
        createTypesTable(db);
        createTallyView(db);
        initTypsData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
    
    private void createTalliesTable (SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TALLIES_NAME
                + "("
                + TalliesColumns.TALLIES_ID + " INTEGER PRIMARY KEY,"
                + TalliesColumns.TALLY_USER_ID + " INTEGER DEFAULT 0,"
                + TalliesColumns.TYPE_ID + " INTEGER DEFAULT 0,"
                + TalliesColumns.COST_TYPE_ID + " INTEGER DEFAULT 0,"
                + TalliesColumns.INCOME_TYPE_ID + " INTEGER DEFAULT 0,"
                + TalliesColumns.TALLIES_DAY + " TEXT NOT NULL,"
                + TalliesColumns.TALLIES_MONTH + " TEXT NOT NULL,"
                + TalliesColumns.TALLIES_YEAR + " TEXT NOT NULL,"
                + TalliesColumns.CREATE_TIME + " TEXT NOT NULL,"
                + TalliesColumns.EXPENDITURE + " TEXT ,"
                + TalliesColumns.INCOME + " TEXT ,"
                + TalliesColumns.REMARKS + " TEXT "
                + ")";
        db.execSQL(sql);
    }
    
    private void createCostTypesTable (SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_COST_NAME
                + "("
                + CostTypesColumns.COST_TYPE_ID + " INTEGER PRIMARY KEY,"
                + CostTypesColumns.COST_TYPE_NAME + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql);
    }
    
    private void createInComeTypesTable (SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_INCOME_NAME
                + "("
                + InComeTypesColumns.INCOME_TYPE_ID + " INTEGER PRIMARY KEY,"
                + InComeTypesColumns.INCOME_TYPE_NAME + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql);
    }
    
    private void createTalliesUsersTable (SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_NAME
                + "("
                + TallyUserColumns.TALLY_USER_ID + " INTEGER PRIMARY KEY,"
                + TallyUserColumns.TALLY_USER_NAME + " TEXT NOT NULL,"
                + TallyUserColumns.USER_CREATE_TIME + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql);
    }
    
    private  void createTypesTable (SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TYPES_NAME
                + "("
                + TallyTypesColumns.TYPE_ID + " INTEGER PRIMARY KEY,"
                + TallyTypesColumns.TYPE_NAME + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql);
    }
    
    private void createTallyView (SQLiteDatabase db) {
        String view = "CREATE VIEW IF NOT EXISTS " + VIEW_TALLY_NAME + " AS SELECT * FROM "
                + TABLE_TALLIES_NAME 
                + " LEFT JOIN " + TABLE_COST_NAME + " ON "
                + TABLE_TALLIES_NAME + "." + TalliesColumns.COST_TYPE_ID + "=" + TABLE_COST_NAME + "." + CostTypesColumns.COST_TYPE_ID
                + " LEFT JOIN " + TABLE_INCOME_NAME + " ON "
                + TABLE_TALLIES_NAME + "." + TalliesColumns.INCOME_TYPE_ID + "=" + TABLE_INCOME_NAME + "." + InComeTypesColumns.INCOME_TYPE_ID
                + " LEFT JOIN " + TABLE_USER_NAME + " ON "
                + TABLE_TALLIES_NAME + "." + TalliesColumns.TALLY_USER_ID + "=" + TABLE_USER_NAME + "." + TallyUserColumns.TALLY_USER_ID
                + " LEFT JOIN " + TABLE_TYPES_NAME + " ON "
                + TABLE_TALLIES_NAME + "." + TalliesColumns.TYPE_ID + "=" + TABLE_TYPES_NAME + "." + TallyTypesColumns.TYPE_ID;
        db.execSQL(view);
    }
    
    private void initTypsData (SQLiteDatabase db) {
        String[] costs = mContext.getResources().getStringArray(R.array.cost_types);
        String[] incomes = mContext.getResources().getStringArray(R.array.income_types);
        String[] types = mContext.getResources().getStringArray(R.array.types);
        TallyLog.i("costs = " + costs.length);
        String costSql = "INSERT INTO " + TABLE_COST_NAME 
                + "("
                + CostTypesColumns.COST_TYPE_NAME
                + ")"
                + " VALUES (?)";
        String incomeSql = "INSERT INTO " + TABLE_INCOME_NAME 
                + "("
                + InComeTypesColumns.INCOME_TYPE_NAME
                + ")"
                + " VALUES (?)";
        String userSql = " INSERT INTO " + TABLE_USER_NAME
                + "("
                + TallyUserColumns.TALLY_USER_NAME + ","
                + TallyUserColumns.USER_CREATE_TIME
                + ")"
                + " VALUES (?,?)";
        String typeSql = " INSERT INTO " + TABLE_TYPES_NAME
                + "("
                + TallyTypesColumns.TYPE_NAME
                + ")"
                + " VALUES (?)";
        String tallySql = "INSERT INTO " + TABLE_TALLIES_NAME
                + "("
                + TalliesColumns.TALLY_USER_ID + ","
                + TalliesColumns.TYPE_ID + ","
                + TalliesColumns.COST_TYPE_ID + ","
                + TalliesColumns.TALLIES_DAY + ","
                + TalliesColumns.TALLIES_MONTH + ","
                + TalliesColumns.TALLIES_YEAR + ","
                + TalliesColumns.CREATE_TIME + ","
                + TalliesColumns.EXPENDITURE + ","
                + TalliesColumns.REMARKS 
                + ")"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            db.beginTransaction();
            if (costs.length != 0) {
                SQLiteStatement ss = db.compileStatement(costSql);
                for (String str : costs) {
                    ss.bindString(1, str);
                    ss.executeInsert();
                }
            }
            if (incomes.length != 0) {
                SQLiteStatement ss = db.compileStatement(incomeSql);
                for (String str : incomes) {
                    ss.bindString(1, str);
                    ss.executeInsert();
                }
            }
            if (types.length != 0) {
                SQLiteStatement ss = db.compileStatement(typeSql);
                for (String str : types) {
                    ss.bindString(1, str);
                    ss.executeInsert();
                }
            }
            SQLiteStatement ss = db.compileStatement(userSql);
            ss.bindString(1, mContext.getResources().getString(R.string.default_user));
            ss.bindString(2, System.currentTimeMillis() + "");
            ss.executeInsert();
            
            SQLiteStatement ss1 = db.compileStatement(tallySql);
            ss1.bindLong(1, 1);
            ss1.bindLong(2, 1);
            ss1.bindLong(3, 3);
            ss1.bindString(4,"9月26日");
            ss1.bindString(5,"9月");
            ss1.bindString(6,"2014年");
            ss1.bindLong(7,System.currentTimeMillis());
            ss1.bindString(8,"123");
            ss1.bindString(9,"test");
            ss1.executeInsert();
            
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.endTransaction();
            }
        }
    }

}
