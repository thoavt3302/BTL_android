package com.nganlth.bookmanager.Model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    String maHoaDon;
    String tenKhachHang;
    String ngayMua;

    public HoaDon(String maHoaDon, String tenKhachHang, String ngayMua) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.ngayMua = ngayMua;
    }

    public HoaDon(String tenKhachHang, String ngayMua) {
        this.tenKhachHang = tenKhachHang;
        this.ngayMua = ngayMua;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }
}
