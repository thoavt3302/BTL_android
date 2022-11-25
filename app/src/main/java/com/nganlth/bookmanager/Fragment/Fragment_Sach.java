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
import com.nganlth.bookmanager.Adapter.Adapter_Sach;
import com.nganlth.bookmanager.Adapter.Adapter_Theloai;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Add_Sach;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.DAO.TheLoaiDAO;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

public class Fragment_Sach extends Fragment {
    FloatingActionButton fabThemSach;
    public static RecyclerView rcv_sach;
    public static Adapter_Sach adapterSach;
    public static ArrayList<Sach> data_sach;
    public static SachDAO sachDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sach,container,false);
        fabThemSach = v.findViewById(R.id.fab_Sach);
        rcv_sach=v.findViewById(R.id.rv_Sach);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcv_sach.setHasFixedSize(true);
        rcv_sach.setLayoutManager(layoutManager);
        data_sach=new ArrayList<>();
        sachDAO = new SachDAO(getActivity());
        data_sach=sachDAO.getSach();
        adapterSach=new Adapter_Sach(getActivity(),data_sach);

        rcv_sach.setAdapter(adapterSach);

        fabThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_Sheet_Add_Sach bottomSheetAddSach = new Bottom_Sheet_Add_Sach();
                bottomSheetAddSach.show(getFragmentManager(),"TAG");
            }
        });
        return v;
    }
}
