package com.nganlth.bookmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nganlth.bookmanager.Database.DbHelper;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.Model.TheLoai;

import java.util.ArrayList;


public class HoaDonDAO {
    public SQLiteDatabase db;
    public DbHelper dbHelper;

    public HoaDonDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<HoaDon> getHoaDon(){
        ArrayList<HoaDon> data = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HoaDon",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maHoaDon = cursor.getString(0);
            String tenKhachHang = cursor.getString(1);
            String ngayMua = cursor.getString(2);
            data.add(new HoaDon(maHoaDon,tenKhachHang,ngayMua));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }
    public void insert(HoaDon hoaDon){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //put dữ liệu
        values.put("maHoaDon",hoaDon.getMaHoaDon());
        values.put("tenKhachHang",hoaDon.getTenKhachHang());
        values.put("ngayMua",hoaDon.getNgayMua());
        db.insert("HoaDon",null,values);

    }
    public boolean update(String maHoaDon, String tenKhachHang, String ngayMua){
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHoaDon",maHoaDon);
        values.put("tenKhachHang",tenKhachHang);
        values.put("ngayMua",ngayMua);
        int row = db.update("HoaDon",values,"maHoaDon=?",new String[]{maHoaDon});
        return row>0;
    }
    public boolean delete(String maHoaDon){
        db = dbHelper.getWritableDatabase();
        int row = db.delete("HoaDon","maHoaDon=?",new String[]{maHoaDon});
        return row>0;
    }

}
