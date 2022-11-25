package com.nganlth.bookmanager.Model;

public class TheLoai {
    private String maTheLoai;
    private String tenTheLoai;
    private String moTa;
    private String viTri;

    public TheLoai(String maTheLoai, String tenTheLoai, String moTa, String viTri) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public TheLoai(String tenTheLoai, String moTa, String viTri) {
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getViTri() {
        return viTri;
    }


}
