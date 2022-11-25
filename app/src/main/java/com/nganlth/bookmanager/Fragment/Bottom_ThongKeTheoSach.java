package com.nganlth.bookmanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nganlth.bookmanager.Adapter.Adapter_Sach;
import com.nganlth.bookmanager.DAO.HoaDonChiTietDAO;
import com.nganlth.bookmanager.DAO.SachDAO;
import com.nganlth.bookmanager.Model.Sach;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;
import java.util.List;

public class Bottom_ThongKeTheoSach extends Fragment {
    public static List<Sach> dsSach = new ArrayList<>();
    RecyclerView rcvBook;
    Adapter_Sach adapter = null;
    SachDAO sachDAO;
    EditText edThang;
    Button btnSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_thongkesach,container,false);
        rcvBook = v.findViewById(R.id.rcvBookTop);
        edThang = v.findViewById(R.id.edThang);
        btnSearch = v.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VIEW_SACH_TOP_10(v);
            }
        });
        return v;
    }
    public void VIEW_SACH_TOP_10(View view){
        if (Integer.parseInt(edThang.getText().toString())>13 ||
                Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getContext(), "Không đúng định dạng tháng (1-12)", Toast.LENGTH_SHORT).show();
        }else {
            sachDAO = new SachDAO(getContext());
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new Adapter_Sach(getContext(), (ArrayList<Sach>) dsSach);
            rcvBook.setAdapter(adapter);
        }

    }
}
