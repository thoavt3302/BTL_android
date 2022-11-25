package com.nganlth.bookmanager.Bottom_Sheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nganlth.bookmanager.Adapter.Adapter_HDCT;
import com.nganlth.bookmanager.Adapter.Adapter_HoaDon;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_HoaDon;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_Sach;
import com.nganlth.bookmanager.ChiTietHoaDon;
import com.nganlth.bookmanager.DAO.HoaDonChiTietDAO;
import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.Model.HoaDonChiTiet;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.R;
import java.util.ArrayList;
import static com.nganlth.bookmanager.ChiTietHoaDon.rcvHDCT;


public class Bottom_Sheet_Add_HDCT extends BottomSheetDialogFragment {
    EditText edSoluongHDCT, edMaHoaDon;
    Spinner spHoaDon,spSach;
    Button btnAddHDCT,btnthanhtoan;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HoaDonDAO hoaDonDAO;
    SachDAO sachDAO;
    ArrayList<HoaDonChiTiet> dsHDCT;
    ArrayList<HoaDon> dsHoaDon;
    ArrayList<Sach> dsSach;
//    Adapter_Spinner_HoaDon spinnerHoaDon;
    Adapter_Spinner_Sach spinnerSach;
    Adapter_HDCT adapter_hdct;
    Adapter_HoaDon adapterHoaDon;
    String maHD, ngayMua,tenKhachHang;
    public Bottom_Sheet_Add_HDCT() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_hdct,container,false);
        spSach = v.findViewById(R.id.spSach);
        edMaHoaDon = v.findViewById(R.id.edMaHoaDon);
        edSoluongHDCT = v.findViewById(R.id.edSoluongHDCT);
        btnAddHDCT = v.findViewById(R.id.btnAddHdct);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        // Spinner Sach
        sachDAO = new SachDAO(getContext());
        dsSach = sachDAO.getSach();
        spinnerSach = new Adapter_Spinner_Sach(getContext(),dsSach);
        spSach.setAdapter(spinnerSach);
        adapterHoaDon = new Adapter_HoaDon(getContext(),dsHoaDon);
        hoaDonDAO = new HoaDonDAO(getContext());
        Intent i = getActivity().getIntent();
        Bundle bundle1 = i.getExtras();
        maHD = bundle1.getString("maHoaDon");
        edMaHoaDon.setText(maHD);
        btnAddHDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sachDAO = new SachDAO(getActivity());
            dsSach = new ArrayList<>();
            dsSach = sachDAO.getSach();
                String soLuong = edSoluongHDCT.getText().toString();
                String idHoadon = edMaHoaDon.getText().toString();
                int indexSach = spSach.getSelectedItemPosition();
                String idSach = dsSach.get(indexSach).getMaSach();
                HoaDonChiTiet hdct = new HoaDonChiTiet(idHoadon, idSach,soLuong);
                hoaDonChiTietDAO.insert(hdct);
                dsHDCT = new ArrayList<HoaDonChiTiet>();
                dsHDCT = hoaDonChiTietDAO.getHDCTById(maHD);
                adapter_hdct = new Adapter_HDCT(getContext(), dsHDCT);
                rcvHDCT.setAdapter(adapter_hdct);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });
        return v;
    }

}
