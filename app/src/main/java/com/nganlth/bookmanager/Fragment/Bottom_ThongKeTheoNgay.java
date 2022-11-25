package com.nganlth.bookmanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nganlth.bookmanager.DAO.HoaDonChiTietDAO;
import com.nganlth.bookmanager.R;

public class Bottom_ThongKeTheoNgay extends Fragment {
    TextView tkNgay, tkThang, tkNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_thongkengay,container,false);
        tkNgay = v.findViewById(R.id.day);
        tkThang = v.findViewById(R.id.month);
        tkNam = v.findViewById(R.id.year);

        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        tkNgay.setText("Theo ngày: "+hoaDonChiTietDAO.getDoanhThuTheoNgay()+"");
        tkThang.setText("Theo tháng: "+hoaDonChiTietDAO.getDoanhThuTheoThang()+"");
        tkNam.setText("Theo năm: " +hoaDonChiTietDAO.getDoanhThuTheoNam()+"");

        return v;
    }
}
