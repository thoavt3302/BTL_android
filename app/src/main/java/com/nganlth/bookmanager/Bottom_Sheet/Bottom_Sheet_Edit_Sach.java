package com.nganlth.bookmanager.Bottom_Sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nganlth.bookmanager.Adapter.Adapter_Sach;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_TheLoai;
import com.nganlth.bookmanager.Adapter.Adapter_Theloai;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_Sach.rcv_sach;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.adapterTheloai;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.rcv_phanloai;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.theLoaiDAO;

public class Bottom_Sheet_Edit_Sach extends BottomSheetDialogFragment {
    EditText edTieuDe,edTacGia, edNXB, edGiaBan, edSoLuong;
    Button btnSave;
    Spinner spTheLoai;
    SachDAO sachDAO;
    ArrayList<TheLoai> dsTheLoai;
    ArrayList<Sach> dsSach;
    Adapter_Spinner_TheLoai spinnerTheLoai;
    Adapter_Sach adapterSach;
    public Bottom_Sheet_Edit_Sach(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_sach,container,false);
        edTieuDe = v.findViewById(R.id.edTieude_edit);
        edTacGia = v.findViewById(R.id.edTacgia_edit);
        edNXB = v.findViewById(R.id.edNXB_edit);
        edGiaBan = v.findViewById(R.id.edGiaban_edit);
        edSoLuong = v.findViewById(R.id.edSoluong_edit);
        btnSave = v.findViewById(R.id.btnEditSach);
        spTheLoai = v.findViewById(R.id.spTheLoai_edit);

        sachDAO = new SachDAO(getContext());
        final Bundle mArgs = getArguments();
        String tieuDe = mArgs.getString("tieuDe");
        String tacGia = mArgs.getString("tacGia");
        String nhaXuatBan = mArgs.getString("nhaXuatBan");
        String giaBan = mArgs.getString("giaBan");
        String soLuong = mArgs.getString("soLuong");
        final String maTheLoai = mArgs.getString("maTheLoai");

        // Nhận dữ liệu gán vào ô editext
        edTieuDe.setText(tieuDe);
        edTacGia.setText(tacGia);
        edNXB.setText(nhaXuatBan);
        edGiaBan.setText(giaBan);
        edSoLuong.setText(soLuong);
        dsTheLoai =new ArrayList<>();
        theLoaiDAO = new TheLoaiDAO(getContext());
        dsTheLoai=theLoaiDAO.getTheLoai();
        spinnerTheLoai = new Adapter_Spinner_TheLoai(getContext(),dsTheLoai);
        spTheLoai.setAdapter(spinnerTheLoai);
        for (int i=0;i<dsTheLoai.size();i++){
            if(dsTheLoai.get(i).getTenTheLoai().equals(maTheLoai)){
                spTheLoai.setSelection(i);
            }
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieuDe = edTieuDe.getText().toString();
                String tacGia = edTacGia.getText().toString();
                String nhaXuatBan = edNXB.getText().toString();
                String giaBan = edGiaBan.getText().toString();
                String soLuong = edSoLuong.getText().toString();
                String maSach = mArgs.getString("maSach");

                int index = spTheLoai.getSelectedItemPosition();
                String idLoai = dsTheLoai.get(index).getTenTheLoai();

//                Sach sach = new Sach(tieuDe,tacGia,nhaXuatBan,giaBan,soLuong,idLoai);
                sachDAO.update(maSach,tieuDe,tacGia,nhaXuatBan,giaBan,soLuong,idLoai);
                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
            }
        });

        return v;
    }
    public void capnhat(){
        dsSach = new ArrayList<>();
        sachDAO = new SachDAO(getContext());
        dsSach= sachDAO.getSach();
        adapterSach = new Adapter_Sach(getActivity(),dsSach);
        rcv_sach.setAdapter(adapterSach);
    }
}
