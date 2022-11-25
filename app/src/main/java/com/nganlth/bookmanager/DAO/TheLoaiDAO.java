package com.nganlth.bookmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.nganlth.bookmanager.Database.DbHelper;
import com.nganlth.bookmanager.Model.TheLoai;

import java.util.ArrayList;


public class TheLoaiDAO  {
    public SQLiteDatabase db;
    public DbHelper dbHelper;

    public TheLoaiDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<TheLoai> getTheLoai(){
        ArrayList<TheLoai> data = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TheLoai",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maTheLoai = cursor.getString(0);
            String tenTheLoai = cursor.getString(1);
            String moTa = cursor.getString(2);
            String viTri = cursor.getString(3);
            data.add(new TheLoai(maTheLoai,tenTheLoai,moTa,viTri));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }
    public void insert(TheLoai theLoai){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //put dữ liệu
        values.put("tenTheLoai",theLoai.getTenTheLoai());
        values.put("moTa",theLoai.getMoTa());
        values.put("viTri",theLoai.getViTri());
        db.insert("TheLoai",null,values);

    }
    public boolean update(String maTheLoai, String tenTheLoai, String moTa, String viTri){
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenTheLoai",tenTheLoai);
        values.put("moTa",moTa);
        values.put("viTri",viTri);

        int row = db.update("TheLoai",values,"maTheLoai=?",new String[]{maTheLoai});
        return row>0;
    }
    public boolean delete(String maTheLoai){
        db = dbHelper.getWritableDatabase();
        int row = db.delete("TheLoai","maTheLoai=?",new String[]{maTheLoai});
        return row>0;
    }

}
