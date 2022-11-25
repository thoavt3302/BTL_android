package com.nganlth.bookmanager.Model;

public class Sach {
    private String maSach;
    private String tieuDe;
    private String tacGia;
    private String nhaXuatBan;
    private String giaBan;
    private String soLuong;
    private String maTheLoai;

    public Sach() {
    }

    public Sach(String maSach, String tieuDe, String tacGia, String nhaXuatBan, String giaBan, String soLuong, String maTheLoai) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.maTheLoai = maTheLoai;
    }

    public Sach(String tieuDe, String tacGia, String nhaXuatBan, String giaBan, String soLuong, String maTheLoai) {
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.maTheLoai = maTheLoai;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }
}
