package com.nganlth.bookmanager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nganlth.bookmanager.Adapter.Adapter_HoaDon;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Add_HDCT;
import com.nganlth.bookmanager.Bottom_Sheet.Bottom_Sheet_Add_HoaDon;
import com.nganlth.bookmanager.ChiTietHoaDon;
import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.Model.HoaDonChiTiet;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;


public class Fragment_Hoadon extends Fragment {
    FloatingActionButton fabHoaDon;
    public static RecyclerView rcvHoaDon;
    public static Adapter_HoaDon adapterHoaDon;
    public static ArrayList<HoaDon> data;
    public static HoaDonDAO hoaDonDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hoadon,container,false);
        fabHoaDon = v.findViewById(R.id.fabHoaDon);
        rcvHoaDon = v.findViewById(R.id.rvHoaDon);

        rcvHoaDon.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcvHoaDon.setLayoutManager(linearLayoutManager);

        hoaDonDAO = new HoaDonDAO(getContext());
        data = new ArrayList<HoaDon>();
        data = hoaDonDAO.getHoaDon();
        adapterHoaDon = new Adapter_HoaDon(getActivity(),data);
        rcvHoaDon.setAdapter(adapterHoaDon);

        fabHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_Sheet_Add_HoaDon bottomSheetAddHoaDon = new Bottom_Sheet_Add_HoaDon();
                bottomSheetAddHoaDon.show(getFragmentManager(),"TAG");
            }
        });
        adapterHoaDon.setOnItemClickListener(new Adapter_HoaDon.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                HoaDon item = data.get(position);
                Intent i = new Intent(getContext(), ChiTietHoaDon.class);
                Bundle b = new Bundle();
                b.putString("MaHoaDon", item.getMaHoaDon());
                i.putExtras(b);
                startActivity(i);
            }
        });
        return v;
    }
}
