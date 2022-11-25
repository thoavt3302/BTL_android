package com.nganlth.bookmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nganlth.bookmanager.Database.DbHelper;
import com.nganlth.bookmanager.Model.Sach;;

import java.util.ArrayList;


public class SachDAO {
    public SQLiteDatabase db;
    public DbHelper dbHelper;

    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getSach(){
        ArrayList<Sach> dataSach = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Sach",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maSach = cursor.getString(0);
            String tieuDe = cursor.getString(1);
            String tacGia = cursor.getString(2);
            String nhaXuatBan = cursor.getString(3);
            String giaBan = cursor.getString(4);
            String soLuong = cursor.getString(5);
            String maTheLoai = cursor.getString(6);
            dataSach.add(new Sach(maSach,tieuDe,tacGia,nhaXuatBan,giaBan,soLuong,maTheLoai));
            cursor.moveToNext();
        }
        cursor.close();
        return dataSach;
    }
    public void insert(Sach sach){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //put dữ liệu
        values.put("maTheLoai",sach.getMaTheLoai());
        values.put("tieuDe",sach.getTieuDe());
        values.put("tacGia",sach.getTacGia());
        values.put("nhaXuatBan",sach.getNhaXuatBan());
        values.put("giaBan",sach.getGiaBan());
        values.put("soLuong",sach.getSoLuong());
        values.put("maTheLoai",sach.getMaTheLoai());
        db.insert("Sach",null,values);

    }
    public boolean update(String maSach, String tieuDe,String tacGia, String nhaXuatBan,String giaBan, String soLuong,String maTheLoai){
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("tieuDe",tieuDe);
        values.put("tacGia",tacGia);
        values.put("nhaXuatBan",nhaXuatBan);
        values.put("giaBan",giaBan);
        values.put("soLuong",soLuong);
        values.put("maTheLoai",maTheLoai);
        int row = db.update("Sach",values,"maSach=?",new String[]{maSach});
        return row>0;
    }
    public boolean delete(String maSach){
        db = dbHelper.getWritableDatabase();
        int row = db.delete("Sach","maSach=?",new String[]{maSach});
        return row>0;
    }
    public ArrayList<Sach> getSachTop10(String month){
        db = dbHelper.getWritableDatabase();
        ArrayList<Sach> dsSach = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month = "0"+month;
        }
        String sSQL = "SELECT MaSach, SUM(SoLuongHDCT) as SoLuongHDCT FROM HoaDonChiTiet INNER JOIN HoaDon " + "ON HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon WHERE strftime('%m',HoaDon.Ngaymua) = '"+month+"' " + "GROUP BY MaSach ORDER BY SoLuongHDCT DESC LIMIT 10";
        Cursor cursor = db.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Log.d("//=====",cursor.getString(0));
            Sach s = new Sach();
            s.setMaSach(cursor.getString(0));
            s.setSoLuong(cursor.getString(1));
            s.setGiaBan("0");
            s.setMaTheLoai("");
            s.setTieuDe("");
            s.setTacGia("");
            s.setNhaXuatBan("");
            dsSach.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        return dsSach;
    }

}
