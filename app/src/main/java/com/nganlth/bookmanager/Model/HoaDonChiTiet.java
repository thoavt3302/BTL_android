package com.nganlth.bookmanager.Model;

public class HoaDonChiTiet {
    private String maHDCT;
    private String maHoaDon;
    private String maSach;
    private String soLuongHDCT;


    public HoaDonChiTiet() {
    }


    public HoaDonChiTiet(String maHoaDon, String maSach, String soLuongHDCT) {
        this.maHoaDon = maHoaDon;
        this.maSach = maSach;
        this.soLuongHDCT = soLuongHDCT;
    }

    public HoaDonChiTiet(String maHDCT, String maHoaDon, String maSach, String soLuongHDCT) {
        this.maHDCT = maHDCT;
        this.maHoaDon = maHoaDon;
        this.maSach = maSach;
        this.soLuongHDCT = soLuongHDCT;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getSoLuongHDCT() {
        return soLuongHDCT;
    }

    public void setSoLuongHDCT(String soLuongHDCT) {
        this.soLuongHDCT = soLuongHDCT;
    }


}
