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
import com.nganlth.bookmanager.Adapter.Adapter_User;
import com.nganlth.bookmanager.DAO.UserDAO;
import com.nganlth.bookmanager.Model.User;
import com.nganlth.bookmanager.R;
import java.util.ArrayList;
import static com.nganlth.bookmanager.Fragment.Fragment_User.adapterUser;
import static com.nganlth.bookmanager.Fragment.Fragment_User.rcv_user;

public class Bottom_Sheet_Add_NguoiDung extends BottomSheetDialogFragment {
    EditText edt_Username, edt_hoTen, edt_Password,edt_Sodienthoai;
    Button btn_add_User;
    UserDAO userDAO;
    ArrayList<User> ds_User;
    public Bottom_Sheet_Add_NguoiDung() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_nguoidung,container,false);
        edt_Username = v.findViewById(R.id.edUsername);
        edt_Password = v.findViewById(R.id.edPassword);
        edt_hoTen = v.findViewById(R.id.edHoTen);
        edt_Sodienthoai = v.findViewById(R.id.edSodienthoai);
        btn_add_User = v.findViewById(R.id.btn_add_User);

        btn_add_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edt_Username.getText().toString();
                String hoTen = edt_hoTen.getText().toString();
                String soDienThoai = edt_Sodienthoai.getText().toString();
                User user = new User(userName,null,hoTen,soDienThoai);
                userDAO = new UserDAO(getActivity());
                ds_User = new ArrayList<>();
                userDAO.insert(user);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
            }
        });
        return v;
    }
        public void capnhat(){
        userDAO = new UserDAO(getContext());
        ds_User= userDAO.getUser();
        adapterUser = new Adapter_User(getActivity(),ds_User);
            rcv_user.setAdapter(adapterUser);
    }
}
