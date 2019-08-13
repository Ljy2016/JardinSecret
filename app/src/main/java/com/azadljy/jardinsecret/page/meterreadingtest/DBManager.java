package com.azadljy.jardinsecret.page.meterreadingtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.amap.api.maps.offlinemap.City;
import com.azadljy.pleasantlibrary.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.azadljy.jardinsecret.page.meterreadingtest.MyLayoutManager.TAG;

public class DBManager {
    private String DB_NAME = "anso.db";
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }


    public void createData() {
        String dbDir = Environment.getExternalStorageDirectory() + "/1008611/";
        String dbPath = dbDir + DB_NAME;
        File file = FileUtil.CreateNewFile(dbDir, DB_NAME);
        if (file.exists()) {
            if(file.length()>100){
                return;
            }
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = mContext.getAssets().open("anso.db");
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1)
                    out.write(buffer, 0, readBytes);
                in.close();
                out.close();
            } catch (IOException e) {
                Log.e(TAG, "getDatabase: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase getDatabase() {
        String dbDir = Environment.getExternalStorageDirectory() + "/1008611/";
        String dbPath = dbDir + DB_NAME;
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }

    //查询
    public List<UserBookInfo> query(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {

        List<UserBookInfo> userBookInfoList = new ArrayList<>();

        try {
            String table = "userbookinfo";
            Cursor cursor = sqliteDB.query(table, null, null, null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String accounttype = cursor.getString(cursor.getColumnIndex("accounttype"));
                    String customeraddress = cursor.getString(cursor.getColumnIndex("customeraddress"));
                    String customername = cursor.getString(cursor.getColumnIndex("customername"));
                    float avgwaternum = cursor.getFloat(cursor.getColumnIndex("avgwaternum"));
                    int lastmeterdata = cursor.getInt(cursor.getColumnIndex("lastmeterdata"));
                    UserBookInfo userBookInfo = new UserBookInfo(id, lastmeterdata, accounttype, customeraddress, customername, avgwaternum);
                    userBookInfoList.add(userBookInfo);
                }
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "query: " + userBookInfoList.size());
        return userBookInfoList;
    }


    public void updata(SQLiteDatabase sqliteDB, UserBookInfo userBookInfo) {
        String table = "userbookinfo";
        String[] values = new String[]{"id", "lastmeterdata", "accounttype", "customeraddress", "customername"};
        ContentValues values1 = new ContentValues();
        values1.put(values[0], userBookInfo.getId());
        values1.put(values[1], userBookInfo.getLastmeterdata());
        values1.put(values[2], userBookInfo.getAccounttype());
        values1.put(values[3], userBookInfo.getCustomeraddress());
        values1.put(values[4], userBookInfo.getCustomername());
        sqliteDB.update(table, values1, "id=?", new String[]{userBookInfo.getId() + ""});
    }

}

