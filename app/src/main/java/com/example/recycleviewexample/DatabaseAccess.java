package com.example.recycleviewexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private SQLiteOpenHelper openHelper;

    private DatabaseAccess(Context context) {
        this.openHelper = new Database(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        if (this.database != null)
            this.database.close();
    }

    public boolean insertdata (Cars cars)
    {


        ContentValues values= new ContentValues();
        values.put(Database.CAR_COL_MODEL,cars.getModel());
        values.put(Database.CAR_COL_COLOR,cars.getColor());
        values.put(Database.CAR_COL_DISTANCELIT,cars.getDistancelitrer());
        values.put(Database.CAR_COL_IMG,cars.getImage());
        values.put(Database.CAR_COL_DESCRPTION,cars.getDescreption());
        long result = database.insert(Database.TABLE_NAME_DB,null,values);
        return result !=-1;

    }
    public boolean Ubdate_data(Cars cars)
    {

        ContentValues values= new ContentValues();
        values.put(Database.CAR_COL_MODEL,cars.getModel());
        values.put(Database.CAR_COL_COLOR,cars.getColor());
        values.put(Database.CAR_COL_DISTANCELIT,cars.getDistancelitrer());
        values.put(Database.CAR_COL_IMG,cars.getImage());
        values.put(Database.CAR_COL_DESCRPTION,cars.getDescreption());
        String []arg={cars.getId()+""};
        long result=database.update(Database.TABLE_NAME_DB,values,"id=?",arg);
        return result>0;

    }
    public boolean deletdata(Cars cars)
    {


        String []arg={cars.getId()+""};
        int result=database.delete(Database.TABLE_NAME_DB,"id=?",arg);
        return result >0;

    }
    public long getcount(){

        return DatabaseUtils.queryNumEntries(database, Database.TABLE_NAME_DB);
    }
    public ArrayList<Cars> getallcar(){
        ArrayList arrayList= new ArrayList();

        Cursor cursor = database.rawQuery("SELECT * FROM " + Database.TABLE_NAME_DB,null);
        if (cursor !=null &&   cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Database.CAR_COL_ID));
                String model = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_MODEL));
                double distance = cursor.getDouble(cursor.getColumnIndex(Database.CAR_COL_DISTANCELIT));
                String color = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_COLOR));
                String image = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_IMG));
                String description = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_DESCRPTION));
                Cars cars =new Cars(id,model,color,distance,description,image);
                arrayList.add(cars);
            }
            while (cursor.moveToNext());
            cursor.close();

        }


        return arrayList;
    }
    public ArrayList<Cars> serechdata(String serchmodel){
        ArrayList arrayList= new ArrayList();


        Cursor cursor = database.rawQuery("SELECT * FROM " +Database.TABLE_NAME_DB+" WHERE "+ Database.CAR_COL_MODEL+" LIKE ?" ,new String[]{serchmodel+"%"});
        if (cursor !=null &&  cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Database.CAR_COL_ID));
                String model = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_MODEL));
                double distance = cursor.getDouble(cursor.getColumnIndex(Database.CAR_COL_DISTANCELIT));
                String color = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_COLOR));
                String image = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_IMG));
                String description = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_DESCRPTION));
                Cars cars =new Cars(id,model,color,distance,description,image);
                arrayList.add(cars);
            }
            while (cursor.moveToNext());
            cursor.close();

        }


        return arrayList;
    }
    public Cars  getcar(int carid){


        Cursor cursor = database.rawQuery("SELECT * FROM " + Database.TABLE_NAME_DB+" WHERE "+Database.CAR_COL_ID+"=?",new String[]{String.valueOf(carid)});
        if (cursor !=null &&   cursor.moveToFirst())
        {

                int id = cursor.getInt(cursor.getColumnIndex(Database.CAR_COL_ID));
                String model = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_MODEL));
                double distance = cursor.getDouble(cursor.getColumnIndex(Database.CAR_COL_DISTANCELIT));
                String color = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_COLOR));
                String image = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_IMG));
                String description = cursor.getString(cursor.getColumnIndex(Database.CAR_COL_DESCRPTION));
                Cars cars =new Cars(id,model,color,distance,description,image);



            cursor.close();
          return cars;
        }


        return null;
    }
}