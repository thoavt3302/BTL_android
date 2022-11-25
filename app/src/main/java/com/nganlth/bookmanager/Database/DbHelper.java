package com.nganlth.bookmanager.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "BookManager.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User(Username text primary key,Password text, Hoten text, Sodienthoai integer)");
        db.execSQL("INSERT INTO User VALUES('H', 'ngan1234','Lê Thị Hiếu Ngân','0793879960')");
        db.execSQL("INSERT INTO User VALUES('Hồ Thị Lan', 'lan1234','Hồ Thị Lan','0986868635')");
        db.execSQL("INSERT INTO User VALUES('Tuấn Vũ', 'vu1234','Lê Tuấn Vũ','0793879960')");

        db.execSQL("CREATE TABLE TheLoai (MaTheLoai integer primary key autoincrement, TenTheLoai text, MoTa text, Vitri text)");
        db.execSQL("INSERT INTO TheLoai VALUES(null, 'Kinh tế','Sách về kinh tế','1')");
        db.execSQL("INSERT INTO TheLoai VALUES(null, 'Công nghệ thông tin','Sách về CNTT','2')");
        db.execSQL("INSERT INTO TheLoai VALUES(null, 'Tiếng Anh','Sách về tiếng Anh','3')");
        db.execSQL("INSERT INTO TheLoai VALUES(null, 'HTML5 & CSS3','Web', '4')");
        db.execSQL("INSERT INTO TheLoai VALUES(null, 'Truyện ngắn','Truyện', '5')");
        db.execSQL("INSERT INTO TheLoai VALUES(null, 'Tiểu thuyết','Tiểu thuyết', '6')");

        db.execSQL("CREATE TABLE Sach (MaSach integer primary key autoincrement, TieuDe text, TacGia text, NhaXuatBan text," +
                "GiaBan double, SoLuong int, MaTheLoai interger references TheLoai(MaTheLoai))");



        db.execSQL("CREATE TABLE HoaDon (MaHoaDon integer primary key autoincrement,TenKhachHang text, Ngaymua date)");

        db.execSQL("INSERT INTO HoaDon(TenKhachHang, Ngaymua) VALUES('Lê Thị Hiếu Ngân','2020-10-24')");
        db.execSQL("INSERT INTO HoaDon(TenKhachHang, Ngaymua) VALUES('Hồ Thị Lan','2020-10-25')");

        db.execSQL("CREATE TABLE HoaDonChiTiet(MaHDCT integer primary key autoincrement," +
                "MaHoaDon interger references HoaDon(MaHoaDon)," +
                "MaSach integer references Sach(MaSach)," +
                "SoLuongHDCT integer)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TheLoai ");
        db.execSQL("DROP TABLE IF EXISTS User ");
        db.execSQL("DROP TABLE IF EXISTS Sach ");
        db.execSQL("DROP TABLE IF EXISTS HoaDon ");
        db.execSQL("DROP TABLE IF EXISTS HoaDonChiTiet ");
    }
}
