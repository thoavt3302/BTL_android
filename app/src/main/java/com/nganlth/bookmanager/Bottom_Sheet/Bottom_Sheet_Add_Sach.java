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
import com.nganlth.bookmanager.Adapter.Adapter_Sach;
import com.nganlth.bookmanager.Adapter.Adapter_Spinner_TheLoai;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;
import java.util.ArrayList;
import static com.nganlth.bookmanager.Fragment.Fragment_Sach.rcv_sach;


public class Bottom_Sheet_Add_Sach extends BottomSheetDialogFragment {
    EditText edTieuDe, edTacGia, edNhaXuatBan,edGiaBan, edSoLuong;
    Spinner spTheLoai;
    Button btnAddSach;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> dsTheLoai;
    ArrayList<Sach> dsSach;
    Adapter_Spinner_TheLoai spinnerTheLoai;
    Adapter_Sach adapterSach;

    public Bottom_Sheet_Add_Sach() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_sach,container,false);
        edTieuDe = v.findViewById(R.id.edTieude);
        edTacGia = v.findViewById(R.id.edTacgia);
        edNhaXuatBan = v.findViewById(R.id.edNXB);
        edGiaBan = v.findViewById(R.id.edGiaban);
        edSoLuong = v.findViewById(R.id.edSoluong);
        btnAddSach = v.findViewById(R.id.btn_add_sach);
        spTheLoai = v.findViewById(R.id.spTheLoai);


        theLoaiDAO = new TheLoaiDAO(getContext());
        dsTheLoai = theLoaiDAO.getTheLoai();

        spinnerTheLoai = new Adapter_Spinner_TheLoai(getContext(),dsTheLoai);
        spTheLoai.setAdapter(spinnerTheLoai);


        btnAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieuDe = edTieuDe.getText().toString();
                String tacGia = edTacGia.getText().toString();
                String nhaXuatBan = edNhaXuatBan.getText().toString();
                String giaBan = edGiaBan.getText().toString();
                String soLuong = edSoLuong.getText().toString();

                int index = spTheLoai.getSelectedItemPosition();
                String idLoai = dsTheLoai.get(index).getTenTheLoai();

                Sach sach = new Sach(tieuDe,tacGia,nhaXuatBan,giaBan,soLuong,idLoai);
                theLoaiDAO = new TheLoaiDAO(getActivity());
                dsSach = new ArrayList<>();
                sachDAO=new SachDAO(getContext());
                sachDAO.insert(sach);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
            }
        });
        return v;
    }
        public void capnhat(){
        sachDAO = new SachDAO(getContext());
        dsSach= sachDAO.getSach();
        adapterSach = new Adapter_Sach(getActivity(),dsSach);
        rcv_sach.setAdapter(adapterSach);
    }
}
