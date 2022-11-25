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
import com.nganlth.bookmanager.Adapter.Adapter_Theloai;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Add_Theloai;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

public class Fragment_Theloai extends Fragment {
    FloatingActionButton fab_theloai;
    public static RecyclerView rcv_phanloai;
    public static Adapter_Theloai adapterTheloai;
    public static ArrayList<TheLoai> data;
    public static TheLoaiDAO theLoaiDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_theloai,container,false);
            fab_theloai = v.findViewById(R.id.fab_Theloai);
            rcv_phanloai = v.findViewById(R.id.rv_Theloai);

            rcv_phanloai.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            rcv_phanloai.setLayoutManager(linearLayoutManager);

            theLoaiDAO = new TheLoaiDAO(getContext());
            data = new ArrayList<TheLoai>();
            data = theLoaiDAO.getTheLoai();
            adapterTheloai = new Adapter_Theloai(getActivity(),data);
            rcv_phanloai.setAdapter(adapterTheloai);

            fab_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_Sheet_Add_Theloai bottomSheetAddTheloai = new Bottom_Sheet_Add_Theloai();
                bottomSheetAddTheloai.show(getFragmentManager(),"TAG");
            }
        });
        return v;
    }
}
