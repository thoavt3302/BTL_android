package com.nganlth.bookmanager.Bottom_Sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nganlth.bookmanager.Adapter.Adapter_Theloai;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.adapterTheloai;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.rcv_phanloai;

public class Bottom_Sheet_Add_Theloai extends BottomSheetDialogFragment {
    EditText edt_tenTheLoai, edt_moTa, edt_viTri;
    Button btn_add;
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> ds_theloai;


    public Bottom_Sheet_Add_Theloai() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_theloai,container,false);
        edt_tenTheLoai = v.findViewById(R.id.edTentheloai);
        edt_moTa = v.findViewById(R.id.edMota);
        edt_viTri = v.findViewById(R.id.edVitri);
        btn_add = v.findViewById(R.id.btn_add_phanloai);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTheLoai = edt_tenTheLoai.getText().toString();
                String moTa = edt_moTa.getText().toString();
                String viTri = edt_viTri.getText().toString();
                TheLoai tl = new TheLoai(tenTheLoai,moTa,viTri);
                theLoaiDAO = new TheLoaiDAO(getActivity());
                theLoaiDAO.insert(tl);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
            }
        });
        return v;
    }
        public void capnhat(){
        ds_theloai = new ArrayList<>();
        theLoaiDAO = new TheLoaiDAO(getContext());
        ds_theloai= theLoaiDAO.getTheLoai();
        adapterTheloai = new Adapter_Theloai(getActivity(),ds_theloai);
        rcv_phanloai.setAdapter(adapterTheloai);
    }
}
