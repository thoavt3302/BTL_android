package com.nganlth.bookmanager.Bottom_Sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nganlth.bookmanager.Adapter.Adapter_HDCT;
import com.nganlth.bookmanager.Adapter.Adapter_Sach;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_HoaDon;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_Sach;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_TheLoai;
import com.nganlth.bookmanager.DAO.HoaDonChiTietDAO;
import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.Model.HoaDonChiTiet;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.ChiTietHoaDon.rcvHDCT;
import static com.nganlth.bookmanager.Fragment.Fragment_Hoadon.rcvHoaDon;
import static com.nganlth.bookmanager.Fragment.Fragment_Sach.rcv_sach;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.theLoaiDAO;

public class Bottom_Sheet_Edit_HDCT extends BottomSheetDialogFragment {
    EditText edSoluongHDCT;
    Spinner spHoaDon,spSach;
    Button btnEditHDCT;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HoaDonDAO hoaDonDAO;
    SachDAO sachDAO;
    ArrayList<HoaDonChiTiet> dsHDCT;
    ArrayList<HoaDon> dsHoaDon;
    ArrayList<Sach> dsSach;
    Adapter_Spinner_HoaDon spinnerHoaDon;
    Adapter_Spinner_Sach spinnerSach;
    Adapter_HDCT adapter_hdct;
    public Bottom_Sheet_Edit_HDCT(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_hdct,container,false);
        spHoaDon = v.findViewById(R.id.spHoaDonEdit);
        spSach = v.findViewById(R.id.spSachEdit);
        edSoluongHDCT = v.findViewById(R.id.edSoluongHDCTEdit);
        btnEditHDCT = v.findViewById(R.id.btnEditHdct);
        sachDAO = new SachDAO(getContext());
        final Bundle mArgs = getArguments();
        final  String maHDCT = mArgs.getString("maHDCT");
        final String maHoaDon = mArgs.getString("maHoaDon");
        final String maSach = mArgs.getString("maSach");
        final String soLuongHDCT = mArgs.getString("soLuongHDCT");

//        final String maTheLoai = mArgs.getString("maTheLoai");

        // Nhận dữ liệu gán vào ô editext
        edSoluongHDCT.setText(soLuongHDCT);
        //Spinner Hóa đơn
        dsHoaDon =new ArrayList<>();
        hoaDonDAO = new HoaDonDAO(getContext());
        dsHoaDon=hoaDonDAO.getHoaDon();
        spinnerHoaDon = new Adapter_Spinner_HoaDon(getContext(),dsHoaDon);
        spHoaDon.setAdapter(spinnerHoaDon);
        for (int i=0;i<dsHoaDon.size();i++){
            if(dsHoaDon.get(i).getMaHoaDon().equals(maHoaDon)){
                spHoaDon.setSelection(i);
            }
        }
        //Spinner sách
        dsSach = new ArrayList<>();
        sachDAO = new SachDAO(getContext());
        dsSach=sachDAO.getSach();

        spinnerSach = new Adapter_Spinner_Sach(getContext(),dsSach);
        spSach.setAdapter(spinnerSach);
        for (int i=0;i<dsSach.size();i++){
            if(dsSach.get(i).getMaSach().equals(maSach)){
                spSach.setSelection(i);
            }
        }
        btnEditHDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soLuongHDCT = edSoluongHDCT.getText().toString();

                String maSach = mArgs.getString("maHDCT");

                int indexHD = spHoaDon.getSelectedItemPosition();
                String idHD = dsHoaDon.get(indexHD).getMaHoaDon();

                int indexSach = spSach.getSelectedItemPosition();
                String idSach = dsSach.get(indexHD).getMaSach();

//                Sach sach = new Sach(tieuDe,tacGia,nhaXuatBan,giaBan,soLuong,idLoai);
                hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
                hoaDonChiTietDAO.update(maHDCT,maHoaDon,idSach,soLuongHDCT);
                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
            }
        });

        return v;
    }
    public void capnhat(){
        dsHDCT = new ArrayList<>();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        dsHDCT = hoaDonChiTietDAO.getHDCT();
        adapter_hdct = new Adapter_HDCT(getActivity(),dsHDCT);
        rcvHDCT.setAdapter(adapter_hdct);
    }
}
