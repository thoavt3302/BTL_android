package com.nganlth.bookmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nganlth.bookmanager.Adapter.Adapter_HDCT;
import com.nganlth.bookmanager.Adapter.Adapter_HoaDon;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_Sach;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Add_HDCT;
import com.nganlth.bookmanager.DAO.HoaDonChiTietDAO;
import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.Model.HoaDonChiTiet;
import com.nganlth.bookmanager.Model.Sach;

import java.util.ArrayList;

public class ChiTietHoaDon extends AppCompatActivity {

    FloatingActionButton fabHDCT;
    public static RecyclerView rcvHDCT;
    Adapter_HDCT adapter_hdct;
    ArrayList<HoaDonChiTiet> dataHDCT = new ArrayList<>();
    HoaDonChiTietDAO hoaDonChiTietDAO;

    HoaDonDAO hoaDonDAO;
    SachDAO sachDAO;
    ArrayList<HoaDonChiTiet> dsHDCT;
    ArrayList<HoaDon> dsHoaDon;
    ArrayList<Sach> dsSach;
    //    Adapter_Spinner_HoaDon spinnerHoaDon;
    Adapter_Spinner_Sach spinnerSach;

    Adapter_HoaDon adapterHoaDon;
    String maHD, ngayMua, tenKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        fabHDCT = findViewById(R.id.fabHDCT);
        rcvHDCT = findViewById(R.id.rvHDCT);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChiTietHoaDon.this, LinearLayoutManager.VERTICAL, false);
        rcvHDCT.setHasFixedSize(true);
        rcvHDCT.setLayoutManager(layoutManager);

        hoaDonChiTietDAO = new HoaDonChiTietDAO(ChiTietHoaDon.this);
        Intent i = getIntent();
        Bundle bundle1 = i.getExtras();
        maHD = bundle1.getString("maHoaDon");
        dataHDCT = hoaDonChiTietDAO.getHDCTById(maHD);
//        dataHDCT = hoaDonChiTietDAO.getHDCT();
        Toast.makeText(this, "hhh"+dataHDCT.size(), Toast.LENGTH_SHORT).show();
        adapter_hdct = new Adapter_HDCT(ChiTietHoaDon.this, dataHDCT);
        rcvHDCT.setAdapter(adapter_hdct);

        fabHDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_Sheet_Add_HDCT bottomSheetAddHdct = new Bottom_Sheet_Add_HDCT();
                bottomSheetAddHdct.show(getSupportFragmentManager(),"TAG");

            }

        });
    }
    public void capnhatHDCT(){
        dataHDCT = hoaDonChiTietDAO.getHDCTById(maHD);
        adapter_hdct = new Adapter_HDCT(ChiTietHoaDon.this, dataHDCT);
        rcvHDCT.setAdapter(adapter_hdct);
    }
}
