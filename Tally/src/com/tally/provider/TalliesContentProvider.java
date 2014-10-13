package com.tally.provider;

import com.tally.log.TallyLog;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

public class TalliesContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.tally.tally";
    
    private static final int TALLIES = 1;
    private static final int TALLIES_ID = 2;
    private static final int COST_TYPES = 3;
    private static final int COST_TYPES_ID = 4;
    private static final int INCOME_TYPES = 5;
    private static final int INCOME_TYPES_ID = 6;
    private static final int USERS = 7;
    private static final int USERS_ID = 8;
    private static final int TYPES = 9;
    private static final int TYPES_ID = 10;
    private static final int TALLIES_VIEW = 11;
    
    
    private TalliesOpenHelper mTalliesOpenHelper;
    
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    
    static {
        mUriMatcher.addURI(AUTHORITY, "tallies", TALLIES);
        mUriMatcher.addURI(AUTHORITY, "tallies/#", TALLIES_ID);
        mUriMatcher.addURI(AUTHORITY, "costtypes", COST_TYPES);
        mUriMatcher.addURI(AUTHORITY, "costtypes/#", COST_TYPES_ID);
        mUriMatcher.addURI(AUTHORITY, "incometypes", INCOME_TYPES);
        mUriMatcher.addURI(AUTHORITY, "incometypes/#", INCOME_TYPES_ID);
        mUriMatcher.addURI(AUTHORITY, "users", USERS);
        mUriMatcher.addURI(AUTHORITY, "users/#", USERS_ID);
        mUriMatcher.addURI(AUTHORITY, "types", TYPES);
        mUriMatcher.addURI(AUTHORITY, "types/#", TYPES_ID);
        mUriMatcher.addURI(AUTHORITY, "tallies_view", TALLIES_VIEW);
    }
    
    @Override
    public boolean onCreate() {
        mTalliesOpenHelper = TalliesOpenHelper.getInstance(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        int match = mUriMatcher.match(uri);
        switch (match) {
        case TALLIES:
            return "vnd.android.cursor.dir/tallies";
        case TALLIES_ID:
            return "vnd.android.cursor.item/alarms";
        case COST_TYPES:
            return "vnd.android.cursor.dir/costtypes";
        case COST_TYPES_ID:
            return "vnd.android.cursor.item/costtypes";
        case INCOME_TYPES:
            return "vnd.android.cursor.dir/incometypes";
        case INCOME_TYPES_ID:
            return "vnd.android.cursor.item/incometypes";
        case USERS:
            return "vnd.android.cursor.dir/users";
        case USERS_ID:
            return "vnd.android.cursor.item/users";
        case TYPES:
            return "vnd.android.cursor.dir/types";
        case TYPES_ID:
            return "vnd.android.cursor.item/types";
        case TALLIES_VIEW:
            return "vnd.android.cursor.dir/tallies_view";
        default:
            throw new IllegalArgumentException("Unknown URL");
        }
    }
    
    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mTalliesOpenHelper.getWritableDatabase();
        int count = 0;
        String primaryKey;
        switch (mUriMatcher.match(uri)) {
        case TALLIES:
            count = db.delete(TalliesOpenHelper.TABLE_TALLIES_NAME, whereClause, whereArgs);
            break;
        case TALLIES_ID:
            primaryKey = uri.getLastPathSegment();
            if (TextUtils.isEmpty(whereClause)) {
                whereClause = TalliesColumns.TALLIES_ID + " = " + primaryKey;
            } else {
                whereClause = TalliesColumns.TALLIES_ID + " = " + primaryKey + " AND (" + whereClause + ")";
            }
            count = db.delete(TalliesOpenHelper.TABLE_TALLIES_NAME, whereClause, whereArgs); 
            break;
        case COST_TYPES:
            count = db.delete(TalliesOpenHelper.TABLE_COST_NAME, whereClause, whereArgs);
            break;
        case COST_TYPES_ID:
            primaryKey = uri.getLastPathSegment();
            if (TextUtils.isEmpty(whereClause)) {
                whereClause = CostTypesColumns.COST_TYPE_ID + " = " + primaryKey;
            } else {
                whereClause = CostTypesColumns.COST_TYPE_ID + " = " + primaryKey + " AND (" + whereClause + ")";
            }
            count = db.delete(TalliesOpenHelper.TABLE_COST_NAME, whereClause, whereArgs);
            break;
        case INCOME_TYPES:
            count = db.delete(TalliesOpenHelper.TABLE_INCOME_NAME, whereClause, whereArgs);
            break;
        case INCOME_TYPES_ID:
            primaryKey = uri.getLastPathSegment();
            if (TextUtils.isEmpty(whereClause)) {
                whereClause = InComeTypesColumns.INCOME_TYPE_ID + " = " + primaryKey;
            } else {
                whereClause = InComeTypesColumns.INCOME_TYPE_ID + " = " + primaryKey + " AND (" + whereClause + ")";
            }
            count = db.delete(TalliesOpenHelper.TABLE_INCOME_NAME, whereClause, whereArgs);
            break;
        case USERS:
            count = db.delete(TalliesOpenHelper.TABLE_USER_NAME, whereClause, whereArgs);
            break;
        case USERS_ID:
            primaryKey = uri.getLastPathSegment();
            if (TextUtils.isEmpty(whereClause)) {
                whereClause = TallyUserColumns.TALLY_USER_ID + " = " + primaryKey;
            } else {
                whereClause = TallyUserColumns.TALLY_USER_ID + " = " + primaryKey + " AND (" + whereClause + ")";
            }
            count = db.delete(TalliesOpenHelper.TABLE_USER_NAME, whereClause, whereArgs);
            break;
        case TYPES:
            count = db.delete(TalliesOpenHelper.TABLE_TYPES_NAME, whereClause, whereArgs);
            break;
        case TYPES_ID:
            primaryKey = uri.getLastPathSegment();
            if (TextUtils.isEmpty(whereClause)) {
                whereClause = TallyTypesColumns.TYPE_ID + " = " + primaryKey;
            } else {
                whereClause = TallyTypesColumns.TYPE_ID + " = " + primaryKey + " AND (" + whereClause + ")";
            }
            count = db.delete(TalliesOpenHelper.TABLE_TYPES_NAME, whereClause, whereArgs);
            break;
        default:
            throw new IllegalArgumentException("Cannot delete from URL: " + uri);
        }
        return count;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId;
        SQLiteDatabase db = mTalliesOpenHelper.getWritableDatabase();
        Uri result;
        switch (mUriMatcher.match(uri)) {
        case TALLIES:
            rowId = db.insert(TalliesOpenHelper.TABLE_TALLIES_NAME, null, values);
            result = ContentUris.withAppendedId(TalliesColumns.CONTENT_URI, rowId);
            break;
        case COST_TYPES:
            rowId = db.insert(TalliesOpenHelper.TABLE_COST_NAME, null, values);
            result = ContentUris.withAppendedId(CostTypesColumns.CONTENT_URI, rowId);
            break;
        case INCOME_TYPES:
            rowId = db.insert(TalliesOpenHelper.TABLE_INCOME_NAME, null, values);
            result = ContentUris.withAppendedId(InComeTypesColumns.CONTENT_URI, rowId);
            break;
        case USERS:
            rowId = db.insert(TalliesOpenHelper.TABLE_USER_NAME, null, values);
            result = ContentUris.withAppendedId(TallyUserColumns.CONTENT_URI, rowId);
            break;
        case TYPES:
            rowId = db.insert(TalliesOpenHelper.TABLE_TYPES_NAME, null, values);
            result = ContentUris.withAppendedId(TallyTypesColumns.CONTENT_URI, rowId);
            break;

        default:
            throw new IllegalArgumentException("Unknown URL" + uri);
        }
        getContext().getContentResolver().notifyChange(result, null);
        return result;
    }
    
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (mUriMatcher.match(uri)) {
        case TALLIES:
            qb.setTables(TalliesOpenHelper.TABLE_TALLIES_NAME);
            break;
        case TALLIES_ID:
            qb.setTables(TalliesOpenHelper.TABLE_TALLIES_NAME);
            qb.appendWhere(TalliesColumns.TALLIES_ID + "=");
            qb.appendWhere(uri.getLastPathSegment());
            break;
        case COST_TYPES:
            qb.setTables(TalliesOpenHelper.TABLE_COST_NAME);
            break;
        case COST_TYPES_ID:
            qb.setTables(TalliesOpenHelper.TABLE_COST_NAME);
            qb.appendWhere(CostTypesColumns.COST_TYPE_ID + "=");
            qb.appendWhere(uri.getLastPathSegment());
            break;
        case INCOME_TYPES:
            qb.setTables(TalliesOpenHelper.TABLE_INCOME_NAME);
            break;
        case INCOME_TYPES_ID:
            qb.setTables(TalliesOpenHelper.TABLE_INCOME_NAME);
            qb.appendWhere(InComeTypesColumns.INCOME_TYPE_ID + "=");
            qb.appendWhere(uri.getLastPathSegment());
            break;
        case USERS:
            qb.setTables(TalliesOpenHelper.TABLE_USER_NAME);
            break;
        case USERS_ID:
            qb.setTables(TalliesOpenHelper.TABLE_USER_NAME);
            qb.appendWhere(TallyUserColumns.TALLY_USER_ID + "=");
            qb.appendWhere(uri.getLastPathSegment());
            break;
        case TYPES:
            qb.setTables(TalliesOpenHelper.TABLE_TYPES_NAME);
            break;
        case TYPES_ID:
            qb.setTables(TalliesOpenHelper.TABLE_TYPES_NAME);
            qb.appendWhere(TallyTypesColumns.TYPE_ID + "=");
            qb.appendWhere(uri.getLastPathSegment());
            break;
        case TALLIES_VIEW:
            qb.setTables(TalliesOpenHelper.VIEW_TALLY_NAME);
            break;
        default:
            throw new IllegalArgumentException("Unknown URL" + uri);
        }
        SQLiteDatabase db = mTalliesOpenHelper.getWritableDatabase();
        Cursor ret = qb.query(db, projection, selection, selectionArgs,
                              null, null, sortOrder);
        if (null == ret) {
            TallyLog.e("query failed!");
        } else {
            ret.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return ret;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where,
            String[] whereArgs) {
        int count = 0;
        SQLiteDatabase db = mTalliesOpenHelper.getWritableDatabase();
        String id;
        switch (mUriMatcher.match(uri)) {
        case TALLIES:
        case TALLIES_ID:
            id = uri.getLastPathSegment();
            if (!TextUtils.isEmpty(id)) {
                where = TalliesColumns.TALLIES_ID + " = " + id + " AND (" + where + ")";
            }
            count = db.update(TalliesOpenHelper.TABLE_TALLIES_NAME, values, where, whereArgs);
            break;
        case COST_TYPES:
        case COST_TYPES_ID:
            id = uri.getLastPathSegment();
            if (!TextUtils.isEmpty(id)) {
                where = CostTypesColumns.COST_TYPE_ID + " = " + id + " AND (" + where + ")";
            }
            count = db.update(TalliesOpenHelper.TABLE_COST_NAME, values, where, whereArgs);
            break;
        case INCOME_TYPES:
        case INCOME_TYPES_ID:
            id = uri.getLastPathSegment();
            if (!TextUtils.isEmpty(id)) {
                where = InComeTypesColumns.INCOME_TYPE_ID + " = " + id + " AND (" + where + ")";
            }
            count = db.update(TalliesOpenHelper.TABLE_INCOME_NAME, values, where, whereArgs);
            break;
        case USERS:
        case USERS_ID:
            id = uri.getLastPathSegment();
            if (!TextUtils.isEmpty(id)) {
                where = TallyUserColumns.TALLY_USER_ID + " = " + id + " AND (" + where + ")";
            }
            count = db.update(TalliesOpenHelper.TABLE_USER_NAME, values, where, whereArgs);
            break;
        case TYPES:
            count = db.update(TalliesOpenHelper.TABLE_TYPES_NAME, values,where, whereArgs);
            break;
        case TYPES_ID:
            id = uri.getLastPathSegment();
            if (TextUtils.isEmpty(id)) {
                where = TallyTypesColumns.TYPE_ID + " = " + id + " AND (" + where + ")";
            }
            count = db.update(TalliesOpenHelper.TABLE_TYPES_NAME, values,where, whereArgs);
            break;
        default:
            throw new UnsupportedOperationException("Can't update " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    public static class TalliesColumns implements BaseColumns {
        
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tallies");
        public static final String TALLIES_ID = "tallies_id";
        public static final String TALLY_USER_ID = "tally_user_id";
        public static final String TYPE_ID = "type_id";
        public static final String COST_TYPE_ID = "cost_type_id";
        public static final String INCOME_TYPE_ID = "income_type_id";
        public static final String TALLIES_YEAR = "tallies_year";
        public static final String TALLIES_MONTH = "tallies_month";
        public static final String TALLIES_DAY = "tallies_day";
        public static final String CREATE_TIME = "create_time";
        public static final String EXPENDITURE = "expenditure";
        public static final String INCOME = "income";
        public static final String REMARKS = "remarks";
        
    }
    
    public static class CostTypesColumns implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/costtypes");
        public static final String COST_TYPE_ID = "cost_type_id";
        public static final String COST_TYPE_NAME = "cost_type_name";
    }
    
    public static class InComeTypesColumns implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/incometypes");
        public static final String INCOME_TYPE_ID = "income_type_id";
        public static final String INCOME_TYPE_NAME = "income_type_name";
    }
    
    public static class TallyUserColumns implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/users");
        public static final String TALLY_USER_ID = "tally_user_id";
        public static final String USER_CREATE_TIME = "create_time";
        public static final String TALLY_USER_NAME = "tally_user_name";
    }
    
    public static class TallyTypesColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/types");
        public static final String TYPE_ID = "type_id";
        public static final String TYPE_NAME = "type_name";
    }
    
    public static class TallyViewColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tallies_view");
    }
    
}
