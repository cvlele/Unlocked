package src.unlocked;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ABC on 4/13/2016.
 */
public class ConfigureUtils {



        private static final String DATABASE_NAME = "cfg.db";
        private static final String TABLE_NAME = "configure";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_NUMBER = "number";
        private static final String COLUMN_SMS = "sms";
        private static final String COLUMN_TIME = "time";
        private static final String COLUMN_KM = "km";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_CREATION_QUERY =
                "create table " + TABLE_NAME + "(" + COLUMN_ID + " integer " +
                        "primary key autoincrement," + COLUMN_NUMBER + " number," + COLUMN_SMS + " text,"
                        + COLUMN_TIME + " text," + COLUMN_KM + " text);";

        private EmployeeHelper employeeHelper;
        private SQLiteDatabase sqLiteDatabase;
        private Context context;

        public ConfigureUtils(Context context) {
            this.context = context;
            employeeHelper = new EmployeeHelper(context);
        }

        public void open() {
            sqLiteDatabase = employeeHelper.getWritableDatabase();
        }

        public void close() {
            sqLiteDatabase.close();
        }

        public void insertRecord(String number, String sms, String time, String km) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NUMBER, number);
            values.put(COLUMN_SMS, sms);
            values.put(COLUMN_TIME, time);
            values.put(COLUMN_KM, km);
            long rowId = sqLiteDatabase.insert(TABLE_NAME, null, values);
            if (rowId != -1) {
                Toast.makeText(context, "Insert Success:" +
                        rowId, Toast.LENGTH_LONG).show();
            }
        }
   /* public Cursor getNumber()

    {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COLUMN_NUMBER FROM TABLE_NAME WHERE COLUMN_ID = ?",new String[]{"1"});
        return cursor;

    }

    public Cursor getText()

    {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COLUMN_SMS FROM TABLE_NAME WHERE COLUMN_ID = ?",new String[]{"1"});
        return cursor;

    }

    public Cursor getTime()

    {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COLUMN_TIME FROM TABLE_NAME WHERE COLUMN_ID = ?",new String[]{"1"});
        return cursor;

    }

    public Cursor getDistance()

    {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COLUMN_KM FROM TABLE_NAME WHERE COLUMN_ID = ?",new String[]{"1"});
        return cursor;

    } */



            public String fetchNumber() {
                Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                        null, null, null, null);
                cursor.moveToLast();

                        String number = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));
                        ;

                    cursor.close();
                return number;
                }

    public String fetchMessage() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);
        cursor.moveToLast();

        String message1 = cursor.getString(cursor.getColumnIndex(COLUMN_SMS));
        ;

        cursor.close();
        return message1;
    }
    public String fetchTime() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);
        cursor.moveToLast();

        String time1 = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
        ;

        cursor.close();
        return time1;
    }
    public String fetchDistance() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null);
        cursor.moveToLast();

        String km1 = cursor.getString(cursor.getColumnIndex(COLUMN_KM));
        ;

        cursor.close();
        return km1;
    }

/*
            public void deleteRecord(String empName){

                sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?",
                        new String[]{empName});
            }
*/
            public void updateRecord(String newNumber,String newMessage,String newTime, String newDistance ){
                ContentValues values = new ContentValues();
                values.put(COLUMN_NUMBER, newNumber);
                values.put(COLUMN_SMS, newMessage);
                values.put(COLUMN_TIME, newTime);
                values.put(COLUMN_KM, newDistance);
                long rowId=sqLiteDatabase.update(TABLE_NAME, values, COLUMN_ID + "=?",
                        new String[]{"1"});
                if (rowId != -1) {
                    Toast.makeText(context, "Details Saved:" +
                            rowId, Toast.LENGTH_LONG).show();
                }
            }

        private class EmployeeHelper extends SQLiteOpenHelper {

            public EmployeeHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL(TABLE_CREATION_QUERY);
                ContentValues values = new ContentValues();
                values.put(COLUMN_NUMBER, "9999");
                values.put(COLUMN_SMS, "Default");
                values.put(COLUMN_TIME, "1");
                values.put(COLUMN_KM, "1");
                long rowId = sqLiteDatabase.insert(TABLE_NAME, null, values);
                if (rowId != -1) {
                    Toast.makeText(context, "Please Configure Details:" +
                            rowId, Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                // we will not use this for now
            }
        }
    }

