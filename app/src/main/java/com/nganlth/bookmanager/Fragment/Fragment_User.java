package com.nganlth.bookmanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nganlth.bookmanager.Adapter.Adapter_User;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Add_NguoiDung;
import com.nganlth.bookmanager.DAO.UserDAO;
import com.nganlth.bookmanager.Model.User;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

public class Fragment_User extends Fragment {
    FloatingActionButton fab_User;
    public static RecyclerView rcv_user;
    public static Adapter_User adapterUser;
    public static ArrayList<User> dataUser;
    public static UserDAO userDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nguoidung,container,false);
            fab_User = v.findViewById(R.id.fab_Nguoidung);
            rcv_user = v.findViewById(R.id.rv_User);

            rcv_user.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            rcv_user.setLayoutManager(linearLayoutManager);

            userDAO = new UserDAO(getContext());
            dataUser = new ArrayList<User>();
            dataUser = userDAO.getUser();
            adapterUser = new Adapter_User(getActivity(),dataUser);
            rcv_user.setAdapter(adapterUser);

            fab_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_Sheet_Add_NguoiDung bottomSheetAddNguoidung = new Bottom_Sheet_Add_NguoiDung();
                bottomSheetAddNguoidung.show(getFragmentManager(),"TAG");
            }
        });
        return v;
    }
}
