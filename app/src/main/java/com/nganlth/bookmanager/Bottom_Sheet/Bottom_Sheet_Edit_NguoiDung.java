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
import com.nganlth.bookmanager.Adapter.Adapter_User;
import com.nganlth.bookmanager.DAO.UserDAO;
import com.nganlth.bookmanager.Model.User;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

import static com.nganlth.bookmanager.Fragment.Fragment_User.adapterUser;
import static com.nganlth.bookmanager.Fragment.Fragment_User.rcv_user;

public class Bottom_Sheet_Edit_NguoiDung extends BottomSheetDialogFragment {
    EditText edUsername,edPass, edSoDienThoai, edHoTen;
    Button btnSave;
    UserDAO userDAO;
    ArrayList<User> dsUser;

    public Bottom_Sheet_Edit_NguoiDung(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_nguoidung,container,false);
        edUsername = v.findViewById(R.id.edUsername);
        edPass = v.findViewById(R.id.edPassword);
        edSoDienThoai = v.findViewById(R.id.edSodienthoai);
        edHoTen = v.findViewById(R.id.edHoTen);
        btnSave = v.findViewById(R.id.btnSave);

        userDAO = new UserDAO(getContext());
        Bundle mArgs = getArguments();
        final String userName = mArgs.getString("userName");
        final String passWord = mArgs.getString("passWord");
        final String hoTen = mArgs.getString("hoTen");
        final String soDienThoai = mArgs.getString("soDienThoai");
        // Nhận dữ liệu gán vào ô editext
        edUsername.setText(userName);
        edPass.setText(passWord);
        edHoTen.setText(hoTen);
        edSoDienThoai.setText(soDienThoai);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUsername.getText().toString();
                String passWord = edPass.getText().toString();
                String hoTen = edHoTen.getText().toString();
                String soDienThoai = edSoDienThoai.getText().toString();
                User user = new User(userName,passWord,hoTen,soDienThoai);
                userDAO.update(userName,passWord,hoTen,soDienThoai);
                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
            }
        });
        return v;
    }
    public void capnhat(){
        userDAO = new UserDAO(getContext());
        dsUser= userDAO.getUser();
        adapterUser = new Adapter_User(getActivity(),dsUser);
        rcv_user.setAdapter(adapterUser);
    }
}
