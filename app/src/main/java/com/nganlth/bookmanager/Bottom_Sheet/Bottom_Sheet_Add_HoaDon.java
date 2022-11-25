package com.nganlth.bookmanager.Bottom_Sheet;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nganlth.bookmanager.Adapter.Adapter_HoaDon;
import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.nganlth.bookmanager.Fragment.Fragment_Hoadon.rcvHoaDon;


public class Bottom_Sheet_Add_HoaDon extends BottomSheetDialogFragment {
    EditText edTenKhachHang,edNgayMua;
    Button btnAdd;
    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> dsHoaDon;
    Adapter_HoaDon adapterHoaDon;
    public Bottom_Sheet_Add_HoaDon() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_hoadon,container,false);
        edTenKhachHang = v.findViewById(R.id.edTenKhachHang);
        edNgayMua = v.findViewById(R.id.edNgayMua);
        btnAdd = v.findViewById(R.id.btnAddHoaDon);
        // Lấy ngày, tháng hiện tại
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        // Set thời gian cho today
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        // Hiển thi datepickerdialog

        edNgayMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        calendar.set(y,m,d);
                        edNgayMua.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKhachHang = edTenKhachHang.getText().toString();
                String ngayMua = edNgayMua.getText().toString();
                HoaDon hoaDon = new HoaDon(tenKhachHang,ngayMua);
                hoaDonDAO = new HoaDonDAO(getActivity());
                hoaDonDAO.insert(hoaDon);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();

                Bundle bundle = new Bundle();
                bundle.putString("Ngay",ngayMua);
                bundle.putString("Kh",tenKhachHang);
            }
        });
        return v;
    }
        public void capnhat(){
        dsHoaDon = new ArrayList<>();
        hoaDonDAO = new HoaDonDAO(getContext());
        dsHoaDon= hoaDonDAO.getHoaDon();
        adapterHoaDon = new Adapter_HoaDon(getActivity(),dsHoaDon);
        rcvHoaDon.setAdapter(adapterHoaDon);
    }
}
