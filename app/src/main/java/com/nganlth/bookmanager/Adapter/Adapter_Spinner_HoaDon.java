package com.nganlth.bookmanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nganlth.bookmanager.DAO.HoaDonDAO;
import com.nganlth.bookmanager.Model.HoaDon;
import com.nganlth.bookmanager.Model.TheLoai;
import com.nganlth.bookmanager.R;

import java.util.ArrayList;

public class Adapter_Spinner_HoaDon extends BaseAdapter {
    Context context;
    ArrayList<HoaDon> dsHoaDon;
    HoaDonDAO hoaDonDAO;

    public Adapter_Spinner_HoaDon(Context context, ArrayList<HoaDon> dsHoaDon) {
        this.context = context;
        this.dsHoaDon = dsHoaDon;
    }
    @Override
    public int getCount() {
        return dsHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_spinner_hoadon,null);
        TextView tvSPTheLoai = convertView.findViewById(R.id.tvSPHoaDon);
        HoaDon hoaDon = dsHoaDon.get(position);
        tvSPTheLoai.setText(hoaDon.getMaHoaDon());
        return convertView;
    }
}
