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


public class Bottom_Sheet_Edit_HoaDon extends BottomSheetDialogFragment {
    EditText edNgayMua, edTenKhachHang;
    Button btnSave;
    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> dsHoaDon;
    Adapter_HoaDon adapterHoaDon;

    public Bottom_Sheet_Edit_HoaDon(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_hoadon,container,false);
        edTenKhachHang = v.findViewById(R.id.edTenKhachHangEdit);
        edNgayMua = v.findViewById(R.id.edNgayMuaEdit);
        btnSave = v.findViewById(R.id.btnSave);

        hoaDonDAO = new HoaDonDAO(getContext());
        final Bundle mArgs = getArguments();
        final String maHoaDon = mArgs.getString("maHoaDon");
        final String tenKhachHang = mArgs.getString("tenKhachHang");
        final String ngayMua = mArgs.getString("ngayMua");

        // Nhận dữ liệu gán vào ô editext
        edNgayMua.setText(ngayMua);
        edTenKhachHang.setText(tenKhachHang);
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);

        edNgayMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i,i1,i2);
                        edNgayMua.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKhachHang = edTenKhachHang.getText().toString();
                String ngayMua = edNgayMua.getText().toString();
                String maHoadon = mArgs.getString("maHoaDon");
                HoaDon hoaDon = new HoaDon(tenKhachHang,ngayMua);
                hoaDonDAO.update(maHoaDon,tenKhachHang,ngayMua);
                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dismiss();
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
