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
import com.nganlth.bookmanager.Adapter.Adapter_User;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.DAO.UserDAO;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.Model.User;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.adapterTheloai;
import static com.nganlth.bookmanager.Fragment.Fragment_Theloai.rcv_phanloai;
import static com.nganlth.bookmanager.Fragment.Fragment_User.adapterUser;
import static com.nganlth.bookmanager.Fragment.Fragment_User.rcv_user;

public class Bottom_Sheet_Edit_TheLoai extends BottomSheetDialogFragment {
    EditText edTenTheLoai,edMoTa, edViTri;
    Button btnSave;
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> dsTheLoai;

    public Bottom_Sheet_Edit_TheLoai(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_theloai,container,false);
        edTenTheLoai = v.findViewById(R.id.edTentheloai);
        edMoTa = v.findViewById(R.id.edMota);
        edViTri = v.findViewById(R.id.edVitri);
        btnSave = v.findViewById(R.id.btnSave);

        theLoaiDAO = new TheLoaiDAO(getContext());
        Bundle mArgs = getArguments();
        final String maTheLoai = mArgs.getString("maTheLoai");
        String tenTheLoai = mArgs.getString("tenTheLoai");
        String moTa = mArgs.getString("moTa");
        String viTri = mArgs.getString("viTri");

        // Nhận dữ liệu gán vào ô editext
        edTenTheLoai.setText(tenTheLoai);
        edTenTheLoai.setEnabled(false);
        edMoTa.setText(moTa);
        edViTri.setText(viTri);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTheLoai = edTenTheLoai.getText().toString();
                String moTa = edMoTa.getText().toString();
                String viTri = edViTri.getText().toString();
                TheLoai theLoai = new TheLoai(tenTheLoai,moTa,viTri);
                theLoaiDAO.update(maTheLoai,tenTheLoai,moTa,viTri);
                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
            }
        });

        return v;
    }
    public void capnhat(){
        dsTheLoai = new ArrayList<>();
        theLoaiDAO = new TheLoaiDAO(getContext());
        dsTheLoai= theLoaiDAO.getTheLoai();
        adapterTheloai = new Adapter_Theloai(getActivity(),dsTheLoai);
        rcv_phanloai.setAdapter(adapterTheloai);
    }
}
