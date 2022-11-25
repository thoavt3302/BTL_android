package com.nganlth.bookmanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nganlth.bookmanager.DAO.HoaDonChiTietDAO;
import com.nganlth.bookmanager.R;

public class Fragment_Thongke extends Fragment {
    TextView tkNgay, tkThang, tkNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thongke,container,false);

        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottom_nav);
        if (savedInstanceState == null){
            loadFragment(new Bottom_ThongKeTheoNgay());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.bottomThongke:
                        // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fr_, new Fragment_thongke()).commit();
                        fragment = new Bottom_ThongKeTheoNgay();
                        loadFragment(fragment);
                        break;
                    case R.id.bottomSach:
                        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fr_, new Fragment_tk_chi()).commit();
                        fragment = new Bottom_ThongKeTheoSach();
                        loadFragment(fragment);
                        break;
                }
                return false;
            }
        });

        return v;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_tk, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
