package com.nganlth.bookmanager.Model;

public class User {
    private String userName;
    private String passWord;
    private String hoTen;
    private String soDienThoai;

    public User(String userName, String passWord, String hoTen, String soDienThoai) {
        this.userName = userName;
        this.passWord = passWord;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
