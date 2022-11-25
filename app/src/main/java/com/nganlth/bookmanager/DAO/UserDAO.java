package com.nganlth.bookmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nganlth.bookmanager.Database.DbHelper;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.Model.User;

import java.util.ArrayList;

public class UserDAO {
    public SQLiteDatabase db;
    public DbHelper dbhp;

    public UserDAO(Context context) {
        dbhp = new DbHelper(context);
    }
    public void register(User user){
        db = dbhp.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", user.getUserName());
        values.put("passWord", user.getPassWord());
        long row = db.insert("User",null, values);
    }
    public static boolean checkLogin(Context context, String userName, String pass){
            DbHelper helper1 = new DbHelper(context);
            SQLiteDatabase db = helper1.getReadableDatabase();
            String sql = "SELECT * FROM User WHERE Username LIKE '" + userName + "' AND Password LIKE '" + pass +"'";
            Cursor cs = db.rawQuery(sql, null);
            if (cs.getCount() <= 0){
                return false;
            }
            return true;
        }
    public boolean changepass(String userName, String newPassWord){
        db = dbhp.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("Password", newPassWord);
        int row = db.update("User", values,"Username=?",new String[]{userName} );
        return row>0 ;
    }
    public ArrayList<User> getUser(){
        ArrayList<User> dataUser = new ArrayList<>();
        db = dbhp.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String user = cursor.getString(0);
            String pass = cursor.getString(1);
            String hoTen = cursor.getString(2);
            String soDienThoai = cursor.getString(3);
            dataUser.add(new User(user,pass,hoTen,soDienThoai));
            cursor.moveToNext();
        }
        cursor.close();
        return dataUser;
    }
    public void insert(User user){
        db = dbhp.getWritableDatabase();
        ContentValues values = new ContentValues();
        //put dữ liệu
        values.put("Username",user.getUserName());
        values.put("Password",user.getPassWord());
        values.put("Hoten",user.getHoTen());
        values.put("Sodienthoai",user.getSoDienThoai());
        db.insert("User",null,values);

    }
    public boolean update(String userName, String passWord, String hoTen, String soDienThoai){
        db = dbhp.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username",userName);
        values.put("Password",passWord);
        values.put("Hoten",hoTen);
        values.put("Sodienthoai",soDienThoai);

        int row = db.update("User",values,"Username=?",new String[]{userName});
        return row>0;
    }
    public boolean delete(String userName){
        db = dbhp.getWritableDatabase();
        int row = db.delete("User","Username=?",new String[]{userName});
        return row>0;
    }

}
