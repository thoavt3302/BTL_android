package com.nganlth.bookmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nganlth.bookmanager.Database.DbHelper;
import com.nganlth.bookmanager.Model.HoaDonChiTiet;

import java.util.ArrayList;


public class HoaDonChiTietDAO {
    public SQLiteDatabase db;
    public DbHelper dbHelper;
    public HoaDonChiTietDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<HoaDonChiTiet> getHDCT(){
        ArrayList<HoaDonChiTiet> data = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        String sSQL = "SELECT hdct.MaHDCT, hdct.MaHoaDon, s.MaSach,s.TieuDe, s.GiaBan, hdct.SoLuongHDCT, hdct.SoLuongHDCT*s.GiaBan as ThanhTien FROM  HoaDonChiTiet hdct JOIN Sach s ON hdct.MaSach=s.MaSach";
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            HoaDonChiTiet ee = new HoaDonChiTiet();
            ee.setMaHDCT(cursor.getString(0));
            ee.setMaHoaDon(cursor.getString(1));
            ee.setMaSach(cursor.getString(2));
            ee.setSoLuongHDCT(cursor.getString(5));
            data.add(ee);
            Log.d("//=====",ee.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }

    public ArrayList<HoaDonChiTiet> getHDCTById(String maHoaDon){
        ArrayList<HoaDonChiTiet> data = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        String sSQL = "SELECT hdct.MaHDCT, hdct.MaHoaDon, s.MaSach,s.TieuDe, s.GiaBan, hdct.SoLuongHDCT, hdct.SoLuongHDCT*s.GiaBan as ThanhTien FROM  HoaDonChiTiet hdct JOIN Sach s ON hdct.MaSach=s.MaSach where MaHoaDon ='"+maHoaDon+"'";
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            HoaDonChiTiet ee = new HoaDonChiTiet();
            ee.setMaHDCT(cursor.getString(0));
            ee.setMaHoaDon(cursor.getString(1));
            ee.setMaSach(cursor.getString(2));
            ee.setSoLuongHDCT(cursor.getString(5));
            data.add(ee);
            Log.d("//=====",ee.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }
    public void insert(HoaDonChiTiet hoaDonChiTiet){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //put dữ liệu
        values.put("maHoaDon",hoaDonChiTiet.getMaHoaDon());
        values.put("maSach",hoaDonChiTiet.getMaSach());
        values.put("soLuongHDCT",hoaDonChiTiet.getSoLuongHDCT());
        db.insert("HoaDonChiTiet",null,values);

    }
    public boolean update(String maHDCT, String maHoaDon,String maSach, String soLuongHDCT){
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHoaDon",maHoaDon);
        values.put("maSach",maSach);
        values.put("soLuongHDCT",soLuongHDCT);
        int row = db.update("HoaDonChiTiet",values,"maHDCT=?",new String[]{maHDCT});
        return row>0;
    }
    public boolean delete(String maHDCT){
        db = dbHelper.getWritableDatabase();
        int row = db.delete("HoaDonChiTiet","maHDCT=?",new String[]{maHDCT});
        return row>0;
    }
    public double getDoanhThuTheoNgay(){
        db = dbHelper.getReadableDatabase();
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.GiaBan * HoaDonChiTiet.SoLuongHDCT) as 'tongtien' " + "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon " + "INNER JOIN Sach on HoaDonChiTiet.MaSach = Sach.MaSach where HoaDon.Ngaymua = date('now') GROUP BY HoaDonChiTiet.MaSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.GiaBan * HoaDonChiTiet.SoLuongHDCT) as 'tongtien' " + "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon " + "INNER JOIN Sach on HoaDonChiTiet.MaSach = Sach.MaSach where strftime('%m',HoaDon.Ngaymua) = strftime('%m','now') GROUP BY HoaDonChiTiet.MaSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        double doanhThu = 0;
            String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.GiaBan * HoaDonChiTiet.SoLuongHDCT) as 'tongtien' " + "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon " + "INNER JOIN Sach on HoaDonChiTiet.MaSach = Sach.MaSach where strftime('%Y',HoaDon.Ngaymua) = strftime('%Y','now') GROUP BY HoaDonChiTiet.MaSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

}
